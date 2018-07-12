package com.example.dmindlin.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dmindlin.parsetagram.model.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private String imagePath = "/sdcard/DCIM/Camera/IMG_20180710_135934.jpg";

    private EditText etDescription;
    private Button btnPost;
    private Button btnRefresh;
    public List<Post> posts;
//    public PostAdapter postAdapter;

    TimelineFragment timelineFragment = new TimelineFragment();
    Fragment cameraFragment; //TODO camera fragment
    Fragment profileFragment; //TODO profile fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        etDescription = findViewById(R.id.etDescription);
        btnPost = findViewById(R.id.btnPost);
        btnRefresh = findViewById(R.id.btnRefresh);

//        postAdapter = new PostAdapter(posts);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.flTimeline, timelineFragment).commit();


    }
    public void logout(MenuItem mi) {
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null

        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void onPost (View view) {
        final String description = etDescription.getText().toString();
        final ParseUser user = ParseUser.getCurrentUser();
        final File file = new File(imagePath);
        final ParseFile image = new ParseFile(file);

        image.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("HomeActivity", "image upload success!");
                    createPost(description, image, user);
                } else {
                    e.printStackTrace();
                }
            }
        });

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
}
