package com.andraganoid.playsomemedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.Video;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 111;
    PlayViewModel playViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
        } else {
            init();

        }
    }

    private void init() {
        playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);

        playViewModel.getAllVideos().observe(this, new Observer <List <Video>>() {
            @Override
            public void onChanged(List <Video> videos) {


                System.out.println("VIDEO CHANGED");
                // getMedia(videos);


            }
        });

        playViewModel.getAllAudios().observe(this, new Observer <List <Audio>>() {
            @Override
            public void onChanged(List <Audio> audios) {


                System.out.println("AUDIO CHANGED");
                // getMedia(videos);

                for (Audio a:audios){
                    System.out.println(a.getData()+a.getDisplayName());
                }

            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == REQUEST_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, "IS GRANTED", Toast.LENGTH_SHORT).show();
                // getMedia();
            } else {
                Toast.makeText(this, "IS DENIED", Toast.LENGTH_SHORT).show();

            }
        return;

    }
}
