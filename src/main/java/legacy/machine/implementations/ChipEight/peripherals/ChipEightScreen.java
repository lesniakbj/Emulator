package legacy.machine.implementations.ChipEight.peripherals;

import legacy.machine.base.BaseScreen;
import legacy.machine.implementations.ChipEight.ChipEightConstants;
import legacy.machine.interfaces.IMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightScreen, stubbed.
 */
public class ChipEightScreen extends BaseScreen {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightScreen.class);

    private static byte[][] screen;

    public ChipEightScreen() {
        setScreenName("Chip-8 Screen");
        screen = new byte[ChipEightConstants.SCREEN_WIDTH][ChipEightConstants.SCREEN_HEIGHT];
    }

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }

    @Override
    public void clearDisplay() {
        logger.debug("Clearing screen!");
        logger.debug("Screen was: [{}]", screen);
        for (int x = 0; x < screen.length; x++) {
            for (int y = 0; y < screen[x].length; y++) {
                screen[x][y] = 0;
            }
        }
        logger.debug("Screen is now: [{}]", screen);
    }
}
