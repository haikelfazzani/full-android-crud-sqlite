package com.example.haikel.tpsqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haikel.tpsqlite.model.Post;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, desc;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.desc = itemView.findViewById(R.id.desc);
        }
    }


    private Context context;
    private List<Post> mDataset;

    public MyAdapter(Context context, List<Post> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_recy_items, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Post p = this.mDataset.get(position);
        holder.title.setText("title : "+p.getTitle());
        holder.desc.setText("Description : "+p.getBody());
    }

    @Override
    public int getItemCount() {return mDataset.size();}
}


