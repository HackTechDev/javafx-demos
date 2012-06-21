package com.sai.javafx.demos;

import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.RectangleBuilder;

import com.sai.javafx.common.ExtendedApplicationX;

public class RadialGradientDrill extends ExtendedApplicationX  {
	private VBox vb;
	
	@Override
	protected void setup() {
		vb = new VBox();
		vb.setSpacing(10);
		
		drillMultiple();
		drillColorStops();
		drillRepeat();
		drillReflect();
		drillRadiusWithPercent();
		drillRadiusWithPixel();
		drillCenter();
		drillCenterWithFocus();
		drillFocusDistance();
		drillFocusAngle();
		
		root.setContent(vb);
	}
	
	
	private void drillFocusAngle() {
		vb.getChildren().add(LabelBuilder.create()
									.text("FOCUS ANGLE").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 45deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 45deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 45deg, focus-distance 50%, radius 100%, red, darkgray, black);").build() 
						).build())	;	

		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 90deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 90deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 90deg, focus-distance 50%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		// #4
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#4").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 135deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 135deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 135deg, focus-distance 50%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		
	}

	private void drillFocusDistance() {
		vb.getChildren().add(LabelBuilder.create()
									.text("FOCUS DISTANCE").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 25%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 25%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 25%, radius 100%, red, darkgray, black);").build() 
						).build())	;	

		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 50%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		// #4
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#4").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 75%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 75%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 75%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		
	}
	private void drillCenter() {
		vb.getChildren().add(LabelBuilder.create()
									.text("CENTER").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 0% 0%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 0% 0%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 0% 0%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 25%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 25%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 25%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 50% 50%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 50% 50%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 50% 50%, radius 100%, red, darkgray, black);").build() 
						).build())	;	

		// #4
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#4").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 50%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 50%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 25% 50%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		// #5
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#5").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 75% 25%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 75% 25%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 0deg, focus-distance 0%, center 75% 25%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		
	}

	private void drillCenterWithFocus() {
		vb.getChildren().add(LabelBuilder.create()
									.text("CENTER").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 0% 0%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 0% 0%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 0% 0%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 25%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 25%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 25%, radius 100%, red, darkgray, black);").build() 
						).build())	;	
		
		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
				.children(
						LabelBuilder.create()
							.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
						,CircleBuilder.create().radius(75)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 50% 50%, radius 100%, red, darkgray, black);").build()
						,RectangleBuilder.create().width(150).height(150)
							.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 50% 50%, radius 100%, red, darkgray, black);").build()
						,LabelBuilder.create()
							.text("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 50% 50%, radius 100%, red, darkgray, black);").build() 
						).build())	;	

		// #4
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#4").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 50%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 50%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 25% 50%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		// #5
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
								.children(
										LabelBuilder.create()
											.text("#5").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
										,CircleBuilder.create().radius(75)
											.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 75% 25%, radius 100%, red, darkgray, black);").build()
										,RectangleBuilder.create().width(150).height(150)
											.style("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 75% 25%, radius 100%, red, darkgray, black);").build()
										,LabelBuilder.create()
											.text("-fx-fill:radial-gradient(focus-angle 60deg, focus-distance 30%, center 75% 25%, radius 100%, red, darkgray, black);").build() 
										).build())	;	
		
		
	}

	private void drillRadiusWithPixel() {
		vb.getChildren().add(LabelBuilder.create()
				.text("RADIUS WITH PIXEL").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 10px, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 10px, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 10px, red, darkgray, black);").build() 
											).build())	;	
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 30px, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 30px, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 30px, red, darkgray, black);").build() 
											).build())	;	
		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 60px, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 60px, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 60px, red, darkgray, black);").build() 
											).build())	;	
		
	}

	private void drillRadiusWithPercent() {
		vb.getChildren().add(LabelBuilder.create()
				.text("RADIUS WITH PERCENT").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 10%, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 10%, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 10%, red, darkgray, black);").build() 
											).build())	;
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 30%, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 30%, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 30%, red, darkgray, black);").build() 
											).build())	;	
		// #3
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#3").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 60%, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 60%, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 60%, red, darkgray, black);").build() 
											).build())	;
	}
	
	private void drillRepeat() {
		vb.getChildren().add(LabelBuilder.create()
				.text("REPEAT").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40px,repeat, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40px,repeat, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40px,repeat, red, darkgray, black);").build() 
											).build())	;	
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%,repeat, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%,repeat, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40%,repeat, red, darkgray, black);").build() 
											).build())	;	
		
	}

	private void drillReflect() {
		vb.getChildren().add(LabelBuilder.create()
				.text("REFLECT").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40px,reflect, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40px,reflect, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40px,reflect, red, darkgray, black);").build() 
											).build())	;	
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%,reflect, red, darkgray, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%,reflect, red, darkgray, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40%,reflect, red, darkgray, black);").build() 
											).build())	;	
	}

	private void drillColorStops() {
		vb.getChildren().add(LabelBuilder.create()
				.text("COLOR STOPS").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build() 
											).build())	;
		// #2
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#2").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 70%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 70%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 70%, red 10%, blue 30%, yellow 60%, green 85%, black);").build() 
											).build())	;
		
		
	}
	private void drillMultiple() {
		vb.getChildren().add(LabelBuilder.create()
				.text("COLOR STOPS").style("-fx-font-weight:bold;-fx-font-size:20;").build() );
		// #1
		vb.getChildren().add(HBoxBuilder.create().spacing(10)
										.children(
											LabelBuilder.create()
												.text("#1").style("-fx-font-weight:bold;-fx-font-size:20;").build() 
											,CircleBuilder.create().radius(75)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,RectangleBuilder.create().width(150).height(150)
												.style("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build()
											,LabelBuilder.create()
												.text("-fx-fill:radial-gradient(center 50% 50%, radius 40%, red 10%, blue 30%, yellow 60%, green 85%, black);").build() 
											).build())	;
	
	}
	
	public static void main(String[] args) {
		ExtendedApplicationX.launch(args);
	}
}
