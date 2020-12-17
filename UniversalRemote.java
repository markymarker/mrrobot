// Mark Fletcher
// 2020-12-11

import java.awt.Color;
import java.awt.Frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;


/**
 * Settings window.
 * No time, I'll explain later!
 */
class UniversalRemote extends JDialog {

  private static final String ACTION_SAVE = "Save";
  private static final String ACTION_RESET = "Reset";
  private static final String ACTION_CANCEL = "Cancel";

  private static final Border BORDER_ACTIVE = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GREEN, Color.GREEN);
  private static final Border BORDER_NORMAL = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.GRAY);
  private static final Border BORDER_ERROR = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.RED, Color.RED);
  //private static final Border BORDER_NORMAL = BorderFactory.createLineBorder(Color.GRAY, 1, true);
  //private static final Border BORDER_ERROR = BorderFactory.createLineBorder(Color.RED, 2, true);


  Values settings;

  // Window interaction elements
  JButton bsave;
  JButton breset;
  JButton bcancel;

  // Label elements
  JLabel linterval;
  JLabel lkeystroke;

  // Settings input elements
  JTextField finterval;
  WhichKeyField fkeystroke;


  /**
   * Create a new UniversalRemote.
   * The provided owner is used only for window positioning.
   * The provided values object will be updated directly by this class.
   *
   * @param owner The owning frame for this interface
   * @param sharedVals The values object to read from and store to
   */
  public UniversalRemote(Frame owner, Values sharedVals){
    super(owner, "RoboSet The RoboSettings", JDialog.ModalityType.DOCUMENT_MODAL);
    setLocationRelativeTo(owner);
    getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

    settings = sharedVals;

    JPanel tp;  // Reusable temp panel var for groupings

    linterval = new JLabel("Interval (s)");
    finterval = new JTextField(8);
    tp = new JPanel();
    tp.add(linterval);
    tp.add(finterval);
    add(tp);

    lkeystroke = new JLabel("Keystroke");
    fkeystroke = new WhichKeyField(25);
    fkeystroke.setBorders(BORDER_ACTIVE, BORDER_NORMAL);
    tp = new JPanel();
    tp.add(lkeystroke);
    tp.add(fkeystroke);
    add(tp);

    bsave = new JButton(ACTION_SAVE);
    breset = new JButton(ACTION_RESET);
    bcancel = new JButton(ACTION_CANCEL);
    tp = new JPanel();
    tp.add(bcancel);
    tp.add(breset);
    tp.add(bsave);
    add(tp);

    loadSettings();  // Will also set [normal] borders
    registerListeners();

    pack();
  }


  /**
   * Set all of the field borders to their normal appearance.
   */
  private void setNormalBorders(){
    finterval.setBorder(BORDER_NORMAL);
    fkeystroke.setBorder(BORDER_NORMAL);
  }


  /**
   * Define and set the action (and etc.) listener(s) for input(s).
   * The listener(s) to use are defined in-place in this method and are not
   * accessible elsewhere in this class.
   */
  private void registerListeners(){
    ActionListener al = new ActionListener(){
      public void actionPerformed(ActionEvent e){
        switch(e.getActionCommand()){
        case ACTION_SAVE:
          saveSettings();
        break;
        case ACTION_RESET:
          loadSettings();
        break;
        case ACTION_CANCEL:
          loadSettings();
          takethAway();
        break;
        }
      }
    };

    bsave.addActionListener(al);
    breset.addActionListener(al);
    bcancel.addActionListener(al);
  }


  /**
   * Show this settings window.
   */
  public void giveth(){
    // TODO: Reset location?
    setVisible(true);
  }


  /**
   * Hide this settings window.
   */
  public void takethAway(){
    setVisible(false);
  }


  /**
   * Load values into fields.
   * Refreshes the values shown in the fields to match what is in the values
   * object. This will also reset all of the field borders to neutral.
   */
  public void loadSettings(){
    finterval.setText(String.valueOf(settings.interval));
    fkeystroke.setKeyStroke(settings.key);

    setNormalBorders();
  }


  /**
   * Persist values in fields to values object.
   * This is triggered by the save button. If all is successful, the settings
   * window will hide. If any errors occur, a notice will be shown -- failure
   * actions are handled on a per-field basis by the relevant save methods. A
   * failure in any field(s) will not prevent other valid values from saving.
   */
  public void saveSettings(){
    boolean[] outcomes = {
      saveInterval(),
      saveKeystroke()
    };

    boolean allPassed = true;
    for(boolean o : outcomes){
      allPassed &= o;
      // Go through all, no early exit; may do more here later
    }

    if(!allPassed){
      JOptionPane.showMessageDialog(
        this,
        "Something was not valid, bub", "WTF",
        JOptionPane.ERROR_MESSAGE
      );
    } else {
      takethAway();
    }
  }


// // SAVERS // //

  /**
   * Saver for the interval field.
   * Expects to be able to parse the entered value to an integer.
   *
   * @return TRUE on successful parsing and saving, FALSE on failure
   */
  private boolean saveInterval(){
    finterval.setBorder(BORDER_NORMAL);
    try {
      settings.interval = Integer.parseInt(finterval.getText());
      return true;
    } catch(Exception e){
      finterval.setBorder(BORDER_ERROR);
      return false;
    }
  }


  /**
   * Saver for the keystroke field.
   * Field contents are managed programmatically, so hopefully jank junk just
   * doesn't happen. May save a null if unparsable, but that's okay.
   *
   * @return TRUE on successful parsing and saving, FALSE on failure
   */
  private boolean saveKeystroke(){
    settings.key = fkeystroke.getKeyStroke();
    return true;  // There's no way this could fail!
  }

}

