package legacy.machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface IProgram {
    boolean loadIntoMemory(int offset, IMemory mem);
}