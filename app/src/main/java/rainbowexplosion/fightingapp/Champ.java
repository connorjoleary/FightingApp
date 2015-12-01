package rainbowexplosion.fightingapp;

import android.service.textservice.SpellCheckerService;

import java.util.ArrayList;

/**
 * Created by TheTStone on 11/28/2015.
 */
public class Champ {

    private int HP;
    private int mana;
    private int attack;
    private int defence;
    private int speed;
    private int accuracy;
    private int dodge;
    private ArrayList<Move> Attacks;
    private ArrayList<Move> Spells;
    private ArrayList<Move> Consumables;
    private ArrayList<Move> ALLMoves;
    private String champName;


    public Champ(String name, int hp, int man, int att,int  def, int spe, int acc, int dog) {
        champName = name;
        HP = hp;
        mana = man;
        attack = att;
        defence = def;
        speed = spe;
        accuracy = acc;
        dodge = dog;
        Attacks = new ArrayList<Move>();
        Spells = new ArrayList<Move>();
        Consumables = new ArrayList<Move>();
        ALLMoves = new ArrayList<Move>();
    }

    public int getHP() {
        return HP;
    }

    public int getMana() {
        return mana;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getDodge() {
        return dodge;
    }

    public void changeHP(int change)
    {
        HP += change;
    }

    public void changeMana(int change)
    {
        mana += change;
    }

    public void changeAttack(int change)
    {
        attack += change;
    }

    public void changeDefence(int change)
    {
        defence += change;
    }

    public void changeSpeed(int change)
    {
        speed += change;
    }

    public void changeAccuracy(int change)
    {
        accuracy += change;
    }

    public void changeDodge(int change)
    {
        dodge += change;
    }

    public boolean isAlive()
    {
        if(HP > 0)
            return true;
        return false;
    }

    public void addAttMove(Move m) {
        m.setCaster(this);
        Attacks.add(m);
        ALLMoves.add(m);
    }
    public void addSpellMove(Move m) {
        m.setCaster(this);
        Spells.add(m);
        ALLMoves.add(m);
    }
    public void addConMove(Move m) {
        m.setCaster(this);
        Consumables.add(m);
        ALLMoves.add(m);
    }

    public ArrayList getAllMoves(){
        return ALLMoves;
    }

    public ArrayList getAttacks()
    {
        return Attacks;
    }

    public ArrayList getSpells()
    {
        return Spells;
    }

    public ArrayList getConsum()
    {
        return Consumables;
    }

    public Move findMove(String moveName)
    {
        for(Move m: ALLMoves)
        {
            if(m.getName().equals(moveName))
                return m;
        }
        return null;
    }

    public String getChampName()
    {
        return champName;
    }
}
