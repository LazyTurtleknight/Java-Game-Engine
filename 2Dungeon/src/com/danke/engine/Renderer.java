package com.danke.engine;

import java.awt.image.DataBufferInt;
import java.util.function.BiFunction;
import java.util.function.Function;

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
    //parameter spriteID is the ID of the sprite in the sprite array
    public void drawSpriteAnimation(SpriteAnimation image, int offsetX, int offsetY, int spriteID) {
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
    	
    	Sprite sprite = image.getLoadedSprites()[spriteID];
    	
    	for(int y = newY; y < newHeight; y++) {
    		
    		for(int x  = newX; x < newWidth; x++) {
    			
    			
    			setPixel(x + offsetX, y + offsetY, sprite.getPixels()[x + y *sprite.getWidth()]);
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
    
    //draw a simple rectangle
    public void drawRect(int offsetX, int offsetY, int width, int height, int color) {
  
    	//draws top and bottom
    	for(int x = 0; x<width;x++) {
    		setPixel(offsetX +x, offsetY, color);
    		setPixel(offsetX +x, offsetY+height-1, color);
    	}
    	
    	//draws left and right
    	for(int y = 0; y<height;y++) {
    		setPixel(offsetX , y+ offsetY, color);
    		setPixel(offsetX+width-1 , y+ offsetY, color);
    	}
    }

  
    //only draws vertical or horizontal lines
    public void drawStraightLine(int offsetX, int offsetY, int endpointX, int endpointY, int color) {
    	
    	//check if the points lie on a vertical or horizontal line
    	if(endpointX != offsetX && endpointY != offsetY) {
    		return;
    	}
    	
    	int distance = 0;

    	if(endpointX > offsetX || endpointY > offsetY) {
    		//one of the summands will be zero
    		distance = endpointX-offsetX + endpointY-offsetY;
    		//x is only incremented if endpointX and offsetX are different from each other, work for y respectively
    		//starts at offset and draws point toward endpoint
        	for(int x = 0,y = 0; x < distance && y < distance;x+=(endpointX-offsetX)/distance,y+=(endpointY-offsetY)/distance) {
        		setPixel(offsetX+x, offsetY+y, color);
        	}
    	}else{
    		distance = offsetX-endpointX + offsetY-endpointY;
    		//starts at endpoint and draws point toward offset
        	for(int x = 0, y = 0; x < distance && y < distance;x+=(offsetX-endpointX)/distance,y+=(offsetY-endpointY)/distance) {
        		setPixel(offsetX-x, offsetY-y, color);
        	}	
    	}
    }
    
    
    //draws a rectangle with chosen borderline
    public void drawRectwithBorderline(int offsetX, int offsetY, int width, int height, int borderline, int color) {
    	
    	//starts by drawing a rectangle with borderline 1 and then continues by drawing smaller rectangle in it
    	for(int borderwidth = 0; borderwidth < borderline; borderwidth++) {
    		drawRect(offsetX, offsetY, width, height, color);
    		//values for the next rectangle
    		offsetX++;
    		offsetY++;
    		width-=2;
    		height-=2;
    	}
    	
    }
    
    //yes, this is probably superfluous but it was fun to code : D
    //...and maybe in a distance piece of code this becomes useful
    //TODO: fix pls
    public void drawRectAsSpiral(int offsetX, int offsetY, int width, int height, int borderline, int color) {
    
    	// borderline is not valid
    	if(borderline<1 || borderline>=height/2+1) {
    		return;
    	}
    	
    	int x=0;
    	int y=0;
    	
    	Function<Integer,Integer> lambdax = parameter -> {return parameter+1;};
    	Function<Integer,Integer> lambday = parameter -> {return parameter+1;};
    	
    	BiFunction<Integer,Integer,Boolean> condx = (parameter1,parameter2) -> {return parameter1<parameter2-1;};
    	BiFunction<Integer,Integer,Boolean> condy = (parameter1,parameter2) -> {return parameter1<parameter2-1;};   	
    	
    	int boarderwidth=0;
    	
    	for(;condy.apply(y,height);y = lambday.apply(y)) {
        	
        	setPixel(offsetX+x,offsetY+y, color);
        	
    		if(x==width-1 && lambday.apply(y)==height-1){
    			
        		lambday = parameter -> {return parameter-1;};
        		condy = (parameter1,parameter2) -> {return parameter1>0;};
        		
    			lambdax = parameter -> {return parameter-1;};
    			condx = (parameter1,parameter2) -> {return parameter1>0;};
    		}
        	
    		
        	for(;condx.apply(x,width);x = lambdax.apply(x)) {
    			
        		setPixel(offsetX+x,offsetY+y, color);
        		
        	}
    		
        	if(x==0 && lambday.apply(y)==0) {
        		if(boarderwidth==borderline) {
        			return;
        		}
            	//condx = (parameter1,parameter2) -> {return parameter1<=parameter2-1;};
            	//condy = (parameter1,parameter2) -> {return parameter1<=parameter2-1;}; 
    			offsetX++;
    			offsetY++;
    			x-=2;
    			y-=2;
    			width-=2;
    			height-=2;
    			boarderwidth++;
        	}
    	}
    }
}