import java.util.Random;

public class DungeonMap {
    private Room[][] rooms;
    private int playerX;
    private int playerY;
    private Random rand = new Random();

    String[] names = {
        "Dusty Crypt", "Flooded Hall", "Spider Den",
        "Broken Gate", "Guard Post",
        "Bone Chamber", "Armoury"
    };



    public DungeonMap() {
        playerX = 0;
        playerY = 0;
        rooms = new Room[3][3];
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (x == 0 && y == 0) {
                    rooms[y][x] = new Room("Central Hall", null, false, false);
                } else if (x == 2 && y == 2) {
                    rooms[y][x] = new Room("Dragon's Lair", new Dragon(), false, false);
                } else {
                    String name = names[rand.nextInt(names.length)];
                    Character enemy  = rand.nextInt(2) == 0 ? new Goblin() : null;
                    boolean hasTreasure = rand.nextInt(2) == 0;
                    boolean hasTrap = rand.nextInt(2) == 0;
                    rooms[y][x] = new Room(name, enemy, hasTreasure, hasTrap);
                }
            }   
        }
        
    }

    
    public boolean move(String direction) {
        int nx = playerX;
        int ny = playerY;

        switch (direction.toUpperCase()) {
            case "N": ny--; break;
            case "S": ny++; break;
            case "E": nx++; break;
            case "W": nx--; break;
            default:
                System.out.println("Unknown direction. Use N/S/E/W.");
                return false;
        }

        if (nx < 0 || nx > 2 || ny < 0 || ny > 2) {
            System.out.println("  There's a wall in that direction.");
            return false;
        }

        if(rooms[playerY][playerX].getEnemy() != null && rooms[playerY][playerX].getEnemy().isAlive()) {
            System.out.println("  You can't leave while an enemy is alive!");
            return false;
        }

        playerX = nx;
        playerY = ny;
        return true;
    }

    public Room getCurrentRoom() {
        return rooms[playerY][playerX];
    }

    public void printMap() {
        System.out.println("\n  [Map: . = visited, ? = unknown, @ = you]");
        for (int y = 0; y < 3; y++) {
            System.out.print("  ");
            for (int x = 0; x < 3; x++) {
                if (x == playerX && y == playerY) System.out.print("@ ");
                else if (rooms[y][x].isVisited())  System.out.print(". ");
                else                               System.out.print("? ");
            }
            System.out.println();
        }
    }
}