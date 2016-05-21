package machine.base;

import machine.interfaces.IScreen;

/**
 * Created by Brendan on 5/21/2016.
 */
public class BaseScreen implements IScreen {
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
}
