package com.danke.engine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprite {

	private int width, height;
	private int[] pixels;
	
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
		
		//TODO: what does this do?
		image.flush();
	}
	
	public Sprite(BufferedImage image) {
		
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		//TODO: what does this do?
		image.flush();
	}
	
	//TODO: one of these two constructor is probably superfluous
	public Sprite(int[] pixels, int width, int height) {
		
		this.width = width;
		this.height = height; 
		this.pixels = pixels;
		
	}

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
}
