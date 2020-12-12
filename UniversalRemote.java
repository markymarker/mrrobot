// Mark Fletcher
// 2020-12-11

import java.awt.Frame;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class UniversalRemote extends JDialog {

  JLabel lInterval;

  JTextField interval;


  public UniversalRemote(Frame owner){
    super(owner, "RoboSet The RoboSettings", JDialog.ModalityType.DOCUMENT_MODAL);
    setLocationRelativeTo(owner);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    JPanel tp;

    lInterval = new JLabel("Interval (s)");
    interval = new JTextField(8);

    tp = new JPanel();
    tp.add(lInterval);
    tp.add(interval);
    add(tp);

    pack();
  }


  public void giveth(){
    setVisible(true);
  }


  public void takethAway(){
    setVisible(false);
  }


  public Values getSettings(){
    return new Values(){
      public int interval = convertInterval();
    };
  }


  private int convertInterval(){
    try {
      return Integer.parseInt(interval.getText());
    } catch(Exception e) {
      return 0;
    }
  }


// // DATA STRUCTURE // //

  public abstract class Values {
    public int interval;

    public String toString(){
      StringBuilder p = new StringBuilder();

      p.append("Interval: " + interval);

      return p.toString();
    }
  }

}

