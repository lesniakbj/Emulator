package legacy.machine.implementations.ChipEight;

import legacy.machine.implementations.ChipEight.cpu.ChipEightCPU;
import legacy.machine.implementations.ChipEight.memory.EightBitMemoryBank;
import legacy.machine.implementations.ChipEight.program.ChipEightProgram;
import legacy.machine.interfaces.*;
import legacy.machine.interfaces.MachinePart.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.GlobalUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brendan on 5/17/2016.
 */
public class ChipEightMachine implements Machine {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightMachine.class);
    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    private String machineName;
    private boolean isRunning;
    private volatile long runningTime;

    private Map<Identifier, MachinePart> parts;
    private Program requestedProgram;

    private volatile long timerTaskTime;


    public ChipEightMachine() {
        this("CHIP 8");
    }

    public ChipEightMachine(String name) {
        this(name, null);
    }

    public ChipEightMachine(String name, Program program) {
        machineName = name;
        parts = new HashMap<>();
        requestedProgram = program;
    }

    private boolean loadProgram(Program chipEightProgram, MachinePart mem) {
        ChipEightProgram.class.cast(chipEightProgram).loadIntoMemory(0, mem);
        logger.info("Loading new program into {} memory {}", chipEightProgram, mem);
        logger.debug("Total RAM usage: {}B/{}B", ((ChipEightProgram) chipEightProgram).getSize(), ((EightBitMemoryBank) mem).getSize());
        return true;
    }

    public boolean turnOn() {
        logger.info("Turning on {}!", machineName);
        isRunning = true;
        timer.scheduleAtFixedRate(globalTimer(), 1, 1, TimeUnit.MILLISECONDS);

        boolean successfulLoad = false;
        if (GlobalUtils.TEST || requestedProgram == null) {
            logger.info("Loading test program...");
            successfulLoad = loadProgram(new ChipEightProgram("test.program"), parts.get(Identifier.RAM));
        } else {
            successfulLoad = loadProgram(requestedProgram, parts.get(Identifier.RAM));
        }

        return successfulLoad;
    }

    private Runnable globalTimer() {
        return () -> {
            runningTime++;

            if (runningTime - timerTaskTime >= ChipEightCPU.TIMER_DELAY) {
                logger.debug("Should be running a task...");
                CPU cpu = (CPU) parts.get(Identifier.CPU);
                cpu.signalTimer();
                timerTaskTime = runningTime;
            }
        };
    }

    public boolean turnOff() {
        logger.info("Turning off {}!", machineName);
        isRunning = false;
        timer.shutdown();
        return true;
    }

    public boolean attach(MachinePart part) {
        logger.info("Attaching new part... {}", part);
        parts.put(part.getPartName(), part);
        return true;
    }

    @Override
    public boolean test() {
        return false;
    }

    public void run() {
        CPU cpu = (CPU) parts.get(Identifier.CPU);
        Memory mem = (Memory) parts.get(Identifier.RAM);
        while (isRunning) {
            boolean noError = runCycle(cpu, mem);
            logger.debug("There is no error? {}", noError);

            if (cpu.hasTimerSignal()) {
                // Do something now that the timer has fired...
                logger.debug("Timer has signaled!");
                //cpu.updateTimers();

                cpu.resetTimerSignal();
            }

            if (!noError) {
                isRunning = false;
                return;
            }
        }
    }

    private boolean runCycle(CPU<Byte> cpu, Memory<Byte, Short> mem) {
        logger.info("Running a single CPU Cycle!");
        // Fetch
        // Decode
        // Execute
        CodedInstruction codedInstruction = cpu.fetch(mem);
        if (codedInstruction == null) {
            return false;
        }
        CPUOpcode instruction = cpu.decode(codedInstruction);
        return cpu.execute(instruction);
    }

    public String toString() {
        return "[MACHINE - " + machineName + "]";
    }
}
