package com.andraganoid.playsomemedia.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.fragments.AudioFragment;
import com.andraganoid.playsomemedia.fragments.PreviewOnClickListener;
import com.andraganoid.playsomemedia.fragments.StreamFragment;
import com.andraganoid.playsomemedia.fragments.VideoFragment;
import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.model.StreamRepository;
import com.andraganoid.playsomemedia.model.Video;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
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


public class Preview extends AppCompatActivity implements PreviewOnClickListener, View.OnClickListener {


    public String USER_AGENT;
    private SimpleExoPlayer player;
    public PlayerView playerView;

    private long playbackPosition;
    private int currentWindow;
    private boolean playWhenReady = true;
    public ConstraintLayout playerViewWrapper;

    BottomNavigationView bottomBar;

    private int screenWidth;

    private final Fragment VIDEO_FRAGMENT = new VideoFragment();
    private final Fragment AUDIO_FRAGMENT = new AudioFragment();
    private final Fragment STREAM_FRAGMENT = new StreamFragment();

    private final String TYPE_VIDEO = "Video";
    private final String TYPE_AUDIO = "Audio";
    private final String TYPE_STREAM = "Stream";
    private final String TYPE_STREAM_URL = "url";

    private String mediaUri;
    private String mediaName;
    private String mediaType;

    ImageButton closeButton;
    ImageButton fullscreenButton;
    TextView previewTitle;
    TextView previewType;

    @Override
    protected void onResume() {
        super.onResume();
        previewTitle.setText("");
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
        bottomBar = findViewById(R.id.preview_bottom_bar);
        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.bottom_video:
                        setFragment(VIDEO_FRAGMENT);
                        bottomBar.getMenu().getItem(0).setChecked(true);

                        break;

                    case R.id.bottom_audio:
                        setFragment(AUDIO_FRAGMENT);
                        bottomBar.getMenu().getItem(1).setChecked(true);
                        break;

                    case R.id.bottom_stream:
                        setFragment(STREAM_FRAGMENT);
                        bottomBar.getMenu().getItem(2).setChecked(true);
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

        previewTitle = findViewById(R.id.preview_title);
        previewType = findViewById(R.id.preview_type);
        closeButton = findViewById(R.id.player_close);
        fullscreenButton = findViewById(R.id.preview_fullscreen);
        closeButton.setOnClickListener(this);
    }


    private void setFragment(Fragment fragment) {

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.preview_fragment_view, fragment)
                    .commit();
        }
    }

    public void initializePlayer(String mUri, String mName, String mType) {
        mediaUri = mUri;
        mediaName = mName.isEmpty() ? mUri : mName;
        mediaType = mType;

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
        int fullScr = View.VISIBLE;
        if (mediaType.equals(TYPE_AUDIO)) {
            t = TYPE_AUDIO;
            fullScr = View.GONE;
        }

        previewTitle.setText(mediaName);
        previewType.setText(t);
        fullscreenButton.setVisibility(fullScr);

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

                switch (playbackState) {

                    case Player.STATE_BUFFERING:
                        fullscreenButton.setOnClickListener(null);
                        break;

                    case Player.STATE_ENDED:
                        fullscreenButton.setOnClickListener(null);
                        break;

                    case Player.STATE_IDLE:
                        break;

                    case Player.STATE_READY:
                        if (mediaType.equals(TYPE_STREAM_URL)) {
                            new StreamRepository(getApplication()).insertStream(new Stream("", mediaUri));
                        }
                        fullscreenButton.setOnClickListener(Preview.this);
                        break;
                }
            }
        });
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

    @Override
    public void streamChoosed(Stream stream) {
        initializePlayer(stream.getUrl(), stream.getName(), TYPE_STREAM);
    }

    public void streamUrlInput(String url) {
        initializePlayer(url, "", TYPE_STREAM_URL);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.player_close:player.stop();
                break;

            case R.id.preview_fullscreen:
                Intent playIntent = new Intent(getApplicationContext(), com.andraganoid.playsomemedia.view.Player.class);
                playIntent.putExtra("mediaUri", mediaUri);
                playIntent.putExtra("mediaName", mediaName);
                startActivity(playIntent);
                break;
        }
    }
}
