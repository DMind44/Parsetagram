package com.example.dmindlin.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmindlin.parsetagram.model.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;


public class ComposeActivity extends AppCompatActivity {
    private EditText etDescription;
    private Button btnPost;
    private File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.compose);
        etDescription = findViewById(R.id.etDescription);
        btnPost = findViewById(R.id.btnPost);
        Intent intent = getIntent();
        image = (File) intent.getExtras().get("image");
        super.onCreate(savedInstanceState);
    }
    private void createPost(String description, ParseFile imagefile, ParseUser user) {
        final Post newPost = new Post();
        newPost.setDescription(description);
        newPost.setImage(imagefile);
        newPost.setUser(user);
        newPost.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "Create Post Success!");
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onPost (View view) {
        final String description = etDescription.getText().toString();
        final ParseUser user = ParseUser.getCurrentUser();
        final ParseFile parseImage = new ParseFile(image);

        parseImage.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "image upload success!");
                    createPost(description, parseImage, user);
                    Intent intent = new Intent(ComposeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    e.printStackTrace();
                }
            }
        });

    }
}
