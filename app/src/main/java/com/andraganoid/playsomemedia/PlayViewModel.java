package com.andraganoid.playsomemedia;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.AudioRepository;
import com.andraganoid.playsomemedia.model.GetSomeMedia;
import com.andraganoid.playsomemedia.model.GetSomeMediaCallback;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.model.Video;
import com.andraganoid.playsomemedia.model.VideoRepository;

import java.util.List;

public class PlayViewModel extends AndroidViewModel implements GetSomeMediaCallback {

    private VideoRepository vRepo;
   private AudioRepository aRepo;
  //  private StreamRepository sRepo;
    private LiveData <List <Video>> playVideo;
    private LiveData <List <Audio>> playAudio;
    private LiveData <List <Stream>> playStream;


    public PlayViewModel(@NonNull Application application) {
        super(application);

        vRepo = new VideoRepository(application);
         aRepo = new AudioRepository(application);
        //  sRepo = new StreamRepository(application);

        playVideo=vRepo.getAllVideos();
        playAudio=aRepo.getAllAudios();
        //  playStream=sRepo.getAllStreams();
        new GetSomeMedia(application,this);

    }

    LiveData <List <Video>> getAllVideos() {
        return playVideo;
    }

    LiveData <List <Audio>> getAllAudios() {
        return playAudio;
    }

    LiveData <List <Stream>> getAllStreams() {
        return playStream;
    }


    @Override
    public void videoListCollected(List <Video> getVideo) {
        vRepo.insertVideoList(getVideo);

    }

    @Override
    public void audioListCollected(List <Audio> getAudio) {
        aRepo.insertAudioList(getAudio);
    }




}
