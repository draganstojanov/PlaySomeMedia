package com.andraganoid.playsomemedia.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.widget.TextView;

import com.andraganoid.playsomemedia.PlayViewModel;
import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.fragments.AudioFragment;
import com.andraganoid.playsomemedia.fragments.PreviewOnClickListener;
import com.andraganoid.playsomemedia.fragments.VideoFragment;
import com.andraganoid.playsomemedia.model.Audio;
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


public class Preview extends AppCompatActivity implements PreviewOnClickListener {


    public String USER_AGENT;
    private SimpleExoPlayer player;
    public PlayerView playerView;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    public ConstraintLayout playerViewWrapper;

    public PlayViewModel playViewModel;
    BottomNavigationView bottomBar;

    private int screenWidth;

    private final Fragment VIDEO_FRAGMENT = new VideoFragment();
    private final Fragment AUDIO_FRAGMENT = new AudioFragment();
    // private final Fragment STREAM_FRAGMENT = new StreamFragment();

    private final String TYPE_VIDEO = "Video";
    private final String TYPE_AUDIO = "Audio";
    private final String TYPE_STREAM = "Stream";

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preview);

        playerViewWrapper = findViewById(R.id.preview_video_view_wrapper);
        // vFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("VIDEO_FRAGMENT");

        bottomBar = findViewById(R.id.preview_bottom_bar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                // findViewById(menuItem.getItemId()).setBackgroundColor(getResources().getColor(R.color.colorPrimary));

                switch (menuItem.getItemId()) {

                    case R.id.bottom_video:
                        setFragment(VIDEO_FRAGMENT);
                        break;

                    case R.id.bottom_audio:
                        setFragment(AUDIO_FRAGMENT);
                        break;

                    case R.id.bottom_stream:
                        // setFragment(userFragment);
                        break;
                }
                return false;
            }
        });


        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        screenWidth = dm.widthPixels;
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(screenWidth, screenWidth * 9 / 16);
        playerViewWrapper.setLayoutParams(params);
        setFragment(VIDEO_FRAGMENT);
        playerView = findViewById(R.id.preview_video_view);
        USER_AGENT = getResources().getString(R.string.app_name);

    }


    private void setFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.preview_fragment_view, fragment)
                    .commit();
        }
    }

    public void initializePlayer(String mediaUri, String chName, String type) {

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

        String t = "";
        if (type.equals(TYPE_AUDIO)) {
            t = TYPE_AUDIO;
        }

        ((TextView) findViewById(R.id.preview_title)).setText(chName);
        ((TextView) findViewById(R.id.preview_type)).setText(t);

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


    @Override
    public void videoChoosed(Video video) {
        initializePlayer(video.getData(), video.getTitle(), TYPE_VIDEO);
    }

    @Override
    public void audioChoosed(Audio audio) {
        initializePlayer(audio.getData(), audio.getFormattedTitle(), TYPE_AUDIO);
    }
}
