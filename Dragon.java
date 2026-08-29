public class Dragon extends Character{
    private int phase;
    public Dragon(){
        super("Ancient Dragon", 200, 100, 25, 10);
        phase = 1;
    }

    @Override
    public void takeDamage(int damage){
        super.takeDamage(damage);
        if (hp <= maxhp / 2 && phase == 1) {
            phase = 2;
            attack += 10;
            defense += 5;
            System.out.println("The Dragon enters a rage! --- it's eyes glow red!");
        }
    }

    @Override
    public void specialMove(Character target){
        int dmg = attack + rand.nextInt(10) + 5;
        target.takeDamage(dmg);
        System.out.printf("  Dragon breathes FIRE on %s for %d damage!%n",target.getName(), dmg);
    }

    public int getPhase() { return phase; }
}
