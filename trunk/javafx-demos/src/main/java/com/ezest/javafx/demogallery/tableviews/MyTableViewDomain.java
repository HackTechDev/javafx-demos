package com.ezest.javafx.demogallery.tableviews;

import javafx.beans.property.SimpleStringProperty;

public class MyTableViewDomain{
	private SimpleStringProperty name = new SimpleStringProperty();
	private SimpleStringProperty description = new SimpleStringProperty();
	
	public MyTableViewDomain(String name, String desc){
		this.name.set(name);
		this.description.set(desc);
		
	}
	public MyTableViewDomain(String desc){
		this.description.set(desc);
	}
	public MyTableViewDomain(){}
	
	public String getDescription() {
        return description.get();
    }
    
    public SimpleStringProperty descriptionProperty(){
    	return description;
    }
    
    public String getName() {
        return name.get();
    }
    
    public SimpleStringProperty nameProperty(){
    	return name;
    }

}
