package com.game;


import static com.game.Constants.IMAGES_CYBER;
import static com.game.Constants.JUMP;

import java.io.File;
import com.engine.GameContainer;
import com.engine.Renderer;
import com.engine.gfx.SpriteAnimation;



/*
 * Prototype player class.
 */
public class Player extends BasicCharacter{

  private Boolean jumping = false;

  private SpriteAnimation jumpAnimation;

  public Player(String name, int offsetX, int offsetY, int spriteWidth, int spriteHeight) {

    super(name, offsetX, offsetY, spriteWidth, spriteHeight);

    String path =  IMAGES_CYBER + File.separator + name;
    File file = new File(path);

    //TODO: check beforehand if this directory and files exists
    SpriteAnimation animation = new SpriteAnimation(file.getPath() + File.separator + name + JUMP, spriteWidth, spriteHeight);
    animation.loadSprites();
    this.setJumpAnimation(animation);

  }

  @Override
  public void render(Renderer renderer, int zoomX, int zoomY) {

    if(this.getJumping()) {
      renderer.drawSpriteAnimation(this.getJumpAnimation(), this.getOffsetX(), this.getOffsetY(), (int) this.getDelta());
    }else if(this.getIdle()){
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

  public Boolean getJumping() {
    return jumping;
  }

  public void setJumping(Boolean jumping) {
    this.jumping = jumping;
  }
}
