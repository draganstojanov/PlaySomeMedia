package com.andraganoid.playsomemedia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.AudioRepository;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.model.StreamRepository;
import com.andraganoid.playsomemedia.model.Video;
import com.andraganoid.playsomemedia.model.VideoRepository;

import java.util.List;

public class PlayViewModel extends AndroidViewModel {

    private VideoRepository vRepo;
    private AudioRepository aRepo;
      private StreamRepository sRepo;
    private LiveData <List <Video>> playVideo;
    private LiveData <List <Audio>> playAudio;
    private LiveData <List <Stream>> playStream;

    Application app;

    public PlayViewModel(@NonNull Application application) {
        super(application);
        this.app = application;

        vRepo = new VideoRepository(application);
        aRepo = new AudioRepository(application);
       sRepo = new StreamRepository(application);

        playVideo = vRepo.getAllVideos();
        playAudio = aRepo.getAllAudios();
          playStream=sRepo.getAllStreams();

    }


    public LiveData <List <Video>> getAllVideos() {
        return playVideo;
    }

    public LiveData <List <Audio>> getAllAudios() {
        return playAudio;
    }

    public LiveData <List <Stream>> getAllStreams() {
        return playStream;
    }

}
