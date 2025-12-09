package com.example.tracks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.FirebaseFirestore;

public class AddTrackFragment extends Fragment {

    private EditText editTrackName, editRaceDistance, editNumberOfLaps, editFirstGrandPrix;
    private Button btnAddTrack;
    private ImageView trackImageView;
    private Uri trackImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;

    private FirebaseFirestore db;

    public AddTrackFragment() {
        // Required empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_track, container, false);

        // ربط الحقول
        editTrackName = view.findViewById(R.id.editTrackName);
        editRaceDistance = view.findViewById(R.id.editRaceDistance);
        editNumberOfLaps = view.findViewById(R.id.editNumberOfLaps);
        editFirstGrandPrix = view.findViewById(R.id.editFirstGrandPrix);
        btnAddTrack = view.findViewById(R.id.btnAddTrack);
        trackImageView = view.findViewById(R.id.trackImageView);

        db = FirebaseFirestore.getInstance();

        // عند الضغط على الصورة
        trackImageView.setOnClickListener(v -> openFileChooser());

        // عند الضغط على زر إضافة التراك
        btnAddTrack.setOnClickListener(v -> addTrackToFirestore());

        return view;
    }

    // فتح المعرض لاختيار صورة
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    // استقبال الصورة المختارة
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                && data != null && data.getData() != null) {
            trackImageUri = data.getData();
            trackImageView.setImageURI(trackImageUri);
        }
    }

    private void addTrackToFirestore() {
        String trackName = editTrackName.getText().toString().trim();
        String raceDistance = editRaceDistance.getText().toString().trim();
        String numberOfLaps = editNumberOfLaps.getText().toString().trim();
        String firstGrandPrix = editFirstGrandPrix.getText().toString().trim();
        String imageUrl = trackImageUri != null ? trackImageUri.toString() : "";

        if (TextUtils.isEmpty(trackName) || TextUtils.isEmpty(raceDistance)
                || TextUtils.isEmpty(numberOfLaps) || TextUtils.isEmpty(firstGrandPrix)) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        F1Track track = new F1Track(trackName, raceDistance, numberOfLaps, firstGrandPrix, imageUrl);

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
        trackImageView.setImageResource(android.R.color.transparent);
        trackImageUri = null;
    }
}