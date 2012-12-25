package com.sai.javafx.pixelgraphics;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ColorComponentsOfImage extends Application {
    
   @Override
   public void start(Stage primaryStage) {
        
       Image image = new Image(ColorComponentsOfImage.class.getResource("/images/duke.png").toString());
       ImageView imageView = new ImageView();
       imageView.setImage(image);
        
       PixelReader pixelReader = image.getPixelReader();
        
       int width = (int)image.getWidth();
       int height = (int)image.getHeight();
        
       //Copy from source to destination pixel by pixel
       WritableImage writableImage
               = new WritableImage(width, height);
       PixelWriter pixelWriter = writableImage.getPixelWriter();
        
       WritableImage writableImageRed
               = new WritableImage(width, height);
       PixelWriter pixelWriterRed = writableImageRed.getPixelWriter();
        
       WritableImage writableImageGreen
               = new WritableImage(width, height);
       PixelWriter pixelWriterGreen = writableImageGreen.getPixelWriter();
        
       WritableImage writableImageBlue
               = new WritableImage(width, height);
       PixelWriter pixelWriterBlue = writableImageBlue.getPixelWriter();
        
       WritableImage writableImageBrightness
               = new WritableImage(width, height);
       PixelWriter pixelWriterBrightness = writableImageBrightness.getPixelWriter();
        
       WritableImage writableImageHue
               = new WritableImage(width, height);
       PixelWriter pixelWriterHue = writableImageHue.getPixelWriter();
        
       WritableImage writableImageSaturation
               = new WritableImage(width, height);
       PixelWriter pixelWriterSaturation = writableImageSaturation.getPixelWriter();
        
       WritableImage writableImageOpacity
               = new WritableImage(width, height);
       PixelWriter pixelWriterOpacity = writableImageOpacity.getPixelWriter();
        
       for (int y = 0; y < height; y++){
           for (int x = 0; x < width; x++){
               Color color = pixelReader.getColor(x, y);
               pixelWriter.setColor(x, y, color);
                
               double red = color.getRed();
               double green = color.getGreen();
               double blue = color.getBlue();
               double brightness = color.getBrightness();
               double hue = color.getHue()/360.0;  //getHue() return 0.0-360.0
               double saturation = color.getSaturation();
               double opacity = color.getOpacity();
                
               pixelWriterRed.setColor(x, y, new Color(red, 0.0, 0.0, 1.0));
               pixelWriterGreen.setColor(x, y, new Color(0.0, green, 0.0, 1.0));
               pixelWriterBlue.setColor(x, y, new Color(0.0, 0.0, blue, 1.0));
                
               pixelWriterBrightness.setColor(x, y, new Color(brightness, brightness, brightness, 1.0));
               pixelWriterHue.setColor(x, y, new Color(hue, hue, hue, 1.0));
               pixelWriterSaturation.setColor(x, y, new Color(saturation, saturation, saturation, 1.0));
               pixelWriterOpacity.setColor(x, y, new Color(opacity, opacity, opacity, 1.0));
           }
       }
        
       ImageView destImageView = new ImageView();
       destImageView.setImage(writableImage);
       ImageView imageViewRed = new ImageView();
       imageViewRed.setImage(writableImageRed);
       ImageView imageViewGreen = new ImageView();
       imageViewGreen.setImage(writableImageGreen);
       ImageView imageViewBlue = new ImageView();
       imageViewBlue.setImage(writableImageBlue);
        
       ImageView imageViewBrightness = new ImageView();
       imageViewBrightness.setImage(writableImageBrightness);
       ImageView imageViewHue = new ImageView();
       imageViewHue.setImage(writableImageHue);
       ImageView imageViewSaturation = new ImageView();
       imageViewSaturation.setImage(writableImageSaturation);
       ImageView imageViewOpacity = new ImageView();
       imageViewOpacity.setImage(writableImageOpacity);
        
       HBox hBox = new HBox();
       hBox.getChildren().addAll(imageView, destImageView,
               imageViewRed, imageViewGreen, imageViewBlue,
               imageViewBrightness, imageViewHue, imageViewSaturation, imageViewOpacity);

       StackPane root = new StackPane();
       root.getChildren().add(hBox);
       Scene scene = new Scene(root, 400, 100);
       primaryStage.setTitle("java-buddy.blogspot.com");
       primaryStage.setScene(scene);
       primaryStage.show();
   }

   public static void main(String[] args) {
       launch(args);
   }
}
