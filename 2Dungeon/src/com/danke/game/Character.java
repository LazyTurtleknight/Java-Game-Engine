package com.danke.game;

import com.danke.engine.gfx.Sprite;

public class Character {

	private int offsetX;
	private int offsetY;
	private Sprite sprite;
	
	public Character(String path, int offsetX, int offsetY) {
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		
		sprite = new Sprite(path);
		
	}
	
	public void move() {
		
	}
}