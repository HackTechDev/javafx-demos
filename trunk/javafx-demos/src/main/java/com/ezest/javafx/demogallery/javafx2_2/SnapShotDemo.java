package com.ezest.javafx.demogallery.javafx2_2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.PerspectiveCameraBuilder;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.SnapshotResult;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Shear;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.imageio.ImageIO;

import com.javafx.experiments.scenicview.ScenicView;

/**
 * https://blogs.oracle.com/thejavatutorials/entry/take_a_snaphot_with_javafx
 * @author Sai.Dandem
 *
 */
public class SnapShotDemo extends Application {

	Stage stage;
	Scene scene;
	StackPane root;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		configureScene();
		configureStage();
		
		VBox vb = new VBox();
		
		final Button btn = new Button("Simple SnapShot");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showSimpleSnapShot();
			}
		});
		
		Button btn2 = new Button("Parameters SnapShot");
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				showParametersSnapShot();
				
			}
		});
		
		
		vb.getChildren().addAll(new TextField(), btn,btn2);
		root.getChildren().add(vb);
	}
	
	private void showSimpleSnapShot(){
		WritableImage image = new WritableImage(400, 400);
		scene.snapshot(image);
		generateImageFile(image);
		openStage(image);
	}
	
	private void showParametersSnapShot(){
		WritableImage image = new WritableImage(400, 400);
		Callback<SnapshotResult,java.lang.Void> callBack = new Callback<SnapshotResult, Void>() {
			@Override
			public Void call(SnapshotResult result) {
				SnapshotParameters param = result.getSnapshotParameters();
				return null;
			}
		};
		SnapshotParameters param = new SnapshotParameters(); 
		param.setCamera(PerspectiveCameraBuilder.create().fieldOfView(45).build());
		param.setDepthBuffer(true);
		param.setFill(Color.CORNSILK);
		
		
		Shear shear = new Shear(0.7, 0);
		Rotate rt = new Rotate(5);
		Scale scl = new Scale(6, 6);
		
		param.setTransform(scl);
		root.snapshot(callBack,param, image);
		
		generateImageFile(image);
		openStage(image);
	}
	
	private void openStage(Image image){
		StackPane sp = new StackPane();
		sp.getChildren().add(new ImageView(image));
		
		Stage stg = new Stage();
		stg.setWidth(440);
		stg.setHeight(470);
		stg.setScene(new Scene(sp));
		stg.show();
	}
	
	private void generateImageFile(WritableImage image){
		try {
		    BufferedImage bimg = convertToAwtImage(image);
		    File outputfile = new File("saved.png");
		    ImageIO.write(bimg, "png", outputfile);
		} catch (IOException e) {
		   System.out.println("");
		}
	}

	private java.awt.image.BufferedImage convertToAwtImage(javafx.scene.image.Image fxImage) {
		if (Image.impl_isExternalFormatSupported(BufferedImage.class)) {
			java.awt.image.BufferedImage awtImage = new BufferedImage((int)fxImage.getWidth(), (int)fxImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
			return (BufferedImage)fxImage.impl_toExternalImage(awtImage);
		} else {
			return null;
		}
	}

	private void configureStage(){
		stage.setTitle(this.getClass().getSimpleName());
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	}
	
	private void configureScene(){
		root = new StackPane();
		BorderPane bp = new BorderPane();
		bp.setBottom(getBottom());
		bp.setCenter(root);
		bp.autosize();
		this.scene = new Scene(bp, Color.LINEN);
		scene.getStylesheets().add("styles/template.css");
	}

	private Node getBottom() {
		StackPane sp = new StackPane();
		sp.setMinHeight(25);
		sp.setAlignment(Pos.TOP_RIGHT);
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/images/mglass.gif")));
		image.setCursor(Cursor.HAND);
		image.setTranslateX(-5);
		image.setTranslateY(3);
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
				ScenicView.show(scene);
			}
		});
		sp.getChildren().addAll(new Separator(),image);
		return sp;
	}

}


