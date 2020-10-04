package com.example.madprojecttest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter2 extends PagerAdapter {
    private ArrayList<Criminal> criminals;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter2(ArrayList<Criminal> criminals, Context context) {
        this.criminals=criminals;
        this.context = context;
    }

    @Override
    public int getCount() {
        return criminals.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.criminal, container,false);


        TextView title,desc;

        ImageView imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        desc = view.findViewById(R.id.desc);



        //  imageView.setImageURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/ursafe-74546.appspot.com/o/images%2F09089fc5-1ad8-4b6d-915c-f90a56f4f40f?alt=media&token=a1ae6c7b-b0f4-46b2-bee1-8a3005c1e9e2"));
        Picasso.get().load(criminals.get(position).getPic()).into(imageView);
        title.setText(criminals.get(position).getCrime());
        desc.setText(criminals.get(position).getArea());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(),DeleteCriminal.class);
                context.startActivity(intent);
            }
        });

        container.addView(view,0);

        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
