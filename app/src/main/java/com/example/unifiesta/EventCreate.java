package com.example.unifiesta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EventCreate extends AppCompatActivity {
    final Calendar calendar = Calendar.getInstance();
    private String selectedDateAndTime; // Variable to store selected date and time
    private String selectedCategory; // Variable to store selected category

    private EditText EventName, Numtix, price, venue, datetime;
    private Button Addevent;
    private ImageButton back;
    Spinner category;
    private DBHandler dbHandler;

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        EventName = findViewById(R.id.name);
        Numtix = findViewById(R.id.numtix);
        price = findViewById(R.id.price);
        venue = findViewById(R.id.venue);
        category = findViewById(R.id.spinner);
        Addevent = findViewById(R.id.idBtnAddCourse);
        datetime = findViewById(R.id.datetime);
        back = findViewById(R.id.back);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);

        Addevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = EventName.getText().toString();
                String Eventvenue = venue.getText().toString();
                String priceoftix = price.getText().toString();
                String datetimeofevent = datetime.getText().toString();
                String Nooftix = Numtix.getText().toString();
                selectedCategory = category.getSelectedItem().toString();

                if (Name.isEmpty() && Eventvenue.isEmpty() && selectedCategory.isEmpty()) {
                    Toast.makeText(EventCreate.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewEvent(Name, Eventvenue, selectedCategory, priceoftix, datetimeofevent, Nooftix );

                Toast.makeText(EventCreate.this, "Ticket has been added.", Toast.LENGTH_SHORT).show();
                Numtix.setText("");
                EventName.setText("");
                datetime.setText("");
                price.setText("");
            }

        });

        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker();
            }
        });

        dbHandler = new DBHandler(EventCreate.this);
        back.setOnClickListener(v -> {
            Intent i= new Intent(EventCreate.this, Profile.class);
            startActivity(i);
        });
    }

    private void showDateTimePicker() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(EventCreate.this, time, hour, minute, false).show();
            }
        };
        new DatePickerDialog(EventCreate.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy HH:mm";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.ENGLISH);
        selectedDateAndTime = dateFormat.format(calendar.getTime());
        datetime.setText(selectedDateAndTime);
    }

}
