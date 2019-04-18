package com.andraganoid.playsomemedia.model;

import java.util.List;

public interface GetSomeMediaCallback {

    void videoListCollected(List<Video> getVideo);

    void audioListCollected(List<Audio> getAudio);
}
