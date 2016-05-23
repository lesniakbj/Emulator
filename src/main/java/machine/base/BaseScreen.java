package machine.base;

import machine.interfaces.IMachine;
import machine.interfaces.IScreen;

/**
 * Created by Brendan on 5/21/2016.
 */
public abstract class BaseScreen implements IScreen {
    private IMachine attachedMachine;
    private String screenName;

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String toString() {
        return "[SCREEN - [Screen Name: " + screenName + "]]";
    }

    public IMachine getAttachedMachine() {
        return attachedMachine;
    }

    public void setAttachedMachine(IMachine attachedMachine) {
        this.attachedMachine = attachedMachine;
    }
}
