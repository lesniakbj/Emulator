package machine.implementations.ChipEight.cpu;

import machine.base.BaseCPU;
import machine.implementations.ChipEight.cpu.opcode.CPUOperations;
import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcode;
import machine.implementations.ChipEight.cpu.opcode.ChipEightRawInstruction;
import machine.interfaces.ICPUOpcode;
import machine.interfaces.IInstruction;
import machine.interfaces.IMachine;
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
    private static int emptyInstructions = 0;

    public ChipEightCPU() {
        setCpuName("Chip-8 CPU");
        setInstructionPointer(0);
        setStackPointer(2048);
    }

    @Override
    public IInstruction fetch() {
        IMemory mem = getAttachedMachine().getRAM();

        logger.debug("Fetching instruction from {}", mem);
        if (getInstructionPointer() >= mem.getSize()) {
            logger.debug("Instruction pointer out of bounds!");
            return null;
        }

        short insShort = mem.getMemoryWord(getInstructionPointer());

        if (insShort == 0) {
            emptyInstructions++;
        } else {
            emptyInstructions = (emptyInstructions == 0) ? 0 : emptyInstructions - 1;
        }

        logger.info("There are {} empty instructions.", emptyInstructions);
        if (emptyInstructions >= 5) {
            return null;
        }

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

        ICPUOpcode opCode = new ChipEightOpcode(getAttachedMachine(), insShort);
        opCode.decode();
        return opCode;
    }


    @Override
    public boolean execute(ICPUOpcode op) {
        logger.debug("Executing instruction!");
        if (op == null || op.getCommandType() == CPUOperations.CommandType.UNKNOWN) {
            return false;
        }

        op.execute();
        return true;
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

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }
}
