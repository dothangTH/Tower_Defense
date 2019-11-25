package TowerDefense;

import Enemy.BasicEnemy;
import Enemy.Enemy;
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

    Button button;
    Controller controller;
    int i = 0;

    @Override
    public void start(Stage theStage) throws Exception{
        Canvas canvas = new Canvas(600, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        button = new Button();
        button.setText("Spawn!!");
        button.setLayoutY(10);
        button.setLayoutX(10);
        button.setOnAction(this::handle);

        root.getChildren().add(button);

        controller = new Controller();
        controller.gameStage.towerList.add(NormalTower.clone(new Point(7,4)));
        controller.gameStage.towerList.add(NormalTower.clone(new Point(8,8)));

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
                controller.gameStage.render(gc);
                try {
                    controller.gameStage.update();
                } catch (FileNotFoundException | CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource() == button){
            try {
                controller.gameStage.enemyList.add(BasicEnemy.clone(controller.gameStage.getMap().getStart()));
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
