package com.game;

import static com.game.Constants.IDLE;
import static com.game.Constants.IMAGES_CYBER;
import static com.game.Constants.RUN;

import java.io.File;
import com.engine.GameContainer;
import com.engine.Renderer;
import com.engine.gfx.SpriteAnimation;

/*
 * A basic character class that defines a couple of necessary properties 
 * like the position and animation for different states.
 * TODO: expand this base class
 */
public class BasicCharacter implements GameObject {

	private int offsetX;
	private int offsetY;
	private String name;
	
	//used to determine which animation has to used
	private Boolean idle = true;
	//keeps track of which frame has to be rendered
	private double delta = 0; 
	private SpriteAnimation idleAnimation;
	private SpriteAnimation runAnimation;
	private SpriteAnimation hitAnimation;

	/*
	 * Constructor.
	 * @param name name of character used to find and read corresponding sprites
	 * @param offsetX x coordinate of start location
	 * @param offsetY y coordinate of start location
	 * @param spriteWidth width of single sprite used to read from files
	 * @param spriteHeight height of single sprite used to read form files
	 */
	public BasicCharacter(String name, int offsetX, int offsetY, int spriteWidth, int spriteHeight) {
		
		this.setName(name);
		
		String path =  IMAGES_CYBER + File.separator + name;
		File file = new File(path);
	
		//TODO: check beforehand if this directory and files exists
		SpriteAnimation animation = null;
	
		animation = new SpriteAnimation(file.getPath() + File.separator + name + IDLE, spriteWidth, spriteHeight);
		animation.loadSprites();
		this.setIdleAnimation(animation);
	
	
		animation = new SpriteAnimation(file.getPath() + File.separator + name + RUN, spriteWidth, spriteHeight);
		animation.loadSprites();
		this.setRunAnimation(animation);

	}

	// part of the GameObject interface
	public void render(Renderer renderer, int zoomX, int zoomY) {
		
	}
	
	public void update(GameContainer game) {
		
	}
	
	public void moveUp(int steps) {
		offsetY -= steps;
	}

	public void moveDown(int steps) {
		offsetY += steps;
	}

	public void moveRight(int steps) {
		offsetX += steps;
	}
	
	public void moveLeft(int steps) {
		offsetX -= steps;
	}
	
	public void incrementDelta(double inc) {
		this.delta +=inc;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpriteAnimation getIdleAnimation() {
		return idleAnimation;
	}

	public void setIdleAnimation(SpriteAnimation idleAnimation) {
		this.idleAnimation = idleAnimation;
	}

	public SpriteAnimation getRunAnimation() {
		return runAnimation;
	}

	public void setRunAnimation(SpriteAnimation runAnimation) {
		this.runAnimation = runAnimation;
	}

	public SpriteAnimation getHitAnimation() {
		return hitAnimation;
	}

	public void setHitAnimation(SpriteAnimation hitAnimation) {
		this.hitAnimation = hitAnimation;
	}

	public Boolean getIdle() {
		return idle;
	}

	public void setIdle(Boolean idle) {
		this.idle = idle;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(int delta) {
		this.delta = delta;
	}

}