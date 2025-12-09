package com.example.tracks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
        Context context;
        ArrayList<TrackItem> list;
        private OnItemClickListener listener;

        public MyAdapter(Context context, ArrayList<TrackItem> list) {
            this.context = context;
            this.list = list;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            TrackItem item = list.get(position);
            holder.trackName.setText(item.getTrackName());
            holder.raceDistance.setText(item.getRaceDistance());
            holder.NumberOfLaps.setText(item.getNumberOfLaps());
            holder.FirstGrandPrix.setText(item.getFirstGrandPrix());
            // إذا أردت عرض الصورة لاحقاً
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {

            TextView trackName, raceDistance, NumberOfLaps, FirstGrandPrix;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                trackName = itemView.findViewById(R.id.tvTrackName);
                raceDistance = itemView.findViewById(R.id.tvRaceDistance);
                NumberOfLaps = itemView.findViewById(R.id.tvNumberOfLaps);
                FirstGrandPrix = itemView.findViewById(R.id.tvFirstGrandPrix);
            }
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }
    }

