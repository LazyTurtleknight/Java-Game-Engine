package com.engine.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Class to load and play soundtracks.
 */

public class Soundtrack {

  //clip is a special data line where data is pre-loaded enabling start at any position in its audio data
  private Clip clip;
  private FloatControl gainControl;

  /*
   * Constructor.
   * @param path file path to read from
   */
  public Soundtrack(String path){
    try {

      InputStream audioSource = Soundtrack.class.getResourceAsStream(path);
      InputStream bufferedI = new BufferedInputStream(audioSource);
      AudioInputStream audioIstream = AudioSystem.getAudioInputStream(bufferedI);
      AudioFormat baseFormat = audioIstream.getFormat();
      AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
          baseFormat.getSampleRate(),
          16,
          baseFormat.getChannels(),
          baseFormat.getChannels() * 2,
          baseFormat.getSampleRate(),
          false);	
      AudioInputStream decaudioIstream = AudioSystem.getAudioInputStream(decodeFormat, audioIstream);

      clip = AudioSystem.getClip();
      clip.open(decaudioIstream);

      gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN); 

    }
    catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  /*
   * Play the loaded soundtrack.
   */
  public void play() {
    if(clip == null) {
      return;
    }
    stop();
    clip.setFramePosition(0);
    while(!clip.isRunning()) {
      clip.start();
    }
  }

  /*
   * Stop playing the loaded soundtrack.
   */
  public void stop() {
    if(clip.isRunning()) {
      clip.stop();
    }
  }

  public void close() {
    stop();
    //empties the stream
    clip.drain();
    clip.close();
  }

  public void loop() {
    clip.loop(Clip.LOOP_CONTINUOUSLY);
    play();
  }

  public void setVolume(float value) {
    gainControl.setValue(value);
  }

  public boolean isRunning() {
    return clip.isRunning();
  }

}
