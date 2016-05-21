package legacy.machine.builder;

import legacy.machine.implementations.ChipEight.ChipEightMachine;
import legacy.machine.implementations.ChipEight.cpu.ChipEightCPU;
import legacy.machine.implementations.ChipEight.keyboard.ChipEightKeyboard;
import legacy.machine.implementations.ChipEight.memory.EightBitMemoryBank;
import legacy.machine.implementations.ChipEight.screen.ChipEightScreen;
import legacy.machine.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/17/2016.
 */
public class MachineBuilder {
    private static final Logger logger = LoggerFactory.getLogger(MachineBuilder.class);

    private Machine buildCache;

    public static Machine getDefaultMachine() {
        return new MachineBuilder().newDefaultMachine().addCPU(new ChipEightCPU())
                .addMemory(MachinePart.Identifier.RAM, new EightBitMemoryBank(4086))
                .addMemory(MachinePart.Identifier.DISK, new EightBitMemoryBank(4086))
                .addPeripheral(new ChipEightKeyboard()).addPeripheral(new ChipEightScreen())
                .build();
    }

    public MachineBuilder newDefaultMachine() {
        logger.info("Getting new default legacy.machine...");
        this.buildCache = new ChipEightMachine();
        return this;
    }

    public MachineBuilder addCPU(CPU processor) {
        logger.info("Attaching a CPU... {}", processor);
        this.buildCache.attach(processor);
        return this;
    }

    public MachineBuilder addMemory(MachinePart.Identifier type, Memory mem) {
        logger.info("Attaching Memory... [{}, {}]", type, mem.getSize());
        mem.setMemoryType(type);
        this.buildCache.attach(mem);
        return this;
    }

    public MachineBuilder addPeripheral(Peripheral peripheral) {
        logger.info("Adding a Peripheral... {}", peripheral);
        this.buildCache.attach(peripheral);
        return this;
    }

    public Machine build() {
        logger.info("Building final legacy.machine... {}", this.buildCache);
        return this.buildCache;
    }
}
