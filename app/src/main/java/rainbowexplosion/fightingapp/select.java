package rainbowexplosion.fightingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by User on 12/1/2015.
 */
public class select extends AppCompatActivity {
    Spinner sp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_player);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        sp = (Spinner)this.findViewById(R.id.PlayerSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, databaseAccess.getString("Player", "Name"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        findViewById(R.id.to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((PlayerName) getApplication()).setName((String) sp.getSelectedItem());
                Intent i = new Intent(select.this, MainActivity.class);
                select.this.startActivity(i);
            }
        });
    }

}
