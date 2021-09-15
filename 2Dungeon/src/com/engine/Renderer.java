package com.engine;

import java.awt.image.DataBufferInt;

import java.util.function.BiFunction;
import java.util.function.Function;

import com.engine.gfx.Font;
import com.engine.gfx.Sprite;
import com.engine.gfx.SpriteAnimation;

/*
 * Class that defines how object are rendered.
 */

public class Renderer{

	// width and height of window from game container
    private int pixelWidth, pixelHeight;
    private int[] pixels;
    
    private Font font;
    
    //used to buffer alpha values
    private int[] zbuffer;

    //TODO: what does this do 
    private int zDepth = 0;
    
    public Renderer(GameContainer gamecon){

        pixelWidth = gamecon.getWidth();
        pixelHeight = gamecon.getHeight();

        /*
         *Instance type after each get function:
         *Window
         *BufferedImage
         *WritableRaster -> extends Raster and provides pixel writing
         *databuffer of raster
         *int[] reference to buffered pix array
         */
        pixels =  ((DataBufferInt)gamecon.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zbuffer = new int[pixels.length];
        // default font
        font = new Font("/images/Fonts/FreeSans.png");
    }

    public void clear(){

        for(int i = 0; i < pixels.length;i++){

            pixels[i] = 0;
            zbuffer[i] = 0; 
        }
    }
    
    /*
     * int x: x coordinate
     * int y: y coordinate
     * int value: (4 byte) int ARGB color where 1. byte is alpha, 2.byte red, 3. byte green, 4. byte blue
     */
    public void setPixel(int x, int y, int value) {
    	
    	//value gets shifted to the right by 24 bits
    	//preserving the sign
    	//result is positive int of the 8 most significant bits in value (before the shift)
    	int alpha = (value >> 24) & 0xff;
    	
    	// simple skip condition when we do not need to set a pixel's value
    	if(x < 0 || x >= pixelWidth || y < 0 ||  y >= pixelHeight || alpha == 0) {
    		
    		return;
    	}
    	
    	//TODO: clarification
    	if(zbuffer[pixelWidth * y + x] > zDepth) {
    		return;
    	}
    	
    	if(alpha == 255) {
    		pixels[pixelWidth * y + x] =  value;
    	}else {
    		//calculate new color
    		int pixelColor = pixels[pixelWidth * y + x];
    		
    		// newcolor = aplha * A + (1-alpha) * B
    		// newcolor = aplha * A - alpha * B + B
    		// newcolor = B - alpha * (B-A)
    		int newRed   = ((pixelColor >> 16) & 0xff) - (int) ((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255f));
    		int newGreen = ((pixelColor >>  8) & 0xff) - (int) ((((pixelColor >>  8) & 0xff) - ((value >>  8) & 0xff)) * (alpha / 255f));
    		int newBlue  =  (pixelColor        & 0xff) - (int) ((( pixelColor        & 0xff) - ( value        & 0xff)) * (alpha / 255f));
    		
    		pixels[pixelWidth * y + x] = (255 << 24| newRed << 16 | newGreen << 8 | newBlue);
    		
    		
    	}
    	
    }
    
    public void drawSprite(Sprite image, int offsetX, int offsetY){
    	
    	//if the image would be outside of the window
    	if(offsetX < -image.getWidth() || offsetY < -image.getHeight() || offsetX > pixelWidth || offsetY > pixelHeight) {
    		
    		return;
    	}
    	
    	//if only parts of the image are outside of the window 
    	//calculate new width and height to skip unnecessary setPixel(...) function calls
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
    
    //
    public void drawText(String text, int offsetX, int offsetY, int color) {
    	
    	int offset = 0;
    	
    	for (int i = 0; i < text.length(); i++) {
    		
    		int unicode = text.codePointAt(i);
    		
    		for(int y = 0; y < font.getFontImage().getHeight(); y++) {
    		
        		for(int x = 0; x < font.getWidths()[unicode]; x++) {
        			
        			// character fonts are rectangular shaped parts of the fontImage with every pixel being transparent except for
        			// the pixels forming the character (black) and top left (pink) and top right (pink) corner
        			// this checks if a pixel of a character is black and if so sets it
        			if(font.getFontImage().getPixels()[x +  font.getOffsets()[unicode] + y * font.getFontImage().getWidth()] == 0xffffffff) {
        				
        				this.setPixel(offsetX + x + offset, offsetY + y, color);
        			}
        		}
    		}
    		
    		offset += font.getWidths()[unicode];
    	}
    }
    
    /*
     * SpriteAnimation image: sprite animation containing multiple sprites in a grid
     * int spriteID: ID of the sprite in the sprite array
     */
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
    
    /*
     * SpriteAnimation image: sprite animation containing multiple sprites in a grid
     * int spriteX: x coordinates of the sprite in the grid
     * int spriteY: y coordinates of the sprite in the grid
     */
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
    
    //draw a rectangle
    //TODO: add check if rectangle is in window
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

  
    // draws vertical or horizontal lines
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

    // getter and setter
	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
