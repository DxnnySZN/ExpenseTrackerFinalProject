package com.example.expensetrackerproject.Authentication;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensetrackerproject.FirebaseHelper;
import com.example.expensetrackerproject.InitialUserData.HomePage;
import com.example.expensetrackerproject.InitialUserData.HowOftenPaidActivity;
import com.example.expensetrackerproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;

public class LogInFragment extends Fragment {

    private String email, password;
    // used for communicating with Firebase auth and Firestore db
    public static FirebaseHelper firebaseHelper;
    private LinearLayout logInActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);
        if (firebaseHelper == null)
            firebaseHelper = new FirebaseHelper();
        logInActivity = view.findViewById(R.id.logInActivityId);
        Button logInButton = view.findViewById(R.id.login_button);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailTV = view.findViewById(R.id.login_email);
                String emailStr = emailTV.getText().toString();
                TextView passwordTV = view.findViewById(R.id.login_password);
                String passwordStr = passwordTV.getText().toString();
                String testStr = validateEditTexts(emailStr, passwordStr);
                if(testStr.equals("Yippee")) {
                    email = emailStr.trim();
                    password = passwordStr.trim();
                    logIn();
                }
                // Code to be executed when the button is clicked

            }


        });

        return view;
    }
    private String validateEditTexts(String email, String password) {
        email = email.trim();
        password = password.trim();
        if(email.equals("")) {
            Snackbar.make(logInActivity, "Please enter an email", Snackbar.LENGTH_SHORT).show();
            return "No email";
        }
        if(password.equals("")) {
            Snackbar.make(logInActivity, "Please enter a password", Snackbar.LENGTH_SHORT).show();
            return "No password";
        }
        return "Yippee";

    }
    private void snackbar(String msg) {
        Snackbar.make(logInActivity, msg,
                        Snackbar.LENGTH_SHORT)
                .show();
    }

    private void logIn() {
        firebaseHelper.getmAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in successful, update UI with the currently signed in user's info
                            firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());
                            firebaseHelper.addListener();
                            Intent intent = new Intent(requireActivity(), HomePage.class);
                            startActivity(intent);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e.getMessage().contains("INVALID_LOGIN_CREDENTIALS")) {
                            snackbar("Invalid email or password, try again");
                        } else if (e.getMessage().contains("The email address is badly formatted")) {
                            snackbar("Poorly formatted email, try again");
                        } else {
                            snackbar("Unknown error, try again");
                        }
                    }
                });
    }


}