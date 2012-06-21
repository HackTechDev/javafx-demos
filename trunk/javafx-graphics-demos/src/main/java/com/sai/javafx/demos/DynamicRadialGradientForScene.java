package com.sai.javafx.demos;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DynamicRadialGradientForScene  extends Application {

	Stage stage;
	Scene scene;
	StackPane root;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.stage.setWidth(600);
		this.stage.setHeight(600);
		
		root = new StackPane();
		root.getStyleClass().add("sceneGradient");
		
		this.scene = new Scene(root, Color.LINEN);
		scene.getStylesheets().add("styles/template.css");
		
		stage.setTitle("Radial Gradient Scene");
		stage.setScene(this.scene);
	    stage.show();
	    
	    
	    VBox vb = new VBox();
	    vb.setSpacing(15);
	    vb.setPadding(new Insets(15));
	    vb.getChildren().addAll(getButton("Red", Color.RED),
	    						getButton("Blue", Color.BLUE),
	    						getButton("Green", Color.GREEN),
	    						getButton("Yellow", Color.YELLOW),
	    		                getButton("Black", Color.BLACK) );
	    root.getChildren().add(vb);
	}
	
	private Button getButton(String title, final Color color){
		Button btn = new Button(title);
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				root.setStyle("-fx-base:"+rgbToHex(color)+";");
			}
		});
		return btn;
	}
	
	public String rgbToHex(Color color) {
		int i = (int)Math.round((double)color.getRed() * 255D);
        int j = (int)Math.round((double)color.getGreen() * 255D);
        int k = (int)Math.round((double)color.getBlue() * 255D);
    	return "#"+toHex(i)+toHex(j)+toHex(k) ; 
	}
	
	private String toHex(int code){
		String str ="0123456789ABCDEF";
		return str.charAt(code/16)+""+str.charAt(code%16);
	}

}

