package utils;

/**
 * Created by Brendan on 5/20/2016.
 */
public class BinaryUtils {
    public static String toBinaryString(Byte b) {
        return String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
    }
}
