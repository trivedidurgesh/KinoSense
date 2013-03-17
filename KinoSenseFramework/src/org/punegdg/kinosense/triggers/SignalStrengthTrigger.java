package org.punegdg.kinosense.triggers;

import org.punegdg.kinosense.triggerReceiver.TriggerReceiver;

import org.punegdg.kinosense.triggers.framework.BroadCastReceiverBasedTrigger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;

import android.widget.Toast;

public class SignalStrengthTrigger extends BroadcastReceiver implements BroadCastReceiverBasedTrigger
{
	/**
	 * Android Application Context
	 */
	private Context context = null;

	/**
	 * Telephony Manager to listen Signal
	 */
	TelephonyManager telemgr = null;


	public void onCreate(Context context)
	{
		this.context = context;
		this.telemgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

		IntentFilter filter = new IntentFilter();
		
		//filter.addAction(Intent.ACTION_NETWORK_LOW);
		filter.addAction(Intent.ACTION_MANAGE_NETWORK_USAGE);
		
		context.registerReceiver(this.getBroadCastReceiver(), filter);
		PhoneStateListener signalListener = new PhoneStateListener() {
			@Override
			public void onSignalStrengthsChanged(SignalStrength signalStrength)
			{
				super.onSignalStrengthsChanged(signalStrength);
				int strength = signalStrength.getGsmSignalStrength();

				if ( strength < 8  )
				{
					Toast.makeText(SignalStrengthTrigger.this.context, "Poor Range ", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(SignalStrengthTrigger.this.context, "Good Range ", Toast.LENGTH_SHORT).show();
				}
			}
		};
		this.telemgr.listen(signalListener, PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);
	}


	public BroadcastReceiver getBroadCastReceiver()
	{
		return this;
	}


	public void onDestroy()
	{
		this.context.unregisterReceiver(this.getBroadCastReceiver());
		this.telemgr = null;
	}


	@Override
	public void onReceive(Context context, Intent intent)
	{

	  
	    if ( intent.getExtras().getInt("state") == 0 )
		{
			Intent bcSIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcSIntent.putExtra("trigger", "GOOD_SIGNAL_STRENGHTH");
			context.sendBroadcast(bcSIntent);

		}
		
	    	 else
		{
			Intent bcSIntent = new Intent(TriggerReceiver.ACTION_KINOSENSE_TRIGGER);
			bcSIntent.putExtra("trigger", "LOW_SIGNAL_STRENGTH");
			context.sendBroadcast(bcSIntent);
		}
	}

}
