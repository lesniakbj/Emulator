package machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface ICpu extends IMachinePart {
    IInstruction fetch(IMemory mem);

    ICPUOpcode decode(IInstruction memoryLoc);

    boolean execute(ICPUOpcode ins);

    void signal();

    boolean hasSignal();

    void resetSignal();
}
