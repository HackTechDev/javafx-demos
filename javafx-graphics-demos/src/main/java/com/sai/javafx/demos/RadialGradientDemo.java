package com.sai.javafx.demos;

import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CircleBuilder;

import com.sai.javafx.common.ExtendedApplicationX;

public class RadialGradientDemo extends ExtendedApplicationX {

	@Override
	protected void setup() {
		VBox vb = new VBox();
		vb.setSpacing(10);
		
		// #1
		vb.getChildren().add(HBoxBuilder.create()
								.children(
										LabelBuilder.create()
											.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(50)
											.style("-fx-fill:radial-gradient(center 30% 80%, radius 100%, green 10%, red 90%);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(center 30% 80%, radius 100%, green 10%, red 90%);").build() 
										).build())	;	
		
		// #2
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(center 50% 50%, radius 100%, green 10%, red 90%);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(center 50% 50%, radius 100%, green 10%, red 90%);").build() 
						).build())	;	

		
		// #3
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(center 10% 10%, radius 50%, green 10%, red 90%);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(center 10% 10%, radius 50%, green 10%, red 90%);").build() 
						).build())	;	

		// #4
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#4").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(center 10% 10%, radius 100%, green 10%, red 90%);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(center 10% 10%, radius 100%, green 10%, red 90%);").build() 
						).build())	;	

		// #5
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#5").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(radius 100%, red, darkgray, black);").build() 
						).build())	;	

		// #6
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#6").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(focus-angle 45deg, focus-distance 20%, center 25% 25%, radius 50%, reflect, gray, darkgray 75%, dimgray);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 45deg, focus-distance 20%, center 25% 25%, radius 50%, reflect, gray, darkgray 75%, dimgray);").build() 
						).build())	;	

		// #7
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#7").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(center 50% 50%, radius 50%, gray, darkgray 60%, dimgray);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(center 50% 50%, radius 50%, gray, darkgray 60%, dimgray);").build() 
						).build())	;	

		// #8
		vb.getChildren().add(HBoxBuilder.create()
				.children(
						LabelBuilder.create()
							.text("#8").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(50)
							.style("-fx-fill:radial-gradient(center 50% 50%, radius 50%, gray, darkgray 85%, dimgray);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(center 50% 50%, radius 50%, gray, darkgray 85%, dimgray);").build() 
						).build())	;	

		
		root.setContent(vb);
	}
	public static void main(String[] args) {
		ExtendedApplicationX.launch(args);
	}
}
