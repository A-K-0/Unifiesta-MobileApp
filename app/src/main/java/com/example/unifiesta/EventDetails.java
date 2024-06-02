package com.example.unifiesta;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDetails extends AppCompatActivity {
    TextView tv1, tv2, tv3, tv4;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        button1 = findViewById(R.id.formlink);
        tv2 = findViewById(R.id.idTVCourseDescription);
        tv3 = findViewById(R.id.idTVCourseName);
        tv4 = findViewById(R.id.idTVCourseTracks);

        String courseDuration = getIntent().getStringExtra("courseDuration");

        tv2.setText(getIntent().getStringExtra("courseDescription"));
        tv3.setText(getIntent().getStringExtra("courseName"));
        tv4.setText(getIntent().getStringExtra("courseTracks"));

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = courseDuration; // Use the courseDuration value as the URL
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}