public class Room {
    private String description;
    private Character enemy;       
    private boolean hasLoot;
    private boolean hasTrap;
    private boolean visited;

    public Room(String description, Character enemy, boolean hasLoot, boolean hasTrap) {
        this.description = description;
        this.enemy       = enemy;
        this.hasLoot     = hasLoot;
        this.hasTrap     = hasTrap;
        this.visited     = false;
    }

    public void describe() {
        System.out.println("\n=== " + description + " ===");
        if (enemy != null && enemy.isAlive()) {
            System.out.println("  A " + enemy.getName() + " lurks here!");
        } else if (hasLoot) {
            System.out.println("  You spot a glinting chest.");
        } else if (hasTrap) {
            System.out.println("  The floor looks suspicious...");
        } else {
            System.out.println("  The room is quiet.");
        }
        visited = true;
    }

    
    public Character getEnemy()  { return enemy; }
    public boolean hasLoot()     { return hasLoot; }
    public boolean hasTrap()     { return hasTrap; }
    public boolean isVisited()   { return visited; }


    public void clearLoot()      { hasLoot = false; }
    public void disarmTrap()     { hasTrap = false; }
}
