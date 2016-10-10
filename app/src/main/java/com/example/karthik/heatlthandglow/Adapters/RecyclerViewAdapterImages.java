package com.example.karthik.heatlthandglow.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.karthik.heatlthandglow.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by karthik on 08/10/16.
 */

public class RecyclerViewAdapterImages extends RecyclerView.Adapter<RecyclerViewAdapterImages.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    ImageView main;
    JSONArray image_array;

    public RecyclerViewAdapterImages(Context context,ImageView main,JSONArray image_array) {
        this.context = context;
        this.main = main;
        this.image_array = image_array;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView name;
        ImageView image;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            //name = (TextView)  mView.findViewById(R.id.name);
            image = (ImageView) mView.findViewById(R.id.img);
            //main.setImageDrawable();


        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.images_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try {
            Glide.with(context)
                    .load(image_array.getString(position))
                    //.placeholder(R.drawable.image_placeholder)
                    .dontAnimate()
                    .into(holder.image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (position == 0){
            try {
                Glide.with(context)
                        .load(image_array.getString(position))
                        //.placeholder(R.drawable.image_placeholder)
                        .dontAnimate()
                        .into(main);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Glide.with(context)
                            .load(image_array.getString(position))
                            //.placeholder(R.drawable.image_placeholder)
                            .dontAnimate()
                            .into(main);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return image_array.length();
    }


}
