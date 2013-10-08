package org.punegdg.kinosense.dialogs;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;

/**
 * Create you own custom dialog by adding the public method corresponding to you custom dialog
 * 
 * @author durgesht
 */
public class CustomDialog extends AlertDialog {
    private Context context;
    private String title;
    private String textMessage;
    private String number;
    private String jsonMessage;

    public CustomDialog(final Context context, final String title) {
        super(context);
        this.title = title;
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    /**
     * Dialog box to insert the message to send as SMS
     * 
     * @return dialog builder
     */
    public AlertDialog.Builder createMessageOnlyDialog() {
        final EditText input = new EditText(this.context);
        return new AlertDialog.Builder(this.context).setTitle(this.title).setView(input)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int whichButton) {
                        JSONObject json = new JSONObject();
                        try {
                            CustomDialog.this.textMessage = input.getText().toString();
                            json.put("message", CustomDialog.this.textMessage);
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        CustomDialog.this.jsonMessage = json.toString();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int whichButton) {
                        // Do nothing.
                    }
                });
    }

    /**
     * Get the JSON for addition info message string
     * 
     * @return json message string for additional info
     */
    public String getJSONString() {
        return this.jsonMessage;
    }
}
