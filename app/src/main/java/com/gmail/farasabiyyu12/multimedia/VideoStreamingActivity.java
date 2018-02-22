package com.gmail.farasabiyyu12.multimedia;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoStreamingActivity extends AppCompatActivity {

    @BindView(R.id.videoView2)
    VideoView videoView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_streaming);
        ButterKnife.bind(this);

        final ProgressDialog dialog = new ProgressDialog(VideoStreamingActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Tunggu Bentaran");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        MediaController controller = new MediaController(this);
        videoView2.setMediaController(controller);

        String url = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
        Uri uri_url = Uri.parse(url);
        videoView2.setVideoURI(uri_url);
        videoView2.requestFocus();

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                dialog.dismiss();
                mediaPlayer.start();
            }
        });
    }
}
