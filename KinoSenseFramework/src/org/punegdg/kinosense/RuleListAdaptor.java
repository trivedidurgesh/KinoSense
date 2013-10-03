/**
 *                     
 */

package org.punegdg.kinosense;

import java.util.List;

import org.punegdg.kinosense.rule.Rule;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

/**
 * @author saurabhg
 */
public class RuleListAdaptor extends ArrayAdapter<Rule> {
    List<Rule> myListItems;
    Context context;
    int textViewResourceId;

    /**
     * @param context
     * @param textViewResourceId
     * @param objects
     */
    public RuleListAdaptor(final Context context, final int textViewResourceId, final List<Rule> objects) {
        super(context, textViewResourceId, objects);
        this.textViewResourceId = textViewResourceId;
        this.context = context;
        this.myListItems = objects;
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#notifyDataSetChanged()
     */
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    /*
     * (non-Javadoc)
     * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        TextView row = null;
        CheckedTextView editRow = null;
        Rule rule = this.myListItems.get(position);
        if (null == row) {
            LayoutInflater inflater = ((Activity) this.context).getLayoutInflater();
            if (this.textViewResourceId == android.R.layout.simple_list_item_multiple_choice) {
                editRow = (CheckedTextView) inflater.inflate(this.textViewResourceId, parent, false);
                editRow.setText(rule.getRuleText());
                editRow.setChecked(rule.getState());
                if (rule.getState()) {
                    editRow.setBackgroundColor(Color.GREEN);
                }
                return editRow;
            } else {
                row = (TextView) inflater.inflate(this.textViewResourceId, parent, false);
                row.setText(rule.getRuleText());
                if (rule.getState()) {
                    row.setBackgroundColor(Color.GREEN);
                }
                return row;
            }
        }
        return null;

    }
}
