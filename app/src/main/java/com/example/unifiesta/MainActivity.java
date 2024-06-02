package com.example.unifiesta;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 2000; // Delay time in milliseconds (adjust as needed)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Use a Handler to post a delayed action
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    // User is already authenticated, navigate to homepage
                    startActivity(new Intent(MainActivity.this, homepage.class));
                    finish(); // Optional: Finish this activity to prevent going back to it on back press
                } else {
                    // User is not authenticated, navigate to login/signup page
                    startActivity(new Intent(MainActivity.this, LoginPage.class));
                    finish(); // Optional: Finish this activity to prevent going back to it on back press
                }
                // Finish this activity to prevent the user from coming back to it using the back button
                finish();
            }
        }, DELAY_TIME);

    }
}
