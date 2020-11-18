package com.danke.game;

import com.danke.engine.gfx.Sprite;

public class Player extends BasicCharacter{

	public Player(Sprite sprite, int offsetX, int offsetY, String name) {
		super(sprite, offsetX, offsetY);
		this.setName(name);
	}
}
