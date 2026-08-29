import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== DUNGEON RPG ===");
        System.out.print("Enter your hero's name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        
        System.out.println("\nChoose your class:");
        System.out.println("  [1] Warrior  — 130 HP | 18 ATK | 10 DEF | 10% evade | Shield Bash");
        System.out.println("  [2] Mage     —  70 HP | 22 ATK |  3 DEF | 15% evade | Fireball + Burn");
        System.out.println("  [3] Rogue    —  90 HP | 14 ATK |  4 DEF | 35% evade | Backstab + Crits");
        System.out.print("> ");

        Hero hero;
        String choice = scanner.nextLine().trim();
        switch (choice) {
            case "1": hero = new Warrior(name); break;
            case "2": hero = new Mage(name);    break;
            case "3": hero = new Rogue(name);   break;
            default:
                System.out.println("Invalid choice — defaulting to Warrior.");
                hero = new Warrior(name);
        }

        System.out.println("\nYou chose: " + hero.getClassName()
                           + " | HP: " + hero.getHp()
                           + " | ATK: " + hero.attack
                           + " | DEF: " + hero.defense);

        Game game = new Game(hero);
        
        game.start();

        scanner.close();
    }
}