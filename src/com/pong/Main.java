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
 *
 * @author User
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

    public Client getClient() {
        return client;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public Opponent getOpponent() {
        return opponent;
    }

    public Ball getBall() {
        return ball;
    }

    public GraphicsContext getGc() {
        return gc;
    }
    
    public Scene getScene() {
        return this.scene;
    }
    
    @Override
    public void start(Stage stage) {
        
        
        //tu bude nastavenie úvodnej obrazovky s prihlasovaním
        
        
        
        
        client = new Client("127.0.0.1", 5000, this);
        
        
//        boolean loggedProperly = false;
//        while(!loggedProperly){
//            //prijať login údaje z obrazovky
//            //client.logIn("bollocks","username");
//            client.logIn("andrej", "hunter2");
//            if(client.isLogged()){
//                loggedProperly = true;
//            }
//        }
        
        initLogin(stage);
        //stage.show();
//        for(int i=1;i<100000;i++){
//            System.out.println("a");
//        }
//        System.out.println("b");

        //client.logIn("andrej", "hunter2");
//        System.out.println(client.isLogged());
//        while(true){
//            
//            if(client.getLoggedIn().equals("true")){
//                System.out.println("I'm logged in now");
//                break;
//            }
//            else if (client.getLoggedIn().equals("false")){
//                System.out.println("Login was not successful");
//                //toto bude treba vyriešiť, že sa opakuje zadávanie hesla
//            }
//            else{
//                System.out.println("Server hasn't responded yet, wait.");
//            }
//        }


            


//presunuté do metódy startGame(stage)
//        stage.hide();
//        init(stage);
//        
//        stage.setTitle("PongClient");
//        
//        // pocitani casu...
//        //long startNanoTime = System.nanoTime();
//        //final long lastNanoTime = startNanoTime;        
// 
//        new AnimationTimer() {
//            @Override
//            public void handle(long currentNanoTime) {
//                // pocitani casu
//                //double elapsed = (currentNanoTime - lastNanoTime) / 1000000000.0;
//                
//                if (client.isStarted()) {
//                    background.setDisplayedText(score.getRec1() +" : "+score.getRec2());
//                    background.render(gc);
//                    ball.render(gc);
//                    opponent.render();
//                    player.render();
//                    player.movement();
//                    client.processMessage(player.currentLocation());      
//                }
//                // v else vetvi zobraz nejaky obrazek "cekejte prosim"
//            }
//        }.start();
//        
//        stage.show();
//        
//        stage.setOnCloseRequest((WindowEvent e) -> {
//            if (client.isAlive()) {
//                client.sendQuitMessage();
//            }
//            Platform.exit();
//            System.exit(0);
//        });
    }

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

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
    
    
    private void initLogin(Stage stage){
//        //root = new Group();
//        //scene = new Scene(root);
//        //stage.setScene(scene);
//        
//        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
//
//        //Creating a GridPane container
//        GridPane grid = new GridPane();
//        scene = new Scene(grid, WIDTH * SCALE, HEIGHT * SCALE);
//        stage.setScene(scene);
//        grid.setPadding(new Insets(10, 10, 10, 10));
//        grid.setVgap(5);
//        grid.setHgap(5);
//        //Defining the Name text field
//        final TextField name = new TextField();
//        name.setPromptText("Enter your first name.");
//        name.setPrefColumnCount(10);
//        name.getText();
//        GridPane.setConstraints(name, 0, 0);
//        grid.getChildren().add(name);
//        //Defining the Last Name text field
//        final TextField lastName = new TextField();
//        lastName.setPromptText("Enter your last name.");
//        GridPane.setConstraints(lastName, 0, 1);
//        grid.getChildren().add(lastName);
//        //Defining the Comment text field
//        final TextField comment = new TextField();
//        comment.setPrefColumnCount(15);
//        comment.setPromptText("Enter your comment.");
//        GridPane.setConstraints(comment, 0, 2);
//        grid.getChildren().add(comment);
//        //Defining the Submit button
//        Button submit = new Button("Submit");
//        GridPane.setConstraints(submit, 1, 0);
//        grid.getChildren().add(submit);
//        //Defining the Clear button
//        Button clear = new Button("Clear");
//        GridPane.setConstraints(clear, 1, 1);
//        grid.getChildren().add(clear);

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
        
        //Text wrongLogin = new Text("You've entered a wrong username or password.");
        
        btn.setOnAction((ActionEvent e) -> {
            //                actiontarget.setFill(Color.FIREBRICK);
//                actiontarget.setText("Sign in button pressed");
if(userTextField.getText()!=null
        && !userTextField.getText().isEmpty()
        && pwField.getText()!=null
        && !pwField.getText().isEmpty()){
//                    client.logIn(userTextField.getText(), pwField.getText());
//boolean previousLogin

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
