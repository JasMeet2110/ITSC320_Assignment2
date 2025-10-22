// Laptop class: now uses composition and is immutable


public final class Laptop { // not extending computer anymore
    // keep computer data inside this object (composition)
    private final Computer computer;

    // extra thing for laptop only
    private final String screenSize;

    // constructor: set everything once
    public Laptop(Computer computer, String screenSize) {
        // just save what we get, we assume input was validated outside
        this.computer = computer;
        this.screenSize = screenSize;
    }

    // getters only
    public Computer getComputer() {
        return this.computer;
    }

    public String getScreenSize() {
        return this.screenSize;
    }

    // same output format as before so UI does not change
    @Override
    public String toString() {
        return "Type:Laptop\tCPU:" + computer.getCPU()
                + "\tRAM:" + computer.getRAM()
                + "\tDisk:" + computer.getDisk()
                + "\tScreen:" + this.screenSize;
    }
}
