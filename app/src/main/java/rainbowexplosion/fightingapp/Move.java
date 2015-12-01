package rainbowexplosion.fightingapp;

/**
 * Created by TheTStone on 11/28/2015.
 */
public class Move {

    private String name;
    private int modifyerNameVal;
    private int modValue;
    private int accuracy;
    private int speed;
    private Champ Target;
    private Champ Caster;

    /**
     *
     * @param n
     * @param NM   Hp = 0, Mana = 1, Attack = 2, Defence = 3, Speed = 4, Accuracy =5, Dodge = 6
     * @param V
     * @param Acc
     */
    public Move(String n, int NM, int V, int Acc, int spe) {
        name = n;
        modifyerNameVal = NM;
        modValue = V;
        accuracy = Acc;
        speed = spe;
    }

    public String getName() {

        return name;
    }

    public int getSpeed(){
        return speed;
    }

    public int getModType()
    {
        return modifyerNameVal;
    }

    public int modValue()
    {
        return modValue;
    }

    public int getAcc()
    {
        return accuracy;
    }

    public void setTarget(Champ c)
    {
        Target = c;
    }

    public Champ getTarget()
    {
        return Target;
    }

    public void setCaster(Champ c)
    {
        Caster = c;
    }

    public Champ getCaster()
    {
        return Caster;
    }



    public String toString()
    {
        return "Spell name: " + getName() + "   Spell Caster = " + getCaster().getChampName() + "   Spell Target = " + getTarget().getChampName();
    }
}