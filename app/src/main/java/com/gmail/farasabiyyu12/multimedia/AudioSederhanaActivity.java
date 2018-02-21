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

public class AudioSederhanaActivity extends AppCompatActivity {

    @BindView(R.id.btnPlay2)
    Button btnPlay2;
    @BindView(R.id.btnStop2)
    Button btnStop2;

    String PLAY = "Play", PAUSE = "Pause", RESUME = "Resume", STOP = "Stop";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_sederhana);
        ButterKnife.bind(this);
    }

    MediaPlayer player;

    @OnClick({R.id.btnPlay2, R.id.btnStop2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay2:
                if(btnPlay2.getText().toString().equals(PLAY)){
                    //TODO Play Mp3
                    Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.frontend);
                    player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    btnPlay2.setText(PAUSE);
                    try {
                        player.setDataSource(AudioSederhanaActivity.this, uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();
                }else if(btnPlay2.getText().toString().equals(PAUSE)){
                    btnPlay2.setText(RESUME);
                    player.pause();
                }else if(btnPlay2.getText().toString().equals(RESUME)){
                    btnPlay2.setText(PAUSE);
                    player.start();
                }
                break;
            case R.id.btnStop2:
                btnPlay2.setText(PLAY);
                    player.stop();
                break;
        }
    }
}
