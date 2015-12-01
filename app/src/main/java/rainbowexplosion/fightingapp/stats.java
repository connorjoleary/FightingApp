package rainbowexplosion.fightingapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by User on 11/26/2015.
 */
public class stats extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView t;
    TextView t2;
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.getPlayerData();
        res.moveToNext();

        t=new TextView(this);
        t= (TextView) findViewById(R.id.playerName);
        t.setText(res.getString(0));

        t2=new TextView(this);
        t2= (TextView) findViewById(R.id.playerEXP);
        t2.setText(res.getString(1));


        findViewById(R.id.to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    /*public void toMain(View v){
        Toast.makeText(stats.this,"second button",Toast.LENGTH_LONG).show();
        finish();
        //Intent i = new Intent(stats.this, MainActivity.class);
        //stats.this.startActivity(i);
    }*/
}
