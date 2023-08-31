package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ViewRecord extends AppCompatActivity {

    TextView name, age , gender, weight, height, status;
    Button back;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        name = (TextView) findViewById(R.id.VName);
        name = (TextView) findViewById(R.id.VName);
        name = (TextView) findViewById(R.id.VName);


    }
}