// Mark Fletcher
// 2020-12-22

import java.awt.Robot;


class ZoneOut implements Xecutable {

  private int delay;


  public ZoneOut(int msDelay){
    delay = msDelay;
  }


  public void executeAction(Robot rbt){
    rbt.delay(delay);
  }

}

