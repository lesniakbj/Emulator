package machine.runner;

import machine.builder.MachineBuilder;
import machine.interfaces.Machine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/17/2016.
 */
public class MachineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MachineRunner.class);

    private static Machine machine;

    public MachineRunner() {
        this(MachineBuilder.getDefaultMachine());
    }

    public MachineRunner(Machine machine) {
        MachineRunner.machine = machine;
    }

    public static void main(String[] args) {
        logger.info("Starting up a new machine runner...");

        MachineRunner runner = new MachineRunner();
        runner.getMachine().turnOn();
        runner.getMachine().run();
        runner.getMachine().turnOff();
    }

    public Machine getMachine() {
        return machine;
    }
}
