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

public class VideoStreamingBaruActivity extends AppCompatActivity {

    @BindView(R.id.videoView3)
    VideoView videoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_streaming_baru);
        ButterKnife.bind(this);

        final ProgressDialog dialog = new ProgressDialog(VideoStreamingBaruActivity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Tunggu Bentaran");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        MediaController controller = new MediaController(this);
        videoView3.setMediaController(controller);

        String url = "https://r5---sn-2uuxa3vh-jb36.googlevideo.com/videoplayback?signature=22610EB1BE3EAE8D9735DBE89F496D99DC98FDD9.1A2BA2174D2ABCD3F1C6950176120F674970535A&c=WEB&gir=yes&dur=31.137&id=o-AHlGEtHrApFZAMkveQekEPdOD3pdkDfY0e7ckQ6BMMbI&source=youtube&clen=2868603&ip=159.8.214.205&fvip=5&itag=18&requiressl=yes&ei=33COWp2MEsL51gKmhYfYBQ&mime=video%2Fmp4&key=cms1&sparams=clen,dur,ei,expire,gir,hcs,id,initcwndbps,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pcm2cms,pl,ratebypass,requiressl,shardbypass,source&pl=21&ipbits=0&ratebypass=yes&expire=1519306047&lmt=1519232988761187&title=Honda+PCX+%E2%80%9CExceed+Excellence+%E2%80%9C&redirect_counter=1&rm=sn-5hnee676&req_id=d524332b29bfa3ee&cms_redirect=yes&hcs=yes&ipbypass=yes&mip=36.77.221.5&mm=31&mn=sn-2uuxa3vh-jb36&ms=au&mt=1519284344&mv=m&pcm2cms=yes&shardbypass=yes";
        Uri uri_url = Uri.parse(url);
        videoView3.setVideoURI(uri_url);
        videoView3.requestFocus();

        videoView3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                dialog.dismiss();
                mediaPlayer.start();
            }
        });
    }
}
