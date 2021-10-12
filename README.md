### Mr Robot
###### Keypresser extraordinaire

Originally created simply to robo-press a key every so often to keep a computer from falling asleep (and to experiment with the java Robot class). Evolved somewhat beyond that, so plan for the future is to turn this into a user-configurable macro system that can robo-press many keys in robo-sequence with robo-delays included as desired.

--------
### How to Use

Simply run the program and, barring any errors with the system, it will begin doing its job with default parameters.

At the time of writing, the default behavior is to press and release the NUM_LOCK and CONTROL keys every 20 seconds. The NUM_LOCK keypress in included so the indicator light on the keyboard can be observed to toggle.

The default settings are kind of annoying, since they were intended for testing while developing and have stayed with their original values (as it tends to go), so it is recommended to at least adjust the interval to something more appropriate by clicking the settings button. Currently, only the interval is adjustable; configuration for the keypress is something there are pieces in place for, but full implementation hasn't yet happened.

--------
### Hotkeys and Such

All hotkeys are for the main window unless otherwise noted.

- ESC  
The escape key can be pressed in the main window to exit the program. When some settings windows are open, escape can be pressed to close said settings window. On some settings windows, escape can be pressed to do nothing, but this is ultimately intended to be made consistent (i.e. close the active window/sub-window).

- s  
The "s" key can be pressed to open a preview of the custom settings window. The settings there can be edited and saved, but do not currently take effect.

- S  
The "S" (shift + s) key can be pressed to dump some info to an associated output stream (e.g. the terminal running MrRobot).

- And more!  
There are a couple random other keys that can be pressed to do uninteresting things, added for testing purposes.

--------
### WhichKeyField

I think it turned out pretty cool. A customization of a JTextField to detect and record a keypress along with modifiers when the field is focused. You can see it in action in the custom settings window (hotkey "s", "Keystroke" field).

