package machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface ICpu extends IMachinePart {
    IInstruction fetch(IMemory mem);

    CPUOpcode decode(IInstruction memoryLoc);

    boolean execute(CPUOpcode ins);
}
