// Mark Fletcher
// 2020-12-03

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


class StatsTV extends JPanel {

  private JLabel countdown;
  private JLabel totalKeys;
  private JLabel totalTime;


  public StatsTV(){
    super();
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(new EmptyBorder(5, 5, 5, 5));

    countdown = new JLabel("countdown");
    totalKeys = new JLabel("key total");
    totalTime = new JLabel("time total");

    add(countdown);
    add(totalKeys);
    add(totalTime);
  }


  public void setStats(int next, long ktotal, long ttotal){
    countdown.setText("Next in: " + next);
    totalKeys.setText("Total bonks: " + ktotal);
    totalTime.setText("Operational for: " + ttotal);
  }

}

