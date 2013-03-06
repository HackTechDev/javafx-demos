package main.java.com.sai.jfx8.demos.general;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Sai.Dandem
 * Date: 3/5/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld extends Application {

    Stage stage;
    private Scene scene;
    StackPane root;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        configureScene();
        configureStage();
        // Logic starts

        final ScrollPane sp = new ScrollPane();
        sp.setFitToHeight(true);
        sp.setFitToWidth(true);
        sp.setContent(StackPaneBuilder.create().minHeight(1020).prefWidth(200).build());


        root.getChildren().add(sp);
    }

    private void configureStage() {
        stage.setTitle(this.getClass().getSimpleName());
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setScene(this.scene);
        stage.show();
    }

    private void configureScene() {
        root = new StackPane();
        BorderPane bp = new BorderPane();
        bp.setCenter(root);
        bp.autosize();
        this.scene = new Scene(bp, Color.LINEN);
    }

}
