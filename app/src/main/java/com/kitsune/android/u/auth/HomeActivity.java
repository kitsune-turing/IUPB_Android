package com.kitsune.android.u.auth;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kitsune.android.u.auth.Model.User;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private TextView fullNameTextView, emailTextView, phoneTextView;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inicializar Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://kitsune-app-developer-default-rtdb.firebaseio.com/");
        userRef = database.getReference("users");

        initializeUIComponents();
        loadUserData();
        findViewById(R.id.layout_google_sign_in).setOnClickListener(view -> finish());
    }

    /**
     * Initialize UI components
     */
    private void initializeUIComponents() {
        fullNameTextView = findViewById(R.id.hola_bienve);
        emailTextView = findViewById(R.id.text_email);
        phoneTextView = findViewById(R.id.text_phone);
    }

    /**
     * Load user data from Firebase Realtime Database
     */
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
                            fullNameTextView.setText("Hi, Welcome to " + user.getFullName());
                            emailTextView.setText("Your email is: " + user.getEmail());
                            phoneTextView.setText("Your phone is: " + user.getPhone());
                        }
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