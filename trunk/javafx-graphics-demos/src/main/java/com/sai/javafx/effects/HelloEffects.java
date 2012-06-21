package com.sai.javafx.effects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class HelloEffects extends Application {
 
    Stage stage;
    Scene scene;
    
    private DoubleProperty azimuth = new SimpleDoubleProperty(-135.0);
    private DoubleProperty surfaceScale = new SimpleDoubleProperty(5.0);
    
    @Override public void start(Stage stage) {
        
        scene = new Scene(new Group());
        ObservableList<Node> content = ((Group)scene.getRoot()).getChildren();

        content.add(lighting());
        stage.setScene(scene);
        stage.show();
        animate();
    }
    
    Node lighting() {
        Distant light = new Distant();
        light.azimuthProperty().bind(azimuth);
      //  light.setAzimuth(-135.0f);
 
        Lighting l = new Lighting();
        l.setLight(light);
        l.surfaceScaleProperty().bind(surfaceScale);
       // l.setSurfaceScale(5.0f);
 
        Text t = new Text();
        t.setText("JavaFX"+"\n"+"Lighting!");
        t.setFill(Color.RED);
        t.setFont(Font.font("null", FontWeight.BOLD, 70));
        t.setX(10.0f);
        t.setY(10.0f);
        t.setTextOrigin(VPos.TOP);
 
        t.setEffect(l);
 
        
        return t;
    }
    private void animate() {
        TimelineBuilder.create()
            .cycleCount(Timeline.INDEFINITE)
            .keyFrames(
                new KeyFrame(
                    Duration.seconds(0),
                    new KeyValue(azimuth, -135)
                    //,new KeyValue(surfaceScale, 5.0)
                ),
                new KeyFrame(
                    Duration.seconds(2),
                    new KeyValue(azimuth, 0)
                    //,new KeyValue(surfaceScale, 4.0)
                ),
                new KeyFrame(
                    Duration.seconds(4),
                    new KeyValue(azimuth, 135)
                    //,new KeyValue(surfaceScale, 3.0)
                )

            )
            .build().play();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
