package com.javafx.flipview.working;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.output.ForkXmlOutput;

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
import javafx.util.Duration;

public class Task_2_ClipContainerAndDrag  extends ExtendApplication{
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	@Override
	public void configure() {
		
		StackPane sp1 = StackPaneBuilder.create().styleClass("bgOrange").build();
		StackPane sp2 = StackPaneBuilder.create().styleClass("numberPlate").children(getNumberGrid()).build();
		StackPane sp3 = StackPaneBuilder.create().styleClass("bgRed").translateX(40).translateY(40).build();
		
		//StackPane contianer = StackPaneBuilder.create().alignment(Pos.TOP_LEFT).maxHeight(pageHeight).maxWidth(pageWidth*2).build();
		
		//contianer.getChildren().addAll(sp1,sp2);
		FlipViewContainer flipView = new FlipViewContainer(550,550);
		
		ObservableList<Node> nodes = FXCollections.observableArrayList();
		nodes.addAll(sp1, sp2, sp3);
		
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
	
	
	
	private ListChangeListener<FlipPage> pagesListener = new ListChangeListener<FlipPage>(){
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends FlipPage> paramChange) {
			setNextPage(pages.get(1));
			getChildren().add(nextPage.get());
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
	}
	
	private void configureListeners() {
		prevPage.addListener(prevPageListener);
		nextPage.addListener(nextPageListener);
	}

	public void setPrevPage(FlipPage fp){
		prevPage.set(fp);
		if(prevPage.get()!=null){
			// TODO : remove the drag listener
		}
		prevPage.set(fp);
		addPrevPageDragListeners(prevPage.get());
	}
	
	public void setNextPage(FlipPage fp){
		if(nextPage.get()!=null){
			// TODO : remove the drag listener
		}
		nextPage.set(fp);
		addNextPageDragListeners(nextPage.get());
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
	
	private void goToStart(Node n){
		timelineClose.stop();
		timelineClose.setCycleCount(1); 
		timelineClose.setAutoReverse(true);
		KeyValue kv1 = new KeyValue(n.translateXProperty(), -(pageWidth-cornerTriSize));
		KeyValue kv2 = new KeyValue(n.translateYProperty(), -(pageHeight-cornerTriSize));
		KeyFrame kf1 = new KeyFrame(Duration.valueOf("320ms"), kv1, kv2);
		timelineClose.getKeyFrames().add(kf1);
		timelineClose.play();
	}
	
	
	
	/**
	 * Method to add the drag feature to the pop up.
	 * 
	 * @param n
	 */
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


