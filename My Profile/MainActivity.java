package com.example.myprofile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username, password, address, age;
    private RadioGroup gender;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        output = findViewById(R.id.output);

        Button submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String addressValue = address.getText().toString();
                String ageValue = age.getText().toString();

                int selectedGenderId = gender.getCheckedRadioButtonId();
                RadioButton selectedGender = findViewById(selectedGenderId);
                String genderValue = selectedGender != null ? selectedGender.getText().toString() : "Not Specified";

                String outputText = "User Name: " + userNameValue + "\n" +
                        "Password: " + passwordValue + "\n" +
                        "Address: " + addressValue + "\n" +
                        "Gender: " + genderValue + "\n" +
                        "Age: " + ageValue;

                output.setText(outputText);
            }
        });
    }
}
