package com.example.madprojecttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Civilian_Emergency_Adapter extends RecyclerView.Adapter<Civilian_Emergency_Adapter.EmergancyHolder> {

    private ArrayList<Hotline> contactsList;

    public Civilian_Emergency_Adapter(ArrayList<Hotline> contactsList){
        this.contactsList = contactsList;
    }

    public class EmergancyHolder extends RecyclerView.ViewHolder{
        private TextView contact;
        private TextView no;

        public EmergancyHolder(final View view3){
            super(view3);
            contact = view3.findViewById(R.id.textView131);
            no = view3.findViewById(R.id.textView132);
        }
    }

    @NonNull
    @Override
    public Civilian_Emergency_Adapter.EmergancyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView3 = LayoutInflater.from(parent.getContext()).inflate(R.layout.civilian_contacts_list,parent,false);
        return new EmergancyHolder(itemView3);
    }

    @Override
    public void onBindViewHolder(@NonNull Civilian_Emergency_Adapter.EmergancyHolder holder, int position) {
        String place1 = contactsList.get(position).getName();
        holder.contact.setText(place1);

        Integer phone1 = contactsList.get(position).getNo();
        holder.no.setText(phone1);
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
