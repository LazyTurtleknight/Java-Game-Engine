package com.danke.game;

import com.danke.engine.GameContainer;
import com.danke.engine.Renderer;

//every object is going to implement this interface
public interface GameObject {

	//used to render an object
	public void render(Renderer renderer, int zoomX, int zoomY);
	
	//used to update the state of an object
	public void update(GameContainer game);
}
