package machine.base;

import machine.interfaces.ICpu;

/**
 * Created by Brendan on 5/21/2016.
 */
public abstract class BaseCPU implements ICpu {
    private String cpuName;
    private boolean signalFlag;

    private int instructionPointer;
    private int stackPointer;

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public boolean getSignal() {
        return signalFlag;
    }

    public void setSignal(boolean signalFlag) {
        this.signalFlag = signalFlag;
    }

    public String toString() {
        return "[CPU - [CPU Name: " + cpuName + "]]";
    }

    public int getStackPointer() {
        return stackPointer;
    }

    public void setStackPointer(int stackPointer) {
        this.stackPointer = stackPointer;
    }

    public int getInstructionPointer() {
        return instructionPointer;
    }

    public void setInstructionPointer(int instructionPointer) {
        this.instructionPointer = instructionPointer;
    }
}
