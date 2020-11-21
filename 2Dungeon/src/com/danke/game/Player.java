package com.danke.game;

import com.danke.engine.GameContainer;
import com.danke.engine.Renderer;
import com.danke.engine.gfx.Sprite;
import com.danke.engine.gfx.SpriteAnimation;

public class Player extends BasicCharacter{
	
	private SpriteAnimation jumpAnimation;
	
	public Player(Sprite sprite, int offsetX, int offsetY, String name) {
		super(sprite, offsetX, offsetY);
		this.setName(name);
	}
	
	@Override
	public void render(Renderer renderer, int zoomX, int zoomY) {
		
		if(this.getIdle()) {
			renderer.drawSpriteAnimation(this.getIdleAnimation(), this.getOffsetX(), this.getOffsetY(), (int) this.getDelta());
		}else {
			renderer.drawSpriteAnimation(this.getRunAnimation(), this.getOffsetX(), this.getOffsetY(), (int) this.getDelta());
		}
	}
	
	@Override
	public void update(GameContainer gamecon) {
		
		//this determines how fast the animation is
		this.incrementDelta(gamecon.getUPDATE_CAP());
		if(this.getDelta() > this.getIdleAnimation().getNumberOfFrames()) {
			this.setDelta(0);
		}
		
	}

	
	//getter and setter
	public SpriteAnimation getJumpAnimation() {
		return jumpAnimation;
	}

	public void setJumpAnimation(SpriteAnimation jumpAnimation) {
		this.jumpAnimation = jumpAnimation;
	}
}
