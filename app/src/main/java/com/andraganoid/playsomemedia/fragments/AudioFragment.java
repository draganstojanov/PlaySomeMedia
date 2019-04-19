package com.andraganoid.playsomemedia.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.PlayViewModel;
import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Audio;
import com.andraganoid.playsomemedia.view.Preview;

import java.util.ArrayList;
import java.util.List;



public class AudioFragment extends Fragment {

    View aView;
    RecyclerView aRecView;
    RecyclerView.LayoutManager aLayMngr;
    public AudioAdapter aAdapter;
    Preview preview;
    List <Audio> aList = new ArrayList <>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        aView = inflater.inflate(R.layout.audio_fragment, container, false);

//        try {
//            aList = preview.playViewModel.getAllAudios().getValue();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
        preview = (Preview) getActivity();
        aRecView = aView.findViewById(R.id.audio_rec_view);
        aLayMngr = new LinearLayoutManager(getContext());
        aRecView.setLayoutManager(aLayMngr);

        PlayViewModel playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
        playViewModel.getAllAudios().observe(this, new Observer <List <Audio>>() {
            @Override
            public void onChanged(List <Audio> audios) {
                aList = audios;
                aAdapter = new AudioAdapter(aList, preview);
                aRecView.setAdapter(aAdapter);

            }
        });
        return aView;
    }

  }
