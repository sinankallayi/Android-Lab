package com.example.formvalidaion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    // Buttons
    Button bCancel, bProceed;

    // EditText fields
    EditText etFirstName, etLastName, etPassword;

    // RadioGroup and RadioButtons for Gender
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;

    // CheckBox and Switch
    CheckBox cbTerms;
    Switch swNewMember;

    // Boolean variable to check whether all the text fields are filled
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        bProceed = findViewById(R.id.proceedButton);
        bCancel = findViewById(R.id.cancelButton);

        // Initialize EditText fields
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etPassword = findViewById(R.id.password);

        // Initialize RadioGroup and RadioButtons
        rgGender = findViewById(R.id.genderGroup);
        rbMale = findViewById(R.id.maleRadio);
        rbFemale = findViewById(R.id.femaleRadio);

        // Initialize CheckBox and Switch
        cbTerms = findViewById(R.id.termsCheckBox); // Ensure the ID matches XML
        swNewMember = findViewById(R.id.newMemberSwitch); // Ensure the ID matches XML

        // Handle the PROCEED button
        bProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Store the returned value of the dedicated function which checks
                // whether the entered data is valid or if any fields are left blank.
                isAllFieldsChecked = CheckAllFields();

                // Only proceed if all fields are correctly filled
                if (isAllFieldsChecked) {
                    Intent i = new Intent(MainActivity.this, Form2.class);
                    startActivity(i);
                }
            }
        });

        // Handle the CANCEL button
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // Function to check if all the fields are filled properly
    private boolean CheckAllFields() {
        if (etFirstName.length() == 0) {
            etFirstName.setError("This field is required");
            return false;
        }

        if (etLastName.length() == 0) {
            etLastName.setError("This field is required");
            return false;
        }

        if (etPassword.length() == 0) {
            etPassword.setError("Password is required");
            return false;
        } else if (etPassword.length() < 8) {
            etPassword.setError("Password must be minimum 8 characters");
            return false;
        }

        // Check if a gender is selected
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1) {
            rbMale.setError("Please select a gender");
            rbFemale.setError("Please select a gender");
            return false;
        }

        // Check if terms and conditions are agreed to
        if (!cbTerms.isChecked()) {
            cbTerms.setError("You must agree to the terms and conditions");
            return false;
        }

        // Additional validation for the Switch (if needed)
        if (swNewMember.isChecked()) {
            // Do something if the switch is checked (if necessary)
        }

        // If all validations pass
        return true;
    }
}
