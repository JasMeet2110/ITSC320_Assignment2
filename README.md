# ITSC320_Assignment2


This assignment was updated to follow secure coding changes.

What I did:
1. Changed Laptop.java to use composition (Laptop has Computer inside)
2. Made Laptop immutable (final fields, no setters)
3. Created InputValidator.java to validate CPU, RAM, Disk, GPU, Screen using whitelist and reprompt loop
4. Updated ManageComputers.java to use InputValidator and replace Laptop and Desktop on edit (no setters used)
5. Rewrote Desktop.java to use composition and make it immutable (same style as Laptop)
6. Kept same program output and user flow, only changed what was required
