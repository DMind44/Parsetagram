package com.example.dmindlin.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    EditText etEmail;
    EditText etUser;
    EditText etPass1;
    EditText etPass2;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnCreate = findViewById(R.id.btnCreate);
        etUser = findViewById(R.id.etUser);
        etPass1 = findViewById(R.id.etPass1);
        etPass2 = findViewById(R.id.etPass2);
        etEmail = findViewById(R.id.etEmail);
    }
    public void createAccount(View view) {
        String username = etUser.getText().toString();
        String pass1 = etPass1.getText().toString();
        String pass2 = etPass2.getText().toString();
        String email = etEmail.getText().toString();
        if ((username.length() > 0) && (pass1.length() > 0) && (pass1.equals(pass2))) {
            // Create the ParseUser
            ParseUser user = new ParseUser();
// Set core properties
            user.setUsername(username);
            user.setPassword(pass1);
            user.setEmail(email);
// Invoke signUpInBackground
            user.signUpInBackground(new SignUpCallback() {
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(SignupActivity.this, "You have signed up!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        e.printStackTrace();
                        Toast.makeText(SignupActivity.this, "Failed: invalid login",Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(SignupActivity.this, "Make sure you enter a username, email, and password! Also, your passwords have to match!", Toast.LENGTH_LONG).show();
        }
    }
}
