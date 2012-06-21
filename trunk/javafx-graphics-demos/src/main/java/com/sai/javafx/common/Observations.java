package com.sai.javafx.common;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class Observations {

	public void packmanArc(){
		Arc arc = new Arc();
		arc.setCenterX(50);
		arc.setCenterY(50);
		arc.setRadiusX(25);
		arc.setRadiusY(25);
		arc.setStartAngle(45);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.BLACK);
		arc.setLength(270);
	}
}
