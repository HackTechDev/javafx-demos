package com.ezest.javafx.common;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class FXUtil {

	public static Rectangle2D getScreenBounds(){
		Screen screen = Screen.getPrimary();
	    Rectangle2D bounds = screen.getVisualBounds();
	    return bounds;
	}
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("A");
		set.add("B");
		System.out.println(set);
		
		Set<Obj> set1 = new HashSet<Obj>();
		set1.add(new Obj("Sai"));
		set1.add(new Obj("Paramed"));
		System.out.println(set1);
		
		
	}
}

class Obj{
	private String name;
	private Long id;
	
	public Obj(String s){
		this.name = s;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Obj other = (Obj) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
