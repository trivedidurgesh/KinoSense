package org.punegdg.kinosense.database;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 
 * Database Adapter class for Doing the CRUD operation on Rule entity  
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class RuleAdapter {	

	private final Context context;
	private static RuleDataBase ruleDataBaseHelper;
	private SQLiteDatabase db;

	public RuleAdapter(Context ctx)
	{
		this.context = ctx;
		ruleDataBaseHelper = new RuleDataBase(ctx);
	}

	//---opens the database---
	public RuleAdapter open() throws SQLException
	{
		db = ruleDataBaseHelper.getWritableDatabase();
		return this;
	}
	//---closes the database---
	public void close()
	{
		ruleDataBaseHelper.close();
	}

	//---insert a rule into the database---
	public long insertRule(String rule)
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(RuleDataBase.KEY_RULE, rule);		
		return db.insert(RuleDataBase.getDatabaseTable(), null, initialValues);
	}
	//---deletes a particular rule---
	public boolean deleteRule(long rowId)
	{
		return db.delete(RuleDataBase.getDatabaseTable(), RuleDataBase.KEY_ROWID + "="+ rowId, null) > 0;
	}
	//---retrieves all the rules---
	public Cursor getAllRules()
	{
		return db.query(RuleDataBase.getDatabaseTable(), new String[] {RuleDataBase.KEY_ROWID, RuleDataBase.KEY_RULE}, null, null, null, null, null);
	}

	//---retrieves a particular rule---
	public Cursor getRule(long rowId) throws SQLException
	{
		Cursor mCursor =
				db.query(true, RuleDataBase.getDatabaseTable(), new String[] {RuleDataBase.KEY_ROWID, RuleDataBase.KEY_RULE}, RuleDataBase.KEY_ROWID + "=" + rowId, null,
						null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a rule---
	public boolean updateRule(long rowId, String rule){
		ContentValues args = new ContentValues();
		args.put( RuleDataBase.KEY_RULE, rule);		
		return db.update(RuleDataBase.getDatabaseTable(), args, RuleDataBase.KEY_ROWID + "=" + rowId, null) > 0;
		
	}
	
	
} 