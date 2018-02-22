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

public class VideoStreamingBaru2Activity extends AppCompatActivity {

    @BindView(R.id.videoView4)
    VideoView videoView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_streaming_baru2);
        ButterKnife.bind(this);

        final ProgressDialog dialog = new ProgressDialog(VideoStreamingBaru2Activity.this);
        dialog.setTitle("Loading");
        dialog.setMessage("Tunggu Bentaran");
        dialog.setIndeterminate(false);
        dialog.setCancelable(false);
        dialog.show();

        MediaController controller = new MediaController(this);
        videoView4.setMediaController(controller);

        String url = "https://r4---sn-vgqsrn76.googlevideo.com/videoplayback?clen=771215&sparams=clen,dur,ei,expire,gir,id,ip,ipbits,ipbypass,itag,lmt,mime,mip,mm,mn,ms,mv,pcm2cms,pl,ratebypass,requiressl,source&c=WEB&ipbits=0&lmt=1439791546351485&dur=16.509&source=youtube&requiressl=yes&key=cms1&signature=279570202B69CA4F6606DEFA31F09EA3E29D15F6.07F58034ED406A760F3964765741F150D601DFC5&gir=yes&mime=video%2Fmp4&itag=18&expire=1519306303&fvip=4&ratebypass=yes&ei=33GOWrTgH5aj1wK4rKn4CQ&pl=21&ip=37.58.82.171&id=o-AAuht3VMtXQUQ4s9J0mfiwbUCqhaHd27KVaHR94AmPX_&title=%5BMMD%5D+Hah%2Cgay%21+-FNAF-&rm=sn-5hneld7z&req_id=c99791748293a3ee&ipbypass=yes&mip=36.77.221.5&cm2rm=sn-2uuxa3vh-jb367d,sn-npolz7z&redirect_counter=3&cms_redirect=yes&mm=34&mn=sn-vgqsrn76&ms=ltu&mt=1519284634&mv=m";
        Uri uri_url = Uri.parse(url);
        videoView4.setVideoURI(uri_url);
        videoView4.requestFocus();

        videoView4.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                dialog.dismiss();
                mediaPlayer.start();
            }
        });
    }
}
