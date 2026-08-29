public class Warrior extends Hero{
    
    public Warrior(String name){
        super(name, 130, 30, 18, 10, 0.1);
    }

    @Override
    public void specialMove(Character target){
        int cost = 5;
        if (!hasEnoughMp(cost)) return;
        useMp(cost);
        int dmg = rollDamage() + 5;
        target.takeDamage(dmg);

        target.attack = Math.max(1,target.attack - 3);
         System.out.printf("  %s uses SHIELD BASH on %s for %d damage! " +
                          "(Enemy attack reduced by 3)%n", name, target.getName(), dmg);
    }
}
