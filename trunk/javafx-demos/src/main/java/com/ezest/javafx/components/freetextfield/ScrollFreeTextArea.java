package com.ezest.javafx.components.freetextfield;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;

/**
 * TextArea component which does not have a scroll enabled.
 * @author Sai.Dandem
 */
public class ScrollFreeTextArea extends StackPane{

	private Label label;
	private TextArea textArea;
	private Character enterChar = new Character((char)10);
	private Region content;
	private SimpleDoubleProperty contentHeight = new SimpleDoubleProperty();
	
	
	private final double NEW_LINE_HEIGHT = 18D;
	private final double TOP_PADDING = 3D;
	private final double BOTTOM_PADDING = 6D;
	
	public ScrollFreeTextArea(){
		super();
		configure();
	}
	
	private void configure(){
		setAlignment(Pos.TOP_LEFT);
		
		this.textArea =new TextArea(){
			protected void layoutChildren() {
				super.layoutChildren();
				if(content==null){
					content = (Region)lookup(".content");
					contentHeight.bind(content.heightProperty());
					content.heightProperty().addListener(new ChangeListener<Number>() {
						@Override
						public void changed(ObservableValue<? extends Number> paramObservableValue,	Number paramT1, Number paramT2) {
							//System.out.println("Content View Height :"+paramT2.doubleValue());
						}
					});
				}
			};
		};
		this.textArea.setWrapText(true);
		
		this.label =new Label();
		this.label.setWrapText(true);
		this.label.prefWidthProperty().bind(this.textArea.widthProperty());
		//this.label.textProperty().bind(this.textArea.textProperty());
		label.textProperty().bind(new StringBinding() {
			{
				bind(textArea.textProperty());
			}
			@Override
			protected String computeValue() {
				if(textArea.getText()!=null && textArea.getText().length()>0){
					if(!((Character)textArea.getText().charAt(textArea.getText().length()-1)).equals(enterChar)){ 
						System.out.println("enter added..");
						return textArea.getText()+enterChar;
					}
				}
				return textArea.getText();
			}
		});
		
		StackPane lblContainer = StackPaneBuilder.create()
										  .alignment(Pos.TOP_LEFT)
										  .padding(new Insets(4,7,7,7))
										  .children(label)
										  .build();
		// Binding the container width to the TextArea width.
		lblContainer.maxWidthProperty().bind(textArea.widthProperty());
		
		textArea.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> paramObservableValue,	String paramT1, String value) {
				layoutForNewLine(textArea.getText());
			}
		});
		
		label.heightProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> paramObservableValue,	Number paramT1, Number paramT2) {
				layoutForNewLine(textArea.getText());
			}
		});
		
		getChildren().addAll(lblContainer,textArea);
	}
	
	private void layoutForNewLine(String text){
		if(text!=null && text.length()>0 && 
					((Character)text.charAt(text.length()-1)).equals(enterChar)){ 
			textArea.setPrefHeight(label.getHeight() + NEW_LINE_HEIGHT + TOP_PADDING + BOTTOM_PADDING);
			textArea.setMinHeight(label.getHeight() + NEW_LINE_HEIGHT + TOP_PADDING + BOTTOM_PADDING);
			//setMinHeight(label.getHeight() + NEW_LINE_HEIGHT + TOP_PADDING + BOTTOM_PADDING);
		}else{
			textArea.setPrefHeight(label.getHeight() + TOP_PADDING + BOTTOM_PADDING);
			textArea.setMinHeight(label.getHeight() + TOP_PADDING + BOTTOM_PADDING);
			/*textArea.setPrefHeight(contentHeight.get() + TOP_PADDING + BOTTOM_PADDING);
			textArea.setMinHeight(contentHeight.get() + TOP_PADDING + BOTTOM_PADDING);
			*///setMinHeight(label.getHeight() + TOP_PADDING + BOTTOM_PADDING);
			
			
		}
	}
}
