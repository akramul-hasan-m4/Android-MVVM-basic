package com.example.mvvmhelloworldexample.recyclerview;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvmhelloworldexample.R;
import com.example.mvvmhelloworldexample.databinding.RecyclerItemBinding;
import com.example.mvvmhelloworldexample.model.Post;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.MyViewHolder>{

    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerItemBinding binding;  //this item comes from recyler_xml item layout file with Binding prefix

        MyViewHolder(final RecyclerItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }

    RecyAdapter(List<Post> postList, PostsAdapterListener listener) {
        this.postList = postList;
        this.listener = listener;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RecyclerItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.binding.setPost(postList.get(position));
        holder.binding.thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onPostClicked(postList.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface PostsAdapterListener {
        void onPostClicked(Post post);
    }
}
