### Mr Robot
###### Keypresser extraordinaire

Originally created simply to robo-press a key every so often to keep a computer from falling asleep (and to experiment with the java Robot class). Evolved somewhat beyond that, so plan for the future is to turn this into a user-configurable macro system that can robo-press many keys in robo-sequence with robo-delays included as desired.

--------
### How to Use

Simply run the program and, barring any errors with the system, it will begin doing its job with default parameters.

At the time of writing, the default behavior is to press and release the CONTROL key every 20 seconds. If the setting for keypress is cleared (by pressing DELETE while configuring it), keypress behavior will default to pressing the CONTROL and NUM_LOCK keys (the NUM_LOCK keypress is included so the indicator light on the keyboard can be observed to toggle).

The "Settings" button will open a window where the interval and key to press can be adjusted. Currently, any modifiers on the keypress are ignored (e.g. "CONTROL ALT S" will just result in an "S" keypress).

--------
### Hotkeys and Such

All hotkeys are for the main window unless otherwise noted.

- ESC  
The escape key can be pressed in the main window to exit the program. When some settings windows are open, escape can be pressed to close said settings window. On some settings windows, escape can be pressed to do nothing, but this is ultimately intended to be made consistent (i.e. close the active window/sub-window).

- s  
The "s" key can be pressed to open the custom settings window. This is the same action as clicking the "Settings" button on the interface.

- S  
The "S" (shift + s) key can be pressed to dump some info to an associated output stream (e.g. the terminal running MrRobot).

- And more!  
There are a couple random other keys that can be pressed to do uninteresting things, added for testing purposes.

--------
### WhichKeyField

I think it turned out pretty cool. A customization of a JTextField to detect and record a keypress along with modifiers when the field is focused. You can see it in action in the custom settings window (hotkey "s", "Keystroke" field).

