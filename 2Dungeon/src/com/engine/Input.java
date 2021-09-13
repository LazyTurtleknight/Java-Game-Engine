package com.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

	// may not be necessary
	private GameContainer gamecon;
	
	private final int NUM_KEYS = 256;
	
	// bool array to tell if key is pressed or not
	private boolean[] keys = new boolean[NUM_KEYS];
	
	// keys in the last frame
	private boolean[] keysLast = new boolean[NUM_KEYS];
	
	//TODO: number of buttons (on mouse) may differ -> check 
	private final int NUM_BUTTONS = 5;
	
	// bool array to tell if button is pressed or not
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	
	// buttons in the last frame
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
	
	private int mouseX, mouseY;
	
	// 1 scrolling up, 0 not scrolling, -1 scrolling down and abs indicates how much
	private int scroll;
	
	public Input(GameContainer gamecon) {
		this.gamecon = gamecon;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;
		gamecon.getWindow().getCanvas().addKeyListener(this);
		gamecon.getWindow().getCanvas().addMouseListener(this);
		gamecon.getWindow().getCanvas().addMouseMotionListener(this);
		gamecon.getWindow().getCanvas().addMouseWheelListener(this);
	}

	public void update() {
		
		scroll = 0;
		for(int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}
		
		for(int i = 0; i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}
	}
	
	public boolean isKey(int keyCode) {
		
		return keys[keyCode];
		
	}
	
	public boolean isKeyUp(int keyCode) {
		
		return !keys[keyCode] && keysLast[keyCode];
		
	}
	
	public boolean isKeyDown(int keyCode) {
		
		return keys[keyCode] && !keysLast[keyCode];
		
	}
	
	public boolean isButton(int button) {
		
		return buttons[button];
		
	}
	
	public boolean isButtonUp(int button) {
		
		return !buttons[button] && buttonsLast[button];
		
	}
	
	public boolean isButtonDown(int button) {
		
		return buttons[button] && !buttonsLast[button];
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent eve) {

		scroll = eve.getWheelRotation();
		
	}

	@Override
	public void mouseDragged(MouseEvent eve) {
		
		mouseX = (int) (eve.getX()/ gamecon.getScale());
		mouseY = (int) (eve.getY()/ gamecon.getScale());
		
	}

	@Override
	public void mouseMoved(MouseEvent eve) {

		// 
		mouseX = (int) (eve.getX()/ gamecon.getScale());
		mouseY = (int) (eve.getY()/ gamecon.getScale());
		
	}

	@Override
	public void mouseClicked(MouseEvent eve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent eve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent eve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent eve) {

		buttons[eve.getButton()]= true;
		
	}

	@Override
	public void mouseReleased(MouseEvent eve) {

		buttons[eve.getButton()]= false;
		
	}

	@Override
	public void keyPressed(KeyEvent eve) {

		keys[eve.getKeyCode()] = true; 
		
	}

	@Override
	public void keyReleased(KeyEvent eve) {

		keys[eve.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent eve) {

		
		
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public int getScroll() {
		return scroll;
	}
}
