package com.sai.javafx.pixelgraphics;

/**
 * http://www.javaworld.com/community/node/8373
 * @author Sai.Dandem
 *
 */

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrismPlasma extends Application
{
	private int[] palette;
	private byte[][] plasma;
	private int[] colors;

	@Override
	public void start(final Stage primaryStage)
	{
		primaryStage.setTitle("Animated Plasma");
		primaryStage.setWidth(600);
		primaryStage.setHeight(600);

		
		Text text = new Text();
		text.setText("Animated Plasma");
		text.setFill(Color.YELLOW);
		text.setFont(new Font("Arial BOLD", 24.0));
		text.setEffect(new DropShadow());
		text.xProperty().bind(primaryStage.widthProperty()
				.subtract(text.layoutBoundsProperty().getValue().getWidth())
				.divide(2));
		text.setY(30.0);
		text.setTextOrigin(VPos.TOP);
		
		final Canvas2 canvas = new Canvas2(400, 400);
		canvas.setX(100);
		canvas.setY(100);
		
		final Group group = new Group();
		group.getChildren().addAll(text, canvas);
		group.setEffect(ReflectionBuilder.create().input(new DropShadow()).build());

		LinearGradient lg = new LinearGradient(0.0, 0.0, 0.0, 1.0, true,
				CycleMethod.NO_CYCLE,
				new Stop(0, Color.GOLD),
				new Stop(1.0, Color.YELLOWGREEN));
		Scene scene = new Scene(group, lg);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		setup(canvas);

		final PauseTransition pt = new PauseTransition(Duration.millis(30));
		pt.setCycleCount(1);
		pt.setOnFinished(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				update(canvas);
				pt.play();
			}
		});
		pt.play();
	}
	
	
	void setup(Canvas2 canvas)
	{
		palette = new int[256];
		for (int i = 0; i < palette.length; i++)
		{
			Color color = Color.hsb(i/255.0*360, 1.0, 1.0);
			int red = (int) (color.getRed()*255);
			int grn = (int) (color.getGreen()*255);
			int blu = (int) (color.getBlue()*255);
			palette[i] = (red << 16)|(grn << 8)|blu;
		}

		plasma = new byte[canvas.getHeight()][];
		for (int i = 0; i < plasma.length; i++)
			plasma [i] = new byte[canvas.getWidth()];

		colors = new int[canvas.getWidth()*canvas.getHeight()];
	}
	void update(Canvas2 canvas)
	{
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		for (int row = 0; row < height; row++)
			for (int col = 0; col < width; col++)
				plasma[row][col] =
					(byte) ((128.0+128.0*Math.cos(row/8.0)+
							128.0+128.0*Math.cos(col/8.0))/2);

		int shift = (int) System.currentTimeMillis()/5;
		int base = 0;
		for (int row = 0; row < height; row++)
		{
			for (int col = 0; col < width; col++)
				colors[base+col] = palette[(plasma[row][col]+shift)&255];
			base += width;
		}
		canvas.setRect(0, 0, width-1, height-1, colors);
		canvas.repaint();
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}
