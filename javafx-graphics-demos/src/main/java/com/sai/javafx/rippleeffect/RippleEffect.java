package com.sai.javafx.rippleeffect;

import java.net.URL;
import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.effect.DisplacementMapBuilder;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.FloatMap;
import javafx.scene.effect.Reflection;
import javafx.scene.effect.ReflectionBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RippleEffect extends Application{

	Group root = new Group();
	int width =435;
	int height = 327;
	double k = 60;
	boolean isMinus = true;
	FloatMap floatmap = new FloatMap(width, height);
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(root, 435, 600));
		stage.setTitle("Ripple Effect Demo");
		stage.show();
		
		final ImageView imageView1 = new ImageView( new Image(getResource("/images/moon1.jpg")) );
		final ImageView imageView2 = new ImageView( new Image(getResource("/images/moon1.jpg")) );
		
		//root.getChildren().add(setExample());
		root.getChildren().addAll(imageView2, imageView1);
		
		final DisplacementMap disMap = DisplacementMapBuilder.create()
										.wrap(true)
										.build();
		
		DropShadow dropShadow = DropShadowBuilder.create()
				.blurType(BlurType.THREE_PASS_BOX)
				.height(5)
				.color(Color.GRAY)
				.spread(5)
				.build();
		
		Reflection reflection = ReflectionBuilder.create()
				.input(dropShadow)
				.bottomOpacity(.8)
				.topOpacity(1.0)
				.topOffset(0)
				.fraction(1.0)
				.build();
		reflection.setInput(disMap);
		
		imageView2.setEffect(reflection);
		
		final PauseTransition pt = new PauseTransition(Duration.millis(500));
		pt.setCycleCount(1);
		pt.setOnFinished(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent ae)
			{
				setMkValue();
				update(disMap);
				pt.play();
			}
		});
		
		imageView1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
				pt.play();
			}
		});
		imageView1.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent paramT) {
				pt.pause();
			}
		});

	}

	protected void setMkValue() {
		k--;
		if(k<55){
			k=60;
		}
		
		// Logic : 1
		/*if(isMinus){
			double j = k-1;
			if(j<20){
				isMinus = false;
				k = k+1;
			}else{
				k=j;
			}
		}else{
			double j = k+1;
			if(j>60){
				isMinus = true;
				k = k-1;
			}else{
				k=j;
			}
		}*/
	}

	protected void update(DisplacementMap disMap) {
		floatmap =null;
		floatmap = new FloatMap(width,height);
		for (int i = 0; i < width; i++) {
		    double v = (Math.sin(i/k*Math.PI)-0.5)/k;
		    //double angle = Math.PI * 2.0 * (i + 0.5) / (width / 4.0);
		    //double v = Math.sin(angle);
		    for (int j = 0; j < height; j++) {
		    	floatmap.setSamples(i, j, 0.0f,(float) v);
		    }
		}
		disMap.setMapData(floatmap);
	}

	public Group setExample(){
		int w = 220;
		int h = 100;
		FloatMap map = new FloatMap();
		map.setWidth(w);
		map.setHeight(h);

		for (int i = 0; i < w; i++) {
		    double v = (Math.sin(i/50.0*Math.PI)-0.5)/40.0;
		    for (int j = 0; j < h; j++) {
		    	System.out.println("i = "+i+" , j = "+j+" , v = "+v);
			    map.setSamples(i, j, 0.0f,(float) v);
		    }
		}

		Group g = new Group();
		DisplacementMap dm = new DisplacementMap();
		dm.setMapData(map);
		
		g.setEffect(dm);
		g.setCache(true);

		Rectangle r = new Rectangle();
		r.setX(20.0);
		r.setY(20.0);
		r.setWidth(w);
		r.setHeight(h);
		r.setFill(Color.BLUE);

		g.getChildren().add(r);

		Text t = new Text();
		t.setX(40.0);
		t.setY(80.0);
		t.setText("Wavy Text");
		t.setFill(Color.YELLOW);
		t.setFont(Font.font(null, FontWeight.BOLD, 36));

		g.getChildren().add(t);
		
		return g;
	}
	
	public void distortImage(){
		Random random = new java.util.Random();
		for (int i=0;i<width;i=i+1) {
			for(int y=0;y<height;y=y+1){
				floatmap.setSample(i, y, 3, (random.nextFloat()/100.0f));
			}
		}
		for (int i=0;i<height;i++) {
			for(int y=0;y<width;y++){
				floatmap.setSample(y, i, 1, (random.nextFloat()/100.0f));
			}
		}
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	public static String getResource(String file) {
		URL resource =  RippleEffect.class.getResource(file);
		return resource == null ? file : resource.toExternalForm();
	}
}
