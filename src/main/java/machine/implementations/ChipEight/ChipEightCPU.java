package machine.implementations.ChipEight;

import machine.base.BaseCPU;
import machine.interfaces.ICPUOpcode;
import machine.interfaces.IInstruction;
import machine.interfaces.IMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightCPU, stubbed.
 */
public class ChipEightCPU extends BaseCPU {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightCPU.class);

    public ChipEightCPU() {
        setCpuName("Chip-8 CPU");
    }

    @Override
    public IInstruction fetch(IMemory mem) {
        logger.debug("Fetching instruction from {}", mem);
        return null;
    }

    @Override
    public ICPUOpcode decode(IInstruction memory) {
        logger.debug("Decoding memory fetched: {}", memory);
        return null;
    }

    @Override
    public boolean execute(ICPUOpcode ins) {
        logger.debug("Executing instruction: {}", ins);
        return false;
    }

    @Override
    public void signal() {
        setSignal(true);
    }

    @Override
    public boolean hasSignal() {
        return getSignal();
    }

    @Override
    public void resetSignal() {
        setSignal(false);
    }
}
