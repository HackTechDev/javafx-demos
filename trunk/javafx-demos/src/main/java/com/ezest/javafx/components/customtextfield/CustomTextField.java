package com.ezest.javafx.components.customtextfield;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TextField;

public class CustomTextField extends TextField{

	private SimpleObjectProperty<TextFieldType> type = new SimpleObjectProperty<TextFieldType>();
	private SimpleIntegerProperty size = new SimpleIntegerProperty();
	
	public CustomTextField(){
		this(TextFieldType.ALL);
	}
	
	public CustomTextField(String str){
		this(TextFieldType.ALL);
	}
	
	public CustomTextField(TextFieldType type){
		super();
		setType(type);
	}

	@Override 
	public void replaceText(int start, int end, String text) {
	   // If the replaced text would end up being invalid, then simply ignore this call!
		if (accept(text)) {
	        super.replaceText(start, end, text.trim());
	    }
	}
	 
    @Override 
    public void replaceSelection(String text) {
        if (accept(text)) {
            super.replaceSelection(text);
        }
    }
    
    private boolean accept(String text){
    	text = text.trim();
    	if (text.length() == 0) return true;
    	String newTxt;
    	switch(getType()){
    		case ALPHABETS:
    			if (!text.matches("[a-z]")) return false;
    			break;
    		case INTEGER_ONLY:
    			newTxt = (getText()!=null ? getText() : "") +text;
    			if (!newTxt.matches("^[-+]?[0-9]*")) return false;
    			break;
    		case POSITIVE_INTEGER_ONLY:
    			if (!text.matches("[0-9]")) return false;
    			break;
    		case NUMERIC:
    			newTxt = (getText()!=null ? getText() : "") +text;
    			if (!newTxt.matches("^[-+]?[0-9]*\\.?[0-9]*([eE][-+]?[0-9]+)?$")) return false;
    			break;
    		case POSITIVE_NUMERIC_ONLY:
    			newTxt = (getText()!=null ? getText() : "") +text;
    			if (!newTxt.matches("[0-9]*\\.?[0-9]*([eE][-+]?[0-9]+)?$")) return false;
    			break;
    		case SIZE_RESTRICT:
    			if(getSize()>0){
    				if (getText().length()> (getSize()-1) ) return false;
    			}
    			break;
    	}
    	
    	return true;
    }
	
    
	public TextFieldType getType() {
		return type.get();
	}

	public SimpleObjectProperty<TextFieldType> typeProperty() {
		return type;
	}

	public void setType(TextFieldType type) {
		this.type.set(type);
	}
	
	public int getSize() {
		return size.get();
	}

	public SimpleIntegerProperty sizeProperty() {
		return size;
	}

	public void setSize(int size) {
		this.size.set(size);
	}
}
