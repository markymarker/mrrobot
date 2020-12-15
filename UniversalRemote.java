// Mark Fletcher
// 2020-12-11

import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


class UniversalRemote extends JDialog {

  private static final String ACTION_SAVE = "Save";
  private static final String ACTION_CANCEL = "Cancel";

  Values settings;

  JButton bsave;
  JButton bcancel;

  JLabel linterval;

  JTextField interval;


  public UniversalRemote(Frame owner, Values sharedVals){
    super(owner, "RoboSet The RoboSettings", JDialog.ModalityType.DOCUMENT_MODAL);
    setLocationRelativeTo(owner);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    settings = sharedVals;

    JPanel tp;  // Reusable temp panel var for groupings
    linterval = new JLabel("Interval (s)");
    interval = new JTextField(8);

    tp = new JPanel();
    tp.add(linterval);
    tp.add(interval);
    add(tp);

    bsave = new JButton(ACTION_SAVE);
    bcancel = new JButton(ACTION_CANCEL);

    tp = new JPanel();
    tp.add(bcancel);
    tp.add(bsave);
    add(tp);

    loadSettings();
    registerListeners();

    pack();
  }


  private void registerListeners(){
    ActionListener al = new ActionListener(){
      public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
        case ACTION_SAVE:
          saveSettings();
        break;
        case ACTION_CANCEL:
          loadSettings();
          takethAway();
        break;
        }
      }
    };

    bsave.addActionListener(al);
    bcancel.addActionListener(al);
  }


  public void giveth(){
    setVisible(true);
  }


  public void takethAway(){
    setVisible(false);
  }


  public void loadSettings(){
    interval.setText(String.valueOf(settings.interval));
  }


  public void saveSettings(){
    try {
      settings.interval = Integer.parseInt(interval.getText());

      takethAway();
    } catch(Exception e) {
      // TODO
      System.out.println("Bad setting setting: " + e.getMessage());
      loadSettings();
    }
  }


  // TODO away with
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

}

