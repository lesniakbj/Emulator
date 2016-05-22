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
        setInstructionPointer(0);
        setStackPointer(2048);
    }

    @Override
    public IInstruction fetch(IMemory ins) {
        logger.debug("Fetching instruction from {}", ins);
        if (getInstructionPointer() >= ins.getSize()) {
            logger.debug("Instruction pointer out of bounds!");
            return null;
        }
        short insShort = ins.getMemoryWord(getInstructionPointer());
        setInstructionPointer(getInstructionPointer() + 2);
        return new ChipEightRawInstruction(insShort);
    }

    @Override
    public ICPUOpcode decode(IInstruction ins) {
        logger.debug("Decoding memory fetched: {}", ins);
        if (ins == null) {
            return null;
        }
        short insShort = ins.getInstruction();
        return new ChipEightOpcode(insShort);
    }

    @Override
    public boolean execute(ICPUOpcode ins) {
        logger.debug("Executing instruction: {}", ins);

        return ins != null;

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
