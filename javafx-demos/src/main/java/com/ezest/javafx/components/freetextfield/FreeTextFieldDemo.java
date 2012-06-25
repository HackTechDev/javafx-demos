package com.ezest.javafx.components.freetextfield;

import com.javafx.experiments.scenicview.ScenicView;

import javafx.application.Application;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FreeTextFieldDemo extends Application {

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
		
		FreeTextField text = new FreeTextField();
		StackPane sp = StackPaneBuilder.create().minHeight(50).minWidth(300).children(text).build();
		
		root.getChildren().add(GroupBuilder.create().children(sp).build());
	}

	private void configureStage(){
		stage.setTitle(this.getClass().getSimpleName());
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	    ScenicView.show(this.scene);
	}
	
	private void configureScene(){
		root = new StackPane();
		root.autosize();
		this.scene = new Scene(root, Color.LEMONCHIFFON);
		//scene.getStylesheets().add("styles/template.css");
	}

}

