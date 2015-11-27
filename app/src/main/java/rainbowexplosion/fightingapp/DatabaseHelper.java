package rainbowexplosion.fightingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 11/26/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME = "Player";
    public static final String COL_1 = "Name";
    public static final String COL_2 = "Job";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (Name TEXT PRIMARY KEY, Job TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertPlayer(String name, String Job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(COL_1, name);
        content.put(COL_2, Job);
        long result = db.insert(TABLE_NAME,null,content);
        if(result==-1){
            return false;
        }
        return true;
    }
}
