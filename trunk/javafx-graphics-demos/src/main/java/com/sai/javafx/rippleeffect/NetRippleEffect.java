package com.sai.javafx.rippleeffect;

import java.net.URL;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.DisplacementMapBuilder;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.FloatMap;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NetRippleEffect extends Application{

	Group root = new Group();
	int width =435;
	int height = 327;
	Timeline rippleTimeline;
	FloatMap floatmap = new FloatMap(width+50, height+50);
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(root, 435, 600));
		stage.setTitle("Ripple Effect Demo");
		stage.show();
		configureImage();
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public static String getResource(String file) {
		URL resource =  RippleEffect.class.getResource(file);
		return resource == null ? file : resource.toExternalForm();
	}
	
	private void configureImage() {
		SimpleDoubleProperty sampleX = new SimpleDoubleProperty(1.0);
		SimpleDoubleProperty sampleY = new SimpleDoubleProperty(1.0);
		getRippleTimeLine(sampleX, sampleY);
		
		final ImageView imageView1 = new ImageView( new Image(getResource("/images/moon1.jpg")) );
		final ImageView imageView2 = new ImageView();
		imageView2.imageProperty().bind(imageView1.imageProperty());
		imageView2.setEffect( createDisplacementMap(sampleX.get()/9000.0, sampleY.get()/9000.0));
		
		root.getChildren().addAll(imageView2, imageView1);
		play();
		
	}
	private DisplacementMap createDisplacementMap(double xAmp, double yAmp){
		DropShadow dropShadow = DropShadowBuilder.create()
				.blurType(BlurType.THREE_PASS_BOX)
				.height(5)
				.color(Color.GRAY)
				.spread(5)
				.build();
		
		Reflection reflection = ReflectionBuilder.create()
				.input(dropShadow)
				.bottomOpacity(.8)
				.topOpacity(1.0)
				.topOffset(0)
				.fraction(1.0)
				.build();
		
		DisplacementMap disMap = DisplacementMapBuilder.create()
				.wrap(true)
				.input(reflection)
				.mapData(createMapData(xAmp, yAmp))
				.build();
		
		return disMap;

	}
	
	private FloatMap createMapData(double xAmp, double yAmp){
		int width = floatmap.getWidth();
		int height = floatmap.getHeight();
		
		for (int x =0 ; x<width ; x++) {
            double angle = Math.PI * 2.0 * (x + 0.5) / (width / 4.0);
            float xval = (float)(Math.sin(angle) * xAmp);
            for(int y =0 ; y<height ; y++) {
            	floatmap.setSample(x, y, 0, xval);
            }
        }
		
		for (int y =0 ; y<height ; y++) {
            double angle = Math.PI * 2.0 * (y + 0.5) / (height / 4.0);
            float yval = (float)(Math.sin(angle) * yAmp);
            for(int x =0 ; x<width ; x++) {
            	floatmap.setSample(x, y, 1, yval);
            }
        }
		
        return floatmap;
	}
	
	private Timeline getRippleTimeLine(SimpleDoubleProperty sampleX, SimpleDoubleProperty sampleY){
		KeyFrame kf1 = new KeyFrame(Duration.seconds(3), new KeyValue(sampleX,100.0));
		KeyFrame kf2 = new KeyFrame(Duration.seconds(2), new KeyValue(sampleY,100.0));
		KeyFrame kf3 = new KeyFrame(Duration.seconds(6), new KeyValue(sampleX,-100.0));
		KeyFrame kf4 = new KeyFrame(Duration.seconds(4), new KeyValue(sampleY,-100.0));
		KeyFrame kf5 = new KeyFrame(Duration.seconds(9), new KeyValue(sampleX,100.0));
		KeyFrame kf6 = new KeyFrame(Duration.seconds(8), new KeyValue(sampleY,200.0));
		
		rippleTimeline = new Timeline(kf1,kf2,kf3,kf4,kf5,kf6);
		rippleTimeline.setCycleCount(Timeline.INDEFINITE);
		rippleTimeline.setAutoReverse(true);
		
		return rippleTimeline;
	}
	
	public void play() {
        rippleTimeline.play();
    }

    public void pause() {
        rippleTimeline.pause();
    }
	
}
