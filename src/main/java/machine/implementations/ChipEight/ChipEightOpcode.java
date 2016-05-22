package machine.implementations.ChipEight;

import machine.interfaces.ICPUOpcode;
import utils.BinaryUtils;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightOpcode implements ICPUOpcode {
    private short opcode;
    private ChipEightCPUCommand command;

    public ChipEightOpcode(short b) {
        opcode = b;
        decode(b);
    }

    @Override
    public boolean decode(short b) {
        return false;
    }

    public String toString() {
        return "[OPCODE - [Opcode Byte: " + BinaryUtils.toHexShort(opcode) + "]]";
    }
}
