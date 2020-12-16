// Mark Fletcher
// 2020-12-03

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


/**
 * Displayer of stats, entertainer of children.
 * A panel that shows information about the program and its goings on.
 */
class StatsTV extends JPanel {

  private static final Color active = new Color(50, 50, 50);
  private static final Color inactive = new Color(175, 175, 175);

  private JLabel countdown;
  private JLabel totalKeys;
  private JLabel totalTime;


  /**
   * Main screen turn on.
   * Sets up us the display and gets all the pixels to behave. Unfortunately,
   * there aren't any Zigs to move here; budget's been tight lately.
   */
  public StatsTV(){
    super(new GridLayout(3, 1));
    setBorder(new EmptyBorder(5, 5, 5, 5));

    countdown = new JLabel("countdown", JLabel.LEFT);
    totalKeys = new JLabel("key total", JLabel.LEFT);
    totalTime = new JLabel("time total", JLabel.LEFT);

    //add(javax.swing.Box.createHorizontalStrut(wideScreenness));
    add(countdown);
    add(totalKeys);
    add(totalTime);
  }


  /**
   * Set the stats in the display.
   * Does what it says on the tin.
   *
   * @param next Seconds to next event
   * @param ktotal Total event count
   * @param ttotal Total elapsed time count
   */
  public void setStats(int next, long ktotal, long ttotal){
    countdown.setText("Next in: " + next);
    totalKeys.setText("Total bonks: " + ktotal);
    totalTime.setText("Operational for: " + ttotal);
  }


  /**
   * Set whether or not to display as paused.
   * Another one that does what it says on the tin. Two for two!
   *
   * @param p TRUE to display as paused, FALSE for the opposite
   */
  public void setPauseState(boolean p){
    countdown.setForeground(p ? inactive : active);
  }

}

