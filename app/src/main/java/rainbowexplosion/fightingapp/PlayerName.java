package rainbowexplosion.fightingapp;

import android.app.Application;

/**
 * Created by User on 12/2/2015.
 */
public class PlayerName extends Application{
    private String name;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
}
