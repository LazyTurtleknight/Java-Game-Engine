package com.engine.gfx;

/*
 * Font class to depict fonts and load fonts.
 * This class loads fonts as a sprite and scans up to the defined limit (size of offsets member)
 * symbol by their width.
 * A blue (0xff0000ff) pixel indicates the start and a yellow (0xffffff00) indicates the end.
 * For an example look at the default fonts.
 */

public class Font {

  private Sprite fontImage;
  private int[] offsets;
  private int[] widths;


  /*
   * Constructor.
   * @param path file path to read font from
   */
  public Font(String path) {

    fontImage = new Sprite(path);
    offsets = new int[256];
    widths = new int[256];
    int unicode  = 0;

    for(int i = 0; i < fontImage.getWidth(); i++) {

      //if the pixel is blue (0xff0000ff) then a new symbol stars
      if(fontImage.getPixels()[i] == 0xff0000ff) {

        offsets[unicode] = i;
      }

      //if the pixel is yellow (0xffffff00) then a symbol ends
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
