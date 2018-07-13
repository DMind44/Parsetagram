package com.example.dmindlin.parsetagram;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dmindlin.parsetagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;

public class TimelineFragment extends Fragment {
    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.

    PostAdapter postAdapter;
    RecyclerView rvTimeline;
    ArrayList<Post> fposts;
    Activity activity;
    SwipeRefreshLayout swipeLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.timeline, parent, false);



    }



    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        swipeLayout = view.findViewById(R.id.swipe_container);
        // Setup any handles to view objects here
        rvTimeline = view.findViewById(R.id.rvTimeline);
        fposts = new ArrayList<>();
        postAdapter = new PostAdapter(fposts);
//        rvTimeline.setLayoutManager(new LinearLayoutManager(this));
        rvTimeline.setAdapter(postAdapter);
        rvTimeline.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code here
                refresh();
                Toast.makeText(getContext(), "Works!", Toast.LENGTH_LONG).show();
                // To keep animation for 4 seconds
                swipeLayout.postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 4000); // Delay in millis
            }
        });
        // Scheme colors for animation
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );



    }

    public void refresh() {
        loadTopPosts();
    }

    public void loadTopPosts() {
        final Post.Query postsQuery = new Post.Query();
        postsQuery.getTop().withUser();
        postsQuery.findInBackground();
        postsQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
//                    for (int i = 0; i < objects.size(); i++) {
//                        Log.d("HomeActivity", "Post[" + i + "] = " + objects.get(i).getDescription() + "\nusername = " + objects.get(i).getUser().getUsername());
//                        fposts.add(objects.get(i));
//                        postAdapter.notifyItemInserted(i);
//                    }
                    fposts.addAll(objects);
                    postAdapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        loadTopPosts();
    }
}
