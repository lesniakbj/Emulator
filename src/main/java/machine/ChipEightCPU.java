package machine;

import java.util.Stack;

/**
 * Created by Brendan on 5/24/2016.
 */
public class ChipEightCPU {
    private static byte[] registers;
    private static byte[] specialRegisters;

    private static short indexRegister;
    private static short programCounter;

    private static Stack<Short> callStack;
    private static byte stackPointer;

    public ChipEightCPU() {
        // Create the base registers and the timer and sound registers
        registers = new byte[ChipEight.NUMBER_OF_REGISTERS_8_BIT + ChipEight.FLAGS_REGISTERS_8_BIT];
        specialRegisters = new byte[ChipEight.SPECIAL_REGISTERS_8_BIT];

        // Set the program counter to the expected location
        // of the initially loaded program then the index
        // register to its base value.
        programCounter = ChipEight.PROGRAM_BEGIN;
        indexRegister = 0x000;

        // Setup the stack so that it can be used
        // by calling programs.
        callStack = new Stack<>();
        stackPointer = 0;
    }

    public static byte[] getRegisters() {
        return registers;
    }

    public static byte[] getSpecialRegisters() {
        return specialRegisters;
    }

    public static short getIndexRegister() {
        return indexRegister;
    }

    public static short getProgramCounter() {
        return programCounter;
    }

    public static Stack<Short> getCallStack() {
        return callStack;
    }

    public static byte getStackPointer() {
        return stackPointer;
    }
}
