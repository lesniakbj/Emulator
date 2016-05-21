package legacy.machine.interfaces;

/**
 * Created by Brendan on 5/17/2016.
 */
public interface Memory<I, W> extends MachinePart {
    Identifier getMemoryType();

    void setMemoryType(Identifier type);

    I getMemory(Integer offset);

    boolean setMemory(Integer location, I value);

    W getMemoryWord(Integer offset);

    boolean setMemoryWord(Integer location, W value);

    void extend(Integer size);

    void shrink(Integer target);

    int getSize();
}
