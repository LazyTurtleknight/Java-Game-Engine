package com.engine.gfx;

/*
 * Class to handle animations. 
 */

public class SpriteAnimation extends SpriteSheet {

  private int numberOfFrames;

  //loads an image of multiple sprites that form an animation
  public SpriteAnimation(String path, int spriteWidth, int spriteHeight) {
    super(path, spriteWidth, spriteHeight);

    //check if the given width and height divide the width and height of the image
    if(this.getHeight() % spriteHeight == 0 & this.getWidth() % spriteWidth == 0) {

      this.numberOfFrames = this.getHeight()/spriteHeight * this.getWidth()/spriteWidth;
    }else {

      //TODO: implement appropriate error handling
    }


  }

  //getter and setter
  public int getNumberOfFrames() {
    return numberOfFrames;
  }

  public void setNumberOfFrames(int numberOfFrames) {
    this.numberOfFrames = numberOfFrames;
  }

}
