package com.ezest.javafx.demogallery.controls;

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
		CustomTextField field = new CustomTextField();
		field.setType(CustomTextField.TextFieldType.POSITIVE_INTEGER);
		field.setMaxCharLength(5);
		HBox hb1 = HBoxBuilder.create().spacing(10).children(new Label("INTEGER_ONLY   : ") 
                                                             ,CustomTextFieldBuilder.create()
                                                             		.type(CustomTextField.TextFieldType.POSITIVE_INTEGER)
                                                             		.maxCharLength(5).build()
                                                     ).build();
		
		vb.getChildren().addAll(HBoxBuilder.create().spacing(10)
				                           .children(new Label("POSITIVE_INTEGER   : ") 
                                                     ,CustomTextFieldBuilder.create()
 		                                                 .type(CustomTextField.TextFieldType.POSITIVE_INTEGER)
 		                                                 .maxCharLength(5).build() ).build()
 		                       
 		                       ,HBoxBuilder.create().spacing(10)
				                           .children(new Label("ALPHABET   : ") 
			                               ,CustomTextFieldBuilder.create()
			                                    .type(CustomTextField.TextFieldType.ALPHABET)
			                                    .maxCharLength(5).build() ).build()
	                           
			                   ,HBoxBuilder.create().spacing(10)
				                           .children(new Label("Double   : ") 
			                               ,CustomTextFieldBuilder.create()
			                                    .type(CustomTextField.TextFieldType.DOUBLE)
			                                    .maxCharLength(13).build() ).build()
		 		                       
				);
		
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


