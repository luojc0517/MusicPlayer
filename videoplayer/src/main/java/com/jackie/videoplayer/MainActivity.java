package com.jackie.videoplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VideoView mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnPause).setOnClickListener(this);
        findViewById(R.id.btnReplay).setOnClickListener(this);
        mPlayer = (VideoView) findViewById(R.id.videoView);
        File file = new File(Environment.getExternalStorageDirectory(), "video.mp4");
        mPlayer.setVideoPath(file.getPath());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                if (!mPlayer.isPlaying()) {
                    mPlayer.start();
                }
                break;
            case R.id.btnPause:
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
                break;
            case R.id.btnReplay:
                if (mPlayer.isPlaying()) {
                    //如果当前视频正在播放，重新播放
                    mPlayer.resume();
                }
                break;
        }
    }
}
