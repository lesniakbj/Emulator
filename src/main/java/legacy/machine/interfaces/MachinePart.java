package legacy.machine.interfaces;

/**
 * Created by Brendan on 5/17/2016.
 * <p>
 * Marker interface so that we know that something
 * can be attached to a legacy.machine.
 */
public interface MachinePart {
    /* Identifiers required */
    Identifier getPartName();

    /* Required utility functions */
    boolean test();

    enum Identifier {
        CPU,
        RAM,
        DISK,
        SCREEN,
        KEYBOARD
    }
}
