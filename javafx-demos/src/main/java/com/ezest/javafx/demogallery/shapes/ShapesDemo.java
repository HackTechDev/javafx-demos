package com.ezest.javafx.demogallery.shapes;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ShapesDemo extends Application {

	Stage stage;
	Scene scene;
	FlowPane root;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		configureScene();
		configureStage();
		root.setHgap(10);
		root.setVgap(10);
		createTriangle();
		createRectangle();
		createSquare();
		createOctagone();
		createCurve();
	}

	
	private void configureStage(){
		stage.setTitle("Shapes Demo");
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	}
	
	private void configureScene(){
		root = new FlowPane();
		root.autosize();
		this.scene = new Scene(root, Color.LINEN);
		scene.getStylesheets().add("styles/shapes.css");
	}

	private void createTriangle() {
		Group gp = new Group();
		StackPane leftSide = new StackPane();
		leftSide.setPrefSize(18, 30);
		leftSide.getStyleClass().add("triangle-shape");
		gp.getChildren().add(leftSide);
		
		StackPane rightSide = new StackPane();
		rightSide.setPrefSize(18, 30);
		rightSide.getStyleClass().add("triangle-shape");
		rightSide.setRotate(180);
		
		root.getChildren().addAll(gp,rightSide);
	}

	private void createRectangle() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 20);
		sp.getStyleClass().add("rectangle-shape");
		root.getChildren().add(sp);
	}
	private void createSquare() {
		StackPane sp = new StackPane();
		sp.setPrefSize(40, 40);
		sp.getStyleClass().add("square-shape");
		root.getChildren().add(sp);
	}
	private void createOctagone() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 60);
		sp.getStyleClass().add("octagone-shape");
		root.getChildren().add(sp);
	}
	private void createCurve() {
		StackPane sp = new StackPane();
		sp.setPrefSize(60, 60);
		sp.getStyleClass().add("curve-shape");
		root.getChildren().add(sp);
		
		MainModel model = new MainModel();
		final Button btn1 =new Button("BUtton 1");
		model.flagProperty().bind(btn1.armedProperty());
		
		Button btn2 =new Button("BUtton 2");
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent paramT) {
				System.out.println("Button two clicked");
				btn1.arm();
			}
		});
		root.getChildren().addAll(btn1,btn2);
		
		
	}

}

class MainModel{
	private SimpleBooleanProperty flag = new SimpleBooleanProperty();
	
	public MainModel(){
		flag.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(
					ObservableValue<? extends Boolean> paramObservableValue,
					Boolean paramT1, Boolean paramT2) {
				System.out.println("Modle changed....");
				
			}
		});
	}
	
	public SimpleBooleanProperty flagProperty(){
		return flag;
	}
}

