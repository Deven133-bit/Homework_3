import java.util.Scanner;

public class Main {
    static int health = 100;
    static int potions = 2;
    static String name;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome, brave adventurer!");
        System.out.print("What is your name? ");
        name = scanner.nextLine();

        startScene();

        scanner.close();
        System.out.println("\nApplication ended.");
    }

    static void showStatus() {
        System.out.println("\n--- " + name.toUpperCase() + " STATUS ---");
        System.out.println("HEALTH: " + health + "%");
        System.out.println("POTIONS: " + potions);
        System.out.println("--------------------------");
    }

    static int getChoice() {
        System.out.print("Enter your choice (1 or 2): ");
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        }
        scanner.nextLine();
        return 0;
    }

    static void usePotion() {
        if (health < 100 && potions > 0) {
            potions--;
            health += 30;
            if (health > 100) health = 100;
            System.out.println("-> Used Potion. Health is now " + health + ".");
        } else if (potions == 0) {
            System.out.println("-> You are out of Potions!");
        }
    }

    static void startScene() {
        System.out.println("\n=================================");
        System.out.println("      THE RUINS OF AETHELGARD");
        System.out.println("=================================");
        System.out.println("\nYou seek the Sunstone. The entrance splits into two dark tunnels.");

        showStatus();
        System.out.print("Use potion now? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) usePotion();

        System.out.println("1. Take the LEFT tunnel (smells like salt).");
        System.out.println("2. Take the RIGHT tunnel (smells like smoke).");

        int choice = getChoice();

        if (choice == 1) {
            sceneWaterTunnel();
        } else {
            sceneFireTunnel();
        }
    }

    static void sceneWaterTunnel() {
        System.out.println("\n--- The Grotto Path ---");
        System.out.println("You find a Potion! You now have " + ++potions + " potions.");
        System.out.println("A spectral Guardian blocks your way!");

        showStatus();
        System.out.println("1. ATTACK the Guardian (Risk: 25 damage).");
        System.out.println("2. SNEAK past.");

        if (getChoice() == 1) {
            health -= 25;
            System.out.println("-> You took 25 damage.");
            if (health <= 0) {
                endGame(false, "The Guardian's icy touch was fatal.");
                return;
            }
        } else {
            System.out.println("-> Your stealth succeeds!");
        }
        sceneFinalChamberA();
    }

    static void sceneFireTunnel() {
        System.out.println("\n--- The Volcanic Ascent ---");
        System.out.println("You must cross a stone bridge over lava.");

        showStatus();
        System.out.println("1. RUSH ACROSS (Risk: 15 damage).");
        System.out.println("2. CAREFULLY TEST the stones (Chance to find potion).");

        if (getChoice() == 1) {
            health -= 15;
            System.out.println("-> You took 15 damage.");
        } else {
            System.out.println("-> You cross safely.");
            if (Math.random() > 0.6) {
                System.out.println("-> You found a hidden Potion!");
                potions++;
            }
        }
        sceneFinalChamberB();
    }

    static void sceneFinalChamberA() {
        System.out.println("\n--- The Chamber of Reflections ---");
        System.out.println("The Sunstone is on a pedestal. A plaque warns: 'demands a tithe of life'.");

        showStatus();
        System.out.println("1. GRAB THE SUNSTONE (High Risk: 90 damage).");
        System.out.println("2. PAUSE and look for an alternative.");

        if (getChoice() == 1) {
            health -= 90;
            System.out.println("-> You paid the life-tithe (90 damage).");
            if (health <= 0) {
                endGame(false, "The cost was too high. You fail.");
            } else {
                endGame(true, "You pay the tithe and survive to claim the Sunstone!");
            }
        } else {
            System.out.println("-> You find a hidden button, disarming the trap.");
            endGame(true, "Your caution pays off! You claim the Sunstone without injury.");
        }
    }

    static void sceneFinalChamberB() {
        System.out.println("\n--- The Obsidian Vault ---");
        System.out.println("A door requires a 3-digit code. You only see '5' and '8'.");

        showStatus();
        System.out.println("1. Try to FORCE the door (Risk: 40 damage).");
        System.out.println("2. SEARCH for the missing digit.");

        if (getChoice() == 1) {
            health -= 40;
            System.out.println("-> A thermal blast hits you (40 damage).");
            if (health <= 0) {
                endGame(false, "The thermal blast seals your fate.");
            } else {
                endGame(false, "You take heavy damage, but the door holds fast. You retreat.");
            }
        } else {
            System.out.println("-> You find the number '2' hidden nearby. Code: '5-8-2'.");
            endGame(true, "A clever deduction! You bypass the defenses and claim the Sunstone.");
        }
    }

    static void endGame(boolean won, String message) {
        System.out.println("\n=================================");
        if (won) {
            System.out.println("           *** VICTORY! ***");
        } else {
            System.out.println("            *** DEFEAT ***");
        }
        System.out.println("=================================");
        System.out.println("\n" + message);
        showStatus();
    }
}
