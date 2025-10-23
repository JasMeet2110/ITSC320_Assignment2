// Computer.java
// Immutable base class: manages CPU, RAM, and Disk information securely

public final class Computer {
    private final String cpu;
    private final String ram;
    private final String disk;

    // Constructor with input validation (no null or empty values)
    public Computer(String cpu, String ram, String disk) {
        if (cpu == null || cpu.isEmpty() ||
                ram == null || ram.isEmpty() ||
                disk == null || disk.isEmpty()) {
            throw new IllegalArgumentException("CPU, RAM, and Disk must not be null or empty.");
        }

        this.cpu = cpu;
        this.ram = ram;
        this.disk = disk;
    }

    // Getters only (immutability enforced)
    public String getCPU() {
        return this.cpu;
    }

    public String getRAM() {
        return this.ram;
    }

    public String getDisk() {
        return this.disk;
    }

    @Override
    public String toString() {
        return "CPU: " + this.cpu + ", RAM: " + this.ram + ", Disk: " + this.disk;
    }
}
