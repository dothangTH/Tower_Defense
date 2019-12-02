package TowerDefense;

import Enemy.BasicEnemy;
import Enemy.Enemy;
import GameStage.Player;
import Map.Point;
import Tower.NormalTower;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends Application implements EventHandler<ActionEvent> {

    Button basic;
    Button armored;
    Button buster;
    Button boss;
    Button speedy;
    Button tank;
    Controller controller;
    int i = 0;

    @Override
    public void start(Stage theStage) throws Exception{
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        basic = new Button();
        basic.setText("Basic");
        basic.setLayoutY(10);
        basic.setLayoutX(10);
        basic.setOnAction(this::handle);

        armored = new Button();
        armored.setText("Armored");
        armored.setLayoutY(50);
        armored.setLayoutX(10);
        armored.setOnAction(this::handle);

        boss = new Button();
        boss.setText("Boss");
        boss.setLayoutY(100);
        boss.setLayoutX(10);
        boss.setOnAction(this::handle);

        buster = new Button();
        buster.setText("Buster");
        buster.setLayoutY(150);
        buster.setLayoutX(10);
        buster.setOnAction(this::handle);

        speedy = new Button();
        speedy.setText("Speedy");
        speedy.setLayoutY(200);
        speedy.setLayoutX(10);
        speedy.setOnAction(this::handle);

        tank = new Button();
        tank.setText("Tank");
        tank.setLayoutY(250);
        tank.setLayoutX(10);
        tank.setOnAction(this::handle);

        root.getChildren().add(basic);
        root.getChildren().add(armored);
        root.getChildren().add(boss);
        root.getChildren().add(buster);
        root.getChildren().add(speedy);
        root.getChildren().add(tank);

        controller = new Controller();
        //controller.gameStage.buildTower("Normal", new Point(7,4));
        //controller.gameStage.buildTower("Artillery", new Point(7,3));
        //controller.gameStage.buildTower("Normal", new Point(8,8));
        //controller.gameStage.buildTower("Blaster", new Point(8,7));
        controller.gameStage.buildTower("SMG", new Point(6,7));

        Scene scene = new Scene(root);
        /*scene.setOnMouseClicked(e ->
        {
            if (e.getX() < 200 && e.getY() < 200){
                try {
                    controller.gameStage.towerList.add(NormalTower.clone(new Point(8,8)));
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            }
        });*/

        theStage.setScene(scene);
        theStage.setTitle("Tower_Defense!!!");
        theStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!Player.getInstance().isDefeated()) {
                    controller.gameStage.render(gc);
                    try {
                        controller.gameStage.update();
                    } catch (FileNotFoundException | CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
        controller.mouseEvent(scene);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == basic){
            try {
                controller.gameStage.spawnEnemy("Basic");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == armored){
            try {
                controller.gameStage.spawnEnemy("Armored");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == boss){
            try {
                controller.gameStage.spawnEnemy("Boss");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == buster){
            try {
                controller.gameStage.spawnEnemy("Buster");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == speedy){
            try {
                controller.gameStage.spawnEnemy("Speedy");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        if (actionEvent.getSource() == tank){
            try {
                controller.gameStage.spawnEnemy("Tank");
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
