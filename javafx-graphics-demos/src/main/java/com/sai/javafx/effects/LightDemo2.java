package com.sai.javafx.effects;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LightDemo2 extends Application {

  @Override
  public void start(Stage stage) {
    VBox box = new VBox();
    final Scene scene = new Scene(box, 300, 250);
    scene.setFill(null);

    Light.Distant light = new Light.Distant();
    light.setAzimuth(-135.0);

    Lighting l = new Lighting();
    l.setLight(light);
    l.setSurfaceScale(5.0);

    Text t = new Text();
    t.setText("JavaFX!");
    t.setFill(Color.RED);
    t.setFont(Font.font(null, FontWeight.BOLD, 90));
    t.setX(10.0);
    t.setY(10.0);
    t.setTextOrigin(VPos.TOP);

    t.setEffect(l);

    box.getChildren().add(t);

    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
