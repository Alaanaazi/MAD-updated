package com.example.madprojecttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<News> newsList;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(ArrayList<News> newsList, RecyclerViewClickListener listener){
        this.newsList = newsList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView description;

        public MyViewHolder(final View view){
            super(view);
            title = view.findViewById(R.id.textView7);
            description = view.findViewById(R.id.textView8);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        String head = newsList.get(position).getTitle();
        holder.title.setText(head);

        String body = newsList.get(position).getDescription();
        holder.description.setText(body);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
