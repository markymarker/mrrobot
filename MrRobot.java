// Mark Fletcher
// 2020-12-03

import java.awt.Robot;

import java.awt.event.KeyEvent;


/**
 * Everybody's favorite button-pushing automaton.
 * Skills include: polite, good at repetitive tasks, non-corporeal form.
 * As an added bonus, is the keeper of the main method for this whole circus.
 */
class MrRobot {

// // STATIC // //

  /**
   * Start up the program stuff.
   * Gathers up a Mr. Robot and some quarters for him, puts the robot in the
   * quarters (not vice-versa, this ain't the 50's), and the rest is history.
   */
  public static void main(String[] args){
    MrRobot rowboat = new MrRobot();
    RoboHouse haus = new RoboHouse(rowboat);
    Thread hausland;

    try {

      javax.swing.SwingUtilities.invokeAndWait(new Runnable(){
        public void run(){ haus.arrangeFurniture(); }
      });

      hausland = haus.doCoolStuff();
      hausland.join();

    } catch(InterruptedException e) {
      System.err.println("Rudely interrupted: " + e.getMessage());
      terminationProtocol(1);
    } catch(java.lang.reflect.InvocationTargetException e) {
      System.err.println("Init error: " + e.getMessage());
      terminationProtocol(1);
    } catch(Exception e) {
      System.err.println("Some other error happened: " + e.getMessage());
      e.printStackTrace();
      terminationProtocol(1);
    }

    terminationProtocol();
  }


  /**
   * Shut it down.
   * All good things must come to an end.
   */
  public static void terminationProtocol(int code){
    System.out.println("Toodaloo, sir.");
    System.exit(code);
  }
  public static void terminationProtocol(){ terminationProtocol(0); }


// // NON-STATIC // //

  private Robot robot;


  /**
   * Robo assembly line.
   * Creates a shiny new Mr. Robot and another robot to go inside to help with
   * all that task performing he's looking forward to doing.
   */
  public MrRobot(){
    try {
      robot = new Robot();
    } catch(Exception e) {
      robot = null;
      System.err.println("RoboSystem Error: Unable to init internal Robot... What am I?");
    }
  }


  /**
   * Is this thing on?...
   *
   * @return TRUE if this broken piece of crap is busted; if FALSE, hopefully we
   *   forgot to install the hearing upgrade...
   */
  public boolean functional(){
    return robot != null;
  }


  /**
   * Perform tasks.
   * No slacking on the RoboJob! I'm not paying you to leak oil! Etc.
   */
  public void kickInTheRoboPants(){
    if(!functional()) return;

    robot.keyPress(KeyEvent.VK_NUM_LOCK);
    robot.keyRelease(KeyEvent.VK_NUM_LOCK);

    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

}

