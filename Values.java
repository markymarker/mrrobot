// Mark Fletcher
// 2020-12-13

import javax.swing.KeyStroke;


/**
 * Holds settings values.
 * Values set here be defaults.
 * Viva la Resistance! (No idea why I put this here, but I'm sure it was clever at the time)
 */
public class Values {

  /**
   * Setting for interval between keypresses.
   */
  public int interval = 20;

  /**
   * Key setting key for which key keypresses should be.
   */
  public KeyStroke key = KeyStroke.getKeyStroke("CONTROL");


  /**
   * Useful uverride of to string method.
   * Handy for the avid debugger!
   */
  public String toString(){
    StringBuilder p = new StringBuilder();

    p.append("Interval: " + interval);
    p.append("\n");
    p.append("Keystroke: " + key);

    return p.toString();
  }

}

