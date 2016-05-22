package machine.implementations.ChipEight;

import machine.interfaces.IMemory;
import machine.interfaces.IProgram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Brendan on 5/21/2016.
 */
public class ChipEightProgram implements IProgram {
    private static final Logger logger = LoggerFactory.getLogger(ChipEightProgram.class);
    private static ClassLoader loader;

    private String programName;
    private byte[] programData;
    private long programSize;

    public ChipEightProgram(String s) {
        loader = getClass().getClassLoader();

        this.programName = s;
        programData = loadProgramData(programName);
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    @Override
    public boolean loadIntoMemory(int offset, IMemory mem) {
        for (int i = 0; i < programSize; i++) {
            mem.setMemory(i + offset, programData[i]);
        }

        logger.debug("Loaded program of {} bytes, {} remaining; {}/{}", programSize, mem.getSize() - programSize, programSize, mem.getSize());
        return true;
    }

    private byte[] loadProgramData(String programName) {
        logger.info("Loading a new program's data...");
        if (programName == null) {
            logger.error("No program name passed in to load with!");
            return null;
        }
        try {
            byte[] data = Files.readAllBytes(Paths.get(loadFile(programName)));
            programSize = data.length;
            logger.debug("Program loaded to: {}: {}", programName, data.length);
            return data;
        } catch (IOException e) {
            logger.error("Error trying to read program file!", e);
        }
        return null;
    }

    private String loadFile(String fileName) {
        return String.valueOf(new File(loader.getResource(fileName).getFile()));
    }
}
