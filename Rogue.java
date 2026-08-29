public class Rogue extends Hero {
    private double critChance;
    
    public Rogue(String name) {
        super(name, 90, 50,14, 4, 0.45);
       
        critChance = 0.25;  
    }

    
    @Override
    public void attackTarget(Character target) {
        if (rand.nextDouble() < critChance) {
            int dmg = rollDamage() * 2;
            target.takeDamage(dmg);
            System.out.printf("  %s lands a CRITICAL HIT on %s for %d damage!%n",
                              name, target.getName(), dmg);
        } else {
            super.attackTarget(target);
        }
    }

    @Override
    public void specialMove(Character target) {
        int cost = 15;
        if (!hasEnoughMp(cost)) return;
        useMp(cost);
        int dmg = rollDamage() * 3;
        target.takeDamage(dmg);
        System.out.printf("  %s uses BACKSTAB on %s for %d damage!%n",
                          name, target.getName(), dmg);
    }
}