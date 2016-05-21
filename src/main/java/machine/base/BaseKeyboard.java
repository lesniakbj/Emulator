package machine.base;

import machine.interfaces.IKeyboard;

/**
 * Created by Brendan on 5/21/2016.
 */
public abstract class BaseKeyboard implements IKeyboard {
    private String keyboardName;

    public String getKeyboardName() {
        return keyboardName;
    }

    public void setKeyboardName(String keyboardName) {
        this.keyboardName = keyboardName;
    }

    public String toString() {
        return "[KEYBOARD - [Keyboard Name: " + keyboardName + "]]";
    }
}
