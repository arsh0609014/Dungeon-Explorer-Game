
public class Hound extends Character{
    private double bite;

    public Hound(){
        super("Hound",20,5,5,3);
        bite = 0.2;
    }

    @Override
    public void specialMove(Character target){
        if(rand.nextDouble() < bite){
            target.takeDamage(10);
            System.out.println("Hound used Bite! It dealt 10 damage.");
        } else {
            System.out.println("Hound's Bite missed!");
        }
    }
}