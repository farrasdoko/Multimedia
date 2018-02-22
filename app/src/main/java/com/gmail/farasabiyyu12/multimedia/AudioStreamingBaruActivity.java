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

public class AudioStreamingBaruActivity extends AppCompatActivity {

    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.btnPlay4)
    Button btnPlay4;
    @BindView(R.id.btnStop4)
    Button btnStop4;

    MediaPlayer player;
    String url5 = "http://103.226.246.42/masima-pramborsjakarta"; //prambors JKT (verivied)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_streaming_baru);
        ButterKnife.bind(this);

        //TODO setProgressBar
        progressBar2.setIndeterminate(true);
        progressBar2.setVisibility(View.INVISIBLE);
        progressBar2.setMax(100);

        setPlaying();
    }

    private void setPlaying() {
        player = new MediaPlayer();

        try {
            player.setDataSource(url5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                progressBar2.setIndeterminate(true);
                progressBar2.setSecondaryProgress(100);
            }
        });
    }

    @OnClick({R.id.btnPlay4, R.id.btnStop4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay4:
                progressBar2.setVisibility(View.VISIBLE);
                setProgressBarIndeterminate(true);
                player.prepareAsync();
                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                        progressBar2.setIndeterminate(false);
                    }
                });
                btnPlay4.setEnabled(false);
                btnStop4.setEnabled(true);
                break;
            case R.id.btnStop4:
                if(player == null) return;
                if (player.isPlaying()){
                    player.stop();
                    player.release();
                    setPlaying();
                    progressBar2.setVisibility(View.INVISIBLE);
                    progressBar2.setIndeterminate(false);

                    btnStop4.setEnabled(false);
                    btnPlay4.setEnabled(true);
                }
                break;
        }
    }
}
