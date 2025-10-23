// Computer.java
// Immutable base class: manages CPU, RAM, and Disk information securely

public final class Computer {
    private final String CPU;
    private final String RAM;
    private final String disk;

    // Constructor with input validation (no null or empty values)
    public Computer(String CPU, String RAM, String disk) {
        if (CPU == null || CPU.isEmpty() ||
                RAM == null || RAM.isEmpty() ||
                disk == null || disk.isEmpty()) {
            throw new IllegalArgumentException("CPU, RAM, and Disk must not be null or empty.");
        }

        this.CPU = CPU;
        this.RAM = RAM;
        this.disk = disk;
    }

    // Getters only (immutability enforced)
    public String getCPU() {
        return this.CPU;
    }

    public String getRAM() {
        return this.RAM;
    }

    public String getDisk() {
        return this.disk;
    }

    @Override
    public String toString() {
        return "CPU: " + this.CPU + ", RAM: " + this.RAM + ", Disk: " + this.disk;
    }
}
