package com.example.registrationactivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationText = findViewById(R.id.confirmationText);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "No username");
        String email = sharedPreferences.getString("email", "No email");
        String password = sharedPreferences.getString("password", "No password");

        String confirmationMessage = "Registration Successful!\n\n" +
                "Username: " + username + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password;

        confirmationText.setText(confirmationMessage);
    }
}
