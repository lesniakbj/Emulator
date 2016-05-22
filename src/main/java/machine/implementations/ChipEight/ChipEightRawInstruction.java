package machine.implementations.ChipEight;

import machine.interfaces.IInstruction;
import utils.BinaryUtils;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightRawInstruction implements IInstruction {
    private short instruction;

    public ChipEightRawInstruction(short m) {
        instruction = m;
    }

    public short getInstruction() {
        return instruction;
    }

    public String getInstructionHex() {
        return BinaryUtils.toHexShort(instruction);
    }

    public String toString() {
        return "[INSTRUCTION - [Instruction Byte: " + getInstructionHex() + "]]";
    }
}
