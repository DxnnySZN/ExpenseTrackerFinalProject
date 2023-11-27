package com.example.expensetrackerproject;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.expensetrackerproject.Expenses.Expense;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to hold ALL the code to communicate with Firebase.  This class
 * will connect with Firebase auth and Firebase firestore.  Each class that needs to verify
 * authentication OR access data from the database will reference a variable of this class and
 * call a method of this class to handle the task.
 *
 * Essentially this class is like a "gopher" that will go and do whatever the other classes
 * want or need it to do.  This allows us to keep all our other classes clean of the firebase
 * code and also avoid having to update firebase code in many places.
 *
 * This is MUCH more efficient and less error prone.
 *
 * auth docs: https://firebase.google.com/docs/auth/android/password-auth#java_1
 */

public class FirebaseHelper {
    // do I want to maintain uid now that I have mUser?
    private static String uid = null;      // var will be updated for currently signed in user
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirebaseFirestore db;
    private ArrayList<Expense> myExpenses;

    public FirebaseHelper() {
        mAuth = FirebaseAuth.getInstance(); // connects to auth
        mUser = mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        myExpenses = new ArrayList<>();
        if (mUser != null) {
            uid = mUser.getUid();
            Log.d("Denna", "user is still logged in when entering app");
        }
        else {
            uid = null;
            Log.d("Denna", "No one is logged in when entering app");
        }
    }

    // allows other files to access the mAuth variable for logged in user
    public ArrayList<Expense> getExpenseArrayList() {
        return myExpenses;
    }


    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public void updateUid(String uid) {
        this.uid = uid;
    }

    // if user logs out this will call the signOut method for auth AND reset the local var for uid
    public void logOutUser() {
        mAuth.signOut();
        this.uid = null;
    }
    public String getUid() {
        return uid;
    }

    public void addListener() {
        Log.d("Denna", "Inside the listener");
        // rest of the code implemented later
    }
    public void addUserToFirestore(String email, String newUID) {
        // Create a new user with their name
        // using a HashMap because we are NOT uploading an object of a class
        // This is an alternative to adding an object, you 'put' key value pairs
        // in the object and then add the object to firestore

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);
        // Add a new document with a docID = to the authenticated user's UID
        db.collection("users").document(newUID)
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Denna", email + "'s user account added");
                        addListener();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Denna", "Error adding user account", e);
                    }
                });
    }
    public void addExpenseData(Expense m) {
        db.collection("users").document(uid).collection("myExpenseList")
                .add(m)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Memory that was just added.
                        db.collection("users").document(uid).collection("myExpenseList").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                        Log.i("Denna", "just added " + m.getName());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Denna", "Error adding document", e);
                    }
                });
    }

    public void addIncomeData(Income f) {
        db.collection("users").document(uid).collection("IncomeData")
                .add(f)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // This will set the docID key for the Income object that was just added.
                        db.collection("users").document(uid).collection("IncomeData").
                                document(documentReference.getId()).update("docID", documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("Denna", "Error adding document", e);
                    }
                });
    }



}


