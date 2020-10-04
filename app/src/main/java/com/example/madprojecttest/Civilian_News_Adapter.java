package com.example.madprojecttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Civilian_News_Adapter extends RecyclerView.Adapter<Civilian_News_Adapter.CivilianViewHolder> {

    private ArrayList<Alert> newsList1;

    public Civilian_News_Adapter(ArrayList<Alert> newsList1) {
        this.newsList1 = newsList1;
    }

    public class CivilianViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView datetime;

        public CivilianViewHolder(final View view2) {
            super(view2);
            title = view2.findViewById(R.id.title_civilian);
            description = view2.findViewById(R.id.description_civilian);
            datetime = view2.findViewById(R.id.civi_alertdate);
        }
    }

        @NonNull
        @Override
        public Civilian_News_Adapter.CivilianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View itemView2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.civilian_news_list, parent, false);
            return new CivilianViewHolder(itemView2);
        }

        @Override
        public void onBindViewHolder(@NonNull Civilian_News_Adapter.CivilianViewHolder holder, int position) {

            String head1 = newsList1.get(position).getTitle();
            holder.title.setText(head1);

            String body1 = newsList1.get(position).getDescription();
            holder.description.setText(body1);

            String day1 = newsList1.get(position).getDate();
            holder.datetime.setText(day1);
        }

        @Override
        public int getItemCount() {
            return newsList1.size();
        }

    }
