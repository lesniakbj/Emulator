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

    boolean isRunning();

    long getRunningTime();

    ICpu getCPU();

    IMemory getRAM();

    IMemory getDisk();

    IScreen getScreen();

    IKeyboard getKeyboard();

    IProgram getDefaultProgram();

    boolean isProgramLoaded();
}
