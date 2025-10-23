//Manage Computers program: maintains an ArrayList of Computer objects,
//can be either Laptop or Desktop, but never just Computer-type objects themselves

import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {

    public static void main(String args[]) {

        ArrayList<Object> computers = new ArrayList<Object>();

        Scanner s = new Scanner(System.in);
        String menuOption = "";

        do { // Start of main program loop

            // Show computer data in ArrayList
            showComputers(computers);

            // Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch (menuOption) {
                // Add new computer
                case "a":
                    addComputer(computers, s);
                    break;

                // Delete a computer
                case "d":
                    deleteComputer(computers, s);
                    break;

                // Edit a computer
                case "e":
                    editComputer(computers, s);
                    break;
            }

        } while (!menuOption.equals("x")); // Stop when "x" is entered

        s.close(); // Close keyboard scanner

    } // End of main

    // -----------------------------
    // Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption = "";

        // Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        // Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase();
        return menuOption;
    } // End of getMenuSelection

    // -----------------------------
    // Show data for all laptops and desktops stored in the list
    private static void showComputers(ArrayList<Object> computers) {
        int computerListNumber = 0; // This variable is used to hold the "list number" for each computer, starting
                                    // at 1.

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        if (computers.isEmpty()) {
            System.out.println("(none)");
        } else {
            for (Object obj : computers) {
                computerListNumber++; // Increment list number for each computer
                // just call toString() on whatever object (Laptop or Desktop)
                System.out.println(computerListNumber + ": " + obj.toString());
            }
        }

        System.out.println("=========");
    } // End of showComputers

    // -----------------------------
    // Add a new Laptop or Desktop
    private static void addComputer(ArrayList<Object> computers, Scanner s) {
        String computerType = "";

        System.out.println("ADDING COMPUTER:-");
        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType = s.nextLine();
        computerType = computerType.toLowerCase();

        switch (computerType) {

            // Add a laptop (composition)
            case "l":
                // get validated common values
                Computer base = getComputerData(s);
                // validate screen
                String screenSize = InputValidator.promptScreenSize(s);
                // build immutable laptop using composition
                Laptop newLaptop = new Laptop(base, screenSize);
                computers.add(newLaptop);
                break;

            // Add a desktop
            case "d":
                // get validated common values
                Computer baseD = getComputerData(s);
                // validate GPU
                String GPUType = InputValidator.promptGPU(s);

                // If your Desktop is still inheritance-style, this constructor will exist.
                // If/when the team converts Desktop to composition, switch to: new
                // Desktop(baseD, GPUType)
                computers.add(new Desktop(baseD.getCPU(), baseD.getRAM(), baseD.getDisk(), GPUType));
                break;

            // Invalid computer type to add entered
            default:
                System.out.println("Invalid computer type entered!");

        }
    } // End of addComputer

    // -----------------------------
    // Delete a specified computer from the list
    private static void deleteComputer(ArrayList<Object> computers, Scanner s) {
        System.out.println("DELETE COMPUTER:-");

        if (computers.isEmpty()) {
            System.out.println("Invalid computer number entered!");
            return;
        }

        // safer read with validation
        int computerListIdx = InputValidator.readIndex(s, computers.size());
        computers.remove(computerListIdx);
    } // End of deleteComputer

    // -----------------------------
    // Edit a computer. For immutable classes we replace the object in the list.
    private static void editComputer(ArrayList<Object> computers, Scanner s) {
        String computerType = "";

        System.out.println("EDIT COMPUTER:-");

        if (computers.isEmpty()) {
            System.out.println("Invalid computer number entered!");
            return;
        }

        // safer read with validation
        int listIdx = InputValidator.readIndex(s, computers.size());
        Object target = computers.get(listIdx);

        if (target instanceof Laptop) {
            computerType = "laptop";
        } else if (target instanceof Desktop) {
            computerType = "desktop";
        } else {
            computerType = "";
        }

        // Edit computer
        switch (computerType) {

            // Editing a laptop
            case "laptop":
                System.out.println("Editing a Laptop:");
                // get validated common data
                Computer newBase = getComputerData(s);
                // get validated screen
                String newScreen = InputValidator.promptScreenSize(s);
                // create new immutable object and REPLACE in list
                Laptop updatedLaptop = new Laptop(newBase, newScreen);
                computers.set(listIdx, updatedLaptop);
                break;

            // Editing a desktop (immutable → replace with a new one)
            case "desktop":
                System.out.println("Editing a Desktop:");
                Computer newBaseD = getComputerData(s);
                String newGpu = InputValidator.promptGPU(s);

                // If your Desktop is still inheritance-style, this constructor will exist.
                // If/when the team converts Desktop to composition, switch to: new
                // Desktop(newBaseD, newGpu)
                Desktop updatedDesktop = new Desktop(newBaseD.getCPU(), newBaseD.getRAM(), newBaseD.getDisk(), newGpu);
                computers.set(listIdx, updatedDesktop);
                break;

            default:
                System.out.println("Unknown type to edit.");
        }
    } // End of editComputer

    // -----------------------------
    // Helper: get data common to Laptop and Desktop (CPU, RAM and disk) with
    // whitelist prompts
    private static Computer getComputerData(Scanner s) {
        // whitelist-only values
        String CPU = InputValidator.promptCPU(s);
        String RAM = InputValidator.promptRAM(s);
        String disk = InputValidator.promptDisk(s);

        // return base immutable Computer
        return new Computer(CPU, RAM, disk);
    } // End of getComputerData

} // End of ManageComputer class
