package com.ezest.javafx.components.customtextfield;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class CustomTextFieldDemo extends Application {

	Stage stage;
	Scene scene;
	Group root;
	
	VBox vb;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		vb = new VBox();
		vb.setSpacing(8);
		configureScene();
		configureStage();
		configureScroller();
		root.getChildren().add(vb);
	}

	private void configureScroller() {
		HBox hb1 = HBoxBuilder.create().spacing(10).children(new Label("INTEGER_ONLY   : "), new CustomTextField(TextFieldType.INTEGER_ONLY)).build();
		HBox hb2 = HBoxBuilder.create().spacing(10).children(new Label("POSITIVE_INTEGER_ONLY   : "), new CustomTextField(TextFieldType.POSITIVE_INTEGER_ONLY)).build();
		HBox hb3 = HBoxBuilder.create().spacing(10).children(new Label("ALPHABETS   : "), new CustomTextField(TextFieldType.ALPHABETS)).build();
		HBox hb4 = HBoxBuilder.create().spacing(10).children(new Label("NUMERIC   : "), new CustomTextField(TextFieldType.NUMERIC)).build();
		HBox hb5 = HBoxBuilder.create().spacing(10).children(new Label("POSITIVE_NUMERIC_ONLY   : "), new CustomTextField(TextFieldType.POSITIVE_NUMERIC_ONLY)).build();
		
		CustomTextField t = new CustomTextField(TextFieldType.SIZE_RESTRICT);
		t.setSize(10);
		HBox hb6 = HBoxBuilder.create().spacing(10).children(new Label("SIZE_RESTRICT-10   : "), t).build();
		
		
		vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6 );
		
	}

	private void configureStage(){
		stage.setTitle("Custom Text Field Demo");
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	}
	
	private void configureScene(){
		root = new Group();
		root.autosize();
		this.scene = new Scene(root, Color.LINEN);
	}

}


