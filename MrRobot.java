// Mark Fletcher
// 2020-12-03

import java.awt.Robot;

import java.awt.event.KeyEvent;


class MrRobot {

// // STATIC // //

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
      System.err.println("RoboSystem Error: Unable to init internal Robot... What am I?");
    }
  }


  public boolean functional(){
    return robot != null;
  }


  public void kickInTheRoboPants(){
    if(!functional()) return;

    robot.keyPress(KeyEvent.VK_NUM_LOCK);
    robot.keyRelease(KeyEvent.VK_NUM_LOCK);

    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_CONTROL);
  }

}

