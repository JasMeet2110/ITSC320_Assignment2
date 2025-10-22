//Manage Computers program: maintains an ArrayList of Computer objects, 
//can be either Laptop or Desktop, but never just Computer-type objects themselves

import java.util.ArrayList;
import java.util.Scanner;

public class ManageComputers {

    public static void main(String args[]) {

        // CHANGED: now Object so we can store both new Laptop (not a Computer anymore) and old desktop
        ArrayList<Object> computers = new ArrayList<Object>(); // changed to Object

        Scanner s = new Scanner(System.in);
        String menuOption="";

        do { //Start of main program loop

            //Show computer data in ArrayList
            showComputers(computers); // updated signature

            //Display menu and return menu option selected by the user
            menuOption = getMenuSelection(s);

            switch(menuOption) {
                //Add new computer
                case "a": 
                    addComputer(computers,s);
                    break;

                //Delete a computer    
                case "d": 
                    deleteComputer(computers,s);
                    break;

                //Edit a computer    
                case "e": 
                    editComputer(computers, s);
                    break;
            }

        } while ( ! menuOption.equals("x") ); //Stop when "x" is entered

        s.close(); //Close keyboard scanner
    } //End of main

    //-----------------------------
    //Display menu and get user selection, return it
    private static String getMenuSelection(Scanner s) {
        String menuOption="";

        //Display menu options on-screen
        System.out.println("----------");
        System.out.println("A) Add Computer");
        System.out.println("D) Delete Computer");
        System.out.println("E) Edit Computer");
        System.out.println("X) eXit");
        System.out.println("----------");

        //Get menu selection from keyboard
        System.out.print("Enter menu selection:");
        menuOption = s.nextLine();

        menuOption = menuOption.toLowerCase(); //Make lower case for comparison purposes

        return menuOption;
    } //End of getMenuSelection

    //-----------------------------
    //Show data for all laptops and desktops stored in the list
    private static void showComputers(ArrayList<Object> computers) { // changed param type
        int computerListNumber=0; //This variable is used to hold the "list number" for each computer, starting at 1.

        System.out.println("=========");
        System.out.println("LIST OF COMPUTERS:-");

        for (Object obj: computers) {
            computerListNumber++; //Increment list number for each computer
            // just call toString() on whatever object (Laptop or Desktop)
            System.out.println(computerListNumber + ": " + obj.toString());
        }

        System.out.println("=========");
    } //End of showComputers

    //-----------------------------
    //Add a new Laptop or Desktop
    private static void addComputer(ArrayList<Object> computers, Scanner s) { // changed param type
        String computerType="";

        System.out.println("ADDING COMPUTER:-");
        System.out.println("Enter type of computer to add ('L' for Laptop, 'D' for Desktop):");
        computerType=s.nextLine();
        computerType=computerType.toLowerCase(); //Convert to lower case for comparison purposes

        switch(computerType) {

            //Add a laptop
            case "l": 
                // use validator for CPU/RAM/Disk
                Computer base = getComputerData(s); // validated inside
                // validate screen too
                String screenSize = InputValidator.promptScreenSize(s); // use whitelist

                // build immutable laptop using composition
                Laptop newLaptop = new Laptop(base, screenSize); // new style
                computers.add(newLaptop); // add to list
                break;
            
            //Add a desktop    
            case "d": 
                // use validator for CPU/RAM/Disk
                Computer baseD = getComputerData(s); // validated inside

                // validate GPU
                String GPUType = InputValidator.promptGPU(s); // whitelist

                // keep old Desktop constructor (still inheritance inside that class)
                computers.add(new Desktop(baseD.getCPU(), baseD.getRAM(), baseD.getDisk(), GPUType)); 
                break;

            //Invalid computer type to add entered
            default:
                System.out.println("Invalid computer type entered!");
        }
    } //End of addComputer

    //-----------------------------
    //Delete a specified computer from the list
    private static void deleteComputer(ArrayList<Object> computers, Scanner s) { // changed param type
        int computerListNumberToDelete=0;

        System.out.println("DELETE COMPUTER:-");
        System.out.print("Enter number of computer to delete:");
        computerListNumberToDelete = Integer.parseInt(s.nextLine());

        //Check if computer list number is valid before deleting computer from list
        if (computerListNumberToDelete>=1 && computerListNumberToDelete<=computers.size()) {
            //Subtract 1 to get ArrayList index from on-screen list number to create correct index in ArrayList to delete
            computers.remove(computerListNumberToDelete-1); 
        }   
        else {
            System.out.println("Invalid computer number entered!");
        }
    } //End of deleteComputer

    //-----------------------------
    //Edit a computer. For Laptop (immutable) we will replace the object. For Desktop (now immutable) we also replace.
    private static void editComputer(ArrayList<Object> computers, Scanner s) { // changed param type
        int computerListNumberToEdit=0;
        String computerType="";

        System.out.println("EDIT COMPUTER:-");
        System.out.print("Enter number of computer to edit:");
        computerListNumberToEdit = Integer.parseInt(s.nextLine());

        //Check that computerListNumberToEdit is valid first
        if (computerListNumberToEdit>=1 && computerListNumberToEdit<=computers.size()) {

            Object target = computers.get(computerListNumberToEdit-1);

            if (target instanceof Laptop) { 
                computerType="laptop";
            }
            else if (target instanceof Desktop) { 
                computerType="desktop";
            }

            //Edit computer
            switch(computerType) {

                //Editing a laptop (immutable → replace with a new one)
                case "laptop": 
                    System.out.println("Editing a Laptop:");

                    // get validated common data
                    Computer newBase = getComputerData(s); // validated
                    // get validated screen
                    String newScreen = InputValidator.promptScreenSize(s);

                    // create new immutable object and REPLACE in list
                    Laptop updatedLaptop = new Laptop(newBase, newScreen);
                    computers.set(computerListNumberToEdit-1, updatedLaptop); // replace object
                    break;

                //Editing a desktop (immutable → replace with a new one)
                case "desktop": 
                    System.out.println("Editing a Desktop:");

                    Computer newBaseD = getComputerData(s); // validated
                    String newGpu = InputValidator.promptGPU(s);

                    // create new immutable Desktop and replace in list
                    Desktop updatedDesktop = new Desktop(newBaseD.getCPU(), newBaseD.getRAM(), newBaseD.getDisk(), newGpu);
                    computers.set(computerListNumberToEdit-1, updatedDesktop);
                    break;

                default:
                    System.out.println("Unknown type to edit.");
            }

        }
        else {
            System.out.println("Invalid computer number entered!");
        }
    } //End of editComputer

    //-----------------------------
    //Helper: get data common to Laptop and Desktop (CPU, RAM and disk) with whitelist prompts
    private static Computer getComputerData(Scanner s) {
        // use validator so only allowed values pass
        String CPU  = InputValidator.promptCPU(s);   
        String RAM  = InputValidator.promptRAM(s);   
        String disk = InputValidator.promptDisk(s);  

        // return base immutable Computer
        return new Computer(CPU,RAM,disk);
    } //End of getComputerData

} //End of ManageComputer class
