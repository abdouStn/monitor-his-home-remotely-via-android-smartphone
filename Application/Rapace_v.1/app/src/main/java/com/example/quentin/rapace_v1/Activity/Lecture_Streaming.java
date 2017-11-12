package com.example.quentin.rapace_v1.Activity;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.quentin.rapace_v1.R;
import com.example.quentin.rapace_v1.listener.Bouton_Call_Police_Listener;
import com.example.quentin.rapace_v1.listener.Bouton_Lever_Alerte_Listener;
import java.io.IOException;


public class Lecture_Streaming extends Activity implements SurfaceHolder.Callback, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener {
    /** Bouton permettant de lever une alerte */
    private Button stop_an_alert = null;
    /** Bouton permettant d'appeler la police */
    private Button call_police = null;

    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streaming);

        init_IHM();


        // Partie STREAMING :
         // 1 ) Banque d'url :
            //String url = "https://www.youtube.com/watch?v=UqLRqzTp6Rk";
            String url = "http://www.pocketjourney.com/downloads/pj/video/famous.3gp";
            //String url = "http://10.0.2.2/~quentin/rapace.mp4"; // Fonctionne
            //String url = "http://webcam.aui.ma/axis-cgi/mjpg/video.cgi?resolution=CIF&amp";
            //String url = "http://80.118.196.219/webtv-asx.cgi?channel=arte";
            //String url = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp/";
            //String url = "rtsp://10.0.2.2:8554/test";
            //String url = "http://video-streaming.orange.fr/sports-extreme/papadakis-ce-titre-c-est-un-grand-exploit-VID0000001yT2G.html";
            //String url = "http://10.0.2.2/ter2015/cam.swf";
            //String url = "rtsp://10.0.2.2:8554/";
            //String url = "rtsp://46.249.213.87:554/playlists/b4u_qcif.hpl.3gp";
        VideoView video = (VideoView)findViewById(R.id.video);
        surfaceHolder = video.getHolder();
        surfaceHolder.addCallback(this);

        Intent intent = getIntent();


        if (intent != null) {

            // 2) Solution VideoView :

            //video.setVideoURI(Uri.parse("http://www.pocketjourney.com/downloads/pj/video/famous.3gp"));
            video.setVideoURI(Uri.parse(url));
            //video.setVideoURI(Uri.parse("android.resource://" + this.getPackageName() + "/" + R.raw.video ));
            video.start();

            /*video.setVideoURI(Uri.parse("rtsp://162.38.27.158:8554/test.3gp"));
            video.requestFocus();
            video.start();*/
//----------------------------------------------------------------------------------


            //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDisplay(surfaceHolder);
                mediaPlayer.setDataSource(url);
                mediaPlayer.prepare();
                mediaPlayer.setOnBufferingUpdateListener(this);
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setOnCompletionListener(this);
                mediaPlayer.setOnVideoSizeChangedListener(this);
                mediaPlayer.start();
//            textViewOUT.setText("Action : "+timeStamp);
            } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
                Toast.makeText(getApplicationContext(), "bad", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
//---------------------------------------------------------------------------------------------------
          /*  MediaController vidControl = new MediaController(this);
            vidControl.setAnchorView(video);
            video.setMediaController(vidControl);

            video.setVideoPath("rtsp://10.0.2.2:8554/");

            video.start();*/
//------------------------------------------------------------------------------------------------
           /* Uri stream = Uri.parse("rtsp://10.0.2.2:8554/");
            Intent videointent = new Intent(Intent.ACTION_VIEW,stream);
            startActivity(videointent);*/

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {

    }

    /**
     * Accesseur vers le bouton permettant de lever une alerte.
     * @return bouton permettant de lever une alerte
     */
    public Button getStop_an_alert() {
        return stop_an_alert;
    }

    /**
     * Accesseur vers le bouton permettant d'appeler la police.
     * @return bouton permettant d'appeler la police.
     */
    public Button getCall_police() {
        return call_police;
    }

    /**
     * Initialise les paramètre de l'IHM.
     */
    private void init_IHM() {
        stop_an_alert = (Button) findViewById(R.id.stop_an_alert_button);
        stop_an_alert.setOnClickListener(new Bouton_Lever_Alerte_Listener(this));

        call_police = (Button) findViewById(R.id.call_police);
        call_police.setOnClickListener(new Bouton_Call_Police_Listener(this));

        String etat_alerte = getIntent().getStringExtra("site_etat");
        if (etat_alerte.equals("1")) {
            switch_alert_mode();
        }
    }

    /** Bascule l'IHM en mode "alerte".*/
    public void switch_alert_mode() {
        stop_an_alert.setVisibility(View.VISIBLE);
        call_police.setVisibility(View.VISIBLE);
    }

    /** Bascule l'IHM en mode "safe".*/
    public void switch_safe_mode() {
        stop_an_alert.setVisibility(View.INVISIBLE);
        call_police.setVisibility(View.INVISIBLE);
    }
}