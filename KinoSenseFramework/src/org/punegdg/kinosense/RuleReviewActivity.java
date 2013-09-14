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
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

/**
 * Activity for Showing, Activating and Updating Rules.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 */
public class RuleReviewActivity extends Activity {
    Button buttonreviewsave, buttonreviewnext;
    static List<String> myListItems = Collections.synchronizedList(new ArrayList<String>());
    static ArrayAdapter<String> adapter;

    // Database for storing Rule
    SQLiteDatabase db_ob;
    ListView ruleList;
    private int actionID;
    private int triggerID;
    private String additionalInfo = "";
    private String TextLabel = "";
    private String enabled = "true";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_reviewrule);
        this.ruleList = (ListView) this.findViewById(R.id.rulelist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, myListItems);
        myListItems.clear();
        this.ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        final RuleManager rulemanager = RuleManager.getInstance();
        final ArrayList<Rule> ruleArray = rulemanager.getRules(this.getApplicationContext());
        this.ruleList.setAdapter(adapter);
        for (int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++) {
            myListItems.add(ruleArray.get(iterateCount).getRuleText());
            this.ruleList.setItemChecked(iterateCount, ruleArray.get(iterateCount).getState());
        }
        this.buttonreviewsave = (Button) this.findViewById(R.id.buttonreviewsave);
        this.buttonreviewsave.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                for (int index = 0; index < RuleReviewActivity.this.ruleList.getCount(); index++) {
                    boolean state = ((CheckedTextView) RuleReviewActivity.this.ruleList.getChildAt(index)).isChecked();
                    if (state != ruleArray.get(index).getState()) {
                        rulemanager.updateRule(ruleArray.get(index).getRuleId(), String.valueOf(state), RuleReviewActivity.this);
                    }
                }
                Intent intent = new Intent(RuleReviewActivity.this, MainActivity.class);
                RuleReviewActivity.this.startActivity(intent);
            }

        });

        // button to create another rule , going back to New Action Rule
        this.buttonreviewnext = (Button) this.findViewById(R.id.buttonreviewnext);
        this.buttonreviewnext.setOnClickListener(new OnClickListener() {

            public void onClick(final View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(RuleReviewActivity.this, NewTriggerRuleActivity.class);
                RuleReviewActivity.this.startActivity(intent);
            }
        });
    }
}
