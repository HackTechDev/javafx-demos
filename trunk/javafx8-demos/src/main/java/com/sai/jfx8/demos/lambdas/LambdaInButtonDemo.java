package main.java.com.sai.jfx8.demos.lambdas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Sai.Dandem
 * Date: 3/6/13
 * Time: 10:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class LambdaInButtonDemo extends Application {
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
        Button btn = new Button("Hello");
        btn.setOnAction(e-> System.out.println("hello dude"));
        root.getChildren().add(btn);
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
