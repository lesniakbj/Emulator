package machine.implementations.ChipEight;

import machine.base.BaseCPU;
import machine.interfaces.CPUOpcode;
import machine.interfaces.IInstruction;
import machine.interfaces.IMemory;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * Default implementation of a ChipEightCPU, stubbed.
 */
public class ChipEightCPU extends BaseCPU {
    public ChipEightCPU() {
        setCpuName("Chip-8 CPU");
    }

    @Override
    public IInstruction fetch(IMemory mem) {
        return null;
    }

    @Override
    public CPUOpcode decode(IInstruction memoryLoc) {
        return null;
    }

    @Override
    public boolean execute(CPUOpcode ins) {
        return false;
    }
}
