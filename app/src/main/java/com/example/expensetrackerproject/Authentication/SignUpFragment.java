package com.example.expensetrackerproject.Authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.expensetrackerproject.FirebaseHelper;
import com.example.expensetrackerproject.InitialUserData.HowOftenPaidActivity;
import com.example.expensetrackerproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;

public class SignUpFragment extends Fragment implements OnCompleteListener<AuthResult>{
    private String email, password;
    // used for communicating with Firebase auth and Firestore db
    public static FirebaseHelper firebaseHelper;
    private LinearLayout signUpActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        if (firebaseHelper == null)
            firebaseHelper = new FirebaseHelper();
        signUpActivity = view.findViewById(R.id.signUpLayout);
        // Find the button by its ID
        Button signUpButton = view.findViewById(R.id.signup_button);
        // Set an OnClickListener for the button
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView emailTV = view.findViewById(R.id.signup_email);
                String emailStr = emailTV.getText().toString();
                TextView passwordTV = view.findViewById(R.id.signup_password);
                String passwordStr = passwordTV.getText().toString();
                TextView confirmPasswordTV = view.findViewById(R.id.signup_confirm);
                String confirmStr = confirmPasswordTV.getText().toString();
                String testStr = validateEditTexts(emailStr, passwordStr, confirmStr);
                if(testStr.equals("Yippee")) {
                    email = emailStr.trim();
                    password = passwordStr.trim();
                    signUp();
                }
                // Code to be executed when the button is clicked

            }
        });

        return view;
    }


    private String validateEditTexts(String email, String password, String confirm) {
        email = email.trim();
        password = password.trim();
        confirm = confirm.trim();
        if(email.equals("")) {
            Snackbar.make(signUpActivity, "Please enter an email", Snackbar.LENGTH_SHORT).show();
            return "No email";
        }
        if(password.equals("")) {
            Snackbar.make(signUpActivity, "Please create a password", Snackbar.LENGTH_SHORT).show();
            return "No password";
        }
        if(!password.equals(confirm)) {
            Snackbar.make(signUpActivity, "Passwords do not match", Snackbar.LENGTH_SHORT).show();
            return "Confirm password invalid";
        }
        if(password.length() < 6) {
            Snackbar.make(signUpActivity, "Password must be longer than 6 characters",
                    Snackbar.LENGTH_LONG).show();
            return "Password too short";
        }
        return "Yippee";

    }
    private void snackbar(String msg) {
        Snackbar.make(signUpActivity, msg,
                        Snackbar.LENGTH_SHORT)
                .show();
    }

    private void signUp() {
        // Initialize firebaseHelper and set email/password

        firebaseHelper.getmAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), this)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Denna", "AddOnFailureListener for signUp " + e.getMessage());
                        if (e.getMessage().contains("The email address is already in use")) {
                            snackbar("This email address is already in use");
                        } else if (e.getMessage().contains("The email address is badly formatted")) {
                            snackbar("This email address is badly formatted");
                        } else {
                            snackbar("Account could not be created");
                        }
                    }
                });
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            // Sign up successful, update UI with the currently signed in user's info
            firebaseHelper.updateUid(firebaseHelper.getmAuth().getUid());
            firebaseHelper.addUserToFirestore(email, firebaseHelper.getmAuth().getUid());
            Log.d("Ilyas", email + " created and logged in");
            Intent myIntent = new Intent(requireActivity(), HowOftenPaidActivity.class);
            startActivity(myIntent);
        } else {
            // Handle unsuccessful sign-up
            Log.d("Denna", "AddOnCompleteListener for signUp failed: " + task.getException().getMessage());
            // ... handle failure ...
        }
    }


}