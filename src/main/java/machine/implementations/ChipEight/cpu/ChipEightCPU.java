package machine.implementations.ChipEight.cpu;

import machine.implementations.ChipEight.memory.EightBitMemoryBank;
import machine.interfaces.CPU;
import machine.interfaces.CPUOpcode;
import machine.interfaces.CodedInstruction;
import machine.interfaces.Memory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/17/2016.
 */
public class ChipEightCPU implements CPU<Byte> {
    public static final long TIMER_DELAY = 17;
    private static final Logger logger = LoggerFactory.getLogger(ChipEightCPU.class);
    private static final Identifier partName = Identifier.CPU;
    private static boolean timerSignal;
    private static Integer instructionPointer;

    public ChipEightCPU() {
        instructionPointer = 0;
    }

    @Override
    public CodedInstruction fetch(Memory mem) {
        EightBitMemoryBank ebMem = (EightBitMemoryBank) mem;
        if (instructionPointer == ebMem.getAllMemory().size() - 1) {
            logger.debug("Returning null!");
            return null;
        }

        byte m = ebMem.getMemory(instructionPointer);
        instructionPointer++;
        logger.debug("[{} / {}]: {}", instructionPointer - 1, ebMem.getAllMemory().size(), m);
        return new ChipEightRawInstruction(m);
    }

    @Override
    public CPUOpcode decode(CodedInstruction codedIns) {
        //InstructionDecoder.decode(memoryLoc);
        logger.debug("Decoding memory value: {}", codedIns);
        return null;
    }

    @Override
    public boolean execute(CPUOpcode ins) {
        if (ins == null) {
            // should be false
            return true;
        }
        return true;
    }

    @Override
    public void signalTimer() {
        timerSignal = true;
    }

    @Override
    public boolean hasTimerSignal() {
        return timerSignal;
    }

    @Override
    public void resetTimerSignal() {
        timerSignal = false;
    }

    @Override
    public Identifier getPartName() {
        return partName;
    }

    @Override
    public boolean test() {
        return false;
    }

    public String toString() {
        return "[CPU - " + partName + "]";
    }
}
