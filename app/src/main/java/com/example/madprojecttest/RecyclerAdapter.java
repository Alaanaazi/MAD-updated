package com.example.madprojecttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Alert> alertList;
    private RecyclerViewClickListener listener;

    public RecyclerAdapter(ArrayList<Alert> alertList, RecyclerViewClickListener listener){
        this.alertList = alertList;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView title;
        private TextView description;
        private TextView datetime;

        public MyViewHolder(final View view){
            super(view);
            title = view.findViewById(R.id.textView7);
            description = view.findViewById(R.id.textView8);
            datetime = view.findViewById(R.id.news_date);
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

        String head = alertList.get(position).getTitle();
        holder.title.setText(head);

        String body = alertList.get(position).getDescription();
        holder.description.setText(body);

        String day = alertList.get(position).getDate();
        holder.datetime.setText(day);
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }

}
