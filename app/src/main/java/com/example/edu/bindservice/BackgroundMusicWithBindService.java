package com.example.edu.bindservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class BackgroundMusicWithBindService extends Service {
    MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");

        return new MyBinder();
    }

    public class MyBinder extends Binder{
        BackgroundMusicWithBindService getService(){
            return BackgroundMusicWithBindService.this;
        }
    }

    public void play(){
        mPlayer = MediaPlayer.create(this, R.raw.spring_day);
        mPlayer.setLooping(true);
        mPlayer.setVolume(100, 100);
        mPlayer.start();
    }

    public void stop(){
        mPlayer.stop();
        mPlayer.release();
    }
}
