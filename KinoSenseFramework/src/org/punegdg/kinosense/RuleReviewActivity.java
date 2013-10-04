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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
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
    static ArrayAdapter<Rule> editableAdapter;
    // Database for storing Rule
    SQLiteDatabase db_ob;
    private MenuItem saveMenuItem, deleteMenuItem;
    private CheckBox deleteBox, editBox;
    private ListView ruleList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_reviewrule);

        Intent startServiceIntent = new Intent(this.getApplicationContext(), SensorService.class);
        this.startService(startServiceIntent);

        this.deleteBox = (CheckBox) this.findViewById(R.id.checkboxdelete);
        this.editBox = (CheckBox) this.findViewById(R.id.checkboxedit);
        this.ruleList = (ListView) this.findViewById(R.id.rulelist);
        final RuleManager rulemanager = RuleManager.getInstance();
        RuleReviewActivity.ruleArray = rulemanager.getRules(this.getApplicationContext());
        adapter = new RuleListAdaptor(this, android.R.layout.simple_list_item_1, RuleReviewActivity.ruleArray);

        editableAdapter = new RuleListAdaptor(this, android.R.layout.simple_list_item_multiple_choice, RuleReviewActivity.ruleArray);

        if (RuleReviewActivity.ruleArray.size() > 0) {
            this.deleteBox.setVisibility(View.VISIBLE);
            this.editBox.setVisibility(View.VISIBLE);
        }

        this.ruleList.setAdapter(adapter);

        this.editBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    RuleReviewActivity.this.ruleList.setAdapter(editableAdapter);
                    RuleReviewActivity.this.ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    for (int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++) {
                        RuleReviewActivity.this.ruleList.setItemChecked(iterateCount, ruleArray.get(iterateCount).getState());
                    }
                    RuleReviewActivity.this.deleteBox.setEnabled(false);
                } else {
                    RuleReviewActivity.this.ruleList.setAdapter(adapter);
                    RuleReviewActivity.this.ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    RuleReviewActivity.this.saveMenuItem.setVisible(false);
                    RuleReviewActivity.this.deleteBox.setEnabled(true);
                }
            }
        });

        this.deleteBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    RuleReviewActivity.this.ruleList.setAdapter(editableAdapter);
                    RuleReviewActivity.this.ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    RuleReviewActivity.this.editBox.setEnabled(false);
                } else {
                    RuleReviewActivity.this.ruleList.setAdapter(adapter);
                    RuleReviewActivity.this.ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    RuleReviewActivity.this.deleteMenuItem.setVisible(false);
                    RuleReviewActivity.this.editBox.setEnabled(true);
                }
            }
        });

        this.ruleList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int item, final long arg3) {
                if (RuleReviewActivity.this.editBox.isChecked()) {
                    for (int index = 0; index < RuleReviewActivity.this.ruleList.getCount(); index++) {
                        boolean state = ((CheckedTextView) RuleReviewActivity.this.ruleList.getChildAt(index)).isChecked();
                        if (state != ruleArray.get(index).getState()) {
                            RuleReviewActivity.this.saveMenuItem.setVisible(true);
                            break;
                        } else {
                            RuleReviewActivity.this.saveMenuItem.setVisible(false);
                        }
                    }
                } else if (RuleReviewActivity.this.deleteBox.isChecked()) {
                    if (RuleReviewActivity.this.ruleList.getCheckedItemCount() > 0) {
                        RuleReviewActivity.this.deleteMenuItem.setVisible(true);
                    } else {
                        RuleReviewActivity.this.deleteMenuItem.setVisible(false);
                    }
                }
            }
        });
    }

    private void updateRuleList() {
        final RuleManager rulemanager = RuleManager.getInstance();

        if (this.editBox.isChecked()) {
            SparseBooleanArray sparseBooleanArray = this.ruleList.getCheckedItemPositions();
            for (int index = 0; index < this.ruleList.getCount(); index++) {
                // boolean state = ((CheckedTextView) ruleList.getChildAt(index)).isChecked();
                boolean state = sparseBooleanArray.get(index);

                if (state != ruleArray.get(index).getState()) {
                    ruleArray.get(index).setState(String.valueOf(state));
                    rulemanager.updateRule(ruleArray.get(index).getRuleId(), String.valueOf(state), RuleReviewActivity.this);
                }
                adapter.notifyDataSetChanged();
                editableAdapter.notifyDataSetChanged();
            }

        } else if (this.deleteBox.isChecked()) {

            final boolean[] stateValue = new boolean[this.ruleList.getCount()];

            SparseBooleanArray sparseBooleanArray = this.ruleList.getCheckedItemPositions();

            for (int index = 0; index < this.ruleList.getCount(); index++) {
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
                                RuleReviewActivity.this.editBox.setVisibility(View.GONE);
                                RuleReviewActivity.this.deleteBox.setVisibility(View.GONE);
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
        this.saveMenuItem.setVisible(false);
        this.deleteMenuItem.setVisible(false);
        this.editBox.setChecked(false);
        this.deleteBox.setChecked(false);
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.rule_review_menu, menu);
        this.saveMenuItem = menu.getItem(1);
        this.deleteMenuItem = menu.getItem(2);
        this.saveMenuItem.setVisible(false);
        this.deleteMenuItem.setVisible(false);
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
        case R.id.action_save_selected_rule_states:
            this.updateRuleList();
            return true;
        case R.id.action_delete_selected_rule:
            this.updateRuleList();
            return true;
            // case R.id.action_select_all:
            // return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        RuleReviewActivity.this.finish();
    }
}
