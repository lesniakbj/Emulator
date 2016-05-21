package legacy.machine.interfaces;

/**
 * Created by Brendan on 5/17/2016.
 * <p>
 * Marker interface used to indicate that something
 * is a Machine
 */
public interface Machine {
    /* Functional parts of a legacy.machine */
    void run();

    boolean turnOn();

    boolean turnOff();

    /* Construction of a legacy.machine */
    boolean attach(MachinePart part);

    boolean test();
}
