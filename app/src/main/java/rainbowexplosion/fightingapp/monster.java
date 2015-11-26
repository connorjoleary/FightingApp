package rainbowexplosion.fightingapp;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 11/26/2015.
 */
public class monster extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_screen);

        findViewById(R.id.to_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
