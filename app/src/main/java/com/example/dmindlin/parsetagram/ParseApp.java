package com.example.dmindlin.parsetagram;

import android.app.Application;

import com.example.dmindlin.parsetagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate () {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("Parsetagram")
                .clientKey("m@st3rkey#123")
                .server("https://dmind44-instagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
