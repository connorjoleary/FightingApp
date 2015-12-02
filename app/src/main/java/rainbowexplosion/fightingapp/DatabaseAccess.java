package rainbowexplosion.fightingapp;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import java.util.ArrayList;
        import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getString(String table, String attribute){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT "+table+"."+attribute+" FROM "+ table, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<String> getPlayers(String name) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Player WHERE Player.Name='"+name+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            list.add(cursor.getInt(1) + "");
            list.add(cursor.getInt(2)+"");
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getTools(String name) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Tool.Name, Tool.Slot "+
                "FROM Tool, Player, tool_stock WHERE Tool.Name=tool_stock.Name AND tool_stock.Owner='"
                +name+"'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add("Name: "+cursor.getString(0)+" Slot: "+cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void insertPlayer(String name, String job){
        database.rawQuery("INSERT INTO Player VALUES ('"+name+"',0,1,'"+job+"',NULL, NULL, NULL, NULL, NULL, NULL);",null);
    }
}