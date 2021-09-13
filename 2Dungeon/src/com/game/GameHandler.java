package com.game;

import java.awt.event.KeyEvent;


import com.engine.AbstractGame;
import com.engine.GameContainer;
import com.engine.Renderer;
import com.engine.gfx.Sprite;
import com.engine.gfx.SpriteAnimation;

public class GameHandler extends AbstractGame{

	private SpriteAnimation lizard;
	private Player player;
	private Player player2;
	private GameObject[] objects;
	private Sprite wood;
	private Sprite wood2;
	
	public GameHandler() {
		
		lizard = new SpriteAnimation("/images/lizard.png", 16, 28);
		lizard.loadSprites();
		player = new Player(lizard.getLoadedSprites()[1], 0, 0, "lolzard");
		player2 = new Player(lizard.getLoadedSprites()[1], 0, 0, "lelzard");
		player.setIdleAnimation(lizard);
		player2.setIdleAnimation(lizard);
		objects = new GameObject[2];
		objects[0] = player;
		objects[1] = player2;
		wood = new Sprite("/images/wood.png");
		wood2 = new Sprite("/images/wood.png");
		
		int[] pix = wood2.getPixels();
		for(int i = 0; i < wood2.getPixels().length;i++) {
			pix[i] = (63 << 24| ((pix[i] >> 16) & 0xff) << 16 | ((pix[i] >> 8) & 0xff) << 8 | (pix[i] >> 16) & 0xff);
		}
		wood2.setPixels(pix);
	}
	@Override
	public void update(GameContainer gamecon, float deltatime) {

		if(gamecon.getInput().isKey(KeyEvent.VK_A)) {

			player.moveLeft(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_D)) {

			player.moveRight(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_S)) {

			player.moveDown(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_W)) {

			player.moveUp(1);
		}
		
		if(gamecon.getInput().isKey(KeyEvent.VK_LEFT)) {

			player2.moveLeft(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_RIGHT)) {

			player2.moveRight(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_DOWN)) {

			player2.moveDown(1);
		}
		if(gamecon.getInput().isKey(KeyEvent.VK_UP)) {

			player2.moveUp(1);
		}
		//this will loop over all gameobjects and call their update function
		for(int i=0;i<this.objects.length;i++) {
			objects[i].update(gamecon);
		}	
		
	}

	float delta;
	@Override
	public void render(GameContainer gamecon, Renderer rend) { 
		
		//this will loop over all gameobjects and call their render function
		for(int i=0;i<this.objects.length;i++) {
			objects[i].render(rend, 0, 0);
		}
		
		rend.drawText("Hello", 64, 64, 0xffff00ff);
		rend.drawSprite(wood, 16, 16);
		rend.drawSprite(wood2, gamecon.getInput().getMouseX(), gamecon.getInput().getMouseY());
		//rend.drawSpriteAnimation(lizard, gamecon.getInput().getMouseX(), gamecon.getInput().getMouseY(), (int) delta);
	}

	public static void main(String args[]) {
		
		GameContainer gamecon = new GameContainer(new GameHandler());
		gamecon.start();
	}
	
}
