package machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface IMemory extends IMachinePart {
    byte getMemory(int offset);

    short getMemoryWord(int offset);

    boolean setMemory(int offset, byte value);

    boolean setMemoryWord(int offset, short value);

    void extend(int size);

    void shrink(int target);

    int getSize();
}
