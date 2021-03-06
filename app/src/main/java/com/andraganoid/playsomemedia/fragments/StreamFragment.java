package com.andraganoid.playsomemedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.PlayViewModel;
import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Stream;
import com.andraganoid.playsomemedia.view.Preview;

import java.util.ArrayList;
import java.util.List;


public class StreamFragment extends Fragment implements View.OnClickListener {

    View sView;
    RecyclerView sRecView;
    RecyclerView.LayoutManager sLayMngr;
    StreamAdapter sAdapter;
    Preview preview;
    List <Stream> sList = new ArrayList <>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sView = inflater.inflate(R.layout.stream_fragment, container, false);


//        try {
//            sList = preview.playViewModel.getAllStreams().getValue();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }


        preview = (Preview) getActivity();
        sRecView = sView.findViewById(R.id.stream_rec_view);
        sLayMngr = new LinearLayoutManager(getContext());
        sRecView.setLayoutManager(sLayMngr);

        PlayViewModel playViewModel = ViewModelProviders.of(this).get(PlayViewModel.class);
        playViewModel.getAllStreams().observe(this, new Observer <List <Stream>>() {
            @Override
            public void onChanged(List <Stream> streams) {
                sList = streams;
                sAdapter = new StreamAdapter(sList, preview);
                sRecView.setAdapter(sAdapter);
            }
        });

        sView.findViewById(R.id.stream_input_btn).setOnClickListener(this);
        return sView;
    }

    @Override
    public void onClick(View v) {
        String url = ((EditText) sView.findViewById(R.id.stream_input)).getText().toString();
        if (!url.isEmpty()) {
            if (URLUtil.isValidUrl(url)) {
    preview.streamUrlInput(url);
            }else{
                Toast.makeText(preview, "Not valid!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}