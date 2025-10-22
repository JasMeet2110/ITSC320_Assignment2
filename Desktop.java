//Desktop computer: adds GPU type

public final class Desktop { // not extending Computer now, use composition
    // keep computer data in here (composition)
    private final Computer computer;
    //Only in Desktop subclass
    private final String GPUType; // final because immutable

    //Constructors

    // same params as before so other code doesn't change when creating desktop
    public Desktop(String CPU, String RAM, String disk, String GPUType) {
        //Inherited from Computer superclass  - now we just wrap a Computer object
        this.computer = new Computer(CPU, RAM, disk); 

        //Only in Desktop subclass
        this.GPUType = GPUType;
    }

    //Getter
    public String getGPUType() {
        return this.GPUType;
    }

    // small helper getters
    public String getCPU()  { return this.computer.getCPU(); }
    public String getRAM()  { return this.computer.getRAM(); }
    public String getDisk() { return this.computer.getDisk(); }
    public Computer getComputer() { return this.computer; }

    //Return formatted version of data
    public String toString() {
        return "Type:Desktop\tCPU:" + this.computer.getCPU()
                + "\tRAM:" + this.computer.getRAM()
                + "\tDisk:" + this.computer.getDisk()
                + "\tGPU:" + this.GPUType;
    }
}
