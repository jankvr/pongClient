/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pong;

import com.pong.entity.Player;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import com.pong.entity.Ball;
import com.pong.entity.Opponent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

/**
 * Hlavná trieda aplikácie
 * @author Jan Kovář, Jaroslav Fedorčák
 */
public class Main extends Application {

    private static final int WIDTH = 800; 
    private static final int HEIGHT = WIDTH/4*3;
    private static final double SCALE = 1;
    public static final int MAP_WIDTH = WIDTH;
    public static final int MAP_HEIGHT = HEIGHT;

    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(Main.class.getName());
    
    
    private Scene scene;
    private Group root;
    private Client client;
    private GraphicsContext gc;
    
    private Sprite background;
    private Opponent opponent;
    private Player player;
    private Ball ball;
    private Score score;
    private Stage stage;

    /**
     * 
     * @return Odkaz na inštanciu klienta
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * 
     * @return inštancia hráča
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * 
     * @return inštancia oponenta
     */
    public Opponent getOpponent() {
        return opponent;
    }

    /**
     * 
     * @return inštancia lopty
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * 
     * @return inštancia grafického kontextu spravujúceoh vykresľovanie
     */
    public GraphicsContext getGc() {
        return gc;
    }
    
    /**
     * 
     * @return scéna, na ktorej sa hraje
     */
    public Scene getScene() {
        return this.scene;
    }
    
    @Override
    public void start(Stage stage) {
        
        
        
        
        
        
        
        client = new Client("127.0.0.1", 5000, this);
        
        

        initLogin(stage);
    
    }

    /**
     * inicializuje samotnú hru, metóda spustená až po prihlásení
     * @param stage 
     */
    private void init(Stage stage) {
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        
        gc = canvas.getGraphicsContext2D();
        gc.scale(SCALE, SCALE);
        gc.setStroke(Color.WHITE);//skóre sa vypisuje bielou farbou
        gc.setFont(new Font("Arial Bold",20));
        //System.out.println(new Font(2).getFontNames());
        gc.setTextAlign(TextAlignment.CENTER);
        background = new Sprite("images/background.png");
        Sprite ballSprite = new Sprite("images/ball.png");
        
        player = new Player(20, 120, this);
        player.setMapSize(MAP_WIDTH, MAP_HEIGHT);
        
        opponent = new Opponent(20, 120, this);
        opponent.setMapSize(MAP_WIDTH, MAP_HEIGHT);
        
        ball = new Ball(ballSprite, 400, 200, this);
        score = new Score();
        
        root.getChildren().add(canvas);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * 
     * @return skóre v rámci hry
     */
    public Score getScore() {
        return score;
    }

    /**
     * setter na skóre
     * @param score skóre v rámci hry
     */
    public void setScore(Score score) {
        this.score = score;
    }
    
    /**
     * inicializácia prihlasovacen obrazovky
     * @param stage stage na vykreslenie loginu
     */
    private void initLogin(Stage stage){
        this.stage = stage;
        stage.show();
        stage.setTitle("Login");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welcome to Pong. Please login");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        TextField pwField = new TextField();
        grid.add(pwField, 1, 2);

        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        
        btn.setOnAction((ActionEvent e) -> {
if(userTextField.getText()!=null
        && !userTextField.getText().isEmpty()
        && pwField.getText()!=null
        && !pwField.getText().isEmpty()){

        boolean loggedInSuccessful = false;
    OUTER:
    while (!loggedInSuccessful) {
        client.logIn(userTextField.getText(), pwField.getText());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            LOG.fatal(ex.getMessage());
        }   switch (client.getLoggedIn()) {
            case "true":
                LOG.info("I'm logged in now");
                startGame(stage);
                //break;
                loggedInSuccessful = true;
                break;
//                        else{
//                            System.out.println("Server hasn't responded yet, wait.");
//                        }
            case "false":
                LOG.info("Login was not successful");
                actiontarget.setFill(Color.RED);
                actiontarget.setText("Wrong username or password");
                break OUTER;
                //informovať klienta na okne že to bolo neúspešné
                //client.logIn(userTextField.getText(), pwField.getText());
                //toto bude treba vyriešiť, že sa opakuje zadávanie hesla
            case "alreadyConnected":
                actiontarget.setFill(Color.RED);
                actiontarget.setText("This user is already connected.");
                break OUTER;
            default:
                break;
        }
    }
}
        });

        Scene gridScene = new Scene(grid, 300, 275);
        stage.setScene(gridScene);
        stage.show();
    }
    
    /**
     * začína samotnú hru
     * @param stage stage, na ktorom sa hraje hra
     */
    private void startGame(Stage stage){
        stage.hide();
        init(stage);
        
        stage.setTitle("PongClient");
        gc.drawImage(new Image(Main.class.getResourceAsStream("images/standBy.png")), 0,0);
        // pocitani casu...
        //long startNanoTime = System.nanoTime();
        //final long lastNanoTime = startNanoTime;        
 
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // pocitani casu
                //double elapsed = (currentNanoTime - lastNanoTime) / 1000000000.0;
                
                if (client.isStarted()) {
                    background.setDisplayedText(score.getRec1() +" : "+score.getRec2());
                    background.render(gc);
                    ball.render(gc);
                    opponent.render();
                    player.render();
                    player.movement();
                    client.processMessage(player.currentLocation());  
                    
                    if (!client.isAlive()) {
                        this.stop();
                        popUpWindow();
                    }
                }
                // v else vetvi zobraz nejaky obrazek "cekejte prosim"
            }
        }.start();
        
        stage.show();
        
        //tu hodit stand by obrazovku
        
        stage.setOnCloseRequest((WindowEvent e) -> {
            if (client.isAlive()) {
                client.sendQuitMessage();
            }
            Platform.exit();
            System.exit(0);
        });
    }
    
        
    public void popUpWindow() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(this.stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text("Your opponent has left the game. Log in again to play another game."));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
        
        dialog.setOnCloseRequest((WindowEvent e) -> {
            if (client.isAlive()) {
                client.sendQuitMessage();
            }
            Platform.exit();
            System.exit(0);
        });
    }
}
