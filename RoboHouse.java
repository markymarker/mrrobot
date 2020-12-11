// Mark Fletcher
// 2020-12-03

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import javax.swing.border.EmptyBorder;


class RoboHouse implements Runnable {

  private static final String ACTION_CLOSE = "Close Window";
  private static final String ACTION_BIG_J = "Big J";

  private static final Color ACTIVE_BORDER = new Color(66, 66, 66);
  private static final Color INACTIVE_BORDER = new Color(175, 175, 175);


  private JFrame window;
  private JPanel display;
  private StatsTV stats;
  private TVControls controls;

  private Thread reality;
  private MrRobot tenant;
  private long pressCount = 0;
  private long timeElapsed = 0;
  private volatile int interval = 20;
  private volatile boolean keepGoing;
  private volatile boolean paused;


  public RoboHouse(MrRobot tenant){
    this.tenant = tenant;
  }


  private void setupActions(){
    window.addWindowListener(new Windower());

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
        keepGoing = false;
        //reality.interrupt();
        window.dispose();
        //MrRobot.terminationProtocol();
      break;
      case ACTION_BIG_J:
        System.out.println("Big J (or so)");
      break;
      default:
        System.out.println("No action defined for [" + cmdstr + "]");
      }
  }


  public void arrangeFurniture(){
    int minimumWidescreen = 300;

    window = new JFrame("Mr. Robot");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setMinimumSize(new Dimension(minimumWidescreen, 10));
    window.getRootPane().setBorder(new EmptyBorder(0, 5, 5, 5));
    window.setBackground(new java.awt.Color(66, 66, 66));

    display = new JPanel();
    display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
    display.setBackground(new java.awt.Color(0, 0, 0));
    window.add(display);

    stats = new StatsTV();
    stats.setStats(interval, pressCount, timeElapsed);
    display.add(stats);

    controls = new TVControls(this);
    display.add(controls);

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


  public Thread doCoolStuff(){
    if(reality == null){
      reality = new Thread(this);
      reality.start();
    }
    return reality;
  }


  public int getInterval(){
    return interval;
  }


  public void setInterval(int intval){
    interval = intval;
  }


  public void setPaused(boolean p){
    // Lock pause on if busted robot
    if(!tenant.functional()) p = true;

    this.paused = p;
    stats.setPauseState(p);
  }


  public void togglePaused(){
    setPaused(!paused);
  }


// // RUNNABLE METHODS // //

  public void run(){
    System.out.println("Starting up the smart home...");

    if(!tenant.functional()){
      System.out.println("O dear, Mr. Robot doesn't appear to be home");
      setPaused(true);
    }

    int ticker = 0;
    int intervalInUse = interval;
    int dangerousMalfunctions = 0;
    long lasttickstart = System.currentTimeMillis();
    StringBuilder pictures = new StringBuilder();

    keepGoing = true;

    try {
      while(keepGoing){
        // Pause for a second
        long diff = (lasttickstart + 1000) - System.currentTimeMillis();
        if(diff > 0){
          try { Thread.sleep(diff); }
          catch(Exception e) {
            ++dangerousMalfunctions;
            pictures.append(e.getMessage() + "\n");
            if(dangerousMalfunctions > 8) break;
          }
        }

        lasttickstart = System.currentTimeMillis();

        if(intervalInUse != interval){
          ticker = 0;
          intervalInUse = interval;
        }

        if(!paused) ++ticker;
        ++timeElapsed;
        int remain = intervalInUse - ticker;

        if(!paused && remain <= 0){
          ticker = 0;
          remain = intervalInUse;
          tenant.kickInTheRoboPants();
          ++pressCount;
        }

        stats.setStats(remain, pressCount, timeElapsed);
        //stats.revalidate();
      }
    } catch(Exception e) {
      System.err.println("Smart home not so smart now: " + e.getMessage());
    }

    if(dangerousMalfunctions > 0){
      System.err.println("Safety threshold exceeded");
      System.err.println(pictures.toString());
    }
  }


// // ACTION HANDLER CLASSES // //

  private class Actioner extends AbstractAction {
    private String commandString;

    Actioner(String cmdstr){
      commandString = cmdstr;
    }

    public void actionPerformed(ActionEvent e){
      handleAction(e, commandString);
    }
  }


  private class Windower extends WindowAdapter {
    public void windowActivated(WindowEvent e){
      window.setBackground(ACTIVE_BORDER);
    }

    public void windowDeactivated(WindowEvent e){
      window.setBackground(INACTIVE_BORDER);
    }
  }

}

