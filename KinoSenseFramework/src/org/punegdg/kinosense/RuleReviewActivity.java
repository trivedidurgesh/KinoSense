package org.punegdg.kinosense;

import java.util.ArrayList;

import org.punegdg.kinosense.eventsource.SensorService;
import org.punegdg.kinosense.rule.Rule;
import org.punegdg.kinosense.rule.RuleManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

/**
 * Activity for Showing, Activating and Updating Rules.
 * 
 * @author "Kumar Gaurav"<gauravsitu@gmail.com>
 */
public class RuleReviewActivity extends Activity {
    static ArrayAdapter<Rule> adapter;
    static ArrayList<Rule> ruleArray;
    // Database for storing Rule
    SQLiteDatabase db_ob;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_reviewrule);

        Intent startServiceIntent = new Intent(this.getApplicationContext(), SensorService.class);
        this.startService(startServiceIntent);

        final Button buttonreviewdelte = (Button) this.findViewById(R.id.buttonreviewdelete);
        final CheckBox deleteBox = (CheckBox) this.findViewById(R.id.checkboxdelete);
        final CheckBox editBox = (CheckBox) this.findViewById(R.id.checkboxedit);
        final ListView ruleList = (ListView) this.findViewById(R.id.rulelist);
        final RuleManager rulemanager = RuleManager.getInstance();
        RuleReviewActivity.ruleArray = rulemanager.getRules(this.getApplicationContext());
        adapter = new RuleListAdaptor(this, android.R.layout.simple_list_item_1, RuleReviewActivity.ruleArray);

        final ArrayAdapter<Rule> editableAdapter = new RuleListAdaptor(this, android.R.layout.simple_list_item_multiple_choice,
                RuleReviewActivity.ruleArray);

        // final ArrayAdapter<String> editableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,
        // myListItems);
        // myListItems.clear();

        if (RuleReviewActivity.ruleArray.size() > 0) {
            deleteBox.setVisibility(View.VISIBLE);
            editBox.setVisibility(View.VISIBLE);
        }

        // for (int iterateCount = 0; iterateCount < this.ruleArray.size(); iterateCount++) {
        // myListItems.add(this.ruleArray.get(iterateCount).getRuleText());
        // }
        ruleList.setAdapter(adapter);

        editBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    ruleList.setAdapter(editableAdapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    // final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
                    for (int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++) {
                        ruleList.setItemChecked(iterateCount, ruleArray.get(iterateCount).getState());
                    }
                    deleteBox.setEnabled(false);
                    buttonreviewdelte.setText(R.string.save);
                } else {
                    ruleList.setAdapter(adapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    buttonreviewdelte.setVisibility(View.INVISIBLE);
                    deleteBox.setEnabled(true);
                }
            }
        });

        deleteBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    ruleList.setAdapter(editableAdapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    editBox.setEnabled(false);
                    buttonreviewdelte.setText(R.string.delete);
                } else {
                    ruleList.setAdapter(adapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    buttonreviewdelte.setVisibility(View.INVISIBLE);
                    editBox.setEnabled(true);
                }
            }
        });

        ruleList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int item, final long arg3) {
                if (editBox.isChecked()) {
                    // final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
                    for (int index = 0; index < ruleList.getCount(); index++) {
                        boolean state = ((CheckedTextView) ruleList.getChildAt(index)).isChecked();
                        if (state != ruleArray.get(index).getState()) {
                            buttonreviewdelte.setVisibility(View.VISIBLE);
                            break;
                        } else {
                            buttonreviewdelte.setVisibility(View.INVISIBLE);
                        }
                    }
                } else if (deleteBox.isChecked()) {
                    if (ruleList.getCheckedItemCount() > 0) {
                        buttonreviewdelte.setVisibility(View.VISIBLE);
                    } else {
                        buttonreviewdelte.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        buttonreviewdelte.setOnClickListener(new OnClickListener() {
            public void onClick(final View v) {
                // final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
                if (editBox.isChecked()) {
                    SparseBooleanArray sparseBooleanArray = ruleList.getCheckedItemPositions();
                    for (int index = 0; index < ruleList.getCount(); index++) {
                        // boolean state = ((CheckedTextView) ruleList.getChildAt(index)).isChecked();
                        boolean state = sparseBooleanArray.get(index);

                        if (state != ruleArray.get(index).getState()) {
                            ruleArray.get(index).setState(String.valueOf(state));
                            rulemanager.updateRule(ruleArray.get(index).getRuleId(), String.valueOf(state), RuleReviewActivity.this);
                        }
                        adapter.notifyDataSetChanged();
                        editableAdapter.notifyDataSetChanged();
                    }

                } else if (deleteBox.isChecked()) {

                    final boolean[] stateValue = new boolean[ruleList.getCount()];

                    SparseBooleanArray sparseBooleanArray = ruleList.getCheckedItemPositions();

                    for (int index = 0; index < ruleList.getCount(); index++) {
                        boolean state = sparseBooleanArray.get(index);
                        stateValue[index] = state;
                    }
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RuleReviewActivity.this);
                    alertDialogBuilder.setTitle(R.string.dialog_title_delete);
                    alertDialogBuilder.setMessage(R.string.dialog_message_delete).setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(final DialogInterface dialog, final int id) {
                                    for (int index = (stateValue.length - 1); index >= 0; index--) {
                                        if (stateValue[index]) {
                                            rulemanager.deleteRule(ruleArray.get(index).getRuleId(), RuleReviewActivity.this);
                                            ruleArray.remove(index);
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                    editableAdapter.notifyDataSetChanged();
                                    if (ruleArray.size() == 0) {
                                        editBox.setVisibility(View.GONE);
                                        deleteBox.setVisibility(View.GONE);
                                    }
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                }
                buttonreviewdelte.setVisibility(View.INVISIBLE);
                editBox.setChecked(false);
                deleteBox.setChecked(false);

            }
        });

    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.rule_review_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
        case R.id.action_add_rule:
            Intent newruleintent = new Intent(RuleReviewActivity.this, NewTriggerRuleActivity.class);
            RuleReviewActivity.this.startActivity(newruleintent);
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        // Intent ruleReviewintent = new Intent(this, MainActivity.class);
        // this.startActivity(ruleReviewintent);
        this.moveTaskToBack(true);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        RuleReviewActivity.this.finish();
    }
}
