import java.util.*;

public final class InputValidator {

    // Assignment whitelists
    private static final Set<String> CPU = set("i5", "i7");
    private static final Set<String> RAM = set("16", "32");
    private static final Set<String> DISK = set("512", "1024");
    private static final Set<String> GPU = set("Nvidia", "AMD");
    private static final Set<String> SCREEN = set("13", "14");

    private InputValidator() {
    }

    private static Set<String> set(String... vals) {
        return new HashSet<>(Arrays.asList(vals));
    }

    // ---------- Low-level whitelist reader ----------
    private static String readFrom(Scanner s, String prompt, Set<String> whitelist) {
        while (true) {
            System.out.print(prompt);
            String in = s.nextLine().trim();
            if (whitelist.contains(in))
                return in;
            System.out.println("Invalid value. Allowed: " + whitelist);
        }
    }

    // ---------- Your method names (used in ManageComputers.java) ----------
    public static String promptCPU(Scanner s) {
        return readFrom(s, "Enter CPU (i5/i7):", CPU);
    }

    public static String promptRAM(Scanner s) {
        return readFrom(s, "Enter RAM (16/32):", RAM);
    }

    public static String promptDisk(Scanner s) {
        return readFrom(s, "Enter Disk (512/1024):", DISK);
    }

    public static String promptGPU(Scanner s) {
        return readFrom(s, "Enter GPU:", GPU);
    } // prompt text matches your original

    public static String promptScreenSize(Scanner s) {
        return readFrom(s, "Enter screen size:", SCREEN);
    } // prompt text matches your original

    // ---------- Safe numeric index reader (1-based from user, returns 0-based)
    // ----------
    public static int readIndex(Scanner s, int size) {
        while (true) {
            System.out.print("Enter number of computer:");
            String in = s.nextLine().trim();
            try {
                int v = Integer.parseInt(in);
                if (v >= 1 && v <= size)
                    return v - 1;
            } catch (NumberFormatException ignore) {
            }
            System.out.println("Invalid computer number entered!");
        }
    }
}
