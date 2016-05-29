package legacy.machine.base;

import legacy.machine.interfaces.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by Brendan on 5/21/2016.
 * <p>
 * This is a abstract example of Base Machine. It defines
 * all of the parts that make up a Machine, but does not
 * implement any of the methods that are required in IMachine.
 */
public abstract class BaseMachine implements IMachine {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private String machineName;
    private boolean isRunning;
    private volatile long runningTime;
    private ICpu cpu;
    private IRam ram;
    private IDisk disk;
    private IScreen screen;
    private IKeyboard keyboard;

    private IProgram program;
    private boolean isProgramLoaded;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String name) {
        this.machineName = name;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public long getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(long runningTime) {
        this.runningTime = runningTime;
    }

    public ICpu getCPU() {
        return cpu;
    }

    public void setCPU(ICpu CPU) {
        this.cpu = CPU;
    }

    public IMemory getRAM() {
        return ram;
    }

    public IMemory getDisk() {
        return disk;
    }

    public void setDisk(IDisk disk) {
        this.disk = disk;
    }

    public IScreen getScreen() {
        return screen;
    }

    public void setScreen(IScreen screen) {
        this.screen = screen;
    }

    public ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    public IKeyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(IKeyboard keyboard) {
        this.keyboard = keyboard;
    }

    public IProgram getDefaultProgram() {
        return program;
    }

    public boolean isProgramLoaded() {
        return isProgramLoaded;
    }

    public void setProgramLoaded(boolean programLoaded) {
        isProgramLoaded = programLoaded;
    }

    public void setIsRunning(boolean running) {
        this.isRunning = running;
    }

    public void setRam(IRam ram) {
        this.ram = ram;
    }

    public void setProgram(IProgram program) {
        this.program = program;
    }

    public String toString() {
        return "[MACHINE - \n" +
                "\t[Name: " + machineName + "],\n" +
                "\t[Running: " + isRunning + "],\n" +
                "\t[Running Time: " + runningTime + "],\n" +
                "\t[Default Program: " + program + "],\n" +
                "\t[Program Loaded: " + isProgramLoaded + "]\n" +
                "\t[CPU: \n" +
                "\t\t" + cpu + "\n" +
                "\t]\n" +
                "\t[RAM: \n" +
                "\t\t" + ram + "\n" +
                "\t]\n" +
                "\t[DISK: \n" +
                "\t\t" + disk + "\n" +
                "\t]\n" +
                "\t[SCREEN: \n" +
                "\t\t" + screen + "\n" +
                "\t]\n" +
                "\t[KEYBOARD: \n" +
                "\t\t" + keyboard + "\n" +
                "\t]\n" +
                "]";
    }
}
