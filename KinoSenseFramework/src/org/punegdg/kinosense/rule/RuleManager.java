/**
 * Copyright 2012 Pune-GDG (http://meetup.com/pune-gdg)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.punegdg.kinosense.rule;

import java.util.ArrayList;

import org.punegdg.kinosense.database.RuleAdapter;
import org.punegdg.kinosense.database.RuleDataBase;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

/**
 * @author saurabhg
 */
public class RuleManager {
    // Database for storing Rule
    RuleDataBase ruledata_op;
    RuleAdapter ruleadapter_op;
    static Cursor cursor;

    private ArrayList<Rule> ruleList;

    /**
     * 
     */
    private RuleManager() {
    }

    private static RuleManager ruleManager;

    public static RuleManager getInstance() {
        if (null == ruleManager) {
            ruleManager = new RuleManager();
        }
        return ruleManager;
    }

    /**
     * Temporary method for populating rule list
     */
    public ArrayList<Rule> createRules() {
        // Rule rule1 = new Rule(0, TriggerIdConstants.DEVICE_FLIPPED_DOWN, ActionIdConstants.PHONE_SILENT, null);
        // Rule rule2 = new Rule(0, TriggerIdConstants.DEVICE_FLIPPED_UP, ActionIdConstants.PHONE_RINGING, null);
        // Rule rule3 = new Rule(0, TriggerIdConstants.POWER_CONNECTED_TRIGGER, ActionIdConstants.BRIGHTNESS_HIGH, null);

        ArrayList<Rule> ruleList = new ArrayList<Rule>(1);

        // ruleList.add(rule1);
        // ruleList.add(rule2);
        // ruleList.add(rule3);

        return ruleList;
    }

    public boolean createRule(final int triggerId, final int actionId, final String ruleText, final String additionalInformation, final String enable,
            final Context context) {
        boolean result = false;
    	this.ruleadapter_op = new RuleAdapter(context);
        this.ruleadapter_op.open();
    	if(! (this.ruleadapter_op.isRulePresent(actionId,triggerId)))
    	{
    		// inserting A rule
            @SuppressWarnings("unused")
    		long id = this.ruleadapter_op.insertRule(ruleText, actionId, triggerId, additionalInformation, enable);
    		result = true;
    	}
        return result;
    }

    /**
     * Update the rule state to enable or disable
     * 
     * @param ruleID
     *            ID of the rule
     * @param enable
     *            Rule state String value "true" or "false"
     * @param context
     *            Application context
     */
    public void updateRule(final int ruleID, final String enable, final Context context) {
        this.ruleadapter_op = new RuleAdapter(context);
        this.ruleadapter_op.open();
        // inserting A rule
        this.ruleadapter_op.updateRule(ruleID, enable);

    }

    /**
     * Delete a rule
     * 
     * @param ruleID
     *            ID of the rule to delete
     * @param context
     *            Application context
     */
    public void deleteRule(final int ruleID, final Context context) {
        this.ruleadapter_op = new RuleAdapter(context);
        this.ruleadapter_op.open();
        // inserting A rule
        this.ruleadapter_op.deleteRule(ruleID);

    }

    public ArrayList<Rule> getRules(final Context context) {
        this.ruleadapter_op = new RuleAdapter(context);
        this.ruleadapter_op.open();
        cursor = this.ruleadapter_op.getAllRules();

        int count = cursor.getCount();
        // int startrow = getFirstRowID();
        Log.d("Cursor Count", "" + count);
        // Log.d("FirstRowID", startrow + "");

        this.ruleList = new ArrayList<Rule>();
        this.ruleList.clear();
        if (cursor.moveToFirst()) {
            do {
                Log.d("rule id", cursor.getString(0));
                Log.d("rule id", cursor.getString(1));
                Log.d("rule id", cursor.getString(2));
                Log.d("rule id", cursor.getString(3));
                Log.d("rule id", cursor.getString(4));
                Log.d("rule id", cursor.getString(5));
                Rule tempRule = new Rule(Integer.parseInt(cursor.getString(0)), cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5));
                this.ruleList.add(tempRule);
            } while (cursor.moveToNext());
        }
        this.ruleadapter_op.close();
        return this.ruleList;
    }
    
}
