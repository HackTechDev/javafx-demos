package com.sai.javafx.background;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;

import com.sai.javafx.common.ExtendedApplicationX;

public class BackGroundImgInCssDemo extends ExtendedApplicationX{

	@Override
	protected void setup() {
		StackPane body = StackPaneBuilder.create().styleClass("backgroundBody").build();
		StackPane header = StackPaneBuilder.create().styleClass("backgroundHeader").prefHeight(36).build();
		StackPane footer = StackPaneBuilder.create().styleClass("backgroundFooter").prefHeight(31).build();
		BorderPane bp = new BorderPane();
		bp.setCenter(body);
		bp.setTop(header);
		bp.setBottom(footer);
		root.getChildren().add(bp);
		scene.getStylesheets().add(this.getClass().getResource("/styles/template.css").toString());
	}

	public static void main(String[] args) {
		ExtendedApplicationX.launch(args);
	}
}
