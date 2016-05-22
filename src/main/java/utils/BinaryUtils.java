package utils;

/**
 * Created by Brendan on 5/20/2016.
 */
public class BinaryUtils {
    public static String toBinaryString(byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }

    public static String toHexByte(byte b) {
        return toHex(b, 2);
    }

    public static String toHexShort(short b) {
        return toHex(b, 4);
    }

    private static String toHex(Number b, int len) {
        return String.format("0x%0" + len + "X", b);
    }
}
