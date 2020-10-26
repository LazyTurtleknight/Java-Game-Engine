package com.danke.engine;

import java.awt.image.DataBufferInt;

import com.danke.engine.gfx.Sprite;
import com.danke.engine.gfx.SpriteAnimation;

public class Renderer{

    private int pixelWidth, pixelHeight;
    private int[] pixels;

    public Renderer(GameContainer gamecon){

        pixelWidth = gamecon.getWidth();
        pixelHeight = gamecon.getHeight();

        //Window
        //bufferedImage
        //WritableRaster -> extends Raster and provides pixel writing
        //databuffer of raster
        //int[] reference to buffered pix array
        pixels =  ((DataBufferInt)gamecon.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear(){

        for(int i = 0; i < pixels.length;i++){

            pixels[i] = 0;
        }
    }
    
    public void setPixel(int x, int y, int value) {
    	
    	// add invisible color ? value == 0x.....
    	//if coordinates are out of bounds
    	if(x < 0 || x >= pixelWidth || y < 0 ||  y >= pixelHeight) {
    		
    		return;
    	}
    	pixels[pixelWidth * y + x] =  value;
    }
    
    public void drawSprite(Sprite image, int offsetX, int offsetY){
    	
    	//if the image would be outside of the window
    	if(offsetX < -image.getWidth() || offsetY < -image.getHeight() || offsetX > pixelWidth || offsetY > pixelHeight) {
    		
    		return;
    	}
    	
    	//if only parts of the image are outside of the window 
    	//calculate new width and height to skip unnecessary setPixel(...) function calls
    	//TODO: more clarification of how this works ?
    	int newX = 0;
    	int newY = 0;
    	int newWidth = image.getWidth();
    	int newHeight = image.getHeight();
    	
    	if(offsetX < 0) {
    		
    		newX -= offsetX;
    	}
    	
    	if(offsetY < 0) {
    		
    		newY -= offsetY;
    	}
    	
    	if(newWidth + offsetX >= pixelWidth){
    		
    		newWidth -= newWidth + offsetX - pixelWidth;
    	}
    	
    	if(newHeight + offsetY >= pixelHeight){
    		
    		newHeight -= newHeight + offsetY - pixelHeight;
    	}
    	
    	for(int y = newY; y < newHeight; y++) {
    		
    		for(int x  = newX; x < newWidth; x++) {
    			
    			setPixel(x + offsetX, y + offsetY, image.getPixels()[x + y *image.getWidth()]);
    		}
    	}
    }
    
    //parameter image is a sprite animation containing multiple sprites in a grid
    //parameter spriteX and spriteY specify the coordinates of the sprite in the grid
    public void drawSpriteAnimation(SpriteAnimation image, int offsetX, int offsetY, int spriteX, int spriteY) {
    	//if the image would be outside of the window
    	if(offsetX < -image.getSpriteWidth() || offsetY < -image.getSpriteHeight() || offsetX > pixelWidth || offsetY > pixelHeight) {
    		
    		return;
    	}
    	
    	//if only parts of the image are outside of the window 
    	//calculate new width and height to skip unnecessary setPixel(...) function calls
    	//TODO: more clarification of how this works ?
    	int newX = 0;
    	int newY = 0;
    	int newWidth = image.getSpriteWidth();
    	int newHeight = image.getSpriteHeight();
    	
    	if(offsetX < 0) {
    		
    		newX -= offsetX;
    	}
    	
    	if(offsetY < 0) {
    		
    		newY -= offsetY;
    	}
    	
    	if(newWidth + offsetX >= pixelWidth){
    		
    		newWidth -= newWidth + offsetX - pixelWidth;
    	}
    	
    	if(newHeight + offsetY >= pixelHeight){
    		
    		newHeight -= newHeight + offsetY - pixelHeight;
    	}
    	
    	for(int y = newY; y < newHeight; y++) {
    		
    		for(int x  = newX; x < newWidth; x++) {
    			
    			
    			setPixel(x + offsetX, y + offsetY, image.getPixels()[x + spriteX * image.getSpriteWidth() +
    			                                                     (y + spriteY * image.getSpriteHeight())
    			                                                      *image.getWidth()]);
    		}
    	}	
    }
}