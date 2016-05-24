package machine.implementations.ChipEight;

import machine.base.BaseMachine;
import machine.implementations.ChipEight.program.ChipEightProgram;
import machine.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Brendan on 5/21/2016.
 *
 * Default implementation of a ChipEightMachine, overrides the
 * methods that it needs to from both BaseMachine and the
 * IMachine interface.
 */
public class ChipEightMachine extends BaseMachine {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightMachine.class);
    private static int cycle_count = 0;
    private long taskTimerTime;

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

        if (program == null) {
            logger.debug("No program, loading test program!");
            setProgram(new ChipEightProgram("test.program"));
        }

        taskTimerTime = 0;

        logger.debug("New Machine Created to:\n{}", this);
    }

    @Override
    public boolean turnOn() {
        logger.info("Machine is being turned on!");

        setIsRunning(true);
        setRunningTime(0);
        startSystemTimer();

        return loadProgram(getDefaultProgram());
    }

    private void startSystemTimer() {
        getScheduler().scheduleAtFixedRate(() -> {
            long time = getRunningTime();
            time++;

            if (time - taskTimerTime >= 1000) {
                logger.debug("Should be running a task...");
                getCPU().signal();
                taskTimerTime = time;
            }
        }, 0, 1, TimeUnit.NANOSECONDS);
    }

    @Override
    public boolean turnOff() {
        logger.info("Machine is being turned off!");
        getScheduler().shutdownNow();
        return true;
    }

    @Override
    public void run() {
        logger.info("Machine has turned on... Beginning main loop...");
        while (isRunning()) {
            boolean noError = runCycle(getCPU());
            if (getCPU().hasSignal()) {
                // Do something now that the timer has fired...
                logger.debug("Timer has signaled!");
                //cpu.updateTimers();

                getCPU().resetSignal();
            }

            if (!noError) {
                logger.debug("Error (or other shutdown reason) detected!");
                setIsRunning(false);
            }
        }
    }

    private boolean runCycle(ICpu cpu) {
        cycle_count++;
        if (cycle_count >= ChipEightConstants.CYCLE_DELAY) {
            cycle_count = 0;

            logger.info("Running a single CPU Cycle!");
            IInstruction ins = cpu.fetch();
            ICPUOpcode op = cpu.decode(ins);
            return cpu.execute(op);
        } else {
            return true;
        }
    }

    @Override
    public boolean attach(IMachinePart machinePart) {
        logger.debug("Attempting to attach: {}", machinePart);

        if (machinePart instanceof ICpu) {
            setCPU((ICpu) machinePart);
            return machinePart.attach(this);
        }

        if (machinePart instanceof IRam) {
            setRam((IRam) machinePart);
            return machinePart.attach(this);
        }

        if (machinePart instanceof IDisk) {
            setDisk((IDisk) machinePart);
            return machinePart.attach(this);
        }

        if (machinePart instanceof IScreen) {
            setScreen((IScreen) machinePart);
            return machinePart.attach(this);
        }

        if (machinePart instanceof IKeyboard) {
            setKeyboard((IKeyboard) machinePart);
            return machinePart.attach(this);
        }

        return false;
    }

    @Override
    public boolean loadProgram(IProgram program) {
        setProgramLoaded(program.loadIntoMemory(ChipEightConstants.PROGRAM_START, getRAM()));
        return isProgramLoaded();
    }
}
