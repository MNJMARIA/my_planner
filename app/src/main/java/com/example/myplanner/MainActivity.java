package com.example.myplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();

        LocalDateTime currentDate = LocalDateTime.now();

        setDate(currentDate.getDayOfMonth(),currentDate.getMonthValue(), currentDate.getYear());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                //TODO too long info message, short time or do list of messages
                Toast.makeText(MainActivity.this, dayOfMonth + "." + month + "." + year, Toast.LENGTH_SHORT).show();
            }
        });



        Button goToSecondScreenButton = findViewById(R.id.goToWeekScreenButton);
        goToSecondScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(MainActivity.this, WeekPageActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}