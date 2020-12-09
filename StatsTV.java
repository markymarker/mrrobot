// Mark Fletcher
// 2020-12-03

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


class StatsTV extends JPanel {

  private static final Color active = new Color(50, 50, 50);
  private static final Color inactive = new Color(175, 175, 175);

  private JLabel countdown;
  private JLabel totalKeys;
  private JLabel totalTime;


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


  public void setStats(int next, long ktotal, long ttotal){
    countdown.setText("Next in: " + next);
    totalKeys.setText("Total bonks: " + ktotal);
    totalTime.setText("Operational for: " + ttotal);
  }


  public void setPauseState(boolean p){
    countdown.setForeground(p ? inactive : active);
  }

}

