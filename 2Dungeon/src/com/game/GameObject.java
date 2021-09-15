package com.game;

import com.engine.GameContainer;
import com.engine.Renderer;

/*
 * Abstract interface that has to be implemented by every object in the game (e.g. characters).
 */
public abstract interface GameObject {

	//used to render an object
	public void render(Renderer renderer, int zoomX, int zoomY);
	
	//used to update the state of an object
	public void update(GameContainer game);
}
