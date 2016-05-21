package machine.implementations.ChipEight.memory;

import machine.interfaces.Memory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendan on 5/17/2016.
 */
public class EightBitMemoryBank implements Memory<Byte, Short> {
    private static final Logger logger = LoggerFactory.getLogger(EightBitMemoryBank.class);

    private Identifier partName;
    private List<Byte> memory;
    private Integer memorySize;

    public EightBitMemoryBank(Integer i) {
        memory = new ArrayList<>(i);
        memorySize = i;
        partName = Identifier.RAM;
    }

    public List<Byte> getAllMemory() {
        return memory;
    }

    public Identifier getMemoryType() {
        return partName;
    }

    public void setMemoryType(Identifier type) {
        partName = type;
    }

    public Byte getMemory(Integer offset) {
        return memory.get(offset);
    }

    public Short getMemoryWord(Integer offset) {
        byte low = getMemory(offset);
        byte high = getMemory(offset + 1);
        return (short) ((high << 8) + low);
    }

    public boolean setMemoryWord(Integer location, Short value) {
        byte high = (byte) ((value >> 8) & 0x0F);
        byte low = (byte) (value & 0x0F);

        boolean highSet = setMemory(location + 1, high);
        boolean lowSet = setMemory(location, low);

        return highSet && lowSet;
    }

    public boolean setMemory(Integer location, Byte value) {
        byte isSet = memory.set(location, value);
        return value.equals(isSet);
    }

    public void extend(Integer size) {
        int total = memorySize + size;
        List<Byte> newMem = new ArrayList<>(total);
        newMem.addAll(memory);

        memory = newMem;
        memorySize = total;
    }

    public void shrink(Integer target) {

    }

    @Override
    public int getSize() {
        return memorySize;
    }

    public Identifier getPartName() {
        return partName;
    }

    @Override
    public boolean test() {
        return false;
    }

    public String toString() {
        return "[MEMEORY - " + getPartName() +
                ", " + getMemoryType() +
                ", " + getSize() + "]";
    }
}
