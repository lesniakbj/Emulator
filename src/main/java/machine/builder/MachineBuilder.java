package machine.builder;

import machine.base.BaseMachine;
import machine.implementations.ChipEight.*;
import machine.interfaces.IMachinePart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 *
 * Builds Machines based on the "attach" method present
 * on the IMachine interface.
 */
public class MachineBuilder {
    private static final Logger logger = LoggerFactory.getLogger(MachineBuilder.class);
    private BaseMachine machineCache;

    public static BaseMachine getDefaultMachine() {
        return new MachineBuilder().defaultMachine()
                .attachPart(new ChipEightCPU())
                .attachPart(new ChipEightRAM())
                .attachPart(new ChipEightDisk())
                .attachPart(new ChipEightScreen())
                .attachPart(new ChipEightKeyboard())
                .build();
    }

    private MachineBuilder defaultMachine() {
        machineCache = new ChipEightMachine();
        return this;
    }

    private MachineBuilder attachPart(IMachinePart part) {
        machineCache.attach(part);
        return this;
    }

    private BaseMachine build() {
        logger.debug("Machine that has been built:\n{}", machineCache);
        return machineCache;
    }
}
