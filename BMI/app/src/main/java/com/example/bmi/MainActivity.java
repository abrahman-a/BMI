package com.example.bmi;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText fullNameEditText;
    private EditText ageEditText;
    private RadioGroup genderRadioGroup;
    private EditText weightEditText;
    private EditText heightEditText;
    private Button calculateButton;
    private Button saveButton;
    private Button clearButton;
    private TextView bmiTextView;
    private TextView categoryTextView;

    private Long id;
    String bmiCategory;

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        fullNameEditText = findViewById(R.id.fullNameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        weightEditText = findViewById(R.id.weightEditText);
        heightEditText = findViewById(R.id.heightEditText);
        calculateButton = findViewById(R.id.calculateButton);
        saveButton = findViewById(R.id.saveButton);
        clearButton = findViewById(R.id.clearButton);
        bmiTextView = findViewById(R.id.bmiTextView);
        categoryTextView = findViewById(R.id.categoryTextView);



        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();
            }
        });
    }

    private void calculateBMI() {
        String weightStr = weightEditText.getText().toString();
        String heightStr = heightEditText.getText().toString();

        if (weightStr.isEmpty() || heightStr.isEmpty()) {
            Toast.makeText(this, "Please enter weight and height.", Toast.LENGTH_SHORT).show();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double height = Double.parseDouble(heightStr);

        double bmi = weight / (height * height);
        bmiCategory = getBMICategory(bmi);


        bmiTextView.setText(String.format("Your BMI: %.2f", bmi));
        categoryTextView.setText("Category: " + bmiCategory);
    }

    private void save() {
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = fullNameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
                String gender = selectedRadioButton.getText().toString();
                String weightStr = weightEditText.getText().toString();
                String heightStr = heightEditText.getText().toString();
                double weight = Double.parseDouble(weightStr);
                double height = Double.parseDouble(heightStr);

                sendDataToBackend(id, fullName, age,weight, gender, height, bmiCategory);
//                saveDataToDatabase();
            }
        });
    }


    private void clearData() {
        fullNameEditText.setText("");
        ageEditText.setText("");
        weightEditText.setText("");
        heightEditText.setText("");
        bmiTextView.setText("");
        categoryTextView.setText("");
        genderRadioGroup.clearCheck();
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal weight";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private void sendDataToBackend(Long id,String fullName, int age, double weight, String gender, double height, String bmiCategory) {
        // Create a RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Create the URL for your backend API
        String url = "http://192.168.207.158:8096/api/bmi-records/adduser";

        // Create the request parameters as a JSONObject
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", id);
            jsonObject.put("fullName", fullName);
            jsonObject.put("age", age);
            jsonObject.put("gender", gender);
            jsonObject.put("weight", weight);
            jsonObject.put("height", height);
            jsonObject.put("bmiCategory", bmiCategory);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create the request
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        Toast.makeText(MainActivity.this, "Data sent to backend", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error response from the server
                        Toast.makeText(MainActivity.this, "Error sending data to backend", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(request);
    }

//    private void saveDataToDatabase() {
//        String fullName = fullNameEditText.getText().toString();
//        String ageStr = ageEditText.getText().toString();
//        int age = 0;
//
//        if (fullName.isEmpty() || ageStr.isEmpty()) {
//            Toast.makeText(this, "Please enter full name and age.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            age = Integer.parseInt(ageStr);
//        } catch (NumberFormatException e) {
//            Toast.makeText(this, "Invalid age value.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
//        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
//        String gender = selectedRadioButton.getText().toString();
//
//        String weightStr = weightEditText.getText().toString();
//        String heightStr = heightEditText.getText().toString();
//
//        if (weightStr.isEmpty() || heightStr.isEmpty()) {
//            Toast.makeText(this, "Please calculate BMI first.", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        double weight = Double.parseDouble(weightStr);
//        double height = Double.parseDouble(heightStr);
//
//        long rowId = databaseHelper.insertRecord(fullName, age, gender, weight, height, bmiCategory );
//
//        if (rowId != -1) {
//            Toast.makeText(this, "Data saved to database.", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Failed to save data to database.", Toast.LENGTH_SHORT).show();
//        }
//    }


}
