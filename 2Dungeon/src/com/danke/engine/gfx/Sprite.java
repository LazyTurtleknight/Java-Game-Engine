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

	//getter and setter
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
}
