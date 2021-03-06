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

public class AudioStreamingActivity extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.btnPlay3)
    Button btnPlay3;
    @BindView(R.id.btnStop3)
    Button btnStop3;

    MediaPlayer player;
    String url = "http://103.16.198.36:9160/;stream/1"; // (verivied)
    String url3 = "http://live.indostreamserver.com:9070/batamfm"; //batamFM
    String url5 = "http://103.226.246.42/masima-pramborsjakarta"; //prambors JKT (verivied)
    String url2 = "http://uk6.internet-radio.com:8465/1"; //SHOUTcast  (verivied)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming);
        ButterKnife.bind(this);

        //TODO setProgressBar
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.INVISIBLE);
        progressBar.setMax(100);

        //TODO Create Method Set Up Media Player
        setPlaying();
    }

    private void setPlaying() {
        player = new MediaPlayer();
        try {
            player.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                progressBar.setIndeterminate(true);
                progressBar.setSecondaryProgress(100);
            }
        });
    }

    @OnClick({R.id.btnPlay3, R.id.btnStop3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay3:
                progressBar.setVisibility(View.VISIBLE);
                setProgressBarIndeterminate(true);
                player.prepareAsync();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        progressBar.setIndeterminate(false);
                    }
                });
                btnPlay3.setEnabled(false);
                btnStop3.setEnabled(true);
                break;
            case R.id.btnStop3:
//                if(player == null) return;
//                if (player.isPlaying()){
                    player.stop();
                    player.release();
                    setPlaying();
                    progressBar.setVisibility(View.INVISIBLE);
                    progressBar.setIndeterminate(false);

                    btnStop3.setEnabled(false);
                    btnPlay3.setEnabled(true);
//                }
//                break;
        }
    }
}
