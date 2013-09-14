package org.punegdg.kinosense.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Database Adapter class for Doing the CRUD operation on Rule entity
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * @author "Sagar Pawar" <sagar.pawar@synerzip.com>
 */
public class RuleAdapter {

    private final Context context;
    private static RuleDataBase ruleDataBaseHelper;
    private SQLiteDatabase db;

    public RuleAdapter(final Context ctx) {
        this.context = ctx;
        ruleDataBaseHelper = new RuleDataBase(ctx);
    }

    // ---opens the database---
    public RuleAdapter open() throws SQLException {
        this.db = ruleDataBaseHelper.getWritableDatabase();
        return this;
    }

    // ---closes the database---
    public void close() {
        ruleDataBaseHelper.close();
    }

    // ---insert a rule into the database---
    public long insertRule(final String rule, final int actionID, final int triggerID, final String additionalInfo, final String enable) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(RuleDataBase.KEY_RULE, rule);
        initialValues.put(RuleDataBase.ACTION_ID, actionID);
        initialValues.put(RuleDataBase.TRIGGER_ID, triggerID);
        initialValues.put(RuleDataBase.ADDITION_INFO, additionalInfo);
        initialValues.put(RuleDataBase.ENABLED, enable);
        return this.db.insert(RuleDataBase.getDatabaseTable(), null, initialValues);
    }

    // ---deletes a particular rule---
    public boolean deleteRule(final long rowId) {
        return this.db.delete(RuleDataBase.getDatabaseTable(), RuleDataBase.KEY_ROWID + "=" + rowId, null) > 0;
    }

    // ---retrieves all the rules---
    public Cursor getAllRules() {
        return this.db.query(RuleDataBase.getDatabaseTable(), new String[] { RuleDataBase.KEY_ROWID, RuleDataBase.KEY_RULE, RuleDataBase.ACTION_ID,
                RuleDataBase.TRIGGER_ID, RuleDataBase.ADDITION_INFO, RuleDataBase.ENABLED }, null, null, null, null, null, null);
    }

    // ---retrieves a particular rule---
    public Cursor getRule(final long rowId) throws SQLException {
        Cursor mCursor = this.db.query(true, RuleDataBase.getDatabaseTable(), new String[] { RuleDataBase.KEY_ROWID, RuleDataBase.KEY_RULE },
                RuleDataBase.KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    // ---updates a rule---
    public boolean updateRule(final long rowId, final String rule) {
        ContentValues args = new ContentValues();
        args.put(RuleDataBase.ENABLED, rule);
        return this.db.update(RuleDataBase.getDatabaseTable(), args, RuleDataBase.KEY_ROWID + "=" + rowId, null) > 0;

    }

    public void delete() {
        ruleDataBaseHelper.onUpgrade(this.db, 1, 2);
    }
}