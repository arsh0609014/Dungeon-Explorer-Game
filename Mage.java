public class Mage extends Hero {
    private boolean burnActive;
    private Character burnTarget;
    public Mage(String name){
        super(name, 70, 70, 25, 5, 0.3);
        burnActive = false;
        burnTarget = null;
    }

    @Override
    public void specialMove(Character target){
        int cost = 10;
        if (!hasEnoughMp(cost)) return;
        useMp(cost);
        int dmg = rollDamage() + 10;
        target.takeDamage(dmg);
        System.out.printf("  %s casts FIREBALL on %s for %d damage!%n",
                          name, target.getName(), dmg);

        if (rand.nextInt(2) == 0) {
            burnActive = true;
            burnTarget = target;
            System.out.println("  " + target.getName() + " is BURNING!");
        }
    }

    public void applyBurn() {
        if (burnActive && burnTarget != null && burnTarget.isAlive()) {
            int burnDmg = 5;
            burnTarget.takeDamage(burnDmg);
            System.out.println("  " + burnTarget.getName()
                               + " takes " + burnDmg + " burn damage!");
            burnActive = false;
            burnTarget = null;
        }
    }

    public boolean hasBurn() { return burnActive; }

}
