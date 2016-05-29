package legacy.machine.implementations.ChipEight;

/**
 * Created by Brendan on 5/22/2016.
 */
public class ChipEightConstants {
    public static final int MEMORY_SIZE = 4096;
    public static final int PROGRAM_START = 0x200;
    public static final int STACK_POINTER_START = 0;
    public static final int CYCLE_DELAY = 1000;

    public static final int NUMBER_REGISTERS = 17;
    public static final int REGISTER_I = 16;

    public static final int NUMBER_SPECIAL_REGISTERS = 2;
    public static final int REGISTER_DELAY = 0;
    public static final int REGISTER_SOUND = 1;
    public static final double RATE_OF_DECAY = 16.667; // 60 Hz

    // How many 0x00 instructions in a row do we need
    // to see before we declare there is an error in
    // the machine?
    public static final int MEMORY_RUNOFF = 5;

    public static final int SCREEN_WIDTH = 64;
    public static final int SCREEN_HEIGHT = 32;
}
