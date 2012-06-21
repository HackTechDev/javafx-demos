package com.sai.javafx.pixelgraphics;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Canvas1 extends Group
{
	private Line[][] pixels;

	public Canvas1(int x, int y, int width, int height)
	{
		pixels = new Line[height][];
		for (int row = 0; row < width; row++)
		{
			pixels[row] = new Line[width];
			for (int col = 0; col < height; col++)
				pixels[row][col] = new Line(x+col, y+row, x+col, y+row);
			getChildren().addAll(pixels[row]);
		}
	}
	public int getHeight()
	{
		return pixels.length;
	}
	public int getWidth()
	{
		return pixels[0].length;
	}
	public void setPixel(int x, int y, Color c)
	{
		pixels[y][x].setStroke(c);
	}
	public void setRect(int x1, int y1, int x2, int y2, int[] colors)
	{
		int index = 0;
		for (int y = y1; y <= y2; y++)
			for (int x = x1; x <= x2; x++, index++)
				pixels[y][x].setStroke(Color.rgb((colors[index]>>16)&255, 
						(colors[index]>>8)&255, 
						colors[index]&255));
	}
}
