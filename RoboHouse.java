// Mark Fletcher
// 2020-12-03

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import javax.swing.border.EmptyBorder;


class RoboHouse implements Runnable {

  private static final String ACTION_CLOSE = "Close Window";
  private static final String ACTION_BIG_J = "Big J";


  private JFrame window;
  private StatsTV stats;

  private MrRobot tenant;


  public RoboHouse(MrRobot tenant){
    this.tenant = tenant;
  }


  private void setupActions(){
    InputMap inputs = window.getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
    inputs.put(KeyStroke.getKeyStroke("ESCAPE"), ACTION_CLOSE);
    inputs.put(KeyStroke.getKeyStroke("shift J"), ACTION_BIG_J);
    inputs.put(KeyStroke.getKeyStroke("shift H"), ACTION_BIG_J);

    ActionMap actions = window.getRootPane().getActionMap();
    actions.put(ACTION_CLOSE, new Actioner(ACTION_CLOSE));
    actions.put(ACTION_BIG_J, new Actioner(ACTION_BIG_J));
  }


  public void handleAction(ActionEvent e, String cmdstr){
      switch(cmdstr){
      case ACTION_CLOSE:
        window.dispose();
        MrRobot.terminationProtocol();
      break;
      case ACTION_BIG_J:
        System.out.println("Big J (or so)");
      break;
      default:
        System.out.println("No action defined for [" + cmdstr + "]");
      }
  }


  public void doCoolStuff() throws InterruptedException {
    System.out.println("Starting up the smart home...");
  }


// // RUNNABLE METHODS // //

  public void run(){
    window = new JFrame("Mr. Robot");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setMinimumSize(new Dimension(300, 10));
    window.getRootPane().setBorder(new EmptyBorder(10, 10, 10, 10));

    stats = new StatsTV();
    window.add(stats);

    setupActions();

    window.pack();

    try {
      Point c = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
      c.x = c.x / 3;
      c.y = c.y * 2 / 3;
      window.setLocation(c);
    } catch(Exception e) {
      window.setLocationByPlatform(true);
    }

    window.setVisible(true);
  }


// // ACTION HANDLER CLASS // //

  private class Actioner extends AbstractAction {
    private String commandString;

    Actioner(String cmdstr){
      commandString = cmdstr;
    }

    public void actionPerformed(ActionEvent e){
      handleAction(e, commandString);
    }
  }

}

