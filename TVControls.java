// Mark Fletcher
// 2020-12-08

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


/**
 * Controls for when main screen turn(ed) on.
 * Goes with the main window to provide user-friendly (or at least user-curteous)
 * controls and navigation.
 */
class TVControls extends JPanel implements ActionListener {

  private static final String LABEL_PAUSE = "Pause";
  private static final String LABEL_SET = "Settings";


  private RoboHouse abode;
  private JButton goPause;
  private JButton goConfig;


  /**
   * Set up the control station.
   * This is where the little gnomes put the buttons on and stuff.
   */
  public TVControls(RoboHouse haus){
    super(new BorderLayout());
    setBorder(new EmptyBorder(5, 5, 5, 5));

    abode = haus;

    goPause = new JButton(LABEL_PAUSE);
    goConfig = new JButton(LABEL_SET);

    goPause.addActionListener(this);
    goConfig.addActionListener(this);

    add(goPause, BorderLayout.WEST);
    add(goConfig, BorderLayout.EAST);
  }


// // ACTION_LISTENER METHODS // //

  /**
   * The brains of the controller.
   * This is where our patented algorithm delivers the secret sauce to make the
   * magic happen.
   */
  public void actionPerformed(ActionEvent e){
    //Object source = e.getSource();

    switch(e.getActionCommand()){
    case LABEL_PAUSE:
      abode.togglePaused();
    break;
    case LABEL_SET:
      abode.showSettings();
    break;
    default:
        System.out.println("No action defined for [" + e.getActionCommand() + "]");
    }
  }

}

