package org.punegdg.kinosense;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.punegdg.kinosense.rule.Rule;
import org.punegdg.kinosense.rule.RuleManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    static List<String> myListItems = Collections.synchronizedList(new ArrayList<String>());
    static ArrayAdapter<String> adapter;
    // Database for storing Rule
    SQLiteDatabase db_ob;

    // final ListView ruleList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_reviewrule);
        final Button buttonreviewdelte = (Button) this.findViewById(R.id.buttonreviewdelete);
        final CheckBox deleteBox = (CheckBox) this.findViewById(R.id.checkboxdelete);
        final CheckBox editBox = (CheckBox) this.findViewById(R.id.checkboxedit);
        final ListView ruleList = (ListView) this.findViewById(R.id.rulelist);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myListItems);
        final ArrayAdapter<String> editableAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, myListItems);
        myListItems.clear();
        final RuleManager rulemanager = RuleManager.getInstance();
        ArrayList<Rule> ruleArray = rulemanager.getRules(this.getApplicationContext());
        if (ruleArray.size() > 0) {
            deleteBox.setVisibility(View.VISIBLE);
            editBox.setVisibility(View.VISIBLE);
        }

        ruleList.setAdapter(adapter);
        for (int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++) {
            myListItems.add(ruleArray.get(iterateCount).getRuleText());
        }
        ruleList.setEnabled(false);

        editBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    ruleList.setAdapter(editableAdapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
                    for (int iterateCount = 0; iterateCount < ruleArray.size(); iterateCount++) {
                        ruleList.setItemChecked(iterateCount, ruleArray.get(iterateCount).getState());
                    }
                    deleteBox.setEnabled(false);
                    ruleList.setEnabled(true);
                    buttonreviewdelte.setText(R.string.save);
                } else {
                    ruleList.setAdapter(adapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    buttonreviewdelte.setVisibility(View.INVISIBLE);
                    deleteBox.setEnabled(true);
                    ruleList.setEnabled(false);
                }
            }
        });

        deleteBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    ruleList.setAdapter(editableAdapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                    editBox.setEnabled(false);
                    ruleList.setEnabled(true);
                    buttonreviewdelte.setText(R.string.delete);
                } else {
                    ruleList.setAdapter(adapter);
                    ruleList.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
                    buttonreviewdelte.setVisibility(View.INVISIBLE);
                    editBox.setEnabled(true);
                    ruleList.setEnabled(false);
                }
            }
        });

        ruleList.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(final AdapterView<?> arg0, final View arg1, final int item, final long arg3) {
                if (editBox.isChecked()) {
                    final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
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
                final ArrayList<Rule> ruleArray = rulemanager.getRules(RuleReviewActivity.this.getApplicationContext());
                if (editBox.isChecked()) {
                    for (int index = 0; index < ruleList.getCount(); index++) {
                        boolean state = ((CheckedTextView) ruleList.getChildAt(index)).isChecked();

                        if (state != ruleArray.get(index).getState()) {
                            rulemanager.updateRule(ruleArray.get(index).getRuleId(), String.valueOf(state), RuleReviewActivity.this);
                        }
                        adapter.notifyDataSetChanged();
                        editableAdapter.notifyDataSetChanged();
                    }

                } else if (deleteBox.isChecked()) {

                    final boolean[] stateValue = new boolean[ruleList.getCount()];
                    for (int index = 0; index < ruleList.getCount(); index++) {

                        boolean state = ((CheckedTextView) ruleList.getChildAt(index)).isChecked();
                        stateValue[index] = state;
                    }
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RuleReviewActivity.this);
                    alertDialogBuilder.setTitle(R.string.dialog_title_delete);
                    alertDialogBuilder.setMessage(R.string.dialog_message_delete).setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                public void onClick(final DialogInterface dialog, final int id) {
                                    for (int index = 0; index < stateValue.length; index++) {
                                        if (stateValue[index]) {
                                            rulemanager.deleteRule(ruleArray.get(index).getRuleId(), RuleReviewActivity.this);
                                            myListItems.remove(index);
                                        }
                                    }
                                    adapter.notifyDataSetChanged();
                                    editableAdapter.notifyDataSetChanged();
                                    if (myListItems.size() == 0) {
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
}
