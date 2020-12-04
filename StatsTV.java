// Mark Fletcher
// 2020-12-03

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class StatsTV extends JPanel implements ActionListener {

  private JLabel countdown;
  private JLabel totalKeys;
  private JLabel totalTime;
  private JButton goButton;


  public StatsTV(){
    BoxLayout blay = new BoxLayout(this, BoxLayout.Y_AXIS);
    setLayout(blay);

    countdown = new JLabel("countdown");
    totalKeys = new JLabel("key total");
    totalTime = new JLabel("time total");
    goButton = new JButton("Go");

    goButton.addActionListener(this);

    add(countdown);
    add(totalKeys);
    add(totalTime);
    add(goButton);
  }


// // ACTION_LISTENER METHODS // //

  public void actionPerformed(ActionEvent e){
    Object source = e.getSource();
    if(source.equals(goButton)){
      System.out.println("Go button clicked, neat");
    }
  }

}

