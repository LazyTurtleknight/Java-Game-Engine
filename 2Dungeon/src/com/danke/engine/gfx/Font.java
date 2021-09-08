package com.danke.engine.gfx;

public class Font {

	private Sprite fontImage;
	private int[] offsets;
	private int[] widths;
	
	
	public Font(String path) {
		
		fontImage = new Sprite(path);
		offsets = new int[256];
		widths = new int[256];
		int unicode  = 0;
		
		for(int i = 0; i < fontImage.getWidth(); i++) {
			
			if(fontImage.getPixels()[i] == 0xff0000ff) {
				
				offsets[unicode] = i;
			}
			
			if(fontImage.getPixels()[i] == 0xffffff00) {
				
				widths[unicode] = i - offsets[unicode];
				unicode++;
			}
		}
	}


	// getter and setter
	public Sprite getFontImage() {
		return fontImage;
	}


	public int[] getOffsets() {
		return offsets;
	}


	public int[] getWidths() {
		return widths;
	}
}
