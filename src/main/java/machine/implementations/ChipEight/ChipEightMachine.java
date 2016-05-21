package machine.implementations.ChipEight;

import machine.base.BaseMachine;
import machine.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 *
 * Default implementation of a ChipEightMachine, overrides the
 * methods that it needs to from both BaseMachine and the
 * IMachine interface.
 */
public class ChipEightMachine extends BaseMachine {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightMachine.class);

    public ChipEightMachine() {
        this("Chip-8");
    }

    public ChipEightMachine(String name) {
        this(name, null);
    }

    public ChipEightMachine(String name, IProgram program) {
        setMachineName(name);
        setIsRunning(false);
        setRunningTime(0);
        setProgram(program);
        setProgramLoaded(false);

        logger.debug("New Machine Created to:\n{}", this);
    }

    @Override
    public boolean turnOn() {
        logger.info("Machine is being turned on!");
        return false;
    }

    @Override
    public boolean turnOff() {
        logger.info("Machine is being turned off!");
        return false;
    }

    @Override
    public void run() {
        logger.info("Machine has turned on... Beginning main loop...");
    }

    @Override
    public boolean attach(IMachinePart machinePart) {
        logger.debug("Attempting to attach: {}", machinePart);

        if (machinePart instanceof ICpu) {
            setCPU((ICpu) machinePart);
            return true;
        }

        if (machinePart instanceof IRam) {
            setRam((IRam) machinePart);
            return true;
        }

        if (machinePart instanceof IDisk) {
            setDisk((IDisk) machinePart);
            return true;
        }

        if (machinePart instanceof IScreen) {
            setScreen((IScreen) machinePart);
            return true;
        }

        if (machinePart instanceof IKeyboard) {
            setKeyboard((IKeyboard) machinePart);
            return true;
        }

        return false;
    }

    @Override
    public boolean loadProgram(IProgram program) {
        if (program == null) {
            logger.info("Program was null! Loading default program...");
            setProgramLoaded(getDefaultProgram().loadIntoMemory(0, getRAM()));
            return isProgramLoaded();
        }
        logger.info("Loading program {}", program);
        setProgramLoaded(program.loadIntoMemory(0, getRAM()));
        return isProgramLoaded();
    }
}
