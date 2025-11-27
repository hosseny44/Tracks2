package com.example.tracks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;


public class AddTrackFragment extends Fragment {

    private EditText editTrackName, editRaceDistance, editNumberOfLaps, editFirstGrandPrix;
    private Button btnAddTrack;
    private FirebaseFirestore db;

    public AddTrackFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_track, container, false);

        editTrackName = view.findViewById(R.id.editTrackName);
        editRaceDistance = view.findViewById(R.id.editRaceDistance);
        editNumberOfLaps = view.findViewById(R.id.editNumberOfLaps);
        editFirstGrandPrix = view.findViewById(R.id.editFirstGrandPrix);
        btnAddTrack = view.findViewById(R.id.btnAddTrack);

        db = FirebaseFirestore.getInstance();

        btnAddTrack.setOnClickListener(v -> addTrackToFirestore());

        return view;
    }

    private void addTrackToFirestore() {
        String trackName = editTrackName.getText().toString().trim();
        String raceDistance = editRaceDistance.getText().toString().trim();
        String numberOfLaps = editNumberOfLaps.getText().toString().trim();
        String firstGrandPrix = editFirstGrandPrix.getText().toString().trim();

        if (TextUtils.isEmpty(trackName) || TextUtils.isEmpty(raceDistance)
                || TextUtils.isEmpty(numberOfLaps) || TextUtils.isEmpty(firstGrandPrix)) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        // Hadi is helping Wajd play fortnite
        F1Track track = new F1Track(trackName, raceDistance, numberOfLaps, firstGrandPrix);

        db.collection("F1Tracks")
                .add(track)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(getContext(), "Track added successfully!", Toast.LENGTH_SHORT).show();
                    clearFields();
                })
                .addOnFailureListener(e ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void clearFields() {
        editTrackName.setText("");
        editRaceDistance.setText("");
        editNumberOfLaps.setText("");
        editFirstGrandPrix.setText("");
    }

}