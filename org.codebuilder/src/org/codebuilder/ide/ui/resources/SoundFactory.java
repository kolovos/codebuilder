package org.codebuilder.ide.ui.resources;

import java.applet.AudioClip;
import java.applet.Applet;
import java.io.File;
import java.net.MalformedURLException;

public class SoundFactory {
  public SoundFactory() {
  }

  public static final AudioClip SuccessSound = createAudioClip(
      "Resources/Sounds/mid_success.wav");

  public static final AudioClip ErrorSound = createAudioClip(
      "Resources/Sounds/mid_error.wav");

  private static AudioClip createAudioClip(String filename) {
    AudioClip clip = null;
    File file = new File(filename);
    try {
      clip = Applet.newAudioClip(file.toURL());
    }
    catch (MalformedURLException ex) {
    }
    return clip;
  }
}
