package machine.implementations.ChipEight.peripherals;

import machine.base.BaseScreen;
import machine.interfaces.IMachine;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightScreen, stubbed.
 */
public class ChipEightScreen extends BaseScreen {
    public ChipEightScreen() {
        setScreenName("Chip-8 Screen");
    }

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }
}
