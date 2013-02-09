package org.punegdg.kinosense.actions;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MusicAction extends Service
{
	private MediaPlayer mediaplayer = null;


	@Override
	public void onCreate()
	{
		super.onCreate();
		Uri uri = Uri.parse("/sdcard/external_sd/AyeUdiUdi.mp3"); // address of the song, can vary.
		this.mediaplayer = MediaPlayer.create(this, uri);
		this.mediaplayer.setLooping(false);
		Toast.makeText(this, "Starting music...", Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onDestroy()
	{
		super.onDestroy();
		this.mediaplayer.stop();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		this.mediaplayer.start();
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
	}


	@Override
	public IBinder onBind(Intent intent)
	{
		return null;
	}

}
