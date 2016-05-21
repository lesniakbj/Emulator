package machine.implementations.ChipEight;

import machine.base.BaseMemoryBank;
import machine.interfaces.IDisk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightDisk extends BaseMemoryBank implements IDisk {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightDisk.class);

    public ChipEightDisk() {
        this(4096);
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
}