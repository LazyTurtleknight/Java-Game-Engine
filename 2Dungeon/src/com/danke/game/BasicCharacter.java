package com.danke.game;

import com.danke.engine.gfx.Sprite;

public class BasicCharacter {

	private int offsetX;
	private int offsetY;
	private Sprite sprite;
	private String name;	

	public BasicCharacter(Sprite sprite, int offsetX, int offsetY) {
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.sprite = sprite;
		
	}
	
	public void moveUp() {
		offsetY--;
	}

	public void moveDown() {
		offsetY++;
	}

	public void moveRight() {
		offsetX++;
	}
	
	public void moveLeft() {
		offsetX--;
	}

	//getter and setter
	public int getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}