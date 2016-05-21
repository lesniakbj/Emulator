package machine.implementations.ChipEight.cpu;

import machine.interfaces.CodedInstruction;

/**
 * Created by Brendan on 5/20/2016.
 */
public class ChipEightRawInstruction implements CodedInstruction {
    private Byte codedInstruction;

    public ChipEightRawInstruction(byte m) {
        codedInstruction = m;
    }

    public Byte getInstruction() {
        return codedInstruction;
    }
}
