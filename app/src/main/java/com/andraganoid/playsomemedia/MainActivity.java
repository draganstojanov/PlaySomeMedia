package com.andraganoid.playsomemedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.AudioRepository;
import com.andraganoid.playsomemedia.model.GetSomeMediaCallback;
import com.andraganoid.playsomemedia.model.Video;
import com.andraganoid.playsomemedia.model.VideoRepository;
import com.andraganoid.playsomemedia.view.Preview;

import java.util.ArrayList;
import java.util.List;

//todo
// UI
// remove deleted files from db ???
// button for fullscreen
// button stop playing preview

public class MainActivity extends AppCompatActivity implements GetSomeMediaCallback {

    private final int REQUEST_CODE = 111;
    private int initTask;
    private final int INIT_FINISHED = 2;//todo stavi 3 kad zavrsis stream init


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTask = 0;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            populateDataBase();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateDataBase();
            } else {
                Toast.makeText(this, "Permission denied.", Toast.LENGTH_LONG).show();
            }
    }


    public void play(View v) {
        Intent intent = new Intent(this, Preview.class);
        startActivity(intent);
    }

    private void populateDataBase() {
        getSomeVideo();
        getSomeAudio();
    }

    private void getSomeVideo() {

        List <Video> getVideo = new ArrayList <>();
        Cursor vCursor = getApplicationContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        try {
            vCursor.moveToFirst();
            do {
                getVideo.add(new Video(
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)),
                        vCursor.getLong(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_TAKEN)),
                        vCursor.getLong(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)),
                        vCursor.getInt(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.WIDTH)),
                        vCursor.getInt(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.HEIGHT)),
                        vCursor.getString(vCursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION))
                ));

            } while (vCursor.moveToNext());

            vCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new VideoRepository(getApplication()).insertVideoList(getVideo, this);
    }


    private void getSomeAudio() {
        List <Audio> getAudio = new ArrayList <>();
        Cursor aCursor = getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        try {
            aCursor.moveToFirst();
            do {
                getAudio.add(new Audio(
                        aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)),
                        aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)),
                        aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.TITLE)),
                        aCursor.getString(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)),
                        aCursor.getLong(aCursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))

                ));

            } while (aCursor.moveToNext());

            aCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AudioRepository(getApplication()).insertAudioList(getAudio, this);
    }

    @Override
    public void taskFinished() {
        initTask++;
        if (initTask == INIT_FINISHED) {
            findViewById(R.id.progres_bar).setVisibility(View.GONE);
            findViewById(R.id.go_btn).setVisibility(View.VISIBLE);
        }
    }
}
