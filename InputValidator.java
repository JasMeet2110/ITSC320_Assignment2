// simple input validator using whitelists
// edit the arrays to allow/disallow values
// loops until user enters something valid

import java.util.Scanner;

public class InputValidator {

    // whitelists
    private static final String[] CPU = {"i5", "i7"};
    private static final String[] RAM = {"16", "32"};
    private static final String[] DISK = {"512", "1024"};
    private static final String[] GPU = {"Nvidia", "AMD"};   // desktop only
    private static final String[] SCREEN = {"13", "14"};     // laptop only

    // prompt helpers 
    public static String promptCPU(Scanner s) {
        return promptFromWhitelist(s, "Enter CPU (i5/i7): ", CPU, true);
    }

    public static String promptRAM(Scanner s) {
        return promptFromWhitelist(s, "Enter RAM (16/32): ", RAM, false);
    }

    public static String promptDisk(Scanner s) {
        return promptFromWhitelist(s, "Enter Disk (512/1024): ", DISK, false);
    }

    public static String promptGPU(Scanner s) { // desktop only
        return promptFromWhitelist(s, "Enter GPU (Nvidia/AMD): ", GPU, true);
    }

    public static String promptScreenSize(Scanner s) { // laptop only
        return promptFromWhitelist(s, "Enter Screen (13/14): ", SCREEN, false);
    }

    private static String promptFromWhitelist(Scanner s, String prompt, String[] allowed, boolean keepCaseFor) {
        while (true) {
            System.out.print(prompt);
            String raw = s.nextLine().trim();

            // try to match ignoring case but return canonical value from whitelist
            for (String ok : allowed) {
                if (raw.equalsIgnoreCase(ok)) {
                    // return the canonical version so data is consistent
                    return keepCaseFor ? ok : ok;
                }
            }


            System.out.println("Invalid input. Allowed: " + listOptions(allowed));
        }
    }

    // just shows allowed values in one line
    private static String listOptions(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append("/");
        }
        return sb.toString();
    }

    // optional direct check methods
    public static boolean isValidCPU(String v)    { return inList(v, CPU); }
    public static boolean isValidRAM(String v)    { return inList(v, RAM); }
    public static boolean isValidDisk(String v)   { return inList(v, DISK); }
    public static boolean isValidGPU(String v)    { return inList(v, GPU); }
    public static boolean isValidScreen(String v) { return inList(v, SCREEN); }

    private static boolean inList(String v, String[] arr) {
        for (String x : arr) if (x.equalsIgnoreCase(v)) return true;
        return false;
    }
}
