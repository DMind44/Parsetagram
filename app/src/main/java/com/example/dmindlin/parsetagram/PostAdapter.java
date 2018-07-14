package com.example.dmindlin.parsetagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dmindlin.parsetagram.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    List<Post> mPosts;
    PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    Context context;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View postView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Post post = mPosts.get(mPosts.size()-1-position);
        viewHolder.tvDescription.setText(post.getDescription());
        viewHolder.tvUsername.setText(post.getUser().getUsername());
        Glide.with(context).load(post.getImage().getUrl()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView image;
        public TextView tvUsername;
        public TextView tvDescription;
        public ViewHolder (View itemView) {
            super(itemView);


            // perform findViewById lookups

            image = (ImageView) itemView.findViewById(R.id.imageView);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int i = this.getAdapterPosition();
            Post post = mPosts.get(i);
            Intent intent = new Intent(view.getContext(), DetailsActivity.class);
            intent.putExtra("post",post);
            view.getContext().startActivity(intent);

        }
    }
}
