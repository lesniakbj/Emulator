package machine.implementations.ChipEight.cpu;

import machine.implementations.ChipEight.ChipEightConstants;
import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcode;
import machine.implementations.ChipEight.cpu.opcode.ChipEightOpcodes;
import machine.implementations.ChipEight.memory.ChipEightRAM;
import machine.implementations.ChipEight.peripherals.ChipEightScreen;
import machine.interfaces.IMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BinaryUtils;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Brendan on 5/22/2016.
 */
public class ChipEightCPUExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightCPUExecutor.class);

    public static boolean handleOpcode(ChipEightOpcode op, short cmd, short rawOpcode) {
        IMachine machine = op.attachedMachine();
        ChipEightCPU cpu = (ChipEightCPU) machine.getCPU();

        Map<Short, String> cmdNameMap = ChipEightOpcodes.singleCommandMap();
        logger.debug("Decoding the command: [{}]", cmdNameMap.get(cmd));

        short val;
        short reg;
        short regTwo;
        switch (cmd) {
            case ChipEightOpcodes.SingleOperations.JUMP_TO_ADDRESS:
                // The interpreter sets the program counter to nnn.
                val = (short) (rawOpcode & 0x0FFF);
                logger.info("Setting instruction pointer to: [{}]", BinaryUtils.toHexShort(val));
                cpu.setInstructionPointer(val);
                return true;
            case ChipEightOpcodes.SingleOperations.CALL_ADDRESS:
                // The interpreter increments the stack pointer, then puts the current
                // PC on the top of the stack. The PC is then set to nnn.
                val = (short) (rawOpcode & 0x0FFF);
                logger.info("Stack being set with new call: [{}, {}]", BinaryUtils.toHexByte((byte) (cpu.getStackPointer() + 1)), BinaryUtils.toHexShort((short) cpu.getInstructionPointer()));
                cpu.pushStack(cpu.getInstructionPointer());
                logger.info("Calling subroutine at: [{}]", BinaryUtils.toHexShort(val));
                cpu.setInstructionPointer(val);
                return true;
            case ChipEightOpcodes.SingleOperations.SKIP_IF_EQUAL:
                // Skip next instruction if Vx = kk.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                val = (short) (rawOpcode & 0x00FF);
                if (cpu.getRegisterValue(reg) == val) {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 4);
                } else {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                }
                return true;
            case ChipEightOpcodes.SingleOperations.SKIP_IF_NE:
                // Skip next instruction if Vx != kk.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                val = (short) (rawOpcode & 0x00FF);
                if (cpu.getRegisterValue(reg) != val) {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 4);
                } else {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                }
                return true;
            case ChipEightOpcodes.SingleOperations.SKIP_IF_REG_EQ:
                // Skip next instruction if Vx = Vy.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                regTwo = (short) ((rawOpcode & 0x00F0) >> 4);
                if (cpu.getRegisterValue(reg) == cpu.getRegisterValue(regTwo)) {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 4);
                } else {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                }
                return true;
            case ChipEightOpcodes.SingleOperations.LOAD_REGISTER:
                // Set Vx = kk.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                val = (short) (rawOpcode & 0x00FF);
                cpu.setRegisterValue(reg, val);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.SingleOperations.ADD_TO_REGISTER:
                // Set Vx = Vx + kk.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                val = (short) ((rawOpcode) & 0x00FF);
                int newVal = cpu.getRegisterValue(reg) + val;
                cpu.setRegisterValue(reg, newVal);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.SingleOperations.SKIP_IF_REG_NE:
                // Skip next instruction if Vx != Vy.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                regTwo = (short) ((rawOpcode & 0x00F0) >> 4);
                if (cpu.getRegisterValue(reg) != cpu.getRegisterValue(regTwo)) {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 4);
                } else {
                    cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                }
                return true;
            case ChipEightOpcodes.SingleOperations.LOAD_REGISTER_I:
                // Set I = nnn.
                val = (short) (rawOpcode & 0x0FFF);
                cpu.setRegisterValue(ChipEightConstants.REGISTER_I, val);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.SingleOperations.JUMP_WITH_OFFSET:
                // Jump to location nnn + V0.
                val = (short) ((rawOpcode & 0x0FFF));
                cpu.setInstructionPointer(val + cpu.getRegisterValue(0));
                return true;
            case ChipEightOpcodes.SingleOperations.RANDOM_BYTE:
                // Set Vx = random byte AND kk.
                reg = (short) ((rawOpcode & 0x0F00) >> 8);
                val = (short) (rawOpcode & 0x00FF);
                short rand = (short) (ThreadLocalRandom.current().nextInt(0, 255 + 1) & 0xFFFF);
                short v = (short) (rand & val);
                cpu.setRegisterValue(reg, v);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.SingleOperations.DRAW_SPRITE:
                // Display n-byte sprite starting at memory location I at (Vx, Vy), set VF = collision.
                // The interpreter reads n bytes from memory, starting at the address stored in I. These
                // bytes are then displayed as sprites on screen at coordinates (Vx, Vy). Sprites are XORed
                // onto the existing screen. If this causes any pixels to be erased, VF is set to 1, otherwise
                // it is set to 0. If the sprite is positioned so part of it is outside the coordinates
                // of the display, it wraps around to the opposite side of the screen. See instruction 8xy3
                // for more information on XOR, and section 2.4, Display, for more information on the Chip-8
                // screen and sprites.
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
        }

        return false;
    }

    public static boolean handleMultiMappedOpcode(ChipEightOpcode op, short cmdFamily, short rawOpcode) {
        switch (cmdFamily) {
            case ChipEightOpcodes.MultiMappedOperations.SYSTEM_FAMILY:
                return handleSystemOpcode(op, rawOpcode);
            case ChipEightOpcodes.MultiMappedOperations.ALU_FAMILY:
                return handleALUOpcodes(op, rawOpcode);
            case ChipEightOpcodes.MultiMappedOperations.KEYS_FAMILY:
                return handleKeyPressOpcode(op, rawOpcode);
            case ChipEightOpcodes.MultiMappedOperations.DELAY_FAMILY:
                return handleDelayOpcode(op, rawOpcode);
        }

        return false;
    }

    private static boolean handleDelayOpcode(ChipEightOpcode op, short rawOpcode) {

        short delayOp = (short) (rawOpcode & 0x00FF);
        switch (delayOp) {

        }
        return false;
    }

    private static boolean handleKeyPressOpcode(ChipEightOpcode op, short rawOpcode) {
        IMachine machine = op.attachedMachine();
        ChipEightCPU cpu = (ChipEightCPU) machine.getCPU();

        short keyOp = (short) (rawOpcode & 0x00FF);
        switch (keyOp) {
            case ChipEightOpcodes.MultiMappedOperations.SKIP_KEY_PRESS_VALUE:
                // Skip next instruction if key with the value of Vx is pressed.
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.SKIP_NOT_KEY_PRESS_VALUE:
                // Skip next instruction if key with the value of Vx is not pressed.
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
        }
        return false;
    }

    private static boolean handleALUOpcodes(ChipEightOpcode op, short rawOpcode) {
        IMachine machine = op.attachedMachine();
        ChipEightCPU cpu = (ChipEightCPU) machine.getCPU();
        ChipEightRAM ram = (ChipEightRAM) machine.getRAM();

        int x;
        int y;
        int val;
        byte aluOp = (byte) (rawOpcode & 0x000F);
        logger.debug("Decoding ALU Opcode: The decoded byte is: [{}]", BinaryUtils.toHexByte(aluOp));
        switch (aluOp) {
            case ChipEightOpcodes.MultiMappedOperations.LOAD_IN_REGISTER_VALUE:
                // Stores the value of register Vy in register Vx.
                x = ((rawOpcode & 0x0F00) >> 8);
                y = ((rawOpcode & 0x00F0) >> 4);
                logger.debug("Loading register [{}] with value from register [{}]", x, y);
                cpu.setRegisterValue(x, cpu.getRegisterValue(y));
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.OR_REGISTER_VALUES_VALUE:
                // Set Vx = Vx OR Vy.
                x = ((rawOpcode & 0x0F00) >> 8);
                y = ((rawOpcode & 0x00F0) >> 4);
                logger.debug("Loading register [{}] with value from register [{} OR {}]", x, x, y);
                val = cpu.getRegisterValue(x) | cpu.getRegisterValue(y);
                cpu.setRegisterValue(x, val & 0xFFFF);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.AND_REGISTER_VALUES_VALUE:
                x = ((rawOpcode & 0x0F00) >> 8);
                y = ((rawOpcode & 0x00F0) >> 4);
                logger.debug("Loading register [{}] with value from register [{} AND {}]", x, x, y);
                val = cpu.getRegisterValue(x) & cpu.getRegisterValue(y);
                cpu.setRegisterValue(x, val & 0xFFFF);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.XOR_REGISTER_VALUES_VALUE:
                x = ((rawOpcode & 0x0F00) >> 8);
                y = ((rawOpcode & 0x00F0) >> 4);
                logger.debug("Loading register [{}] with value from register [{} XOR {}]", x, x, y);
                val = cpu.getRegisterValue(x) ^ cpu.getRegisterValue(y);
                cpu.setRegisterValue(x, val & 0xFFFF);
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.ADD_WITH_CARRY_VALUE:
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.SUB_WITH_BORROW_VALUE:
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.SHIFT_RIGHT_VALUE:
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.SUB_NO_BORROW_VALUE:
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.SHIFT_LEFT_VALUE:
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
        }
        return false;
    }

    private static boolean handleSystemOpcode(ChipEightOpcode op, short rawOpcode) {
        IMachine machine = op.attachedMachine();
        ChipEightCPU cpu = (ChipEightCPU) machine.getCPU();
        ChipEightScreen screen = (ChipEightScreen) machine.getScreen();

        byte sysOp = (byte) (rawOpcode & 0x000F);
        logger.debug("Decoding SYSTEM Opcode: The decoded byte is: [{}]", BinaryUtils.toHexByte(sysOp));
        switch (sysOp) {
            case ChipEightOpcodes.MultiMappedOperations.CLEAR_SCREEN_VALUE:
                logger.debug("Clearing the screen!");
                // Clear the display.
                screen.clearDisplay();
                cpu.setInstructionPointer(cpu.getInstructionPointer() + 2);
                return true;
            case ChipEightOpcodes.MultiMappedOperations.RET_SUBROUTINE_VALUE:
                // The interpreter sets the program counter to the address at the top of the stack, then subtracts 1 from the stack pointer.
                logger.debug("CPU was told to return from subroutine!");
                cpu.popStack();
                return true;
        }
        return false;
    }
}
