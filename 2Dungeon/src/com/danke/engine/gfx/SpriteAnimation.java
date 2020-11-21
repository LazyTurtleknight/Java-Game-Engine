package com.danke.engine.gfx;

public class SpriteAnimation extends SpriteSheet {
	
	//may not be needed ?
	private int numberOfFrames;

	//loads an image of multiple sprite that form the animation
	public SpriteAnimation(String path, int spriteWidth, int spriteHeight) {
		super(path, spriteWidth, spriteHeight);

		//TODO: check if this is even division
		this.numberOfFrames = this.getHeight()/spriteHeight * this.getWidth()/spriteWidth;
		
	}
	
	//getter and setter
	public int getNumberOfFrames() {
		return numberOfFrames;
	}

	public void setNumberOfFrames(int numberOfFrames) {
		this.numberOfFrames = numberOfFrames;
	}
	
}
