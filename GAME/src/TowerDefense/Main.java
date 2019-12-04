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

public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception{
        Canvas canvas = new Canvas(768, 768);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Group root = new Group();
        root.getChildren().add(canvas);

        Controller controller = new Controller();

        Scene scene = new Scene(root);

        theStage.setScene(scene);
        theStage.setTitle("Tower_Defense!!!");
        theStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (controller.stageInitialized) {
                    controller.gameStage.render(gc);
                    try {
                        controller.gameStage.update();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
                controller.menu.render(gc);
                controller.mouseEvent(scene);
                if (controller.exit) theStage.close();
            }
        };
        timer.start();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
