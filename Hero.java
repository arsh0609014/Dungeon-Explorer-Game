import java.util.ArrayList;
import java.util.Random;

public abstract class Hero extends Character{
    protected ArrayList<String> inventory;
    protected int gold;
    protected double evadeChance;
    Random rand = new Random();
    
    public Hero(String name, int hp, int mp, int attack, int defense, double evadeChance) {
        super(name,hp,mp,attack,defense);
        this.evadeChance = evadeChance;
        inventory = new ArrayList<>();
        gold = 0;
    }

    public boolean tryEvade(){
        if(rand.nextDouble() < evadeChance){
            System.out.println(name + " evades the attack!");
            return true;
        }
        return false;
    }
    @Override
    public abstract void specialMove(Character target);

    public void pickUpLoot(String item, int goldAmount) {
        inventory.add(item);
        gold += goldAmount;
        System.out.printf("  Picked up: %s (+%d gold)%n", item, goldAmount);
    }

    public void printInventory() {
        System.out.println("Gold: " + gold);
        if (inventory.isEmpty()) {
            System.out.println("Inventory: (empty)");
        } else {
            System.out.println("Inventory: " + inventory);
        }
    }

    public int getGold() { return gold; }
    public String getClassName() { return this.getClass().getSimpleName(); }
}

