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
import javafx.application.Platform;
import javafx.stage.WindowEvent;

/**
 *
 * @author User
 */
public class Pong extends Application {
    
    private Scene scene;
    
    private Player player;
    private Ball ball;
    
    private static final int WIDTH = 640; 
    private static final int HEIGHT = WIDTH/4*3;
    private static final double SCALE = 1;
    public static final int MAP_WIDTH = WIDTH;
    public static final int MAP_HEIGHT = HEIGHT;
    private GraphicsContext gc;
    private Sprite background;
    private Group root;
    private Client client;

    public Player getPlayer() {
        return player;
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
        
        client = new Client("127.0.0.1", 5000, this);
        
        stage.setTitle("PongSample");

        init(stage);

        long startNanoTime = System.nanoTime();
        final long lastNanoTime = startNanoTime;        
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double elapsed = (currentNanoTime - lastNanoTime) / 1000000000.0;
                
                background.render(gc);
                
                player.render();
                ball.render(gc);
                
                player.movement();
            }
        }.start();
        
        stage.show();
        
        stage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void init(Stage stage) {
        root = new Group();
        scene = new Scene(root);
        stage.setScene(scene);
        
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        
        gc = canvas.getGraphicsContext2D();
        gc.scale(SCALE, SCALE);
        background = new Sprite("images/background.png");
        Sprite ballSprite = new Sprite("images/ball.png");
        
        player = new Player(20, 20, this);
        player.setMapSize(MAP_WIDTH, MAP_HEIGHT);
        ball = new Ball(ballSprite, 400, 200, this);
        
        root.getChildren().add(canvas);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
