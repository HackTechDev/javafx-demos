package com.javafx.flipview.working;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Task_2_ClipContainerAndDrag  extends ExtendApplication{
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
		
		FlipViewContainer flipView = new FlipViewContainer(550,550);
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
class FlipViewContainer extends StackPane{
	private double pageWidth;
	private double pageHeight;
	private double cornerTriSize;
	
	private double startDragX;
	private double startDragY;
	private double startNodeX;
	private double startNodeY;
	Timeline timelineClose = new Timeline();
	
	private Rectangle containerClip = new Rectangle();
	
	private ObservableList<FlipPage> pages = FXCollections.observableArrayList();
	private SimpleObjectProperty<FlipPage> prevPage = new SimpleObjectProperty<FlipPage>();
	private SimpleObjectProperty<FlipPage> nextPage = new SimpleObjectProperty<FlipPage>();
	private SimpleObjectProperty<FlipPage> leftPage = new SimpleObjectProperty<FlipPage>();
	private SimpleObjectProperty<FlipPage> rightPage = new SimpleObjectProperty<FlipPage>();
	
	private ChangeListener<FlipPage> prevPageListener = new ChangeListener<FlipPage>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage> paramObservableValue,FlipPage paramT1, FlipPage page) {
			if(page!=null){
				page.setRotate(90);
				page.setTranslateX(-(pageWidth-cornerTriSize));
				page.setTranslateY(-(pageHeight-cornerTriSize));
			}
		}
	};
	
	private ChangeListener<FlipPage> nextPageListener = new ChangeListener<FlipPage>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage> paramObservableValue,FlipPage paramT1, FlipPage page) {
			if(page!=null){
				page.setRotate(-90);
				page.setTranslateX((2*pageWidth)-cornerTriSize);
				page.setTranslateY(-(pageHeight-cornerTriSize));
			}
		}
	};
	
	private ChangeListener<FlipPage> leftPageListener = new ChangeListener<FlipPage>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage> paramObservableValue,FlipPage paramT1, FlipPage page) {
			if(page!=null){
				page.setTranslateX(0);
				page.setTranslateY(0);
			}
		}
	};
	
	private ChangeListener<FlipPage> rightPageListener = new ChangeListener<FlipPage>() {
		@Override
		public void changed(ObservableValue<? extends FlipPage> paramObservableValue,FlipPage paramT1, FlipPage page) {
			if(page!=null){
				page.setTranslateX(pageWidth);
				page.setTranslateY(0);
			}
		}
	};
	
	
	
	private ListChangeListener<FlipPage> pagesListener = new ListChangeListener<FlipPage>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends FlipPage> paramChange) {
			getChildren().addAll(pages);
			setPrevPage(pages.get(0));
			setLeftPage(pages.get(1));
			setRightPage(pages.get(2));
			setNextPage(pages.get(3));
		}
	};
	
	public FlipViewContainer(double pageWidth, double pageHeight) {
		super();
		this.pageWidth = pageWidth;
		this.pageHeight = pageHeight;
		this.cornerTriSize = (pageWidth*.01*10); // 10% of width
		
		setMaxSize(pageWidth*2, pageHeight);
		setPrefSize(pageWidth*2, pageHeight);
		setMinSize(pageWidth*2, pageHeight);
		
		pages.addListener(pagesListener);
		
		getStyleClass().add("flip-view");
		setAlignment(Pos.TOP_LEFT);
		configureListeners();
		
		containerClip.setWidth((pageWidth*2)+2);
		containerClip.setHeight(pageHeight+2);
		this.setClip(containerClip);
	}
	
	private void configureListeners() {
		prevPage.addListener(prevPageListener);
		nextPage.addListener(nextPageListener);
		leftPage.addListener(leftPageListener);
		rightPage.addListener(rightPageListener);
	}

	public void setLeftPage(FlipPage fp){
		removeMouseListeners(fp);
		leftPage.set(fp);
	}
	
	public void setRightPage(FlipPage fp){
		removeMouseListeners(fp);
		rightPage.set(fp);
	}
	
	public void setPrevPage(FlipPage fp){
		if(prevPage.get()!=null){
			removeMouseListeners(prevPage.get());
		}
		prevPage.set(fp);
		prevPage.get().toFront();
		addPrevPageDragListeners(prevPage.get());
	}
	
	public void setNextPage(FlipPage fp){
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
		ObservableList<FlipPage> dumPages = FXCollections.observableArrayList();
		for (Node node : nodes) {
			FlipPage page = new FlipPage(node);
			page.setPageSize(pageWidth, pageHeight);
			dumPages.add(page);
		}
		setNumbersToPages(dumPages);
		pages.addAll(dumPages);
	}
	
	private void setNumbersToPages(ObservableList<FlipPage> dumPages) {
		int i=0;
		for (FlipPage page : dumPages) {
			page.setPageNo(i);
			i++;
		}
	}

	public ObservableList<FlipPage> getPages(){
		return pages;
	}
	
	private void goToPrevPageStart(Node n){
		timelineClose.stop();
		timelineClose.setCycleCount(1); 
		timelineClose.setAutoReverse(true);
		KeyValue kv1 = new KeyValue(n.translateXProperty(), -(pageWidth-cornerTriSize));
		KeyValue kv2 = new KeyValue(n.translateYProperty(), -(pageHeight-cornerTriSize));
		KeyFrame kf1 = new KeyFrame(Duration.valueOf("320ms"), kv1, kv2);
		timelineClose.getKeyFrames().add(kf1);
		timelineClose.play();
	}
	
	private void goToNextPageStart(Node n){
		timelineClose.stop();
		timelineClose.setCycleCount(1); 
		timelineClose.setAutoReverse(true);
		KeyValue kv1 = new KeyValue(n.translateXProperty(), (2*pageWidth)-cornerTriSize);
		KeyValue kv2 = new KeyValue(n.translateYProperty(), -(pageHeight-cornerTriSize));
		KeyFrame kf1 = new KeyFrame(Duration.valueOf("320ms"), kv1, kv2);
		timelineClose.getKeyFrames().add(kf1);
		timelineClose.play();
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
				goToPrevPageStart(n);
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
				goToNextPageStart(n);
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
}// eo FlipViewContainer

/**
 * Flip Page.
 * @author Sai.Dandem
  */
class FlipPage extends StackPane{
	private int pageNo;
	
	public FlipPage(Node nd) {
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
	
}// eo FlipPage


