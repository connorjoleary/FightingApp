package rainbowexplosion.fightingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by User on 11/26/2015.
 */
public class setup extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText insertName, insertJob;
    Button addData;


    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        myDb = new DatabaseHelper(this);

        insertName = (EditText)findViewById(R.id.insertName);
        insertJob = (EditText)findViewById(R.id.insertJob);
        addData = (Button)findViewById(R.id.insert);
        AddData();
    }

    public void AddData(){
        addData.setOnClickListener(
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    myDb.insertPlayer(insertName.getText().toString(), insertJob.getText().toString());
                }
            }
        );
    }
}
