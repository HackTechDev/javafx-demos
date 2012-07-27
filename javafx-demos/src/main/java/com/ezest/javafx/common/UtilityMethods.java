package com.ezest.javafx.common;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;

public class UtilityMethods {
	
	public static Point2D getRelativeStartPointOfNode(Node n){
		return new Point2D(n.localToScene(n.getBoundsInLocal()).getMinX(), 
				           n.localToScene(n.getBoundsInLocal()).getMinY());
	}
	
	public static Point2D getAbsoluteStartPointOfNode(Node n){
		Parent parent = n.getParent();
        Bounds childBounds = n.getBoundsInParent();
        Bounds parentBounds = parent.localToScene(parent.getBoundsInLocal());
        double x = childBounds.getMinX() + parentBounds.getMinX() + parent.getScene().getX() + parent.getScene().getWindow().getX();
        double y = childBounds.getMaxY() + parentBounds.getMinY() + parent.getScene().getY() + parent.getScene().getWindow().getY();
        return new Point2D(x, y);
	}
	
}
