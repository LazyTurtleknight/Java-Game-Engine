package com.danke.engine.gfx;

public class Tile extends Sprite{

	private int posX;
	private int posY;
	
	private int tileWidth;
	private int tileHeight;
	
	public Tile(String path, int posX, int posY, int tileHeight, int tileWidth) {
		super(path);
		this.posX = posX;
		this.posY = posY;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
	}

	//TODO: maybe add some functionality to work with neighboring tiles
	
	//getter and setter

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	
}