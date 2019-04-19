package com.andraganoid.playsomemedia.fragments;

import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.model.Video;

public interface PreviewOnClickListener {

    void videoChoosed(Video video);

    void audioChoosed(Audio audio);

    void streamChoosed(Stream stream);
}
