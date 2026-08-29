import java.util.Scanner;

public class Game {
    private Hero hero;
    private DungeonMap map;
    private Scanner scanner;

    public Game(Hero hero) {
        this.hero = hero;
        map     = new DungeonMap();
        scanner = new Scanner(System.in);
    }


    public void start() {
        System.out.println("\nYou descend into the dungeon, Fear overwhelms you!!!");
        map.getCurrentRoom().describe();

        boolean playing = true;
        while (playing && hero.isAlive()) {
            System.out.println("\nHP: " + hero.getHPbar());
            System.out.println("Move [N/S/E/W] | [I]nventory | [M]ap | [Q]uit");
            System.out.print("> ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "N": case "S": case "E": case "W":
                    if (map.move(input)) {
                        Room room = map.getCurrentRoom();
                        room.describe();
                        handleRoom(room);
                    }
                    break;
                case "I":
                    hero.printInventory();
                    break;
                case "M":
                    map.printMap();
                    break;
                case "Q":
                    playing = false;
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }

        if (!hero.isAlive()) {
            System.out.println("\nYou have fallen in the dungeon. Game over.");
        } else {
            System.out.println("\nYou escaped the dungeon. Farewell, hero!");
        }
    }

    private void handleRoom(Room room) {
        // Trap check
        if (room.hasTrap()) {
            System.out.println("  CLICK — a trap! You take 10 damage.");
            hero.takeDamage(10);
            room.disarmTrap();
        }

        // Combat check
        Character enemy = room.getEnemy();
        if (enemy != null && enemy.isAlive()) {
            combat(enemy);
        }

        // Loot check (after combat so you can't grab it mid-fight)
        if (room.hasLoot() && (room.getEnemy() == null || !room.getEnemy().isAlive())) {
            System.out.println("  You open the chest — Rusty Sword! +20 gold.");
            hero.pickUpLoot("Rusty Sword", 20);
            room.clearLoot();
        }
    }

    private void combat(Character enemy) {
        System.out.println("\n*** COMBAT: " + hero.getName()
                           + " vs " + enemy.getName() + " ***");

        while (hero.isAlive() && enemy.isAlive()) {
            System.out.println("\n  Your HP : " + hero.getHPbar());
            System.out.println("  Enemy HP: " + enemy.getHPbar());
            System.out.println("  [1] Attack  [2] Special  [3] Flee");
            System.out.print("  > ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    hero.attackTarget(enemy);
                    break;
                case "2":
                    hero.specialMove(enemy);
                    break;
                case "3":
                    if (Character.rand.nextInt(2) == 0) {
                        System.out.println("  You flee successfully!");
                        return;
                    } else {
                        System.out.println("  Couldn't escape!");
                    }
                    break;
                default:
                    System.out.println("  Invalid. You hesitate.");
            }

            
            if (enemy.isAlive()) {
    
                if (hero instanceof Mage) {
                    ((Mage) hero).applyBurn();
                }

    
                if (!hero.tryEvade()) {
                    if (Character.rand.nextInt(4) == 0) {
                        enemy.specialMove(hero);
                    } else {
                        enemy.attackTarget(hero);
                    }
    }
}
        }

        if (!enemy.isAlive()) {
            System.out.println("\n  " + enemy.getName() + " is defeated!");
            hero.heal(10);
        }
    }
}