package org.punegdg.kinosense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.punegdg.kinosense.database.RuleAdapter;
import org.punegdg.kinosense.database.RuleDataBase;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



/**
 * 
 * Activity for Showing, Activating and Updating Rules. 
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 * 
 */
public class RuleReviewActivity extends Activity {
	Button buttonreviewback,buttonreviewnext;
	static List<String> myListItems = Collections.synchronizedList(new ArrayList<String>());
	static ArrayAdapter<String> adapter;
	
	//Database for storing Rule
	RuleDataBase ruledata_op;
	RuleAdapter ruleadapter_op;
	SQLiteDatabase db_ob;
	ListView ruleList;
	static Cursor cursor;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		Intent ruleReviewintent = getIntent();
		final String str = ruleReviewintent.getStringExtra("param2");
		//Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
		setContentView(R.layout.activity_reviewrule);

		ruleList = (ListView) findViewById(R.id.rulelist);
		ruleadapter_op = new RuleAdapter(getApplicationContext());
		ruleadapter_op.open();
		
		//inserting A rule			
		@SuppressWarnings("unused")
		long id=ruleadapter_op.insertRule(str);	
		cursor=ruleadapter_op.getAllRules();
		int count=cursor.getCount();
		int startrow=getFirstRowID();
		Log.d("Cursor Count", ""+count); 
		Log.d("FirstRowID", startrow+"");
//		for(int i=startrow;i<=count;i++){
//			long iiid=i;
//			ruleadapter_op.deleteRule(iiid);			
//		}	
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, myListItems);		
               
        if (cursor.moveToFirst()){
        	myListItems.clear();
        do {        	
        	myListItems.add(cursor.getString(1));
        	Log.d("RowID", cursor.getString(0));
        	}while (cursor.moveToNext());
        }
		ruleList.setAdapter(adapter);
		ruleadapter_op.close();
		
		//Back Button to go back to the Main Menu
		buttonreviewback=(Button)findViewById(R.id.buttonreviewback);
		buttonreviewback.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RuleReviewActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});


		//button to create another rule , going back to New Action Rule
		buttonreviewnext=(Button)findViewById(R.id.buttonreviewnext);
		buttonreviewnext.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(RuleReviewActivity.this,NewActionRuleActivity.class);
				startActivity(intent);
			}
		});
	}
	
	public int getFirstRowID(){
		int rowid=0;
		cursor.moveToFirst();		
		rowid=Integer.parseInt(cursor.getString(0));
		return rowid;
	}


}
