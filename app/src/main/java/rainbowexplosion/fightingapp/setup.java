package rainbowexplosion.fightingapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 11/26/2015.
 */
public class setup extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText insertName, insertJob;
    Button addData, seeData;


    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        myDb = new DatabaseHelper(this);

        insertName = (EditText)findViewById(R.id.insertName);
        insertJob = (EditText)findViewById(R.id.insertJob);
        addData = (Button)findViewById(R.id.insert);
        seeData = (Button)findViewById(R.id.see);
        AddData();
        SeeData();
    }

    public void AddData(){
        addData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDb.clearDataPlayer();
                        if (myDb.insertPlayer(insertName.getText().toString(), insertJob.getText().toString())) {
                            finish();
                        }
                    }
                }
        );
    }
    public void SeeData(){
        seeData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getPlayerData();
                        if (res.getCount() == 0) {
                            showMessage("error", "");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :" + res.getString(0) + "\n");
                            buffer.append("Job :" + res.getString(1) + "\n");
                        }

                        //show all
                        showMessage("data", buffer.toString());

                    }
                }
        );
    }
    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
