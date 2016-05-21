package machine.implementations.ChipEight.keyboard;

import machine.interfaces.Peripheral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/17/2016.
 */
public class ChipEightKeyboard implements Peripheral {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightKeyboard.class);
    private static final Identifier partName = Identifier.KEYBOARD;

    public Identifier getPartName() {
        return partName;
    }

    @Override
    public boolean test() {
        return false;
    }

    @Override
    public Object getPeripheralState() {
        return null;
    }

    public String toString() {
        return "[KEYBOARD - " + getPartName() + "]";
    }
}
