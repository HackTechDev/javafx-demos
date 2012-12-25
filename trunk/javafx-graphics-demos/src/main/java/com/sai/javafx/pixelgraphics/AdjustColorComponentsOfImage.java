package com.sai.javafx.pixelgraphics;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AdjustColorComponentsOfImage extends Application {
    
   PixelReader pixelReader;
   int width, height;
    
   WritableImage writableImage, writableImageRed, writableImageGreen, writableImageBlue;
   PixelWriter pixelWriter, pixelWriterRed, pixelWriterGreen, pixelWriterBlue;
   ImageView destImageView, imageViewRed, imageViewGreen, imageViewBlue;
    
   Slider sliderRed, sliderGreen, sliderBlue;
   double adjRed, adjGreen, adjBlue;
    
   @Override
   public void start(Stage primaryStage) {
        
	   Image image = new Image(AdjustColorComponentsOfImage.class.getResource("/images/duke.png").toString());
       ImageView imageView = new ImageView();
       imageView.setImage(image);
        
       pixelReader = image.getPixelReader();
       width = (int)image.getWidth();
       height = (int)image.getHeight();
        
       //Copy from source to destination pixel by pixel
       writableImage = new WritableImage(width, height);
       pixelWriter = writableImage.getPixelWriter();
        
       writableImageRed = new WritableImage(width, height);
       pixelWriterRed = writableImageRed.getPixelWriter();
        
       writableImageGreen = new WritableImage(width, height);
       pixelWriterGreen = writableImageGreen.getPixelWriter();
        
       writableImageBlue = new WritableImage(width, height);
       pixelWriterBlue = writableImageBlue.getPixelWriter();
        
       destImageView = new ImageView();
       imageViewRed = new ImageView();
       imageViewGreen = new ImageView();
       imageViewBlue = new ImageView();
        
       HBox hBox_Image = new HBox();
       hBox_Image.getChildren().addAll(imageView, destImageView);
        
       //Control box for Red
       sliderRed = SliderBuilder.create()
               .prefWidth(300)
               .min(-1)
               .max(1)
               .majorTickUnit(0.2)
               .showTickMarks(true)
               .showTickLabels(true)
               .value(0)
               .build();
       sliderRed.valueProperty().addListener(sliderChangeListener);
       HBox hBox_Red = new HBox();
       hBox_Red.getChildren().addAll(imageViewRed, sliderRed);
        
       //Control box for Green
       sliderGreen = SliderBuilder.create()
               .prefWidth(300)
               .min(-1)
               .max(1)
               .majorTickUnit(0.2)
               .showTickMarks(true)
               .showTickLabels(true)
               .value(0)
               .build();
       sliderGreen.valueProperty().addListener(sliderChangeListener);
       HBox hBox_Green = new HBox();
       hBox_Green.getChildren().addAll(imageViewGreen, sliderGreen);
        
       //Control box for Blue
       sliderBlue = SliderBuilder.create()
               .prefWidth(300)
               .min(-1)
               .max(1)
               .majorTickUnit(0.2)
               .showTickMarks(true)
               .showTickLabels(true)
               .value(0)
               .build();
       sliderBlue.valueProperty().addListener(sliderChangeListener);
       HBox hBox_Blue = new HBox();
       hBox_Blue.getChildren().addAll(imageViewBlue, sliderBlue);
        
       VBox vBox = new VBox();
       vBox.getChildren().addAll(hBox_Image,
               hBox_Red, hBox_Green, hBox_Blue);

       StackPane root = new StackPane();
       root.getChildren().add(vBox);
       Scene scene = new Scene(root, 350, 330);
       primaryStage.setTitle("java-buddy.blogspot.com");
       primaryStage.setScene(scene);
       primaryStage.show();
        
       updateImage();
   }
    
   ChangeListener<Number> sliderChangeListener
           = new ChangeListener<Number>(){

       @Override
       public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
           updateImage();
       }
   };
    
   private void updateImage(){
       adjRed = sliderRed.valueProperty().doubleValue();
       adjGreen = sliderGreen.valueProperty().doubleValue();
       adjBlue = sliderBlue.valueProperty().doubleValue();
        
       for (int y = 0; y < height; y++){
           for (int x = 0; x < width; x++){
               Color color = pixelReader.getColor(x, y);
               pixelWriter.setColor(x, y, color);
                
               double red = color.getRed() + adjRed;
               if(red > 1.0){
                   red = 1.0;
               }else if(red < 0.0){
                   red = 0.0;
               }
                
               double green = color.getGreen() + adjGreen;
               if(green > 1.0){
                   green = 1.0;
               }else if(green < 0.0){
                   green = 0.0;
               }
                
               double blue = color.getBlue() + adjBlue;
               if(blue > 1.0){
                   blue = 1.0;
               }else if(blue < 0.0){
                   blue = 0.0;
               }
                
               double opacity = color.getOpacity();
                
               pixelWriterRed.setColor(x, y, new Color(red, 0.0, 0.0, opacity));
               pixelWriterGreen.setColor(x, y, new Color(0.0, green, 0.0, opacity));
               pixelWriterBlue.setColor(x, y, new Color(0.0, 0.0, blue, opacity));
               pixelWriter.setColor(x, y, new Color(red, green, blue, opacity));

           }
       }
        
       imageViewRed.setImage(writableImageRed);
       imageViewGreen.setImage(writableImageGreen);
       imageViewBlue.setImage(writableImageBlue);
       destImageView.setImage(writableImage);
   }

   public static void main(String[] args) {
       launch(args);
   }
}
