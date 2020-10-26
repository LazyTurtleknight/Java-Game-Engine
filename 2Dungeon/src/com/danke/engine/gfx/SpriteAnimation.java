package com.danke.engine.gfx;

public class Animation {

	private Sprite image;
	private int numberOfFrame;
	
	public Animation(String path) {
		image = new Sprite(path);
		
		//modulo based on size of frames
		if(image.getWidth() % 16 == 0 ) {
			numberOfFrame = image.getWidth()/16;
		}else {
			//handle
		}
		
	}
	
}
