package com.sai.javafx.demos;

import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.SequentialTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.PerspectiveTransformBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import com.sai.javafx.common.ExtendedApplicationX;
import com.sai.javafx.components.CasinoBoard;

public class CasinoBoardDemo extends ExtendedApplicationX{

	@Override
	protected void setup() {
		CasinoBoard normalBoard = new CasinoBoard(true);
		
		StackPane containter = new StackPane();
		containter.getChildren().add(normalBoard);
		Circle c = new Circle(100, 100, 100);
		Rectangle r = new Rectangle(240, 150);
		//containter.setClip(r);
		r.setTranslateX(132);
		
		CasinoBoard slantedBoard = new CasinoBoard(true);
		slantedBoard.setEffect(PerspectiveTransformBuilder.create()
				.ulx(0.0)
				.uly(0.0)
				.urx(503.0)
				.ury(0.0)
				.lrx(503.0)
				.lry(220.0)
				.llx(0.0)
				.lly(220.0).build()	);// Front to Back

		RotateTransition rotateTransition1 = new RotateTransition(Duration.seconds(8));
		rotateTransition1.setFromAngle(0);
		rotateTransition1.setToAngle(720);
		rotateTransition1.setCycleCount(1);
		rotateTransition1.setAutoReverse(false);
		
		RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(10));
		rotateTransition2.setFromAngle(0);
		rotateTransition2.setToAngle(450);
		rotateTransition2.setCycleCount(1);
		rotateTransition2.setAutoReverse(false);
		
		RotateTransition rotateTransition3 = new RotateTransition(Duration.seconds(8));
		rotateTransition3.setFromAngle(0);
		rotateTransition3.setToAngle(720);
		rotateTransition3.setCycleCount(1);
		rotateTransition3.setAutoReverse(false);

		RotateTransition rotateTransition4 = new RotateTransition(Duration.seconds(10), slantedBoard);
		rotateTransition4.setFromAngle(0);
		rotateTransition4.setToAngle(450);
		rotateTransition4.setCycleCount(1);
		rotateTransition4.setAutoReverse(false);

		
		final SequentialTransition sequentialTransition = SequentialTransitionBuilder.create().node(normalBoard)
															.children(rotateTransition1, rotateTransition2)
															.cycleCount(1)
															.autoReverse(true)
															.build();
		
		final SequentialTransition sequentialTransition2 = SequentialTransitionBuilder.create().node(slantedBoard)
															.children(rotateTransition3, rotateTransition4)
															.cycleCount(1)
															.autoReverse(true)
															.build();

		HBox vb = new HBox();
		vb.setSpacing(15);
		Button btn = new Button("Run");
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				sequentialTransition.play();
				sequentialTransition2.play();
			}
		});
		vb.getChildren().addAll(btn,slantedBoard,containter);
		root.getChildren().add(vb);
	}
	
	@Override
	protected void setupCss() {
		scene.getStylesheets().add( this.getClass().getResource("/com/sai/javafx/styles/CasinoBoard.css").toString());
	}

	public static void main(String[] args) {
		ExtendedApplicationX.launch(args);
	}
}
