package org.punegdg.kinosense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.punegdg.kinosense.rule.Rule;
import org.punegdg.kinosense.rule.RuleManager;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
public class RuleReviewActivity extends Activity
{
    Button buttonreviewback, buttonreviewnext;
    static List<String> myListItems = Collections.synchronizedList(new ArrayList<String>());
    static ArrayAdapter<String> adapter;

    //Database for storing Rule
    SQLiteDatabase db_ob;
    ListView ruleList;
    private int actionID;
    private int triggerID;
    private String additionalInfo = "";
    private String TextLabel = "";


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent ruleReviewintent = this.getIntent();
        if ( ruleReviewintent != null )
        {
            this.actionID = ruleReviewintent.getIntExtra("actionID", -1);
            this.triggerID = ruleReviewintent.getIntExtra("triggerID", -1);
            this.TextLabel = ruleReviewintent.getStringExtra("ruletext");
        }
        // Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        this.setContentView(R.layout.activity_reviewrule);

        this.ruleList = (ListView)this.findViewById(R.id.rulelist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myListItems);
        myListItems.clear();
        RuleManager rulemanager = RuleManager.getInstance();
        if((this.actionID!=-1)||(this.triggerID!=-1)) {
            rulemanager.createRules(this.triggerID, this.actionID, this.TextLabel, this.additionalInfo, this.getApplicationContext());
        }
        ArrayList<Rule> ruleArray = rulemanager.getRules(this.getApplicationContext());
        for ( int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++ )
        {
            myListItems.add(ruleArray.get(iterateCount).getRuleText());
        }
        this.ruleList.setAdapter(adapter);

        // Back Button to go back to the Main Menu
        this.buttonreviewback = (Button)this.findViewById(R.id.buttonreviewback);
        this.buttonreviewback.setOnClickListener(new OnClickListener() {
            public void onClick(final View v)
            {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RuleReviewActivity.this, MainActivity.class);
                RuleReviewActivity.this.startActivity(intent);
            }
        });

        // button to create another rule , going back to New Action Rule
        this.buttonreviewnext = (Button)this.findViewById(R.id.buttonreviewnext);
        this.buttonreviewnext.setOnClickListener(new OnClickListener() {

            public void onClick(final View v)
            {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RuleReviewActivity.this, NewTriggerRuleActivity.class);
                RuleReviewActivity.this.startActivity(intent);
            }
        });
    }
}
