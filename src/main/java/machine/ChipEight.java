package machine;

/**
 * Created by Brendan on 5/24/2016.
 */
public class ChipEight {
    /* Addressing Constants */
    public static final short MAX_ADDRESSABLE_RANGE = 4096; // 0x1000
    public static final short MEMORY_BEGIN = 0;             // 0x0000
    public static final short MEMORY_END = 4095;            // 0x0FFF

    /* Memory and Program Constants */
    public static final short RESERVED_BEGIN = 0x000;
    public static final short RESERVED_END = 0x1FF;
    public static final short PROGRAM_BEGIN = 0x200;

    /* Registers and Counters */
    public static final short NUMBER_OF_REGISTERS_8_BIT = 15;
    public static final short FLAGS_REGISTERS_8_BIT = 1;
    public static final short SPECIAL_REGISTERS_8_BIT = 2;
    public static final double REGISTER_DECAY_RATE = 16.6667;

    /* Stack */
    public static final short MAX_STACK_DEPTH = 16;

    /* Display */
    public static final short DISPLAY_WIDTH = 64;
    public static final short DISPLAY_HEIGHT = 32;
    public static final short EXTENDED_DISPLAY_WIDTH = 128;
    public static final short EXTENDED_DISPLAY_HEIGHT = 64;

    /* Sprite */
    public static final short SPRITE_MAX_DATA_LENGTH = 15;
    public static final short SPRITE_MAX_WIDTH = 8;
    public static final short SPRITE_MAX_HEIGHT = 15;
}
