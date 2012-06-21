package com.ezest.javafx.common;

import java.awt.Toolkit;
import java.net.URL;

public abstract class FXTools {

	public static double getAppWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}

	public static double getAppHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}
	
	public static String getResource(String file) {
		URL resource =  FXTools.class.getResource(file);
		return resource == null ? file : resource.toExternalForm();
	}
	public static void main(String[] args) {
		String s = "abc\\n abc";
		String a = s + (new Character((char)10)).toString()+" bde";
		System.out.println(a);
		System.out.println("-----------------------------------");
		System.out.println(a.replaceAll("\\n", "*"));
		
		String d = "123\\n 321";
		System.out.println(d.indexOf("\\n")+" : "+d.length());
		while(d.indexOf("\\n")>-1){
			int index = d.indexOf("\\n");
			d = d.substring(0, index)+"*"+d.substring(index+2);
		}
		System.out.println(d);
		
	}
}
