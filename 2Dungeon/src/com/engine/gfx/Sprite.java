package com.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * Sprite class to load images from a given location.
 */
public class Sprite {

  private int width;
  private int height;
  private int[] pixels;

  //constructors

  //constructor to read from a given file path
  public Sprite(String path) {

    BufferedImage image = null;

    try {
      image = ImageIO.read(Sprite.class.getResourceAsStream(path));
    }
    catch(IOException err) {
      err.printStackTrace();
    }
    width = image.getWidth();
    height = image.getHeight();
    pixels = image.getRGB(0, 0, width, height, null, 0, width);

    image.flush();
  }

  //constructor to read from a given BufferedImage
  public Sprite(BufferedImage image) {

    width = image.getWidth();
    height = image.getHeight();
    pixels = image.getRGB(0, 0, width, height, null, 0, width);

    image.flush();
  }

  //constructor to read from a given pixel array
  public Sprite(int[] pixels, int width, int height) {

    this.width = width;
    this.height = height; 
    this.pixels = pixels;

  }

  //copy constructor
  public Sprite(Sprite sprite) {

    width = sprite.getWidth();
    height = sprite.getHeight(); 
    pixels = sprite.getPixels();

  }	

  //getter and setter
  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int[] getPixels() {
    return pixels;
  }

  public void setPixels(int[] pixels) {
    this.pixels = pixels;
  }
}
