package com.danke.engine.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet extends Sprite {
	
	//width and height of the individual sprites in the sheet
	//TODO: spriteSheets composed of sprites with different sizes ?
	private int spriteWidth, spriteHeight;	

	private Sprite[] loadedSprites = null;
	
	public SpriteSheet(String path, int spriteWidth, int spriteHeight) {
		super(path);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		
	}

	public SpriteSheet(Sprite sprite, int spriteWidth, int spriteHeight) {
		super(sprite);
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
			
	}
	
	public void loadSprites() {

		loadedSprites = new Sprite[(this.getWidth()/spriteWidth)*(this.getHeight()/spriteHeight)];
		
		int spriteID = 0;

		int[] spritePixels = new int[spriteWidth*spriteHeight];
		
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, this.getWidth(), this.getHeight(), this.getPixels(), 0, this.getWidth());
		
		for(int y = 0; y<this.getWidth(); y += spriteHeight) {
			for(int x = 0; x<this.getHeight(); x += spriteWidth) {

				spritePixels = image.getRGB(x, y, spriteWidth, spriteHeight, null, 0, spriteWidth);
				loadedSprites[spriteID] = new Sprite(spritePixels,spriteWidth,spriteHeight);
				spriteID++;
				
			}
		}	
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public void setSpriteWidth(int spriteWidth) {
		this.spriteWidth = spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public void setSpriteHeight(int spriteHeight) {
		this.spriteHeight = spriteHeight;
	}

	public Sprite[] getLoadedSprites() {
		return loadedSprites;
	}

	public void setLoadedSprites(Sprite[] loadedSprites) {
		this.loadedSprites = loadedSprites;
	}
}
