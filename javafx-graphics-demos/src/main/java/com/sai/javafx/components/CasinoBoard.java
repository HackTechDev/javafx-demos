package com.sai.javafx.components;

import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class CasinoBoard extends StackPane{

	private double arcAngle = 9.72972972972973;
	private Rectangle2D bounds = new Rectangle2D(0, 0, 503, 503);
	private double outerGap = 14;
	private double base1Radius ;
	private double base1LineHgt ;
	private double boardEdgeRadius;
	private double arcStackWidth;
	private double arcStackHeight;
	private double arcRadius;
	
	int[] numArr = new int[]{32,15,19,4,21,2,25,17,34,6,27,13,36,11,30,8,23,10,5,24,16,
			                 33,1,20,14,31,9,22,18,29,1,28,12,35,3,26,0};
	private Node rotateBoard;
	public CasinoBoard(boolean flag){
		super();
		super.setPrefSize(bounds.getWidth(), bounds.getHeight());
		ImageView img = new ImageView(new Image(CasinoBoard.class.getResource("/images/board.png").toExternalForm()));
		rotateBoard = new StackPane();
		((StackPane)rotateBoard).getChildren().add(img);
		
		double rr = bounds.getWidth()/2;
		Circle c = new Circle(rr+1, rr+1, rr);
		super.setClip(c);
		//super.setStyle("-fx-background-color:blue;-fx-border-width:1px;");
		super.getStyleClass().add("borderCheck");
		super.getChildren().add(rotateBoard);
		
	}
	public CasinoBoard(){
		super();
		super.setPrefSize(bounds.getWidth(), bounds.getHeight());
		//super.getStyleClass().add("borderCheck");
		this.base1Radius = bounds.getWidth()/2;
		this.base1LineHgt = bounds.getHeight();
		this.boardEdgeRadius = base1Radius-outerGap;
		this.arcStackWidth = bounds.getWidth()- (2*outerGap);
		this.arcStackHeight = bounds.getHeight()- (2*outerGap); 
		this.arcRadius = boardEdgeRadius - 8;
		
		super.getChildren().addAll(getBase1());
		for(int i=0;i<4;i++){
			Line l = getBase1Line();
			l.setRotate(i*45.0+20);
			super.getChildren().add(l);
			
		}
		//super.getChildren().add(getBigArc());
		super.getChildren().add(getRotateBoard());
		
	}
	
	public Node getRotateBoard(){
		if(rotateBoard==null){
			rotateBoard = new StackPane();
			Circle boardEdge = new Circle();
			boardEdge.setRadius(this.boardEdgeRadius);
			boardEdge.getStyleClass().add("boardEdge");
			((StackPane)rotateBoard).getChildren().add(boardEdge);
			
			for(int i=0;i<37;i++){
				StackPane sp = getBigArc(i);
				sp.setRotate(this.arcAngle * i);
				((StackPane)rotateBoard).getChildren().add(sp);
			}
			for(int i=0;i<37;i++){
				StackPane sp = getRotateBoardLine();
				sp.setRotate(this.arcAngle * i);
				((StackPane)rotateBoard).getChildren().add(sp);
			}
			
			((StackPane)rotateBoard).getChildren().add( getInnerCircle());
		}
		return rotateBoard;
	}

	private Circle getBase1(){
		Circle c = new Circle();
		c.setRadius(this.base1Radius);
		c.getStyleClass().add("base1");
		return c;
	}
	
	private StackPane getBigArc(int i){
		StackPane sp = new StackPane();
		sp.setPrefSize(this.arcStackWidth, this.arcStackHeight);
		sp.getChildren().add((i==36? getBigGreenArc() : (i%2==0? getBigRedArc() : getBigBlackArc())));
		
		Text num = new Text(numArr[i]+"");
		num.setStyle("-fx-font-size:24;-fx-font-weight:bold;-fx-fill:#DEBE88;");
		num.setTranslateY(-(arcRadius-21));
		num.setTranslateX(-(18));
		num.setRotate(-5);
		Bloom bloom = new Bloom();
        bloom.setThreshold(0.275);
        num.setEffect(bloom);
        
        Text num1 = new Text(" ");
		num1.setStyle("-fx-font-size:24;-fx-font-weight:bold;-fx-fill:#FFF7DE;");
		num1.setTranslateY(-(arcRadius-75));
		num1.setTranslateX(-(18));
		num1.setEffect(bloom);
        
		sp.getChildren().addAll(num,num1);
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
		arc.setRadiusX(this.arcRadius);
		arc.setRadiusY(this.arcRadius);
		arc.setStartAngle(90);
		arc.setType(ArcType.ROUND);
		//arc.getStyleClass().add("arc");
		//arc.setLength(this.arcAngle);
		arc.setLength(this.arcAngle);
		arc.setTranslateY(-(this.arcRadius/2));
		arc.setTranslateX(-19);
		return arc;
	}
	
	private Line getBase1Line(){
		Line l = new Line();
		l.getStyleClass().add("base1-line");
		l.setStartX(0);
        l.setStartY(0);
        l.setEndX(0);
        l.setEndY(base1LineHgt);
        l.setSmooth(true);
        return l;
	}
	private StackPane getRotateBoardLine(){
		StackPane sp = new StackPane();
		sp.setPrefSize(this.arcStackWidth, this.arcStackHeight);
		
		StackPane sp1 = new StackPane();
		sp1.getStyleClass().add("rotateBoard-line");
		sp1.setMaxSize(3, this.arcRadius);
		sp1.setTranslateY(-((this.arcRadius)/2));
		
		sp.getChildren().add(sp1);
		return sp;
	}
	
	private StackPane getInnerCircle(){
		StackPane sp = new StackPane();
		Circle c1 = new Circle();
		c1.setRadius(this.arcRadius-50);
		c1.setStyle("-fx-stroke-width:2px;-fx-stroke:#DFAE56; -fx-fill:transparent;");
		Circle c2 = new Circle();
		c2.setRadius(this.arcRadius-52);
		c2.setStyle("-fx-stroke-width:1.5px;-fx-stroke:#F5E0B8; -fx-fill:transparent;");
		Circle c3 = new Circle();
		c3.setRadius(this.arcRadius-53.5);
		c3.setStyle("-fx-stroke-width:1.5px;-fx-stroke:#D5A44D; -fx-fill:transparent;");
		Circle c4 = new Circle();
		c4.setRadius(this.arcRadius-55);
		c4.setStyle("-fx-stroke-width:2px;-fx-stroke:#DFAE56; -fx-fill:transparent;");
		
		sp.getChildren().addAll(c1,c2,c3,c4);
		return sp;
	}
	
}
