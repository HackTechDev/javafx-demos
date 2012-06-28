package com.ezest.javafx.components.freetextfield;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPaneBuilder;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import com.javafx.experiments.scenicview.ScenicView;

public class FreeTextFieldDemo extends Application {

	Stage stage;
	Scene scene;
	StackPane root;
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		configureScene();
		configureStage();
		
		final ScrollFreeTextArea text1 = new ScrollFreeTextArea();
		ScrollFreeTextArea text2 = new ScrollFreeTextArea();
		ScrollFreeTextArea text3 = new ScrollFreeTextArea();
		
		/*FreeTextField text1 = new FreeTextField();
		FreeTextField text2 = new FreeTextField();
		FreeTextField text3 = new FreeTextField();
		*/
		VBox vb = VBoxBuilder.create().spacing(15).build();
		
		vb.getChildren().addAll(new Label("Description 1 : "), text1);
		vb.getChildren().addAll(new Label("Description 2 : "), text2);
		vb.getChildren().addAll(new Label("Description 3 : "), text3);
		
		StackPane sp = StackPaneBuilder.create()
								.padding(new Insets(15))
								.children(vb).build();
		
		ScrollPane scroll = ScrollPaneBuilder.create().fitToHeight(true).fitToWidth(true).content(sp).build();
		scroll.widthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> paramObservableValue,Number paramT1, Number paramT2) {
				System.out.println("Scroll Width : "+paramT2.doubleValue());
				//text1.requestGroup();
			}
		});
		root.getChildren().add(scroll);
	}

	private void configureStage(){
		stage.setTitle(this.getClass().getSimpleName());
		stage.setX(0);
	    stage.setY(0);
	    stage.setWidth(600);
	    stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
	    stage.setScene(this.scene);
	    stage.show();
	}
	
	private void configureScene(){
		root = new StackPane();
		root.setPadding(new Insets(15));
		BorderPane bp = new BorderPane();
		bp.setBottom(getBottom());
		bp.setCenter(root);
		bp.autosize();
		this.scene = new Scene(bp,600d,600d, Color.LEMONCHIFFON);
		//scene.getStylesheets().addAll("styles/template.css","styles/template.css");
	}
	
	private Node getBottom() {
		StackPane sp = new StackPane();
		sp.setMinHeight(25);
		sp.setAlignment(Pos.TOP_RIGHT);
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/images/mglass.gif")));
		image.setCursor(Cursor.HAND);
		image.setTranslateX(-5);
		image.setTranslateY(3);
		image.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
				ScenicView.show(scene);
			}
		});
		sp.getChildren().addAll(new Separator(),image);
		return sp;
	}
}

