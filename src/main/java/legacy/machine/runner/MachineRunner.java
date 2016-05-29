package legacy.machine.runner;

import legacy.machine.builder.MachineBuilder;
import legacy.machine.interfaces.IMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 *
 * Runs Machines that conform to the IMachine interface.
 */
public class MachineRunner {
    private static final Logger logger = LoggerFactory.getLogger(MachineRunner.class);

    private static IMachine machine;

    public MachineRunner() {
        this(MachineBuilder.getDefaultMachine());
    }

    public MachineRunner(IMachine machine) {
        MachineRunner.machine = machine;
    }

    public static void main(String[] args) {
        MachineRunner runner = new MachineRunner();

        logger.info("Starting up a new Machine...");
        runner.getMachine().turnOn();
        runner.getMachine().run();
        runner.getMachine().turnOff();
    }

    public IMachine getMachine() {
        return machine;
    }
}