

public class Goblin extends Character{
    private double stealChance;

    public Goblin(){
        super("Goblin", 30, 20, 10, 3);
        stealChance = 0.3;
    }

    @Override
    public void specialMove(Character target){
        if (rand.nextDouble() < stealChance) {
            System.out.println("  Goblin STEALS gold from " + target.getName() + "!");
            
        } else {
            
            attackTarget(target);
            System.out.println("  (Steal failed — attacked instead)");
        }
            
    }
}
