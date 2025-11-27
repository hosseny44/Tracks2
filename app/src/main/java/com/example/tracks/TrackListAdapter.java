package com.example.tracks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrackListAdapter  extends RecyclerView.Adapter<TrackListAdapter.MyViewHolder> {

    Context context;
    ArrayList<TrackItem> trackList;
    private OnItemClickListener itemClickListener;
    private FirebaseServices fbs;

    public TrackListAdapter(Context context, ArrayList<TrackItem> trackList) {
        this.context = context;
        this.trackList = trackList;
        this.fbs = FirebaseServices.getInstance();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TrackItem track = trackList.get(position);


        // ======== النصوص ===========
        holder.TrackName.setText(track.getTrackName());
        holder.RaceDistance.setText(track.getRaceDistance());
        holder.NumberOfLaps.setText(track.getNumberOfLaps());
        holder.FirstGrandPrix.setText(track.getFirstGrandPrix());







        // ======== النقر على العنصر ============
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null)
                itemClickListener.onItemClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }

    // ========= المفضلة ========






    // ========= ViewHolder ===========
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TrackName,RaceDistance, NumberOfLaps, FirstGrandPrix;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            TrackName = itemView.findViewById(R.id.tvTrackName);
            RaceDistance = itemView.findViewById(R.id.tvRaceDistance);
            NumberOfLaps = itemView.findViewById(R.id.tvNumberOfLaps);
            FirstGrandPrix = itemView.findViewById(R.id.tvFirstGrandPrix);

        }
    }

    // ========= Interface for onClick ========
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }
}



