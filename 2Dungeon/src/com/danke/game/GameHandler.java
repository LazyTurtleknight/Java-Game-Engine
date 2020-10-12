package com.danke.game;

import java.awt.event.KeyEvent;

import com.danke.engine.AbstractGame;
import com.danke.engine.GameContainer;
import com.danke.engine.Renderer;
import com.danke.engine.gfx.Image;

public class GameHandler extends AbstractGame{

	private Image image;
	
	public GameHandler() {
		
		image = new Image("/images/blueExplosion.png");
	}
	@Override
	public void update(GameContainer gamecon, float deltatime) {

		if(gamecon.getInput().isKeyDown(KeyEvent.VK_A)) {

			System.out.println("A was pressed");
		}
		
	}

	@Override
	public void render(GameContainer gamecon, Renderer rend) {
		
		rend.drawImage(image, gamecon.getInput().getMouseX() - image.getWidth()/2, gamecon.getInput().getMouseY()- image.getHeight()/2);
		
	}

	public static void main(String args[]) {

		GameContainer gamecon = new GameContainer(new GameHandler());
		gamecon.start();
	}
	
}
