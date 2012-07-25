package com.javafx.flipview.working;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.RowConstraintsBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.util.Duration;

public class Task_1_DragDemo extends ExtendApplication{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	private double startDragX;
	private double startDragY;
	private double startNodeX;
	private double startNodeY;
	double pageHeight = 500d;
	double pageWidth = 500d;
	

	@Override
	public void configure() {
		
		StackPane sp1 = StackPaneBuilder.create().styleClass("bgOrange").build();
		StackPane sp2 = StackPaneBuilder.create().styleClass("numberPlate").maxHeight(pageHeight).maxWidth(pageWidth).children(getNumberGrid()).build();
		
		sp2.setRotate(180);
		sp2.setTranslateX(-(pageWidth-50));
		sp2.setTranslateY(-(pageHeight-50));
		
		StackPane contianer = StackPaneBuilder.create().alignment(Pos.TOP_LEFT).build();
		getRoot().getChildren().add(contianer);
		
		StackPane sp3 = StackPaneBuilder.create().styleClass("bgRed").translateX(40).translateY(40).build();
		contianer.getChildren().addAll(sp1,sp2);
		addDragListeners(sp2);
		
	}
	/**
	 * Method to add the drag feature to the pop up.
	 * 
	 * @param n
	 */
	public void addDragListeners(final Node n) {
		n.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				startDragX = me.getSceneX();
				startDragY = me.getSceneY();
				startNodeX = n.getTranslateX();
				startNodeY = n.getTranslateY();
			}
		});
		
		n.setOnMouseReleased(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				goToStart(n);
			}
		});
		
		n.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				double xTr = startNodeX+(me.getSceneX() - startDragX);
				//if(xTr<0) xTr = 0;
				
				double yTr = startNodeY+(me.getSceneY() - startDragY);
				//if(yTr<0) yTr = 0;
				
				n.setTranslateX(xTr);
				n.setTranslateY(yTr);
			}
		});
	}
	Timeline timelineOpen = new Timeline();
	private void goToStart(Node n){
		timelineOpen.stop();
		timelineOpen.setCycleCount(1); 
		timelineOpen.setAutoReverse(true);
		KeyValue kv1 = new KeyValue(n.translateXProperty(), -(pageWidth-50));
		KeyValue kv2 = new KeyValue(n.translateYProperty(), -(pageHeight-50));
		KeyFrame kf1 = new KeyFrame(Duration.valueOf("320ms"), kv1, kv2);
		timelineOpen.getKeyFrames().add(kf1);
		timelineOpen.play();
	}
	
	private GridPane getNumberGrid(){
		int cols = 10;
		int rows = 10;
		int count = 1;
		GridPane gp = GridPaneBuilder.create().gridLinesVisible(true).build();
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				gp.add(new Label(count+""), c, r);
				if(r==0){
					gp.getColumnConstraints().add(ColumnConstraintsBuilder.create().halignment(HPos.CENTER).percentWidth(100/cols).build());
				}
				count++;
			}
			gp.getRowConstraints().add(RowConstraintsBuilder.create().valignment(VPos.CENTER).percentHeight(100/rows).build());
		}
		return gp;
	}
}
