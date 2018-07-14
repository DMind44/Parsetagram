package com.example.dmindlin.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import static com.example.dmindlin.parsetagram.R.layout;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ParseUser.getCurrentUser() != null) {
            final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(layout.activity_main);
            usernameInput = findViewById(R.id.etusernameInput);
            passwordInput = findViewById(R.id.etPasswordInput);
            loginBtn = findViewById(R.id.btnLogin);
        }
    }
    public void login (View view) {
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e == null) {
                    Log.d("LoginActivity", "login successful!");

                    final Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LoginActivity", "login failure.");
                    e.printStackTrace();
                }
            }
        });

    }
    public void signUp (View view) {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
