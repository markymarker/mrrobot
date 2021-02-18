// Mark Fletcher
// 2020-12-22

import java.awt.Robot;

import javax.swing.KeyStroke;


class Yield implements Xecutable {

  private int code;
  private KeyStroke key;


  public Yield(KeyStroke ks){
    key = ks;
    code = ks.getKeyCode();
  }


  public void executeAction(Robot rbt){
    rbt.keyPress(code);
    rbt.keyRelease(code);
  }

}

