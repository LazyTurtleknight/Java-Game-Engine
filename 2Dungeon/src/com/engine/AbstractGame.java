package com.engine;

/*
 * Abstract game class defining basic interface.
 */

public abstract class AbstractGame {

	public abstract void update(GameContainer gamecon, float deltatime);
	public abstract void render(GameContainer gameocn, Renderer ren);
	
	//TODO: is deltatime needed by anything else ? (introduce variable and getter)
	
}
