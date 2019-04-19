package com.andraganoid.playsomemedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.PlayViewModel;
import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Video;
import com.andraganoid.playsomemedia.view.Preview;

import java.util.ArrayList;
import java.util.List;



public class VideoFragment extends Fragment {

    View vView;
    RecyclerView vRecView;
    RecyclerView.LayoutManager vLayMngr;
    public VideoAdapter vAdapter;
    Preview preview;
    List <Video> vList = new ArrayList <>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vView = inflater.inflate(R.layout.video_fragment, container, false);


        try {
            vList = preview.playViewModel.getAllVideos().getValue();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


        preview = (Preview) getActivity();
        vRecView = vView.findViewById(R.id.video_rec_view);
        vLayMngr = new GridLayoutManager(getContext(),3);
        vRecView.setLayoutManager(vLayMngr);
      //  vAdapter = new VideoAdapter(vList, preview);
      //  vRecView.setAdapter(vAdapter);

        PlayViewModel playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
        playViewModel.getAllVideos().observe(this, new Observer <List <Video>>() {
            @Override
            public void onChanged(List <Video> videos) {
                vList = videos;
               // vAdapter.notifyDataSetChanged();
                vAdapter = new VideoAdapter(vList, preview);
                vRecView.setAdapter(vAdapter);

            }
        });
        return vView;
    }

//    public void vUpdate(List <Video> videos) {
//        Toast.makeText(preview, "FRAGMENT VIDEO UPDATED", Toast.LENGTH_SHORT).show();
//        vList = videos;
//        vAdapter.notifyDataSetChanged();
//    }
}