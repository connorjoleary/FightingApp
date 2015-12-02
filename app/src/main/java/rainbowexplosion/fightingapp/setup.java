package rainbowexplosion.fightingapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;

/**
 * Created by User on 11/26/2015.
 */
public class setup extends AppCompatActivity {
    DatabaseAccess databaseAccess;
    EditText insertName;
    String insertJob;
    Button addData, seeData;
    Spinner sp;

    protected void onCreate(Bundle savedInstanceState) {
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        sp = (Spinner)this.findViewById(R.id.jobSelect);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, databaseAccess.getString("Job", "Title"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        insertName = (EditText)findViewById(R.id.insertName);
        insertJob = (String)sp.getSelectedItem();
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
                        databaseAccess.insertPlayer(insertName.getText().toString(), insertJob);
                        finish();
                    }
                }
        );
    }
    public void SeeData(){
        seeData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        /*Cursor res = myDb.getPlayerData();
                        if (res.getCount() == 0) {
                            showMessage("error", "");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Name :" + res.getString(0) + "\n");
                            buffer.append("Job :" + res.getString(1) + "\n");
                        }*/

                        //show all
                        showMessage("Player name", ((PlayerName) getApplication()).getName());

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
