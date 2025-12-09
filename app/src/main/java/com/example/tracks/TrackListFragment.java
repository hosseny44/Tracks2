package com.example.tracks;

import static java.lang.Character.toLowerCase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class TrackListFragment extends Fragment {


    private RecyclerView recyclerView;
    private SearchView searchView;
    private TrackListAdapter adapter;
    private FirebaseServices fbs;
    private ImageView ivProfile;
    private ArrayList<TrackItem> trackList = new ArrayList<>();
    private ArrayList<TrackItem> filteredList = new ArrayList<>();

    public TrackListFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_track_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        loadSmellsFromFirebase();
    }

    private void init() {
        recyclerView = getView().findViewById(R.id.rvTracklist);
        searchView = getView().findViewById(R.id.srchViewTrack);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        fbs = FirebaseServices.getInstance();
        adapter = new TrackListAdapter(getActivity(), trackList);


        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(position -> {
            Toast.makeText(getActivity(),
                    "Clicked: " + trackList.get(position).getTrackName(),
                    Toast.LENGTH_SHORT).show();
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilter(newText);
                return true;
            }
        });
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void loadSmellsFromFirebase() {
        fbs.getFirestore().collection("F1Tracks" )

                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        trackList.clear();
                        for (QueryDocumentSnapshot doc : task.getResult()) {
                            TrackItem sm = doc.toObject(TrackItem.class);
                            trackList.add(sm);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void applyFilter(String query) {
        if (query.trim().isEmpty()) {
            adapter = new TrackListAdapter(getActivity(), trackList);
            recyclerView.setAdapter(adapter);
            return;
        }

        filteredList.clear();

        for (TrackItem sm : trackList) {
            if (sm.getTrackName().toLowerCase().contains(query.toLowerCase()) ||
                    sm.getRaceDistance().toLowerCase().contains(query.toLowerCase()) ||
                    sm.getNumberOfLaps().toLowerCase().contains(query.toLowerCase()) ||
                    sm.getFirstGrandPrix().toLowerCase().contains(query.toLowerCase()))
            {
                filteredList.add(sm);
            }
        }

        adapter = new TrackListAdapter(getActivity(), filteredList);
        recyclerView.setAdapter(adapter);
    }
}