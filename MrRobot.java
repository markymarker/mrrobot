// Mark Fletcher
// 2020-12-03

import java.awt.Robot;

class MrRobot {

// // STATIC // //

  public static void main(String[] args){
    MrRobot rowboat = new MrRobot();
    RoboHouse haus = new RoboHouse(rowboat);

    javax.swing.SwingUtilities.invokeLater(haus);
  }


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

}

