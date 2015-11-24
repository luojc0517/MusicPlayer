package com.jackie.musicplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnPause).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        initMediaPlayer();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                }
                break;
            case R.id.btnPause:
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
                break;
            case R.id.btnStop:
                if (mPlayer.isPlaying()) {
                    //如果用了stop，那么这个MediaPlayer就不能再播放音乐了
                    mPlayer.reset();
                    initMediaPlayer();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlayer != null) {
            //弃用播放器
            mPlayer.stop();
            //释放资源
            mPlayer.release();
        }
    }

    private void initMediaPlayer() {
        //初始化MediaPlayer
        /*
           bug0001:
           java.io.FileNotFoundException: /storage/emulated/0/winter.mp3: open failed: EACCES (Permission denied)
           不用说了，忘记给权限了，读取SDcard的权限
         */
        File file = new File(Environment.getExternalStorageDirectory(), "winter.mp3");
        try {
            //指定播放源
            mPlayer.setDataSource(file.getPath());
            //准备播放
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
