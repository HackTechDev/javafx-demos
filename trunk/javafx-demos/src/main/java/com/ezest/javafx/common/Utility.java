package com.ezest.javafx.common;


public class Utility {
	
	public static String getImageUrl(String imageName){
		return Utility.class.getResource("/images/"+imageName).toExternalForm();
	}
	
}
