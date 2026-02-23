package com.example.sendgmail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sendgmail.R;

public class MainActivity extends AppCompatActivity {

    private Button sendEmailButton;
    private EditText emailInput, subjectInput, messageInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements ko initialize kar rahe hain
        sendEmailButton = findViewById(R.id.sendEmailButton);
        emailInput = findViewById(R.id.emailInput);
        subjectInput = findViewById(R.id.subjectInput);
        messageInput = findViewById(R.id.messageInput);

        // Button click event listener set karna
        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        // User se email ka input lena
        String recipient = emailInput.getText().toString();
        String subject = subjectInput.getText().toString();
        String message = messageInput.getText().toString();

        // Email intent create karna
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));  // Sirf email apps ko open karega
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipient});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // Check karna ki koi email app available hai ya nahi
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
        }
    }
}