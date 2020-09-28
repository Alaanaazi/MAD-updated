package com.example.madprojecttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyAdapter.MyContactHolder>{

    private ArrayList<Hotline> emergencyList;
    private RecyclerViewClickListener listener_contacts;

    public EmergencyAdapter(ArrayList<Hotline> emergencyList, RecyclerViewClickListener listener_contacts){
        this.emergencyList = emergencyList;
        this.listener_contacts = listener_contacts;
    }

    public class MyContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView contact;
        private TextView no;

        public MyContactHolder(final View view1) {
            super(view1);
            contact = view1.findViewById(R.id.textView11);
            no = view1.findViewById(R.id.textView12);
            view1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view1) {
            listener_contacts.onClick(view1, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View contactview = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
        return new MyContactHolder(contactview);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyAdapter.MyContactHolder holder, int position) {
        String place = emergencyList.get(position).getName();
        holder.contact.setText(place);

        Integer phone = emergencyList.get(position).getNo();
        holder.no.setText(String.valueOf(phone));
    }

    @Override
    public int getItemCount() {
        return emergencyList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }
}
