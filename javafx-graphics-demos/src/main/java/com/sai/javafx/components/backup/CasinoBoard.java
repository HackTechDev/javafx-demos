package com.sai.javafx.components.backup;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

public class CasinoBoard extends StackPane{

	private Rectangle2D bounds = new Rectangle2D(0, 0, 503, 503);
	private double gap = 28;
	private StackPane rotateBoard;
	public CasinoBoard(){
		super();
		super.setPrefSize(bounds.getWidth(), bounds.getHeight());
		//super.getStyleClass().add("borderCheck");
		
		super.getChildren().addAll(getBase1());
		for(int i=0;i<4;i++){
			Line l = getBase1Line();
			l.setRotate(i*45.0+20);
			super.getChildren().add(l);
			
		}
		
		super.getChildren().add(getRotateBoard());
		
	}
	
	public StackPane getRotateBoard(){
		if(rotateBoard==null){
			rotateBoard = new StackPane();
			Circle boardEdge = new Circle();
			boardEdge.setRadius((bounds.getWidth()/2)-(gap/2));
			boardEdge.getStyleClass().add("boardEdge");
			rotateBoard.getChildren().add(boardEdge);
			
			for(int i=0;i<37;i++){
				StackPane sp = getBigArc(i);
				sp.setRotate(9.72972972972973*i);
				rotateBoard.getChildren().add(sp);
			}
			
			
		}
		return rotateBoard;
	}

	private Circle getBase1(){
		Circle c = new Circle();
		c.setRadius(bounds.getWidth()/2);
		c.getStyleClass().add("base1");
		return c;
	}
	
	private StackPane getBigArc(int i){
		StackPane sp = new StackPane();
		sp.setPrefSize(bounds.getWidth()-gap, bounds.getHeight()-gap);
		sp.getChildren().add((i==36? getBigGreenArc() : (i%2==0? getBigBlackArc() : getBigRedArc())));
		return sp;
	}
	
	private Arc getBigBlackArc(){
		Arc a =getBigArc();
		a.getStyleClass().add("bigArcBlackFill");
		return a;
	}
	
	private Arc getBigRedArc(){
		Arc a =getBigArc();
		a.getStyleClass().add("bigArcRedFill");
		return a;
	}
	
	private Arc getBigGreenArc(){
		Arc a =getBigArc();
		a.getStyleClass().add("bigArcGreenFill");
		return a;
	}
	
	
	private Arc getBigArc(){
		Arc arc = new Arc();
		arc.setCenterX(0);
		arc.setCenterY(0);
		arc.setRadiusX((bounds.getWidth()/2)-(gap/2)-18);
		arc.setRadiusY((bounds.getWidth()/2)-(gap/2)-18);
		arc.setStartAngle(90);
		arc.setType(ArcType.ROUND);
		arc.setLength(9.72972972972973);
		arc.setTranslateY(-((bounds.getWidth()/2)-(gap/2))/2);
		arc.setTranslateX(-20);
		return arc;
	}
	
	private Line getBase1Line(){
		Line l = new Line();
		l.getStyleClass().add("base1-line");
		l.setStartX(0);
        l.setStartY(0);
        l.setEndX(0);
        l.setEndY(bounds.getHeight()-2);
        l.setSmooth(true);
        return l;
	}
}
