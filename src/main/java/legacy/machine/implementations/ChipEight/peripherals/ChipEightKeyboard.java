package legacy.machine.implementations.ChipEight.peripherals;

import legacy.machine.base.BaseKeyboard;
import legacy.machine.interfaces.IMachine;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightKeyboard, stubbed.
 */
public class ChipEightKeyboard extends BaseKeyboard {
    public ChipEightKeyboard() {
        setKeyboardName("Chip-8 Keyboard");
    }

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }
}
