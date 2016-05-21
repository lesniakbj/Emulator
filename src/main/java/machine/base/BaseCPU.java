package machine.base;

import machine.interfaces.ICpu;

/**
 * Created by Brendan on 5/21/2016.
 */
public abstract class BaseCPU implements ICpu {
    private String cpuName;

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String toString() {
        return "[CPU - [CPU Name: " + cpuName + "]]";
    }
}
