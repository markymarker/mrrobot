// Mark Fletcher
// 2020-12-08

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;


class TVControls extends JPanel implements ActionListener {

  private static final String LABEL_GO = "Go";
  private static final String LABEL_SET = "Settings";


  private JButton goButton;
  private JButton goConfig;


  public TVControls(){
    super(new BorderLayout());
    setBorder(new EmptyBorder(5, 5, 5, 5));

    goButton = new JButton(LABEL_GO);
    goConfig = new JButton(LABEL_SET);

    goButton.addActionListener(this);
    goConfig.addActionListener(this);

    add(goButton, BorderLayout.WEST);
    add(goConfig, BorderLayout.EAST);
  }


// // ACTION_LISTENER METHODS // //

  public void actionPerformed(ActionEvent e){
    //Object source = e.getSource();

    switch(e.getActionCommand()){
    case LABEL_GO:
      System.out.println("Go button clicked, neat");
    break;
    case LABEL_SET:
      System.out.println("Gonna open those settings, just you wait...");
    break;
    default:
        System.out.println("No action defined for [" + e.getActionCommand() + "]");
    }
  }

}

