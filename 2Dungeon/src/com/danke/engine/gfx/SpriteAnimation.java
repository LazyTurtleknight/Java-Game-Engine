package com.danke.engine.gfx;

public class SpriteAnimation extends Sprite {
	
	//may be removed?
	private int numberOfFrame;
	
	//width and height of the sprites froming the animation
	private int spriteWidth, spriteHeight;
	
	//loads an image of multiple sprite that form the animation
	public SpriteAnimation(String path,int spriteW, int spriteH) {
		super(path);
		spriteWidth = spriteW;
		spriteHeight = spriteH;

		
	}
	
	//getter and setter
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
	
}
