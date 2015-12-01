package rainbowexplosion.fightingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fight);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    //@Override
    /*public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void print(View v)
    {
        //System.out.println("fuck fuck fuck");
        TextView text = (TextView)this.findViewById(R.id.startText);
        text.setText("fuck");


        setContentView(R.layout.fight_main_page);
        initializefight();
        fightControl();
    }

    private Champ a1;
    private Champ a2;
    private Champ e1;
    private Champ e2;


    private ArrayList<Move> stack;

    private Champ selectedChamp;




    public void champSelected(View v)
    {
        String ChampName = (String)v.getContentDescription();
        if(ChampName.equals("a1"))
        {
            selectedChamp = a1;
        }
        if(ChampName.equals("a2"))
        {
            selectedChamp = a2;
        }
    }

    public void getSpinnerMove(View v)
    {
        String type = (String)v.getContentDescription();
        Spinner sp = (Spinner)this.findViewById(R.id.MoveSpinner);

        ArrayList<Move> champsMoves = null;
        if(selectedChamp != null) {
            switch (type)
            {
                case "fight":
                    champsMoves = selectedChamp.getAttacks();
                    break;
                case "spell":
                    champsMoves = selectedChamp.getSpells();
                    break;
                case "consume":
                    champsMoves = selectedChamp.getConsum();
            }
            if(champsMoves != null) {
                String[] moveNames = new String[champsMoves.size()];
                for (int i = 0; i < moveNames.length; i++) {
                    moveNames[i] = champsMoves.get(i).getName();
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, moveNames);
                sp.setAdapter(adapter);
            }
        }
    }


    private boolean ally1MadeMove;
    private boolean ally2MadeMove;

    public void playerDoNothing(View v)
    {
        if(selectedChamp != null)
        {
            if(selectedChamp.getChampName().equals("a1"))
            {
                ally1MadeMove = true;
            }
            else if(selectedChamp.getChampName().equals("a2"))
            {
                ally2MadeMove = true;
            }
        }

        if(ally1MadeMove && ally2MadeMove)
        {
            beginBattle();
        }
    }


    public void playerMadeMove(View v)
    {
        Spinner sp = (Spinner)this.findViewById(R.id.MoveSpinner);

        if(selectedChamp != null && sp.getSelectedItem() != null)
        {
            System.out.println("i win");
            if(selectedChamp.getChampName().equals("a1") && ally1MadeMove == false && a1.isAlive())
            {
                Move m = selectedChamp.findMove((String)sp.getSelectedItem());
                if(v.getContentDescription().equals("e1"))
                {
                    //System.out.println("e1    V.GETCONTENTDESCRIPTION = " + v.getContentDescription());
                    m.setCaster(a1); //`HUGE ISSUE, PLEASE FIX LATER. FOR SOME REASON CASTER IS ALWAYS SET TO E2
                    m.setTarget(e1);

                    System.out.println("added to stack = " + m.toString());

                    stack.add(m);
                    //System.out.println("e1 set as target");
                }
                else
                {
                    //System.out.println("e2    V.GETCONTENTDESCRIPTION = " + v.getContentDescription());
                    m.setCaster(a1); //`HUGE ISSUE, PLEASE FIX LATER. FOR SOME REASON CASTER IS ALWAYS SET TO E2
                    m.setTarget(e2);
                    System.out.println("added to stack = " + m.toString());
                    stack.add(m);
                    //System.out.println("e2 set as target");
                }
                ally1MadeMove = true;
            }
            else if(selectedChamp.getChampName().equals("a2") && ally2MadeMove == false && a2.isAlive())
            {
                Move m = selectedChamp.findMove((String)sp.getSelectedItem());
                if(v.getContentDescription().equals("e1"))
                {
                    m.setCaster(a2); //`HUGE ISSUE, PLEASE FIX LATER. FOR SOME REASON CASTER IS ALWAYS SET TO E2
                    m.setTarget(e1);
                    System.out.println("added to stack = " + m.toString());
                    stack.add(m);
                    //System.out.println("e1 set as target");
                }
                else
                {
                    m.setCaster(a2); //`HUGE ISSUE, PLEASE FIX LATER. FOR SOME REASON CASTER IS ALWAYS SET TO E2
                    m.setTarget(e2);
                    System.out.println("added to stack = " + m.toString());
                    stack.add(m);
                    //System.out.println("e2 set as target");
                }
                ally2MadeMove = true;
            }
            if(!a1.isAlive())
            {
                ally1MadeMove = true;
            }
            if(!a2.isAlive())
            {
                ally2MadeMove = true;
            }

           if(ally1MadeMove && ally2MadeMove)
           {
               beginBattle();
           }

        }
    }

    public void beginBattle()
    {
        ArrayList<Move> moveOrder = new ArrayList<Move>();
        System.out.println("size = " + stack.size());
        //System.out.println("test  " + stack.get(0).getName());
        //System.out.println("end test");
        for(Move m: stack)
        {
            System.out.println("move name = " + m.getName());
            System.out.println("caster = " + m.getCaster().getChampName());
            System.out.println("target = " + m.getTarget().getChampName());
        }

        while (stack.size() > 0) //puts moves in order
        {
            int index = 0;
            int highestSpeed = 0;
            for(int i = 0; i < stack.size(); i++)
            {
                if(stack.get(i).getCaster().getSpeed() + stack.get(i).getSpeed() > highestSpeed)
                {
                    highestSpeed = stack.get(i).getCaster().getSpeed() + stack.get(i).getSpeed();
                    index = i;
                }
            }

            System.out.println("MOVEORDER ADDING: " + stack.get(index).getName() + "  from  " + stack.get(index).getCaster().getChampName());
            moveOrder.add(stack.get(index));
            stack.remove(index);
        }

        for(Move currentMove: moveOrder)
        {
            //System.out.println("SPEED EQUALS = " + (currentMove.getSpeed() + currentMove.getCaster().getSpeed()));
        if(currentMove.getCaster().isAlive()) {
            if (currentMove.getAcc() + currentMove.getCaster().getAccuracy() > currentMove.getTarget().getDodge()) {
                int damageDealt = currentMove.modValue() - currentMove.getCaster().getAttack() + currentMove.getTarget().getDefence();
                if (damageDealt < 0) {
                    switch (currentMove.getTarget().getChampName()) {
                        case "e1":
                            e1.changeHP(damageDealt);
                            break;
                        case "e2":
                            e2.changeHP(damageDealt);
                            break;
                        case "a2":
                            a2.changeHP(damageDealt);
                            break;
                        case "a1":
                            a1.changeHP(damageDealt);
                            break;
                    }
                }
            }
        }
        }


        System.out.println("E2 HP = " + e2.getHP());
        ally2MadeMove = false;
        ally1MadeMove = false;
        fightControl();
    }

    public void initializefight()
    {
        createChamps();
        stack = new ArrayList<Move>();
        ally1MadeMove = false;
        ally2MadeMove = false;
        selectedChamp = null;
    }



    public void fightControl()
    {

        System.out.println("fightcontrol");
        TextView a1HP = (TextView)this.findViewById(R.id.Ally1HP);
        TextView a2HP = (TextView)this.findViewById(R.id.Ally2HP);
        TextView e1HP = (TextView)this.findViewById(R.id.Enemy1HP);
        TextView e2HP = (TextView)this.findViewById(R.id.Enemy2HP);
        a1HP.setText(a1.getHP() + "");
        a2HP.setText(a2.getHP() + "");
        e1HP.setText(e1.getHP() + "");
        e2HP.setText(e2.getHP() + "");



        TextView info = (TextView)this.findViewById(R.id.InfoTextBox);



        if((a1.isAlive() || a2.isAlive()) && (e1.isAlive() || e2.isAlive()))
        {

            Move[] enemyMoves = getEnemyMoves();
            boolean killLoop = true;

            for(Move m:enemyMoves)
            {
                if(m != null) {
                    stack.add(m);
                }
            }


        }

        if(!e1.isAlive() && !e2.isAlive())
        {
            System.out.println("GAMEOVER");
            info.setText("YOU WIN!");
            try{
                wait(10000);

            }catch(Exception e){System.out.println(e.toString());}
            setContentView(R.layout.activity_main);
            TextView t = (TextView)findViewById(R.id.startText);
            t.setText("YOU WIN, GAMEOVER!");
        }
        else if(!a1.isAlive() && !a2.isAlive()){
            System.out.println("GAMEOVER");
            info.setText("ENEMY PLAYER WINS! YOU SUCK!");
            try{
                wait(10000);

            }catch(Exception e){System.out.println(e.toString());}
            setContentView(R.layout.activity_main);
            TextView t = (TextView)findViewById(R.id.startText);
            t.setText("YOU LOSE AND SUCK IN EVERY CONCIEVABLE FASHION! GAMEOVER!");

        }
        System.out.println("END OF FLIGHTCONTROL");
    }



    public Move[] getEnemyMoves()
    {
        int e1target = (int)Math.random();
        int e2target = (int)Math.random();
       // int e1target = 1;
        //int e2target = 0;
        Move e1Move = null;
        Move e2Move = null;
    if(e1.isAlive()) {
        if (e1.getHP() < 20) {
            ArrayList<Move> m = e1.getConsum();
            e1Move = m.get(0);
             e1Move.setTarget(e1);
        } else {
             ArrayList<Move> m = e1.getAllMoves();
             int x = (int) Math.random() * m.size();
              if (e1target == 1 && a1.isAlive()) {
              e1Move = m.get(x);
                  e1Move.setTarget(a1);
            e1Move.setCaster(e1);
        } else if(a2.isAlive()){
            e1Move = m.get(x);
            e1Move.setTarget(a2);
            e1Move.setCaster(e1);
        }else{
                  e1Move = m.get(x);
                  e1Move.setTarget(a1);
                  e1Move.setCaster(e1);
              }
    }
}
//        System.out.println("E1'S MOVE IS = " + e1Move.toString());
    if(e2.isAlive()) {
    if (e2.getHP() < 20) {
        ArrayList<Move> m = e2.getConsum();
        e2Move = m.get(0);
        e2Move.setTarget(e2);
    } else {
        ArrayList<Move> m = e2.getAllMoves();
        int x = (int) Math.random() * m.size();
        if (e2target == 1 && a1.isAlive()) {
            e2Move = m.get(x);
            e2Move.setTarget(a1);
            e2Move.setCaster(e2);
        } else if(a2.isAlive()){
            e2Move = m.get(x);
            e2Move.setTarget(a2);
            e2Move.setCaster(e2);
        }else
        {
            e2Move = m.get(x);
            e2Move.setTarget(a1);
            e2Move.setCaster(e2);
        }
    }
}
        Move[] moves = new Move[2];
        if(e1Move != null)
            moves[0] = e1Move;
        if(e2Move != null)
            moves[1] = e2Move;
        //System.out.println("e1move = " + e1Move.toString());
        //System.out.println("e2move = " + e2Move.toString());
        return moves;
    }




    public void createChamps()
    {
        a1 = new Champ("a1", 100, 50, 10, 10, 10, 50, 50);
        a2 = new Champ("a2", 100, 50, 10, 10, 100, 50, 50);
        e1 = new Champ("e1", 100, 50, 10, 10, 20, 50, 50);
        e2 = new Champ("e2", 100, 50, 10, 10, 120, 50, 50);
        Move m1 = new Move("Slash", 0, -15, 20, 30);
        a1.addAttMove(m1);
        Move m1a = new Move("Slash", 0, -15, 20, 30);
        a2.addAttMove(m1a);
       // e1.addAttMove(m1);
       // e2.addAttMove(m1);

        System.out.println("THIS IS THE NAME TEST:    " + m1.getName());

                Move m2 = new Move("Fireball", 0, -20, 10, 10);
        a1.addSpellMove(m2);
        Move m2a = new Move("Fireball", 0, -20, 10, 10);
        e1.addSpellMove(m2a);
        Move m2d = new Move("Fireball", 0, -20, 10, 10);
        e2.addSpellMove(m2d);

        Move m3 = new Move("Healing Potion", 0, 20, 1000, 15);
        a2.addConMove(m3);
        Move m3a = new Move("Healing Potion", 0, 20, 1000, 15);
        e2.addConMove(m3a);
        Move w = new Move("Healing Potion", 0, 20, 1000, 15);
        e1.addConMove(w);

        Move m4 = new Move("Slap", 0, -5, 60, 100);
        a1.addAttMove(m4);
        Move m4a = new Move("Slap", 0, -5, 60, 100);
        a2.addAttMove(m4a);
    }
}
