import java.util.Random;

public abstract class Character {
    protected String name;
    protected int hp;
    protected int mp;
    protected int attack;
    protected int defense;
    protected int maxhp;
    protected int maxmp;
    protected static Random rand = new Random();

    public Character(String name, int hp, int mp, int attack, int defense) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.defense = defense;
        this.maxhp = hp;
        this.maxmp = mp;
    }

    public int rollDamage(){
        int swing = rand.nextInt(5) - 2;
        return Math.max(1,attack + swing - defense);
    }

    public boolean hasEnoughMp(int cost){
        
        if(mp < cost){
            System.out.println(name + " does not have enough MP!");
            return false;
        }
        return true;

    }

    public void useMp(int cost){
        if(hasEnoughMp(cost))
            mp -= cost;
    }

    public void restoreMp(int amt){
        mp = Math.min(maxmp, mp + amt);
    }
    public void takeDamage(int damage){
        hp = Math.max(0, hp - damage);
    }

    public void attackTarget(Character target){
        int dmg = rollDamage();
        target.takeDamage(dmg);
        System.out.printf("%s attacks %s for %d damage! ",name, target.name ,dmg);
    }
    
    public boolean isAlive(){
        return hp > 0;
    }

    public abstract void specialMove(Character target);

    public String getHPbar(){
        int filled = (int)((double)hp / maxhp * 20);
        return "[" + "=".repeat(filled) + " ".repeat(20 - filled) + "]" + hp + "/" + maxhp;
    }

    public String getName() { return name; }
    public int getHp()      { return hp; }
    public int getMp()      { return mp; }
    public void heal(int amount) { hp = Math.min(maxhp, hp + amount); }


}