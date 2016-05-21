package legacy.machine.interfaces;

/**
 * Created by Brendan on 5/17/2016.
 */
public interface Peripheral<T> extends MachinePart {
    T getPeripheralState();
}
