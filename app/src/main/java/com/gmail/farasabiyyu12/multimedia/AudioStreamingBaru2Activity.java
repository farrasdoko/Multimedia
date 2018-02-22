package com.gmail.farasabiyyu12.multimedia;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioStreamingBaru2Activity extends AppCompatActivity {

    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    @BindView(R.id.btnPlay5)
    Button btnPlay5;
    @BindView(R.id.btnStop5)
    Button btnStop5;

    MediaPlayer mediaPlayer;
    String url3 = "http://live.indostreamserver.com:9070/batamfm"; //batamFM

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming_baru2);
        ButterKnife.bind(this);

        progressBar3.setIndeterminate(true);
        progressBar3.setVisibility(View.INVISIBLE);
        progressBar3.setMax(100);

        setPlaying();
    }

    private void setPlaying() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url3);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                progressBar3.setIndeterminate(true);
                progressBar3.setSecondaryProgress(100);
            }
        });
    }

    @OnClick({R.id.btnPlay5, R.id.btnStop5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay5:
                progressBar3.setVisibility(View.VISIBLE);
                setProgressBarIndeterminate(true);
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        progressBar3.setVisibility(View.INVISIBLE);
                        progressBar3.setIndeterminate(false);
                    }
                });
                btnPlay5.setEnabled(false);
                btnStop5.setEnabled(true);
                break;
            case R.id.btnStop5:
                if(mediaPlayer == null) return;
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    setPlaying();
                    progressBar3.setVisibility(View.INVISIBLE);
                    progressBar3.setIndeterminate(false);

                    btnStop5.setEnabled(false);
                    btnPlay5.setEnabled(true);
                }
                break;
        }
    }
}
