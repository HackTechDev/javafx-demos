/*
 *  Copyright (c) 2012 Michael Zucchi
 *
 *  This programme is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This programme is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this programme.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sai.javafx.tiledslideshow;

import java.io.File;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author notzed
 */
public class FXSlideZ extends Application {

	double rate = 1;
	StackPane root;
	Node current;
	Node next;
	//
	double width = 800;
	double height = 600;
	ImageLoader loader;

	@Override
	public void start(Stage primaryStage) {
		if (false) {
			// TODO: if not an applet, run this, once i've worked out how to
			DirectoryChooser fc = new DirectoryChooser();
			fc.setTitle("Choose Photo Location");
			File file = fc.showDialog(null);
			if (file == null) {
				Platform.exit();
				return;
			}
			String path = file.getAbsolutePath();
			// Start worker thread, and kick off first fade in.
			loader = new DirectoryLoader(path);
			loader.start();
		} else {
			loader = new ResourceLoader();
			loader.start();
		}

		root = new StackPane();
		root.setStyle("-fx-background-color: #000000;");

		Scene scene = new Scene(root, width, height);

		primaryStage.setTitle("Photos");
		primaryStage.setScene(scene);
		//primaryStage.setFullScreen(true);
		primaryStage.show();

		//width = 0;//(int) scene.getWidth();
		//height = 0;//(int) scene.getHeight();


		Image image = loader.getNextImage();

		if (image != null)
			startImage(image);
	}
	Interpolator dropin = Interpolator.SPLINE(0, 0.5, 0.2, 1);
	Interpolator bangout = Interpolator.SPLINE(1, 0, 1, 0);
	Random rand = new Random();
	Random brand = new Random();

	Node startImageBlocks(Image image) {
		// break image into blocks and animate them separately
		double w = image.getWidth();
		double h = image.getHeight();
		int tilesx = 800 / 40;//16;
		int tilesy = 600 / 40;//12;

		Duration time = Duration.seconds(1);

		int delaytype = rand.nextInt(9);
		int transtype = rand.nextInt(7);
		int rotatetype = rand.nextInt(4);
		int scaletype = rand.nextInt(3);

		ParallelTransition pt = new ParallelTransition();
		Pane g = new Pane();
		ObservableList<Node> childs = g.getChildren();
		for (int y = 0; y < tilesy; y++) {
			for (int x = 0; x < tilesx; x++) {
				ImageView iv = new ImageView(image);
				iv.setViewport(new Rectangle2D(x * w / tilesx, y * h / tilesy, w / tilesx, h / tilesy));
				iv.relocate(x * w / tilesx, y * h / tilesy);
				//iv.setFitWidth(w/tilesx);
				//iv.setFitHeight(h/tilesy);
				childs.add(iv);

				Duration delay = Duration.ZERO;

				switch (delaytype) {
					case 0:
						break;
					case 1: // cascade down/right
						delay = Duration.millis(20 * (x + y * 4));
						break;
					case 2: {// zigzag down
						int f = x;
						if ((y & 1) == 1)
							f = tilesx - x;
						delay = Duration.millis(2 * (f + y * tilesx));
						break;
					}
					case 3: { // grow out from centre
						int xx = x - tilesx / 2;
						int yy = y - tilesy / 2;
						double d = Math.sqrt(xx * xx + yy * yy);
						delay = Duration.millis(d * 150);
						break;
					}
					case 4: { // grow in to centre
						int xx = x - tilesx / 2;
						int yy = y - tilesy / 2;
						double d = Math.sqrt(tilesx * tilesx / 4 + tilesy * tilesy / 4) - Math.sqrt(xx * xx + yy * yy);
						delay = Duration.millis(d * 150);
						break;
					}
					case 5: // sweep right
						delay = Duration.millis(40 * x);
						break;
					case 6: // sweep down
						delay = Duration.millis(40 * y);
						break;
					case 7: // sweep left
						delay = Duration.millis(40 * (tilesx - x - 1));
						break;
					case 8: // sweep up
						delay = Duration.millis(40 * (tilesy - y - 1));
						break;
				}

				switch (transtype) {
					case 1: { // from left
						TranslateTransition t = new TranslateTransition(time, iv);
						iv.setTranslateX(-width);
						t.setFromX(-width);
						t.setToX(0);
						//t.setInterpolator(Interpolator.EASE_OUT);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
					case 2: { // from right
						TranslateTransition t = new TranslateTransition(time, iv);
						iv.setTranslateX(width);
						t.setFromX(width);
						t.setToX(0);
						//t.setInterpolator(Interpolator.EASE_OUT);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
					case 3: { // from top
						TranslateTransition t = new TranslateTransition(time, iv);
						iv.setTranslateY(-height);
						t.setFromY(-height);
						t.setToY(0);
						//t.setInterpolator(Interpolator.EASE_OUT);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
					case 4: { // from bottom
						TranslateTransition t = new TranslateTransition(time, iv);
						iv.setTranslateY(height);
						t.setFromY(height);
						t.setToY(0);
						//t.setInterpolator(Interpolator.EASE_OUT);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
					case 5: { // random
						TranslateTransition t = new TranslateTransition(time, iv);
						double fx = rand.nextDouble() * w * 2 - w;
						double fy = rand.nextDouble() * w * 2 - w;

						iv.setTranslateX(fx);
						iv.setTranslateY(fx);
						t.setFromX(fx);
						t.setFromY(fy);
						t.setToX(0);
						t.setToY(0);
						//t.setInterpolator(Interpolator.EASE_OUT);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
					case 6: { // from centre
						TranslateTransition t = new TranslateTransition(time, iv);
						iv.setTranslateX((tilesx / 2 - x) * w / tilesx);
						iv.setTranslateY((tilesy / 2 - y) * h / tilesy);
						t.setFromX((tilesx / 2 - x) * w / tilesx);
						t.setFromY((tilesy / 2 - y) * w / tilesx);
						t.setToX(0);
						t.setToY(0);
						t.setInterpolator(dropin);
						t.setDelay(delay);
						pt.getChildren().add(t);
						break;
					}
				}

				switch (rotatetype) {
					case 1:
					case 2: {
						RotateTransition rt = new RotateTransition(time, iv);
						double angle;

						angle = 360 * 2;
						if (rotatetype == 2)
							angle = -angle;

						rt.setFromAngle(angle);
						iv.setRotate(angle);
						rt.setToAngle(0);
						rt.setDelay(delay);
						pt.getChildren().add(rt);
						break;
					}
					case 3: {
						RotateTransition rt = new RotateTransition(time, iv);
						double angle;

						angle = rand.nextDouble() * 360 * 2 - 360 * 1;
						rt.setFromAngle(angle);
						iv.setRotate(angle);
						rt.setToAngle(0);
						rt.setDelay(delay);
						pt.getChildren().add(rt);
						break;
					}
				}

				switch (scaletype) {
					case 1:
					case 2: {
						ScaleTransition st = new ScaleTransition(time, iv);
						st.setInterpolator(dropin);
						if (scaletype == 1) {
							st.setFromX(4);
							st.setFromY(4);
						} else {
							st.setFromX(0.1);
							st.setFromY(0.1);
						}
						st.setToX(1);
						st.setToY(1);
						st.setDelay(delay);
						pt.getChildren().add(st);
						break;
					}
				}

				iv.setOpacity(0);
				FadeTransition ft = new FadeTransition(Duration.seconds(0.3), iv);
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setDelay(delay);
				pt.getChildren().add(ft);

			}
		}
		pt.play();
		pt.setRate(rate);

		return g;
	}
	Canvas mask;

	class MaskTransition extends Transition {

		Canvas mask;

		public MaskTransition(Duration dur, Canvas mask) {
			this.mask = mask;

			setCycleDuration(dur);
		}

		@Override
		protected void interpolate(double d) {
			// I dunno, draw some shape which closes on d
			// Do the arc thing to start with
			GraphicsContext gg = mask.getGraphicsContext2D();
			gg.clearRect(0, 0, width, height);
			gg.setFill(Color.WHITE);

			double v = width > height ? width / 2 : height / 2;

			double x = width / 2 - 2 * v;
			double y = height / 2 - 2 * v;
			double w = v * 4;
			double h = v * 4;

			gg.fillArc(x, y, w, h, 90, -d * 360, ArcType.ROUND);
			//gg.fillArc(0, 0, width, height, 90, -d * 360, ArcType.ROUND);
			//gg.fillRect(0, 0, width*d, height*d);
		}
	}

	class LengthTransition extends Transition {

		Arc arc;

		public LengthTransition(Duration d, Arc arc) {
			this.arc = arc;

			setCycleDuration(d);
		}

		@Override
		protected void interpolate(double d) {
			arc.setLength(d * 360);
		}
	}

	// Creates a mask
	public Node startImageWipe(Image image) {
		ImageView next = new ImageView(image);

		//if (current != null) {
		//	((StackPane) current).getChildren().remove(mask);
		//	((StackPane) current).getChildren().get(0).setBlendMode(null);
		//}

		next.setFitHeight(height);
		next.setFitHeight(width);
		next.setPreserveRatio(true);

		// Animate a wipe using a graphic blending
		if (mask == null)
			mask = new Canvas(width, height);

		mask.getGraphicsContext2D().clearRect(0, 0, width, height);
		//mask.getGraphicsContext2D().setFill(Color.WHITE);
		//mask.getGraphicsContext2D().fillRect(100, 100, width - 200, height - 200);

		StackPane group = new StackPane();

		group.getChildren().add(mask);
		group.getChildren().add(next);

		// This forces the group to be blended separately
		group.setBlendMode(BlendMode.SRC_OVER);

		next.setBlendMode(BlendMode.SRC_ATOP);

		MaskTransition mt = new MaskTransition(Duration.seconds(1), mask);
		mt.setInterpolator(Interpolator.LINEAR);
		ParallelTransition pt = new ParallelTransition(mt);

		pt.setRate(rate);

		pt.play();
		return group;
	}
	Node mask2;

	// Uses an existing object which is scaled to fill, e.g. text, polygon
	public Node startImageWipe2(Image image) {
		ImageView next = new ImageView(image);

		//if (current != null) {
		//	((StackPane) current).getChildren().remove(mask2);
		//	((StackPane) current).getChildren().get(0).setBlendMode(null);
		//}

		next.setFitHeight(height);
		next.setFitHeight(width);
		next.setPreserveRatio(true);

		//if (mask2 == null)
		{
			Text t = new Text("!Z!");
			t.setFont(Font.font("System", FontWeight.EXTRA_BOLD, 16));
			t.setTextOrigin(VPos.CENTER);
			t.setTextAlignment(TextAlignment.CENTER);
			//t.setFill(Color.BLACK);
			t.setFill(Color.WHITE);

			mask2 = t;
		}

		StackPane group = new StackPane();

		group.getChildren().add(mask2);
		group.getChildren().add(next);

		// This forces the group to be blended separately
		group.setBlendMode(BlendMode.SRC_OVER);

		next.setBlendMode(BlendMode.SRC_ATOP);

		mask2.setScaleX(2);
		mask2.setScaleY(2);

		ScaleTransition st = new ScaleTransition(Duration.seconds(1), mask2);

		st.setInterpolator(bangout);
		st.setFromX(2);
		st.setFromY(2);
		st.setToX(400);
		st.setToY(400);

		RotateTransition rt = new RotateTransition(Duration.seconds(1), mask2);

		mask2.setRotate(360);

		rt.setInterpolator(Interpolator.LINEAR);
		rt.setFromAngle(360);
		rt.setToAngle(45);

		mask2.setOpacity(0);
		FadeTransition ft = new FadeTransition(Duration.seconds(0.2), mask2);
		ft.setFromValue(0);
		ft.setToValue(1);

		ParallelTransition pt = new ParallelTransition(st, rt, ft);

		pt.setRate(rate);

		pt.play();
		return group;
	}

	// Modifies geometry
	public Node startImageWipe3(Image image) {
		ImageView next = new ImageView(image);

		//if (current != null) {
		//	((Pane) current).getChildren().get(1).setBlendMode(null);
		//	((Pane) current).getChildren().remove(mask2);
		//}

		next.setFitHeight(height);
		next.setFitHeight(width);
		next.setPreserveRatio(true);

		// Animate a wipe using a graphic blending

		//if (mask2 == null) {
		{
			double r = width / 2 * Math.sqrt(2);
			Arc t = new Arc(width / 2, height / 2, r, r, -90, 0);
			t.setType(ArcType.ROUND);

			t.setFill(Color.ALICEBLUE);
			mask2 = t;
		}

		Pane group = new Pane();

		group.getChildren().add(mask2);
		group.getChildren().add(next);

		// This forces the group to be blended separately
		group.setBlendMode(BlendMode.SRC_OVER);
		next.setBlendMode(BlendMode.SRC_ATOP);

		LengthTransition lt = new LengthTransition(Duration.seconds(1), (Arc) mask2);

		lt.setInterpolator(Interpolator.LINEAR);

		mask2.setOpacity(0);
		FadeTransition ft = new FadeTransition(Duration.seconds(0.2), mask2);
		ft.setFromValue(0);
		ft.setToValue(1);

		ParallelTransition pt = new ParallelTransition(lt, ft);

		pt.setRate(rate);

		pt.play();
		return group;
	}

	public void startImage(Image image) {
		ObservableList<Node> c = root.getChildren();

		if (current != null)
			c.remove(current);

		current = next;
		next = null;

		// HACK: clean up any masked version from last time
		if (current != null && current instanceof Pane) {
			((Pane) current).getChildren().remove(mask);
			((Pane) current).getChildren().remove(mask2);
			((Pane) current).getChildren().get(0).setBlendMode(null);
		}

		// The rest are a bit boring so mostly do the blocky thing
		int r = rand.nextInt(7);

		switch (r) {
			case 0:
				next = startImageWipe3(image);
				break;
			case 1:
				next = startImageWipe2(image);
				break;
			case 2:
				next = startImageWipe(image);
				break;
			default:
				next = startImageBlocks(image);
		}
		if (false) {
			// Create fade-in for new image.
			ImageView next = new ImageView(image);

			this.next = next;
			next.setFitHeight(height);
			next.setFitHeight(width);
			next.setPreserveRatio(true);
			//next.setScaleX(3);
			//next.setScaleY(3);
		}
		next.setOpacity(1);

		c.add(next);

		Duration time = Duration.seconds(2);

		FadeTransition fadein = new FadeTransition(time, next);

		fadein.setFromValue(1);
		fadein.setToValue(1);

		FadeTransition fadeout = new FadeTransition(time, current);

		fadeout.setFromValue(1);
		fadeout.setToValue(0);

		ScaleTransition dropout = new ScaleTransition(time, current);
		dropout.setInterpolator(Interpolator.EASE_IN);
		dropout.setFromX(1);
		dropout.setFromY(1);
		dropout.setToX(0.5);
		dropout.setToY(0.5);

		//BlurTransition blurin = new BlurTransition(Duration.seconds(1), next);
		//	blurin.setInterpolator(Interpolator.EASE_OUT);

		PauseTransition delay = new PauseTransition(time);

		//SequentialTransition st = new SequentialTransition(new ParallelTransition(dropout, fadein), delay);
		//SequentialTransition st = new SequentialTransition(new ParallelTransition(fadein), delay);
		SequentialTransition st = new SequentialTransition(delay, delay);

		st.setRate(rate);

		st.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				Image image = loader.getNextImage();

				if (image != null)
					startImage(image);
			}
		});

		st.playFromStart();
	}

	@Override
	public void stop() throws Exception {
		super.stop();

		System.out.println("Cancelling");
		if (loader != null)
			loader.cancel();
	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX
	 * application. main() serves only as fallback in case the
	 * application can not be launched through deployment artifacts,
	 * e.g., in IDEs with limited FX support. NetBeans ignores main().
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
