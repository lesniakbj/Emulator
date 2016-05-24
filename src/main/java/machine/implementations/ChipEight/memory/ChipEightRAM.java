package machine.implementations.ChipEight.memory;

import machine.base.BaseMemoryBank;
import machine.implementations.ChipEight.ChipEightConstants;
import machine.interfaces.IMachine;
import machine.interfaces.IRam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightRAM extends BaseMemoryBank implements IRam {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightRAM.class);

    public ChipEightRAM() {
        this(ChipEightConstants.MEMORY_SIZE);
    }

    public ChipEightRAM(int size) {
        setMemory(new byte[size]);
        setType("RAM");
    }

    @Override
    public void extend(int size) {

    }

    @Override
    public void shrink(int target) {

    }

    @Override
    public int getSize() {
        return getMemory().length;
    }

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }
}
