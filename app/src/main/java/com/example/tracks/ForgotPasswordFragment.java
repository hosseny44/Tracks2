package com.example.tracks;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordFragment extends Fragment {

    private FirebaseServices fbs;
    private EditText etEmail;
    private Button btnReset;


    public ForgotPasswordFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fbs = FirebaseServices.getInstance();
        etEmail = view.findViewById(R.id.etEmailForfgotpassword);
        btnReset = view.findViewById(R.id.btnResetForgotPassword);

        btnReset.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                if (getActivity() != null)
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_SHORT).show();
                return;
            }

            fbs.getAuth().sendPasswordResetEmail(email)
                    .addOnCompleteListener(task -> {
                        if (getActivity() != null) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Check your Email and reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "Something went wrong! Check if the email you entered is correct!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}