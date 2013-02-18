/**
 * 
 */
package org.punegdg.kinosense.actions;

import java.util.Map;
import java.util.Calendar;


import org.punegdg.kinosense.MainActivity;
import org.punegdg.kinosense.R;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;




/**
 * 
 * Action which shows notification after each new trigger.
 * 
 *  @author "Rohit Ghatol"<rohitsghatol@gmail.com>
 *  @author "Amruta Deshpande"<deshpande.amruta22@gmail.com>
 */
public class NotificationAction implements AbstractAction
{

	private Context context = null;

	NotificationManager notifymgr = null;



	/*
	 * (non-Javadoc)
	 * @see org.punegdg.kinosense.actions.AbstractAction#onCreate(android.content.Context)
	 */
	public void onCreate(Context context)
	{
		this.context = context;
		this.notifymgr = (NotificationManager)this.context.getSystemService(Context.NOTIFICATION_SERVICE);


	}


	/*
	 * (non-Javadoc)
	 * @see org.punegdg.kinosense.actions.AbstractAction#perform(java.util.Map)
	 */
	public void perform(Map<String, Object> NotifyData)
	{
		String message = (String)NotifyData.get("message");
		
			Intent resultIntent = new Intent(this.context, MainActivity.class);
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this.context, 0, resultIntent,
				Intent.FLAG_ACTIVITY_NEW_TASK);

		int smallIcon = R.drawable.ic_action_search;

		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this.context);
		mBuilder.setContentTitle("KinoSense")
		             .setContentText(message)
    	             .setSmallIcon(smallIcon)
		              .setLights(0xFFFF0000, 500, 500) ;
		
		mBuilder.setContentIntent(resultPendingIntent);
		
	         mBuilder.setAutoCancel(true);  //for clearing notification automatically
         
		this.notifymgr.notify((int)Calendar.getInstance().getTimeInMillis(), mBuilder.getNotification());

		}


	/*
	 * (non-Javadoc)
	 * @see org.punegdg.kinosense.actions.AbstractAction#onDestroy()
	 */
	public void onDestroy()
	{
		// TODO Auto-generated method stub

		this.notifymgr = null;

	}

}
