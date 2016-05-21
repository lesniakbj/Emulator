package machine.interfaces;

/**
 * Created by Brendan on 5/17/2016.
 * <p>
 * This is an extension of the MachinePart marker
 * interface that marks that a MachinePart is of
 * a specific type, in this case a CPU.
 */
public interface CPU<T> extends MachinePart {
    void signalTimer();

    boolean hasTimerSignal();

    void resetTimerSignal();

    CodedInstruction fetch(Memory mem);

    CPUOpcode decode(CodedInstruction memoryLoc);

    boolean execute(CPUOpcode ins);
}
