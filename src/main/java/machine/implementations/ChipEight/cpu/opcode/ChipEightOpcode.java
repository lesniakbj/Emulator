package machine.implementations.ChipEight.cpu.opcode;

import machine.implementations.ChipEight.cpu.ChipEightCPUExecutor;
import machine.interfaces.ICPUOpcode;
import machine.interfaces.IMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BinaryUtils;

import java.util.Map;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightOpcode implements ICPUOpcode {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightOpcode.class);
    private IMachine machine;
    private short rawOpcode;
    private short cmdByte;
    private ChipEightOpcodes.CommandType commandType;

    public ChipEightOpcode(IMachine machine, short b) {
        rawOpcode = b;
        cmdByte = (short) ((rawOpcode >> 12) & 0xF);
        commandType = ChipEightOpcodes.CommandType.UNKNOWN;
        this.machine = machine;
    }

    @Override
    public boolean decode() {
        logger.info("Attempting to decode full opcode: {}", BinaryUtils.toHexShort(rawOpcode));
        logger.info("Attempting to decode, cmd byte: {}", BinaryUtils.toHexShort(cmdByte));

        Map<String, Short> map = ChipEightOpcodes.toNameMap();
        long numMatches = map.entrySet()
                .stream()
                .filter((ent) -> ent.getValue() == cmdByte)
                .count();

        if (numMatches == 0) {
            commandType = ChipEightOpcodes.CommandType.UNKNOWN;
            return false;
        }

        if (numMatches > 1) {
            commandType = ChipEightOpcodes.CommandType.MULTI_MAPPED_FAMILY;
            return true;
        }
        commandType = ChipEightOpcodes.CommandType.SINGLE;
        return true;
    }

    public boolean execute() {
        if (commandType == ChipEightOpcodes.CommandType.MULTI_MAPPED_FAMILY) {
            logger.debug("Decoding special opcode: Family: {}, {}", BinaryUtils.toHexShort(cmdByte), BinaryUtils.toHexShort(rawOpcode));
            return ChipEightCPUExecutor.handleMultiMappedOpcode(this, cmdByte, rawOpcode);
        }

        if (commandType == ChipEightOpcodes.CommandType.SINGLE) {
            logger.debug("Decoding single opcode: {}, {}", BinaryUtils.toHexShort(cmdByte), BinaryUtils.toHexShort(rawOpcode));
            return ChipEightCPUExecutor.handleOpcode(this, cmdByte, rawOpcode);
        }

        return false;
    }

    public ChipEightOpcodes.CommandType getCommandType() {
        return commandType;
    }

    public IMachine attachedMachine() {
        return machine;
    }

    public String toString() {
        return "[OPCODE - [Opcode Byte: " + BinaryUtils.toHexShort(rawOpcode) + "]]";
    }
}
