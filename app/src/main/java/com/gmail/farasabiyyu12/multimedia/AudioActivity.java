package com.gmail.farasabiyyu12.multimedia;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioActivity extends AppCompatActivity {

    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnStop)
    Button btnStop;
    @BindView(R.id.btnResume)
    Button btnResume;
    @BindView(R.id.btnPause)
    Button btnPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        ButterKnife.bind(this);

        benarkan(btnPlay);
        rusakkan(btnStop, btnPause, btnResume);
    }

    private void rusakkan(View... views) {
        for(View v: views){
            v.setEnabled(false);
        }
    }

    private void benarkan(View... views) {
        for (View v: views){
            v.setEnabled(true);
        }
    }

    @OnClick({R.id.btnPlay, R.id.btnStop, R.id.btnResume, R.id.btnPause})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                mainkan();
                break;
            case R.id.btnStop:
                hentikan();
                break;
            case R.id.btnResume:
                lanjutkan();
                break;
            case R.id.btnPause:
                tunggu();
                break;
        }
    }

    private void tunggu() {
        if(player.isPlaying() && player != null){
            player.pause();
            benarkan(btnStop, btnResume);
            rusakkan(btnPlay, btnPause);
        }
    }

    private void lanjutkan() {
        player.start();
        rusakkan(btnResume, btnPlay);
        benarkan(btnPause, btnStop);
    }

    private void hentikan() {
//        if(player.isPlaying() && player != null){
            player.stop();
            rusakkan(btnPause, btnResume, btnStop);
            benarkan(btnPlay);
    }

    MediaPlayer player = new MediaPlayer();
    private void mainkan() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.frontend);
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            player.setDataSource(AudioActivity.this, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
        benarkan(btnPause, btnStop);
        rusakkan(btnResume, btnPlay);
    }
}
