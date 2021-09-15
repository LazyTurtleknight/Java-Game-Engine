package com.game;

import com.engine.gfx.Sprite;

/*
 * A basic object class that defines a couple of necessary properties like the position.
 * TODO: expand this base class
 */
public class BasicObject {
	
	private int offsetX;
	private int offsetY;
	private Sprite sprite;
	private Boolean indistructable = true;
	private Boolean immovable = true;
	
	public BasicObject(int offsetX, int offsetY, Sprite sprite) {
		
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.sprite = sprite;
		
	}

	//getter and setter
	public Boolean getIndistructable() {
		return indistructable;
	}

	public void setIndistructable(Boolean indistructable) {
		this.indistructable = indistructable;
	}

	public Boolean getImmovable() {
		return immovable;
	}

	public void setImmovable(Boolean immovable) {
		this.immovable = immovable;
	}

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}

	public Sprite getSprite() {
		return sprite;
	}
}
