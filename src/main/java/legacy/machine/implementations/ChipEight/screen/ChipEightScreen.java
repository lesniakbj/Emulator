package legacy.machine.implementations.ChipEight.screen;

import legacy.machine.interfaces.Peripheral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/17/2016.
 */
public class ChipEightScreen implements Peripheral<ChipEightScreenState> {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightScreen.class);
    private static final Identifier partType = Identifier.SCREEN;

    @Override
    public ChipEightScreenState getPeripheralState() {
        return null;
    }

    @Override
    public Identifier getPartName() {
        return partType;
    }

    @Override
    public boolean test() {
        return false;
    }

    public String toString() {
        return "[SCREEN - " + getPartName() + "]";
    }
}
