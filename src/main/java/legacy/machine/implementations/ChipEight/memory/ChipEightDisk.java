package legacy.machine.implementations.ChipEight.memory;

import legacy.machine.base.BaseMemoryBank;
import legacy.machine.implementations.ChipEight.ChipEightConstants;
import legacy.machine.interfaces.IDisk;
import legacy.machine.interfaces.IMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightDisk extends BaseMemoryBank implements IDisk {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightDisk.class);

    public ChipEightDisk() {
        this(ChipEightConstants.MEMORY_SIZE);
    }

    public ChipEightDisk(int size) {
        setMemory(new byte[size]);
        setType("DISK");
    }

    @Override
    public void extend(int size) {

    }

    @Override
    public void shrink(int target) {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean attach(IMachine machine) {
        setAttachedMachine(machine);
        return true;
    }
}