package com.kitsune.android.u.auth;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kitsune.android.u.auth.Model.User;
import androidx.annotation.NonNull;


public class HomeActivity extends Activity {

    private static final String TAG = "HomeActivity";
    private TextView fullNameTextView, emailTextView, phoneTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kitsune-app-developer-default-rtdb.firebaseio.com/");
        userRef = database.getReference("users");

        initializeUIComponents();
        loadUserData();
    }

    private void initializeUIComponents() {
        fullNameTextView = findViewById(R.id.text_full_name);
        emailTextView = findViewById(R.id.text_email);
        phoneTextView = findViewById(R.id.text_phone);
    }

    private void loadUserData() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        if (user != null) {
                            fullNameTextView.setText(user.getFullName());
                            emailTextView.setText(user.getEmail());
                            phoneTextView.setText(user.getPhone());
                        }
                    } else {
                        Log.e(TAG, "User data not found.");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e(TAG, "Error loading user data: " + databaseError.getMessage());
                }
            });
        }
    }
}