package machine.base;

import machine.interfaces.IMemory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Brendan on 5/21/2016.
 */
public abstract class BaseMemoryBank implements IMemory {
    private static final Logger logger = LoggerFactory.getLogger(BaseMemoryBank.class);

    private String type;
    private byte[] memory;

    public byte[] getMemory() {
        return memory;
    }

    public void setMemory(byte[] memory) {
        this.memory = memory;
    }

    @Override
    public byte getMemory(int offset) {
        logger.info("Retrieving memory from {} at {}: {}", this, offset, getMemory()[offset]);
        return getMemory()[offset];
    }

    @Override
    public short getMemoryWord(int offset) {
        byte high = getMemory()[offset];
        byte low = getMemory()[offset + 1];
        logger.info("Memory word found to be in {}: {}{} {}", this, high, low, (short) (((high & 0xFF) << 8) | (low & 0xFF)));
        return (short) (((high & 0xFF) << 8) | (low & 0xFF));
    }

    @Override
    public boolean setMemory(int offset, byte value) {
        byte[] mem = getMemory();
        if (offset > mem.length) {
            return false;
        }
        mem[offset] = value;
        return true;
    }

    public boolean setMemoryWord(int offset, short value) {
        byte h = (byte) ((value >> 8) & 0x0F);
        byte l = (byte) (value & 0x0F);

        boolean one = setMemory(offset, h);
        boolean two = setMemory(offset + 1, l);

        return one && two;
    }

    public String toString() {
        return "[MEMORY - [Type: " + type + "], [Memory Array Size: " + memory.length + "]]";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
