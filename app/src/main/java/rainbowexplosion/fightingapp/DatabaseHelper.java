package rainbowexplosion.fightingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 11/26/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "data.db";
    public static final String TABLE_NAME_PLAYER = "Player";
    public static final String PLAYER_COL_1 = "Name";
    public static final String PLAYER_COL_2 = "EXP";
    public static final String PLAYER_COL_3 = "Job";

    public static final String TABLE_NAME_JOB = "Job";
    public static final String JOB_COL_1 = "Name";
    public static final String JOB_COL_2 = "Level";
    public static final String JOB_COL_3 = "Exp Required";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME_PLAYER + " (Name TEXT PRIMARY KEY, EXP INT, Job TEXT)");
        //db.execSQL("create table Job (Name TEXT PRIMARY KEY, EXP INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_PLAYER);
        onCreate(db);
    }

    public boolean insertPlayer(String name, String Job){
        SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
        ContentValues content = new ContentValues();
        content.put(PLAYER_COL_1, name);
        //content.put(PLAYER_COL_2, 0);
        content.put(PLAYER_COL_3, Job);
        long result = db.insert(TABLE_NAME_PLAYER,null,content);
        if(result==-1){
            return false;
        }
        return true;
    }
    public Cursor getPlayerData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Player",null);
        return res;
    }
    public void clearDataPlayer(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_PLAYER, null, null);
        //db.execSQL("create table " + TABLE_NAME_PLAYER + " (Name TEXT PRIMARY KEY, EXP INT, Job TEXT)");
    }
}
