package machine.interfaces;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface IMachine {
    boolean turnOn();

    boolean turnOff();

    void run();

    boolean attach(IMachinePart machine);

    boolean loadProgram(IProgram program);
}
