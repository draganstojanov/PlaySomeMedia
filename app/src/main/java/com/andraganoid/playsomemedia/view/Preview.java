package com.andraganoid.playsomemedia.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.andraganoid.playsomemedia.PlayViewModel;
import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.model.Video;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;

public class Preview extends AppCompatActivity {


    public String USER_AGENT;
    private SimpleExoPlayer player;
    public PlayerView playerView;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    public ConstraintLayout wrapper;

    PlayViewModel playViewModel;
    BottomNavigationView bottomBar;

    private int screenWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);

        bottomBar = findViewById(R.id.preview_bottom_bar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                // findViewById(menuItem.getItemId()).setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                switch (menuItem.getItemId()) {

                    case R.id.bottom_video:
                        // setFragment(listsFragment);
                        break;
                    case R.id.bottom_audio:
                        //  setFragment(msgFragment);
                        break;
                    case R.id.bottom_stream:
                        // setFragment(userFragment);
                        break;
                }
                return false;
            }
        });


        playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
     //   playViewModel.initData();
        playViewModel.getAllVideos().observe(this, new Observer <List <Video>>() {
            @Override
            public void onChanged(List <Video> videos) {
             //   Toast.makeText(Preview.this, "VIDEO", Toast.LENGTH_SHORT).show();
                System.out.println("PREVIEW VIDEO"+System.currentTimeMillis());
                initializePlayer(videos.get(0).getData(), "AAAA");
                Log.d("GET ALL VIDEOS: ", String.valueOf(videos.size()));



            }
        });

        playViewModel.getAllAudios().observe(this, new Observer <List <Audio>>() {
            @Override
            public void onChanged(List <Audio> audios) {
              //  Toast.makeText(Preview.this, "AUDIO", Toast.LENGTH_SHORT).show();


            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        screenWidth = dm.widthPixels;

        playerView = findViewById(R.id.preview_video_view);
        USER_AGENT = getResources().getString(R.string.app_name);
        System.out.println("SHOW: "+System.currentTimeMillis());
      //  System.out.println("VIDEOS1: "+playViewModel.getAllVideos().getValue().size());
      //  initializePlayer(playViewModel.getAllVideos().getValue().get(0).getData(), "AAAA");
        new CountDownTimer(10000, 5000) {
          @Override
           public void onTick(long l) {
            Toast.makeText(Preview.this, String.valueOf(l), Toast.LENGTH_SHORT).show();
         }

            boolean notf=true;
        @Override
         public void onFinish() {
                for(Video v:playViewModel.getAllVideos().getValue()){
                    System.out.println(v.getResolution()+"-"+v.getWidth()+"x"+v.getHeight());

                  //  if(notf&&(v.getWidth()==v.getHeight())&&v.getWidth()==640){notf=false;initializePlayer(v.getData(), "AAAA");}

                }
           // System.out.println("VIDEOS: "+playViewModel.getAllVideos().getValue().size());
            //    initializePlayer(playViewModel.getAllVideos().getValue().get(0).getData(), "AAAA");
          }
       }.start();



        //  intent.putExtra("mediaUri", playViewModel.getAllAudios() .getValue().get(0).getData());
      //  initializePlayer(playViewModel.getAllVideos().getValue().get(0).getData(), "AAAA");
    }


    @Override
    protected void onResume() {
        super.onResume();
        //  wrapper = findViewById(R.id.preview_video_view_wrapper);
       // setPlayerViewSize(0, 0);

    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
        // wrapper.setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }

    void setPlayerViewSize(int w, int h) {

        int sh = screenWidth * 9 / 16;

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(screenWidth, sh);
        playerView.setLayoutParams(params);
    }


    public void initializePlayer(String mediaUri, String chName) {


        if (player == null) {

            if (Util.SDK_INT > 23) {
                releasePlayer();
            }

            player = ExoPlayerFactory.newSimpleInstance(this, new DefaultRenderersFactory(this), new DefaultTrackSelector(), new DefaultLoadControl());
            playerView.setPlayer(player);
            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);
        }


        MediaSource mediaSource = buildMediaSource(Uri.parse(mediaUri));
        player.prepare(mediaSource, true, false);


        //  ((TextView) findViewById(R.id.preview_title)).setText(chName);


//        (findViewById(R.id.preview_fullscreen_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent playIntent = new Intent(getApplicationContext(), Player.class);
//                playIntent.putExtra("mediaUri", mediaUri);
//                playIntent.putExtra("channelName", chName);
//                startActivity(playIntent);
//
//            }
//        });
//
//        (findViewById(R.id.preview_close_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                wrapper.setVisibility(View.GONE);
//                releasePlayer();
//
//            }
//        });

    }


    public void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }


    private MediaSource buildMediaSource(Uri uri) {
        int type = Util.inferContentType(uri);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(new DefaultDataSourceFactory(getApplicationContext(), USER_AGENT)).createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(new DefaultDataSourceFactory(getApplicationContext(), USER_AGENT)).createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(new DefaultDataSourceFactory(getApplicationContext(), USER_AGENT)).createMediaSource(uri);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(getApplicationContext(), USER_AGENT)).createMediaSource(uri);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


}
