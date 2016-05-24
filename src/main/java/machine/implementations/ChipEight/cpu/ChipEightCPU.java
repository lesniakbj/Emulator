package machine.implementations.ChipEight.cpu;

import machine.base.BaseCPU;
import machine.implementations.ChipEight.ChipEightConstants;
import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcode;
import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcodes;
import machine.implementations.ChipEight.cpu.opcode.ChipEightRawInstruction;
import machine.interfaces.ICPUOpcode;
import machine.interfaces.IInstruction;
import machine.interfaces.IMachine;
import machine.interfaces.IMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BinaryUtils;

import java.util.Stack;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightCPU, stubbed.
 */
public class ChipEightCPU extends BaseCPU {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightCPU.class);
    private static int emptyInstructions = 0;

    private static Stack<Integer> stack;
    private static int[] registers;
    private static int[] specialRegisters;

    public ChipEightCPU() {
        setCpuName("Chip-8 CPU");
        setInstructionPointer(ChipEightConstants.PROGRAM_START);
        setStackPointer(ChipEightConstants.STACK_POINTER_START);
        stack = new Stack<>();
        registers = new int[ChipEightConstants.NUMBER_REGISTERS];
        specialRegisters = new int[ChipEightConstants.NUMBER_SPECIAL_REGISTERS];
    }

    @Override
    public IInstruction fetch() {
        IMemory mem = getAttachedMachine().getRAM();

        logger.debug("Fetching instruction from {}", BinaryUtils.toHexShort((short) getInstructionPointer()));
        if (getInstructionPointer() >= mem.getSize()) {
            logger.debug("Instruction pointer out of bounds!");
            return null;
        }

        short insShort = mem.getMemoryWord(getInstructionPointer());

        /* Check to see if we have overrun program memory */
        if (insShort == 0) {
            emptyInstructions++;
        } else {
            emptyInstructions = (emptyInstructions == 0) ? 0 : emptyInstructions - 1;
        }

        logger.info("There are {} empty instructions.", emptyInstructions);
        if (emptyInstructions >= ChipEightConstants.MEMORY_RUNOFF) {
            logger.error("We have run over the Program Memory!");
            return null;
        }
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
        if (op == null || op.getCommandType() == ChipEightOpcodes.CommandType.UNKNOWN) {
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

    public void pushStack(int instructionPointer) {
        stack.push(instructionPointer);
        setStackPointer(getStackPointer() + 1);
    }

    public int getRegisterValue(int reg) {
        return registers[reg];
    }

    public void setRegisterValue(int reg, int val) {
        registers[reg] = val;
    }

    public void popStack() {
        short mem = (short) (stack.pop() & 0xFFFF);
        setInstructionPointer(mem + 2);
        setStackPointer(getStackPointer() - 1);
    }
}
