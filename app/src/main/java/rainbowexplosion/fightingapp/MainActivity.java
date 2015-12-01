package rainbowexplosion.fightingapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void statsButton(View v){
        //Toast.makeText(MainActivity.this, "second button", Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, stats.class);
            MainActivity.this.startActivity(i);
    }

    public void fightButton(View v){
        //Toast.makeText(MainActivity.this, "second button", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, fight.class);
        MainActivity.this.startActivity(i);
    }

    public void monsterButton(View v){
        //Toast.makeText(MainActivity.this, "second button", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, MainFightActivity.class);
        MainActivity.this.startActivity(i);
    }

    public void setupButton(View v){
        //Toast.makeText(MainActivity.this, "second button", Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, setup.class);
        MainActivity.this.startActivity(i);
    }
}
