package machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface ICpu extends IMachinePart {
    IInstruction fetch();

    ICPUOpcode decode(IInstruction memoryLoc);

    boolean execute(ICPUOpcode canExecute);

    void signal();

    boolean hasSignal();

    void resetSignal();
}
