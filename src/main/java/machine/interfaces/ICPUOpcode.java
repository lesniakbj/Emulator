package machine.interfaces;

import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcodes;

/**
 * Created by Brendan on 5/21/2016.
 */
public interface ICPUOpcode {
    boolean decode();

    boolean execute();

    ChipEightOpcodes.CommandType getCommandType();

    IMachine attachedMachine();
}
