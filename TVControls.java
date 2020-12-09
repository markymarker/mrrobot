// Mark Fletcher
// 2020-12-08

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


class TVControls extends JPanel implements ActionListener {

  private static final String LABEL_PAUSE = "Pause";
  private static final String LABEL_SET = "Settings";


  private RoboHouse abode;
  private JButton goPause;
  private JButton goConfig;


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

  public void actionPerformed(ActionEvent e){
    //Object source = e.getSource();

    switch(e.getActionCommand()){
    case LABEL_PAUSE:
      abode.togglePaused();
    break;
    case LABEL_SET:
      //System.out.println("Gonna open those settings, just you wait...");
      // TODO: Create an actual settings window

      try {
        Object inp = JOptionPane.showInputDialog(
            this,
            "Enter interval", "Gimme Input",
            JOptionPane.QUESTION_MESSAGE,
            null, null,
            abode.getInterval()
        );

        if(inp == null) break;

        String sinp = inp.toString();
        if(sinp.length() != 0)
          abode.setInterval(Integer.parseInt(sinp));
      } catch(NumberFormatException ex) {
        JOptionPane.showMessageDialog(
          this,
          "That was not valid", "WTF",
          JOptionPane.ERROR_MESSAGE
        );
      } catch(Exception ex){
        System.err.println("Unable to show input pane");
      }
    break;
    default:
        System.out.println("No action defined for [" + e.getActionCommand() + "]");
    }
  }

}

