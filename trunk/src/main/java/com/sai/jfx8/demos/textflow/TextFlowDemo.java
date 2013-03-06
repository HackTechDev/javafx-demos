package main.java.com.sai.jfx8.demos.textflow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import main.java.com.sai.jfx8.common.ProjectConstants;

/**
 * Created with IntelliJ IDEA.
 * User: Sai.Dandem
 * Date: 3/6/13
 * Time: 10:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextFlowDemo extends Application {
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
        String family = "Helvetica";
        double size = 20;

        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(40);
        textFlow.setLayoutY(40);

        Text text1 = new Text("Hello ");
        text1.setFont(Font.font(family, size));
        text1.getStyleClass().addAll("span1", "fancy1");

        Text text2 = new Text("Bold");
        text2.setFont(Font.font(family, FontWeight.BOLD, size));
        text2.getStyleClass().addAll("span2", "big", "fancy2");

        Text text3 = new Text(" World");
        text3.setFont(Font.font(family, FontPosture.ITALIC, size));
        text3.getStyleClass().addAll("span3", "fancy3","paragraph");

        Text text4 = new Text(" fancy underline");
        text4.getStyleClass().addAll("underlinefancy","paragraph");

        Text text5 = new Text(" fancy strikeout");
        text5.getStyleClass().addAll("strikeout", "fancy2","paragraph");

        textFlow.getChildren().addAll(text1, text2, text3,text4,text5);

        root.getChildren().add(textFlow);
        //ScenicView.show(scene);

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
        scene.getStylesheets().addAll(ProjectConstants.RESOURCE_PATH + "styles/TextFlowDemo.css");
    }

}
