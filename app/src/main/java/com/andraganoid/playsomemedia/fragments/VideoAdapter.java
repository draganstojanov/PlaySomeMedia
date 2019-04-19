package com.andraganoid.playsomemedia.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Video;
import com.bumptech.glide.Glide;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter <VideoAdapter.VideoViewHolder> {

    private List <Video> vList;
    private PreviewOnClickListener click;

    public VideoAdapter(List <Video> vList, PreviewOnClickListener click) {
        this.vList = vList;
        this.click = click;
    }


    @NonNull
    @Override
    public VideoAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FrameLayout itemView = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.video_row, parent, false);
        return new VideoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, final int position) {
        holder.bind(vList.get(position), click);
    }

    @Override
    public int getItemCount() {
        return vList.size();
    }


    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView thumb;
        TextView title;
        TextView resolution;
        TextView duration;


        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.video_thumb);
            title = itemView.findViewById(R.id.video_title);
            resolution = itemView.findViewById(R.id.video_resolution);
            duration = itemView.findViewById(R.id.video_duration);
        }


        public void bind(final Video video, final PreviewOnClickListener click) {

            Glide.with(itemView.getContext())
                    .load(video.getData())
                    .into(thumb);


            title.setText(video.getTitle());
            resolution.setText(video.getResolution());
            duration.setText(String.valueOf(video.getFormattedDuration()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.videoChoosed(video);
                }
            });

        }
    }

}

