package com.andraganoid.playsomemedia.fragments;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andraganoid.playsomemedia.R;
import com.andraganoid.playsomemedia.model.Stream;


import java.util.List;


public class StreamAdapter extends RecyclerView.Adapter <StreamAdapter.StreamViewHolder> {

    private List <Stream> sList;
    private PreviewOnClickListener click;

    public StreamAdapter(List <Stream> sList, PreviewOnClickListener click) {
        this.sList = sList;
        this.click = click;
    }


    @NonNull
    @Override
    public StreamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FrameLayout itemView = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.stream_row, parent, false);
        return new StreamViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull StreamViewHolder holder, final int position) {
        holder.bind(sList.get(position), click);
    }

    @Override
    public int getItemCount() {
        return sList.size();
    }


    public static class StreamViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        StreamViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.stream_title);
        }

        public void bind(final Stream stream, final PreviewOnClickListener click) {

            String name = stream.getName();
            int size = 24;
            if (name.isEmpty()) {
                name = stream.getUrl();
                size = 16;
            }
            title.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            title.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.streamChoosed(stream);
                }
            });
        }
    }

}

