// Mark Fletcher
// 2020-12-16

import java.awt.Color;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.KeyStroke;

import javax.swing.border.Border;


/**
 * A field for inputting a keystroke.
 * Adapted from a text field, but it's, like, different, you know. Text cannot
 * be directly entered into the field, but when it is active, it will listen for
 * a pressed key combination and display the combination that was observed.
 *
 * The value of this field as a KeyStroke object can be retrieved using the
 * relevant method. Same goes for setting the value using a KeyStroke object you
 * happen to posess. This field doesn't ask where you got the KeyStroke and
 * doesn't question why you might be holding a KeyStroke. It simply sets the
 * thing and moves on and isn't asking for trouble, okay?
 *
 * The DELETE key can be used to clear the field's value, which will result in
 * a key setting of null, if you're into that sort of thing.
 */
class WhichKeyField extends JTextField {

// // STATIC // //

  private static final Color COLOR_INACTIVE = Color.WHITE;
  private static final Color COLOR_ACTIVE = new Color(240, 255, 240);

  private static FocusListener fl;
  private static KeyListener kl;


  static {
    /*
     * Set up focus listener.
     * When the field gains focus, we need a solid indication to the user that
     * it is ready to do something with their input, especially since input is
     * disabled in the field. This is accomplished by giving the field a clear
     * difference in appearance while focused. When focus is lost, the effects
     * from focusing should be undone and the field should not grab attention.
     */
    fl = new FocusListener(){
      public void focusGained(FocusEvent e){
        if(!(e.getComponent() instanceof WhichKeyField)) return;
        WhichKeyField c = (WhichKeyField)e.getComponent();

        c.setBackground(COLOR_ACTIVE);
        c.setBorder(c.borderActive);
      }

      public void focusLost(FocusEvent e){
        if(!(e.getComponent() instanceof WhichKeyField)) return;
        WhichKeyField c = (WhichKeyField)e.getComponent();

        c.setBackground(COLOR_INACTIVE);
        c.setBorder(c.borderInactive);
      }
    };

    /*
     * Set up key listener.
     * This is what handles the detection and processing of the user's inputs.
     * When key(s) are pressed, the accumulator is used to track the pressed
     * keys. When a key is released, the field's value is actually updated
     * using what is in the accumulator. This is to tone down the amount
     * of activity/movement/action that we are spamming the user with.
     */
    kl = new KeyListener(){
      public void keyPressed(KeyEvent e){
        if(!(e.getComponent() instanceof WhichKeyField)) return;
        WhichKeyField c = (WhichKeyField)e.getComponent();

        if(e.getKeyCode() == KeyEvent.VK_DELETE){
          c.clearInput();  // Will also clear accumulator
        } else {
          c.accumulator = e;
        }

        e.consume();
      }

      public void keyReleased(KeyEvent e){
        if(!(e.getComponent() instanceof WhichKeyField)) return;
        WhichKeyField c = (WhichKeyField)e.getComponent();

        if(c.accumulator != null){
          KeyStroke s = KeyStroke.getKeyStrokeForEvent(c.accumulator);
          c.setKeyStroke(s);
          c.accumulator = null;
        }

        e.consume();
      }

      public void keyTyped(KeyEvent e){}
    };
  }


// // NON-STATIC // //

  private Border borderActive = null;
  private Border borderInactive = null;

  private KeyEvent accumulator = null;


  /**
   * Create and setup a new WhichKeyField.
   * Does the necessary appearance-adjusting and listener-attaching.
   *
   * @param cols The width of the field in columns, just as JTextField would do
   */
  public WhichKeyField(int cols){
    super(cols);
    setEditable(false);
    setBackground(COLOR_INACTIVE);
    // Perhaps test with receiving focus initially

    addFocusListener(fl);
    addKeyListener(kl);
  }


  /**
   * Set custom borders to use for active and inactive states.
   *
   * @param active The border to use when active
   * @param inactive The border to use when inactive
   */
  public void setBorders(Border active, Border inactive){
    borderActive = active;
    borderInactive = inactive;
  }


  /**
   * Clearing method for the field.
   * Sets the keystroke held by this field to null and clears the accumulator.
   */
  public void clearInput(){
    accumulator = null;
    setKeyStroke(null);
  }


  /**
   * You'll never guess.
   * Spoilers: Sets the given keystroke as text in the field.
   */
  public void setKeyStroke(KeyStroke ks){
    setText(String.valueOf(ks));
  }


  /**
   * You'll never guess.
   * Spoilers: Parses the text in the field into a keystroke.
   * Will return null to indicate that no keystroke is set and/or a setting of
   * "none" is desired by the user.
   */
  public KeyStroke getKeyStroke(){
    return KeyStroke.getKeyStroke(getText());
  }

}

