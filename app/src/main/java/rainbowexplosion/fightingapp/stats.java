package rainbowexplosion.fightingapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ListView;
import java.util.List;

/**
 * Created by User on 11/26/2015.
 */
public class stats extends AppCompatActivity {
    TextView t;
    //TextView t2;
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        List<String> player = databaseAccess.getPlayers(((PlayerName) getApplication()).getName());

        t=new TextView(this);
        t= (TextView) findViewById(R.id.name);
        t.setText("Name: "+player.get(0));

        t=new TextView(this);
        t= (TextView) findViewById(R.id.job);
        t.setText("Job: "+player.get(3));


        this.listView = (ListView) findViewById(R.id.listView);
        List<String> tools = databaseAccess.getTools(((PlayerName) getApplication()).getName());
        tools.addAll(databaseAccess.getTools(((PlayerName) getApplication()).getName()));
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tools);
        this.listView.setAdapter(adapter);


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
