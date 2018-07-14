package com.example.dmindlin.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dmindlin.parsetagram.model.Post;

public class DetailsActivity extends AppCompatActivity {
    Post post;
    ImageView image;
    TextView tvUser;
    TextView tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        image = findViewById(R.id.ivDetailed);
        tvUser = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        Intent intent = getIntent();
        post = (Post) intent.getExtras().get("post");
        Glide.with(getApplicationContext()).load(post.getImage().getUrl()).into(image);
        tvDescription.setText(post.getDescription());
        tvUser.setText(post.getUser().getUsername());
    }
}
