package com.example.edu.bindservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;

public class MusicPlayer extends AppCompatActivity implements View.OnClickListener{

    Button buttonPlay, buttonStop;

    private BackgroundMusicWithBindService mServiceBinder;
    private ServiceConnection myConnection = new ServiceConnection() {//start activity
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServiceBinder = ((BackgroundMusicWithBindService.MyBinder)binder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Intent intent = new Intent(this, BackgroundMusicWithBindService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

        findViewById(R.id.buttonPlay).setOnClickListener(this);
        findViewById(R.id.buttonStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.buttonPlay:
                //mServiceBinder.play();
                play();
                Log.e("tag", "buttonPlay");
                break;
            case R.id.buttonStop:
                //mServiceBinder.stop();
                stop();
                Log.e("tag", "buttonPlay");
                break;
        }
    }
    MediaPlayer mPlayer;
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


/*

    앱으로 구분
1. Context.startService()
        앱의 라이프사이클에 종속 안됨
        앱이 사라졌는데 소리가 들림
        종료 시키기어려움
        (이름이 없는 쓰레드,
        제3자가 나를 호출 어려움)
        결과값 리턴 안됨
        콜백 메소드 구현 안함

        종속되지 않고 계속 떠있음

        2. Context.bindService()
        앱의 라이프사이클에 종속 됨
        앱이 사라졌는데 소리가 안들림
        종료 시키기 쉬움
        (연결고리가 있음)
        인텐드가 있음
        액티비티 스타트 하면 결과를 기대하고
        결과값 리턴
        콜백 메소드 구현 함



        - 서버
        계속 돌아가고 요청에 응답해주는 것
        서비스는 서버다

*/
