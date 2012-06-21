package com.sai.javafx.pixelgraphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Canvas2 extends ImageView
{
   private int width;
   private int height;
   private int stride;
   private byte[] im;
   private com.sun.prism.Image pimage;

   public Canvas2(int width, int height)
   {
      this.width = width;
      this.height = height;
      this.stride = width*3;
      im = new byte[stride*height];
   }
   public int getHeight()
   {
      return height;
   }
   public int getWidth()
   {
      return width;
   }
   public void repaint()
   {
      pimage = com.sun.prism.Image.fromByteRgbData(im, width, height);
      setImage(Image.impl_fromPlatformImage(pimage));
   }
   public void setPixel(int x, int y, int c)
   {
      int index = y*stride+x+x+x;
      im[index++] = (byte) (c >> 16);
      im[index++] = (byte) (c >> 8);
      im[index] = (byte) c;
   }
   public void setRect(int x1, int y1, int x2, int y2, int[] colors)
   {
      int colorIndex = 0;
      int pixelIndex = y1*stride+x1+x1+x1;
      int deltaX = (x2+x2+x2-x1-x1-x1+3);
      for (int y = y1; y <= y2; y++)
      {
         for (int x = x1; x <= x2; x++)
         {
            im[pixelIndex++] = (byte) (colors[colorIndex] >> 16);
            im[pixelIndex++] = (byte) (colors[colorIndex] >> 8);
            im[pixelIndex++] = (byte) colors[colorIndex++];
         }
         pixelIndex += stride-deltaX;
      }
   }
}
