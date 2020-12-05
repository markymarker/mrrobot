// Mark Fletcher
// 2020-12-03

import java.awt.Robot;

import java.awt.event.KeyEvent;


class MrRobot {

// // STATIC // //

  public static void main(String[] args){
    MrRobot rowboat = new MrRobot();
    RoboHouse haus = new RoboHouse(rowboat);

    try {
      javax.swing.SwingUtilities.invokeAndWait(haus);
      haus.doCoolStuff();
    } catch(InterruptedException e) {
      System.err.println("Interrupted while initing: " + e.getMessage());
      terminationProtocol(1);
    } catch(java.lang.reflect.InvocationTargetException e) {
      System.err.println("Init error: " + e.getMessage());
      terminationProtocol(1);
    } catch(Exception e) {
      System.err.println("Some other error happened: " + e.getMessage());
      terminationProtocol(1);
    }
  }

  public static void terminationProtocol(int code){
    System.out.println("Toodaloo, sir.");
    System.exit(code);
  }
  public static void terminationProtocol(){ terminationProtocol(0); }


// // NON-STATIC // //

  private Robot robot;


  public MrRobot(){
    try {
      robot = new Robot();
    } catch(Exception e) {
      robot = null;
      System.err.println("Unable to init Robot");
    }
  }


  public void kickInTheRoboPants(){
    robot.keyPress(KeyEvent.VK_NUM_LOCK);
    robot.keyRelease(KeyEvent.VK_NUM_LOCK);

    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

}

