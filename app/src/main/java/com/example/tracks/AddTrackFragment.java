package com.example.tracks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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


        btnAddTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get data from screen
                addToFirebase();
            }});
        return view;
    }

    private void addToFirebase() {
        String trackName = editTrackName.getText().toString().trim();
        String raceDistance = editRaceDistance.getText().toString().trim();
        String numberOfLaps = editNumberOfLaps.getText().toString().trim();
        String firstGrandPrix = editFirstGrandPrix.getText().toString().trim();

        if (TextUtils.isEmpty(trackName) || TextUtils.isEmpty(raceDistance)
                || TextUtils.isEmpty(numberOfLaps) || TextUtils.isEmpty(firstGrandPrix)) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        F1Track track = new F1Track(trackName, raceDistance, numberOfLaps, firstGrandPrix);

        db.collection("tracks").add(track).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getActivity(), "Successfully added your hotel!", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Failed to add your hotel: ", e.getMessage());
            }
        });
    }

    private void clearFields() {
        editTrackName.setText("");
        editRaceDistance.setText("");
        editNumberOfLaps.setText("");
        editFirstGrandPrix.setText("");
    }

}