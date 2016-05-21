package legacy.machine.implementations.ChipEight.program;

import legacy.machine.implementations.ChipEight.memory.EightBitMemoryBank;
import legacy.machine.interfaces.MachinePart;
import legacy.machine.interfaces.Program;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BinaryUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brendan on 5/19/2016.
 */
public class ChipEightProgram implements Program {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightProgram.class);

    private String programName;
    private List<Byte> programData;
    private Integer programSize;

    public ChipEightProgram(String s) {
        programName = s;
        programData = new ArrayList<>();
        programSize = 0;
        try {
            loadProgramData();
        } catch (IOException e) {
            logger.error("Unable to load program data!", e);
        }
    }

    public boolean loadIntoMemory(Integer offset, MachinePart mem) {
        EightBitMemoryBank ebMem = EightBitMemoryBank.class.cast(mem);
        for (int i = 0; i < programSize; i++) {
            ebMem.getAllMemory().add(programData.get(i + offset));
        }

        return true;
    }

    private void loadProgramData() throws IOException {
        byte[] data = Files.readAllBytes(Paths.get(getProgramFile().getPath()));
        for (byte b : data) {
            b = (byte) (b & 0xFF);
            programData.add(b);
            logger.debug("Byte {}, {}", Integer.toHexString(b), BinaryUtils.toBinaryString(b));
        }

        programSize = data.length;
    }

    private File getProgramFile() {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(programName).getFile());
    }

    public Integer getSize() {
        return programSize;
    }

    public String toString() {
        return "[PROGRAM - " + programName + ", "
                + programSize + "]";
    }
}
