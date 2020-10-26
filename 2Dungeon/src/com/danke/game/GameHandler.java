package com.danke.game;

import java.awt.event.KeyEvent;

import com.danke.engine.AbstractGame;
import com.danke.engine.GameContainer;
import com.danke.engine.Renderer;
import com.danke.engine.gfx.SpriteAnimation;

public class GameHandler extends AbstractGame{

	private SpriteAnimation image;
	
	public GameHandler() {
		
		image = new SpriteAnimation("/images/animation.png", 16, 16);
	}
	@Override
	public void update(GameContainer gamecon, float deltatime) {

		if(gamecon.getInput().isKeyDown(KeyEvent.VK_A)) {

			System.out.println("A was pressed");
		}
		delta += deltatime;
		if(delta > 4) {
			delta=0;
		}
		
	}

	float delta;
	@Override
	public void render(GameContainer gamecon, Renderer rend) {
		
		rend.drawSpriteAnimation(image, gamecon.getInput().getMouseX(), gamecon.getInput().getMouseY(), (int) delta, 0);
		
	}

	public static void main(String args[]) {

		GameContainer gamecon = new GameContainer(new GameHandler());
		gamecon.start();
	}
	
}
