package machine.implementations.ChipEight.cpu.opcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brendan on 5/22/2016.
 */
public final class ChipEightOpcodes {
    private ChipEightOpcodes() {
    }

    public static Map<String, Short> toNameMap() {
        Map<String, Short> map = new HashMap<>();

        // Original Chip-8 OpCodes
        map.put("CLEAR_SCREEN", MultiMappedOperations.CLEAR_SCREEN_FAMILY);
        map.put("RET_SUBROUTINE", MultiMappedOperations.RET_SUBROUTINE_FAMILY);
        map.put("JUMP_TO_ADDRESS", SingleOperations.JUMP_TO_ADDRESS);
        map.put("CALL_ADDRESS", SingleOperations.CALL_ADDRESS);
        map.put("SKIP_IF_EQUAL", SingleOperations.SKIP_IF_EQUAL);
        map.put("SKIP_IF_NE", SingleOperations.SKIP_IF_NE);
        map.put("SKIP_IF_REG_EQ", SingleOperations.SKIP_IF_REG_EQ);
        map.put("LOAD_REGISTER", SingleOperations.LOAD_REGISTER);
        map.put("ADD_TO_REGISTER", SingleOperations.ADD_TO_REGISTER);
        map.put("LOAD_IN_REGISTER", MultiMappedOperations.LOAD_IN_REGISTER_FAMILY);
        map.put("OR_REGISTER_VALUES", MultiMappedOperations.OR_REGISTER_VALUES_FAMILY);
        map.put("AND_REGISTER_VALUES", MultiMappedOperations.AND_REGISTER_VALUES_FAMILY);
        map.put("XOR_REGISTER_VALUES", MultiMappedOperations.XOR_REGISTER_VALUES_FAMILY);
        map.put("ADD_WITH_CARRY", MultiMappedOperations.ADD_WITH_CARRY_FAMILY);
        map.put("SUB_WITH_BORROW", MultiMappedOperations.SUB_WITH_BORROW_FAMILY);
        map.put("SHIFT_RIGHT", MultiMappedOperations.SHIFT_RIGHT_FAMILY);
        map.put("SUB_NO_BORROW", MultiMappedOperations.SUB_NO_BORROW_FAMILY);
        map.put("SHIFT_LEFT", MultiMappedOperations.SHIFT_LEFT_FAMILY);
        map.put("SKIP_IF_REG_NE", SingleOperations.SKIP_IF_REG_NE);
        map.put("LOAD_REGISTER_I", SingleOperations.LOAD_REGISTER_I);
        map.put("JUMP_WITH_OFFSET", SingleOperations.JUMP_WITH_OFFSET);
        map.put("RANDOM_BYTE", SingleOperations.RANDOM_BYTE);
        map.put("DRAW_SPRITE", SingleOperations.DRAW_SPRITE);
        map.put("SKIP_KEY_PRESS", MultiMappedOperations.SKIP_KEY_PRESS_FAMILY);
        map.put("SKIP_NOT_KEY_PRESS", MultiMappedOperations.SKIP_NOT_KEY_PRESS_FAMILY);
        map.put("GET_DELAY_TIMER", MultiMappedOperations.GET_DELAY_TIMER_FAMILY);
        map.put("WAIT_FOR_KEY", MultiMappedOperations.WAIT_FOR_KEY_FAMILY);
        map.put("SET_DELAY_TIMER", MultiMappedOperations.SET_DELAY_TIMER_FAMILY);
        map.put("SET_SOUND_TIMER", MultiMappedOperations.SET_SOUND_TIMER_FAMILY);
        map.put("ADD_TO_IP", MultiMappedOperations.ADD_TO_IP_FAMILY);
        map.put("SET_IP_TO_SPRITE", MultiMappedOperations.SET_IP_TO_SPRITE_FAMILY);
        map.put("STORE_BCD", MultiMappedOperations.STORE_BCD_FAMILY);
        map.put("STORE_REGISTERS", MultiMappedOperations.STORE_REGISTERS_FAMILY);
        map.put("RETRIEVE_REGISTERS", MultiMappedOperations.RETRIEVE_REGISTERS_FAMILY);

        return map;
    }

    public static Map<Short, String> singleCommandMap() {
        Map<Short, String> map = new HashMap<>();

        map.put(SingleOperations.JUMP_TO_ADDRESS, "JUMP_TO_ADDRESS");
        map.put(SingleOperations.CALL_ADDRESS, "CALL_ADDRESS");
        map.put(SingleOperations.SKIP_IF_EQUAL, "SKIP_IF_EQUAL");
        map.put(SingleOperations.SKIP_IF_NE, "SKIP_IF_NE");
        map.put(SingleOperations.SKIP_IF_REG_EQ, "SKIP_IF_REG_EQ");
        map.put(SingleOperations.LOAD_REGISTER, "LOAD_REGISTER");
        map.put(SingleOperations.ADD_TO_REGISTER, "ADD_TO_REGISTER");
        map.put(SingleOperations.SKIP_IF_REG_NE, "SKIP_IF_REG_NE");
        map.put(SingleOperations.LOAD_REGISTER_I, "LOAD_REGISTER_I");
        map.put(SingleOperations.JUMP_WITH_OFFSET, "JUMP_WITH_OFFSET");
        map.put(SingleOperations.RANDOM_BYTE, "RANDOM_BYTE");
        map.put(SingleOperations.DRAW_SPRITE, "DRAW_SPRITE");

        return map;
    }

    public enum CommandType {
        UNKNOWN,
        SINGLE,
        MULTI_MAPPED_FAMILY
    }

    public static final class MultiMappedOperations {
        public static final short SYSTEM_FAMILY = 0x0;
        public static final short CLEAR_SCREEN_FAMILY = 0x0;
        public static final short CLEAR_SCREEN_VALUE = 0x0;
        public static final short RET_SUBROUTINE_FAMILY = 0x0;
        public static final short RET_SUBROUTINE_VALUE = 0xE;

        public static final short ALU_FAMILY = 0x8;
        public static final short LOAD_IN_REGISTER_FAMILY = 0x8;
        public static final short LOAD_IN_REGISTER_VALUE = 0x0;
        public static final short OR_REGISTER_VALUES_FAMILY = 0x8;
        public static final short OR_REGISTER_VALUES_VALUE = 0x1;
        public static final short AND_REGISTER_VALUES_FAMILY = 0x8;
        public static final short AND_REGISTER_VALUES_VALUE = 0x2;
        public static final short XOR_REGISTER_VALUES_FAMILY = 0x8;
        public static final short XOR_REGISTER_VALUES_VALUE = 0x3;
        public static final short ADD_WITH_CARRY_FAMILY = 0x8;
        public static final short ADD_WITH_CARRY_VALUE = 0x4;
        public static final short SUB_WITH_BORROW_FAMILY = 0x8;
        public static final short SUB_WITH_BORROW_VALUE = 0x5;
        public static final short SHIFT_RIGHT_FAMILY = 0x8;
        public static final short SHIFT_RIGHT_VALUE = 0x6;
        public static final short SUB_NO_BORROW_FAMILY = 0x8;
        public static final short SUB_NO_BORROW_VALUE = 0x7;
        public static final short SHIFT_LEFT_FAMILY = 0x8;
        public static final short SHIFT_LEFT_VALUE = 0xE;

        public static final short KEYS_FAMILY = 0xE;
        public static final short SKIP_KEY_PRESS_FAMILY = 0xE;
        public static final short SKIP_NOT_KEY_PRESS_FAMILY = 0xE;

        public static final short DELAY_FAMILY = 0xF;
        public static final short GET_DELAY_TIMER_FAMILY = 0xF;
        public static final short WAIT_FOR_KEY_FAMILY = 0xF;
        public static final short SET_DELAY_TIMER_FAMILY = 0xF;
        public static final short SET_SOUND_TIMER_FAMILY = 0xF;
        public static final short ADD_TO_IP_FAMILY = 0xF;
        public static final short SET_IP_TO_SPRITE_FAMILY = 0xF;
        public static final short STORE_BCD_FAMILY = 0xF;
        public static final short STORE_REGISTERS_FAMILY = 0xF;
        public static final short RETRIEVE_REGISTERS_FAMILY = 0xF;
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
        public static final short LOAD_REGISTER_I = 0xA;
        public static final short JUMP_WITH_OFFSET = 0xB;
        public static final short RANDOM_BYTE = 0xC;
        public static final short DRAW_SPRITE = 0xD;
    }
}
