package com.engine;

public class GameContainer implements Runnable{

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean running = false;
    private final double UPDATE_CAP = 1.0/60.0; // frame cap
    private int width = 320, height = 240;
    private float scale = 4f;
    private String title = "Java Engine";

    public GameContainer(AbstractGame game) {
    	
    	this.game = game;

    }
    
    public void start(){

        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);
        
        thread = new Thread(this);
        thread.run();
    }

    //TODO
    public void stop(){
       
    }

    public void run(){

        running = true;

        boolean render = false;
        // keep track of time for fps
        double currentTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        while(running){

            render = false;
            
            //"The value returned represents nanoseconds since some fixed but arbitrary origin time (perhaps in the future, so values may be negative)."
            //TODO: investigate if this causes problems
            currentTime = System.nanoTime() / 1000000000.0;
            
            //time that passed between this and the previous loop
            passedTime = currentTime - lastTime;
            
            //update time of lastTime to know the time of last loop
            lastTime = currentTime;

            //add up all the passed time to unprocessed time to check if there should be a new update
            unprocessedTime += passedTime;

            //update missed updates as soon as unprocessed time exceeds the frame cap UPDATE_CAP
            //it is possible for this while statement to have more than one loop
            //if this happens we do not (want to) render old updates and just skip them and only render the most recent
            while(unprocessedTime >= UPDATE_CAP){

                unprocessedTime -= UPDATE_CAP;
                render = true;
                //System.out.println(input.getScroll()); 
                
                //update
                game.update(this, (float) UPDATE_CAP);
                input.update();
            }

            //only render if there is an update
            if(render){
            	
            	//render
                renderer.clear();
                game.render(this, renderer);
                window.update();
            }
            else{

                try{

                    Thread.sleep(1);
                }
                catch(InterruptedException e){

                    e.printStackTrace();
                }

            }

        }

        dispose();
    }

    //TODO
    public void dispose(){
        
    }

    //getter and setter
    public void setWidth(int width){
        this.width = width;
    }
    public int getWidth(){
        return this.width;
    }

    public void setHeight(int height){
        this.height = height;
    }
    public int getHeight(){
        return this.height;
    }

    public void setScale(float scale){
        this.scale = scale;
    }
    public float getScale(){
        return this.scale;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }

    public Window getWindow(){
        return this.window;
    }

	public Input getInput() {
		return input;
	}

	public double getUPDATE_CAP() {
		return UPDATE_CAP;
	}
}