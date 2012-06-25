package com.ezest.javafx.components.freetextfield;

import com.sun.javafx.scene.layout.region.Margins;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FreeTextField extends StackPane{

	private Text text;
	private Label label;
	private TextArea textArea;
	
	public FreeTextField(){
		super();
		setAlignment(Pos.TOP_LEFT);
		setStyle("-fx-border-color:red;-fx-border-width:1px;");
		
		text = new Text();
		textArea = new TextArea();
		textArea.setWrapText(true);
		
		label =new Label();
		label.setWrapText(true);
	
		StackPane sp = new StackPane();
		sp.setStyle("-fx-border-color:green;-fx-border-width:1px;");
		sp.setPadding(new Insets(5));
		sp.getChildren().add(label);
		sp.maxWidthProperty().bind(widthProperty());
		
		text.wrappingWidthProperty().bind(textArea.widthProperty());
		text.textProperty().bind(textArea.textProperty());
		
		label.prefWidthProperty().bind(textArea.widthProperty());
		label.textProperty().bind(textArea.textProperty());
		
		textArea.prefHeightProperty().bind(label.heightProperty());
		textArea.setTranslateX(300);
		textArea.setTranslateY(50);
		
		
		getChildren().addAll(label,textArea);
	}
}
