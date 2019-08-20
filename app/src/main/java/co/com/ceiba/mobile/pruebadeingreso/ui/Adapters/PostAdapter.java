package co.com.ceiba.mobile.pruebadeingreso.ui.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.repository.persistence.entities.PostEntity;
import co.com.ceiba.mobile.pruebadeingreso.ui.viewholder.PostViewHolder;


public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private List<PostEntity> mPost = new ArrayList<>();

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_list_item, parent,false);
        PostViewHolder vh = new PostViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, final int position) {
        PostEntity post = mPost.get(position);
        holder.setTextViewTitle(post.getTitle());
        holder.setTextViewBody(post.getBody());
    }

    @Override
    public int getItemCount() {
        return mPost.size();
    }

    public void setPost(List<PostEntity> posts) {
        this.mPost = posts;
        notifyDataSetChanged();
    }
}