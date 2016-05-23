package machine.implementations.ChipEight.cpu.opcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendan on 5/22/2016.
 */
public final class CPUOperations {
    private CPUOperations() {
    }

    public static Map<String, Short> toNameMap() {
        Map<String, Short> map = new HashMap<>();

        // Original Chip-8 OpCodes
        map.put("CLEAR_SCREEN", MultiMappedOperations.CLEAR_SCREEN);
        map.put("RET_SUBROUTINE", MultiMappedOperations.RET_SUBROUTINE);
        map.put("JUMP_TO_ADDRESS", SingleOperations.JUMP_TO_ADDRESS);
        map.put("CALL_ADDRESS", SingleOperations.CALL_ADDRESS);
        map.put("SKIP_IF_EQUAL", SingleOperations.SKIP_IF_EQUAL);
        map.put("SKIP_IF_NE", SingleOperations.SKIP_IF_NE);
        map.put("SKIP_IF_REG_EQ", SingleOperations.SKIP_IF_REG_EQ);
        map.put("LOAD_REGISTER", SingleOperations.LOAD_REGISTER);
        map.put("ADD_TO_REGISTER", SingleOperations.ADD_TO_REGISTER);
        map.put("LOAD_IN_REGISTER", MultiMappedOperations.LOAD_IN_REGISTER);
        map.put("OR_REGISTER_VALUES", MultiMappedOperations.OR_REGISTER_VALUES);
        map.put("AND_REGISTER_VALUES", MultiMappedOperations.AND_REGISTER_VALUES);
        map.put("XOR_REGISTER_VALUES", MultiMappedOperations.XOR_REGISTER_VALUES);
        map.put("ADD_WITH_CARRY", MultiMappedOperations.ADD_WITH_CARRY);
        map.put("SUB_WITH_BORROW", MultiMappedOperations.SUB_WITH_BORROW);
        map.put("SHIFT_RIGHT", MultiMappedOperations.SHIFT_RIGHT);
        map.put("SUB_NO_BORROW", MultiMappedOperations.SUB_NO_BORROW);
        map.put("SHIFT_LEFT", MultiMappedOperations.SHIFT_LEFT);
        map.put("SKIP_IF_REG_NE", SingleOperations.SKIP_IF_REG_NE);
        map.put("SAVE_INS_TO_MEM", SingleOperations.SAVE_INS_TO_MEM);
        map.put("SAVE_AND_INC_PC", SingleOperations.SAVE_AND_INC_PC);
        map.put("RANDOM_BYTE", SingleOperations.RANDOM_BYTE);
        map.put("DRAW_SPRITE", SingleOperations.DRAW_SPRITE);
        map.put("SKIP_KEY_PRESS", MultiMappedOperations.SKIP_KEY_PRESS);
        map.put("SKIP_NOT_KEY_PRESS", MultiMappedOperations.SKIP_NOT_KEY_PRESS);
        map.put("GET_DELAY_TIMER", MultiMappedOperations.GET_DELAY_TIMER);
        map.put("WAIT_FOR_KEY", MultiMappedOperations.WAIT_FOR_KEY);
        map.put("SET_DELAY_TIMER", MultiMappedOperations.SET_DELAY_TIMER);
        map.put("SET_SOUND_TIMER", MultiMappedOperations.SET_SOUND_TIMER);
        map.put("ADD_TO_IP", MultiMappedOperations.ADD_TO_IP);
        map.put("SET_IP_TO_SPRITE", MultiMappedOperations.SET_IP_TO_SPRITE);
        map.put("STORE_BCD", MultiMappedOperations.STORE_BCD);
        map.put("STORE_REGISTERS", MultiMappedOperations.STORE_REGISTERS);
        map.put("RETRIEVE_REGISTERS", MultiMappedOperations.RETRIEVE_REGISTERS);

        return map;
    }

    public enum CommandType {
        UNKNOWN,
        SINGLE,
        MULTI_MAPPED
    }

    public static final class MultiMappedOperations {
        public static final short CLEAR_SCREEN = 0x0;
        public static final short RET_SUBROUTINE = 0x0;

        public static final short LOAD_IN_REGISTER = 0x8;
        public static final short OR_REGISTER_VALUES = 0x8;
        public static final short AND_REGISTER_VALUES = 0x8;
        public static final short XOR_REGISTER_VALUES = 0x8;
        public static final short ADD_WITH_CARRY = 0x8;
        public static final short SUB_WITH_BORROW = 0x8;
        public static final short SHIFT_RIGHT = 0x8;
        public static final short SUB_NO_BORROW = 0x8;
        public static final short SHIFT_LEFT = 0x8;

        public static final short SKIP_KEY_PRESS = 0xE;
        public static final short SKIP_NOT_KEY_PRESS = 0xE;

        public static final short GET_DELAY_TIMER = 0xF;
        public static final short WAIT_FOR_KEY = 0xF;
        public static final short SET_DELAY_TIMER = 0xF;
        public static final short SET_SOUND_TIMER = 0xF;
        public static final short ADD_TO_IP = 0xF;
        public static final short SET_IP_TO_SPRITE = 0xF;
        public static final short STORE_BCD = 0xF;
        public static final short STORE_REGISTERS = 0xF;
        public static final short RETRIEVE_REGISTERS = 0xF;
    }

    public final class SingleOperations {
        public static final short JUMP_TO_ADDRESS = 0x1;
        public static final short CALL_ADDRESS = 0x2;
        public static final short SKIP_IF_EQUAL = 0x3;
        public static final short SKIP_IF_NE = 0x4;
        public static final short SKIP_IF_REG_EQ = 0x5;
        public static final short LOAD_REGISTER = 0x6;
        public static final short ADD_TO_REGISTER = 0x7;
        public static final short SKIP_IF_REG_NE = 0x9;
        public static final short SAVE_INS_TO_MEM = 0xA;
        public static final short SAVE_AND_INC_PC = 0xB;
        public static final short RANDOM_BYTE = 0xC;
        public static final short DRAW_SPRITE = 0xD;
    }
}
