package com.example.unifiesta;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    TextView Name;
    TextView button3;
    String st;

    private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        Name = findViewById(R.id.username);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String username = currentUser.getDisplayName();
            if (username != null) {
                Name.setText(username);
            } else {
                // Username not available
            }
        } else {
            // User not logged in
        }

        mAuth = FirebaseAuth.getInstance();
        profileImageView = findViewById(R.id.profileImage);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            // User is signed in
            Uri photoUrl = user.getPhotoUrl();
            if (photoUrl != null) {
                Picasso.get().load(photoUrl).into(profileImageView);
            }
        } else {

        }

        mAuth = FirebaseAuth.getInstance();
        TextView button = findViewById(R.id.InviteFriends);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, InviteFriends.class);
                startActivity(intent);
            }
        });
        TextView button2 = findViewById(R.id.tvSellTickets);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this,EventCreate.class);
                startActivity(intent);
            }
        });



        TextView logoutTextView = findViewById(R.id.Logout);
        FirebaseAuth finalMAuth = mAuth;
        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call signOut method to log out the user
                finalMAuth.signOut();

                startActivity(new Intent(Profile.this, LoginPage.class));
                finish(); // optional: close the current activity
            }
        });

        ImageButton button4 = findViewById(R.id.back);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, homepage.class);
                startActivity(intent);
            }
        });

        TextView button5 = findViewById(R.id.About);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, About.class);
                startActivity(intent);
            }
        });


    }
}