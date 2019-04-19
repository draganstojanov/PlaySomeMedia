package com.andraganoid.playsomemedia.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Audio;


import java.util.List;


public class AudioAdapter extends RecyclerView.Adapter <AudioAdapter.AudioViewHolder> {

    private List <Audio> aList;
    private PreviewOnClickListener click;

    public AudioAdapter(List <Audio> aList, PreviewOnClickListener click) {
        this.aList = aList;
        this.click = click;
    }


    @NonNull
    @Override
    public AudioAdapter.AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FrameLayout itemView = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.audio_row, parent, false);
        return new AudioViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, final int position) {
        holder.bind(aList.get(position), click);
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }


    public static class AudioViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView duration;


        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.audio_title);
            duration = itemView.findViewById(R.id.audio_duration);
        }


        public void bind(final Audio audio, final PreviewOnClickListener click) {

            title.setText(audio.getFormattedTitle());
            duration.setText(String.valueOf(audio.getFormattedDuration()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.audioChoosed(audio);
                }
            });

        }
    }

}

