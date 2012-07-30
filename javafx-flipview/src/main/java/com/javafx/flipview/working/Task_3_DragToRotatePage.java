package com.javafx.flipview.working;

import java.util.List;

import com.javafx.flipview.utils.GeoUtility;
import com.sun.javafx.geom.Dimension2D;

import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraintsBuilder;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.GridPaneBuilder;
import javafx.scene.layout.RowConstraintsBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Task_3_DragToRotatePage extends ExtendApplication{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void configure() {
		StackPane sp1 = StackPaneBuilder.create().styleClass("numberPlate1").children(getNumberGrid()).build();
		StackPane sp2 = StackPaneBuilder.create().styleClass("numberPlate2").children(getNumberGrid()).build();
		StackPane sp3 = StackPaneBuilder.create().styleClass("numberPlate3").children(getNumberGrid()).build();
		StackPane sp4 = StackPaneBuilder.create().styleClass("numberPlate4").children(getNumberGrid()).build();
		
		ObservableList<Node> nodes = FXCollections.observableArrayList();
		nodes.addAll(sp1, sp2, sp3, sp4);
		
		FlipViewContainer3 flipView = new FlipViewContainer3(550,550);
		flipView.addNodesAsPages(nodes);
		getRoot().getChildren().add(flipView);
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

/**
 * Flip View Container.
 * @author Sai.Dandem
  */
class FlipViewContainer3 extends StackPane{
	private Dimension2D pageDimension;
	private Point2D flipViewLayoutPoint;
	private double cornerTriSize;
	
	private Point2D prevCornerStartPoint;
	private Point2D prevCornerEndPoint ;
	private Point2D nextCornerStartPoint ;
	private Point2D nextCornerEndPoint;
	
	private SimpleDoubleProperty prevPageRotateAngle = new SimpleDoubleProperty(90);
	private SimpleDoubleProperty nextPageRotateAngle = new SimpleDoubleProperty(-90);
	
	private double startDragX;
	private double startDragY;
	private double startNodeX;
	private double startNodeY;
	Timeline timelineClose = new Timeline();
	
	private Rectangle containerClip = new Rectangle();
	
	private ObservableList<FlipPage3> pages = FXCollections.observableArrayList();
	private SimpleObjectProperty<FlipPage3> prevPage = new SimpleObjectProperty<FlipPage3>();
	private SimpleObjectProperty<FlipPage3> nextPage = new SimpleObjectProperty<FlipPage3>();
	private SimpleObjectProperty<FlipPage3> leftPage = new SimpleObjectProperty<FlipPage3>();
	private SimpleObjectProperty<FlipPage3> rightPage = new SimpleObjectProperty<FlipPage3>();
	
	private ChangeListener<FlipPage3> prevPageListener = new ChangeListener<FlipPage3>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage3> paramObservableValue,FlipPage3 paramT1, FlipPage3 page) {
			if(page!=null){
				page.rotateProperty().bindBidirectional(prevPageRotateAngle);
				double d = -(pageDimension.width + ((pageDimension.height-pageDimension.width)/2))+cornerTriSize;
				prevCornerStartPoint = new Point2D(-pageDimension.width, -pageDimension.height);
				prevCornerEndPoint = new Point2D(d, d);
				page.setTranslateX(prevCornerStartPoint.getX());
				page.setTranslateY(prevCornerStartPoint.getY());
			}
		}
	};
	
	private ChangeListener<FlipPage3> nextPageListener = new ChangeListener<FlipPage3>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage3> paramObservableValue,FlipPage3 paramT1, FlipPage3 page) {
			if(page!=null){
				double w = ((pageDimension.height-pageDimension.width)/2)+ (2*pageDimension.width-cornerTriSize);
				double h = -(pageDimension.width + ((pageDimension.height-pageDimension.width)/2))+cornerTriSize;
				nextCornerStartPoint = new Point2D(2*pageDimension.width, -pageDimension.height);
				nextCornerEndPoint = new Point2D(w, h);
				
				page.rotateProperty().bind(nextPageRotateAngle);
				page.setTranslateX(nextCornerEndPoint.getX());
				page.setTranslateY(nextCornerEndPoint.getY());
			}
		}
	};
	
	private ChangeListener<FlipPage3> leftPageListener = new ChangeListener<FlipPage3>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage3> paramObservableValue,FlipPage3 paramT1, FlipPage3 page) {
			if(page!=null){
				page.setTranslateX(0);
				page.setTranslateY(0);
			}
		}
	};
	
	private ChangeListener<FlipPage3> rightPageListener = new ChangeListener<FlipPage3>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage3> paramObservableValue,FlipPage3 paramT1, FlipPage3 page) {
			if(page!=null){
				page.setTranslateX(pageDimension.width);
				page.setTranslateY(0);
			}
		}
	};
	
	
	
	private ListChangeListener<FlipPage3> pagesListener = new ListChangeListener<FlipPage3>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends FlipPage3> paramChange) {
			getChildren().addAll(pages);
			setPrevPage(pages.get(0));
			setLeftPage(pages.get(1));
			setRightPage(pages.get(2));
			setNextPage(pages.get(3));
		}
	};
	
	public FlipViewContainer3(double pageWidth, double pageHeight) {
		super();
		this.pageDimension = new Dimension2D(new Float(pageWidth), new Float(pageHeight));
		this.cornerTriSize = (this.pageDimension.width*.01*10); // 10% of width
		
		setMaxSize(this.pageDimension.width*2, this.pageDimension.height);
		setPrefSize(this.pageDimension.width*2, this.pageDimension.height);
		setMinSize(this.pageDimension.width*2, this.pageDimension.height);
		
		pages.addListener(pagesListener);
		
		getStyleClass().add("flip-view");
		setAlignment(Pos.TOP_LEFT);
		configureListeners();
		
		containerClip.setWidth((this.pageDimension.width*2)+2);
		containerClip.setHeight(this.pageDimension.height+2);
		//this.setClip(containerClip);
		
	}
	
	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		flipViewLayoutPoint = new Point2D(this.getLayoutX(), this.getLayoutY());
	}
	
	private void configureListeners() {
		prevPage.addListener(prevPageListener);
		nextPage.addListener(nextPageListener);
		leftPage.addListener(leftPageListener);
		rightPage.addListener(rightPageListener);
		
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent m) {
				if(isInPrevPageRange(m.getX(), m.getY())){
					if(prevPage.get().getTranslateX()!=prevCornerEndPoint.getX() || prevPage.get().getTranslateY()!=prevCornerEndPoint.getY()){
						animatePageMove(prevPage.get(), prevCornerEndPoint, "100ms");
					}
				}else{
					if(prevPage.get().getTranslateX()!=prevCornerStartPoint.getX() || prevPage.get().getTranslateY()!=prevCornerStartPoint.getY()){
						animatePageMove(prevPage.get(), prevCornerStartPoint, "100ms");
					}
				}
				
				if(isInNextPageRange(m.getX(), m.getY())){
					if(nextPage.get().getTranslateX()!=nextCornerEndPoint.getX() || nextPage.get().getTranslateY()!=nextCornerEndPoint.getY()){
						animatePageMove(nextPage.get(), nextCornerEndPoint, "100ms");
					}
				}else{
					if(nextPage.get().getTranslateX()!=nextCornerStartPoint.getX() || nextPage.get().getTranslateY()!=nextCornerStartPoint.getY()){
						animatePageMove(nextPage.get(), nextCornerStartPoint, "100ms");
					}
				}
			}
		});
	}

	private boolean isInPrevPageRange(double x, double y){
		return (x>=0 && x<=(2*cornerTriSize)) && (y>=0 && y<=(2*cornerTriSize)) ? true : false;
	}
	
	private boolean isInNextPageRange(double x, double y){
		return (x>=(2*pageDimension.width - 2*cornerTriSize) && x<=(2*pageDimension.width)) && (y>=0 && y<=(2*cornerTriSize)) ? true : false;
	}
	
	public void setLeftPage(FlipPage3 fp){
		removeMouseListeners(fp);
		leftPage.set(fp);
	}
	
	public void setRightPage(FlipPage3 fp){
		removeMouseListeners(fp);
		rightPage.set(fp);
	}
	
	public void setPrevPage(FlipPage3 fp){
		if(prevPage.get()!=null){
			removeMouseListeners(prevPage.get());
		}
		prevPage.set(fp);
		prevPage.get().toFront();
		addPrevPageDragListeners(prevPage.get());
	}
	
	public void setNextPage(FlipPage3 fp){
		if(nextPage.get()!=null){
			removeMouseListeners(nextPage.get());
		}
		nextPage.set(fp);
		nextPage.get().toFront();
		addNextPageDragListeners(nextPage.get());
	}
	
	private void removeMouseListeners(Node fp){
		fp.setOnMousePressed(null);
		fp.setOnMouseReleased(null);
		fp.setOnMouseDragged(null);
	}
	
	public void addNodesAsPages(List<Node> nodes){
		ObservableList<FlipPage3> dumPages = FXCollections.observableArrayList();
		for (Node node : nodes) {
			FlipPage3 page = new FlipPage3(node);
			page.setPageSize(pageDimension.width, pageDimension.height);
			dumPages.add(page);
		}
		setNumbersToPages(dumPages);
		pages.addAll(dumPages);
	}
	
	private void setNumbersToPages(ObservableList<FlipPage3> dumPages) {
		int i=0;
		for (FlipPage3 page : dumPages) {
			page.setPageNo(i);
			i++;
		}
	}

	public ObservableList<FlipPage3> getPages(){
		return pages;
	}
	
	private EventHandler<ActionEvent> prevPageStartRotate = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent arg0) {
			prevPage.get().setRotate(90);
		}
	};
	private void animatePageMove(Node n, Point2D location, String time){
		if(timelineClose.getStatus() != Status.RUNNING){
			timelineClose = new Timeline();
			timelineClose.setCycleCount(1); 
			timelineClose.setAutoReverse(true);
			KeyValue kv1 = new KeyValue(n.translateXProperty(), location.getX());
			KeyValue kv2 = new KeyValue(n.translateYProperty(), location.getY());
			KeyFrame kf1 = new KeyFrame(Duration.valueOf(time), prevPageStartRotate, kv1, kv2);
			timelineClose.getKeyFrames().add(kf1);
			timelineClose.play();
		}
	}
	
	private void addPrevPageDragListeners(final Node n) {
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
				animatePageMove(n, prevCornerStartPoint, "320ms");
			}
		});
		
		n.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				
				double p0[] = {flipViewLayoutPoint.getX(), flipViewLayoutPoint.getY(), 0.0};
				double p1[] = {flipViewLayoutPoint.getX()+20, flipViewLayoutPoint.getY(), 0.0};
				double p2[] = {me.getSceneX(), me.getSceneY(), 0.0};
				
				double angle = GeoUtility.computeAngleInDegrees(p0, p1, p2);
				System.out.println("Angle : "+angle);
				prevPageRotateAngle.set(angle*2);
				double xTr = startNodeX+(me.getSceneX() - startDragX);
				//if(xTr<0) xTr = 0;
				
				double yTr = startNodeY+(me.getSceneY() - startDragY);
				//if(yTr<0) yTr = 0;
				
				n.setTranslateX(xTr);
				n.setTranslateY(yTr);
			}
		});
	}
	private void addNextPageDragListeners(final Node n) {
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
				animatePageMove(n, nextCornerStartPoint, "320ms");
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
}// eo FlipViewContainer3

/**
 * Flip Page.
 * @author Sai.Dandem
  */
class FlipPage3 extends StackPane{
	private int pageNo;
	
	public FlipPage3(Node nd) {
		super();
		setAlignment(Pos.TOP_LEFT);
		getChildren().add(nd);
	}

	public void setPageSize(double width, double height){
		setPrefSize(width, height);
		setMaxSize(width, height);
		setMinSize(width, height);
	}
	
	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
}// eo FlipPage3



