package org.punegdg.kinosense.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Database Helper class for Doing the CRUD operation on Rule entity
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * @author "Sagar Pawar" <sagar.pawar@synerzip.com>
 */
public class RuleDataBase extends SQLiteOpenHelper {
    // Database fields
    public static final String KEY_ROWID = "_id";
    public static final String KEY_RULE = "rule";
    private static final String TAG = "RuleAdapter";
    public static final String ACTION_ID = "actionID";
    public static final String TRIGGER_ID = "triggerID";
    public static final String ADDITION_INFO = "additionInfo";

    private static final String DATABASE_NAME = "ruledb";
    private static final String DATABASE_TABLE = "ruletable";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "CREATE TABLE IF NOT EXISTS \"ruletable\" (_id integer primary key autoincrement,"
            + "rule text not null,actionID text not null,triggerID text not null,additionInfo text);";

    public RuleDataBase(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);
        } catch (android.database.SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        // TODO Auto-generated method stub
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS contacts");
        this.onCreate(db);
    }

    public static String getDatabaseTable() {
        return DATABASE_TABLE;
    }
}