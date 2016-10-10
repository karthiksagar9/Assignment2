package com.example.karthik.heatlthandglow.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.karthik.heatlthandglow.Helpers.CommonUtils;
import com.example.karthik.heatlthandglow.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.resource;

/**
 * Created by karthik on 08/10/16.
 */

public class RecyclerViewAdapterFlavours extends RecyclerView.Adapter<RecyclerViewAdapterFlavours.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> image_list,volumes_array;
    RecyclerView volumes;
    JSONArray new_array;
    RecyclerViewAdapter vol;
    int pos;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public RecyclerViewAdapterFlavours(Context context,ArrayList<HashMap<String, String>> image_list,RecyclerView volumes) {
        this.context = context;
        this.volumes = volumes;
        this.image_list = image_list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.shades_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        resultp = image_list.get(position);
       // pos = position;
        try {
            new_array = new JSONArray(resultp.get(CommonUtils.skuVolumesJsonArray));
            System.out.println("new Atrray us " + new_array);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.name.setText(resultp.get(CommonUtils.skuname));
       System.out.println("color cpde " + resultp.get(CommonUtils.skuVolumesJsonArray));



        if(position == 0){
            volumes_array = new ArrayList<HashMap<String, String>>();

            try {
                for(int i=0; i<new_array.length();i++){
                    HashMap<String, String> map = new HashMap<String, String>();
                    JSONObject temp = new_array.getJSONObject(i);
                    System.out.println("flavours json is " + temp);

                    map.put(CommonUtils.skuVolumes,temp.getString(CommonUtils.skuVolumes));
                    map.put(CommonUtils.skuIsSelected,temp.getString(CommonUtils.skuIsSelected));
                    map.put(CommonUtils.skuIsAvaliable,temp.getString(CommonUtils.skuIsAvaliable));
                    map.put(CommonUtils.skuId,temp.getString(CommonUtils.skuId));
                    volumes_array.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            vol = new RecyclerViewAdapter(context,volumes_array);
            volumes.setAdapter(vol);
            //notifyDataSetChanged();
            //volumes.notify();
        }
holder.linearLayout.setTag(position);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultp = image_list.get(position);
                try {
                    new_array = new JSONArray(resultp.get(CommonUtils.skuVolumesJsonArray));
                    System.out.println("new Atrray us " + new_array);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    volumes_array = new ArrayList<HashMap<String, String>>();

                    for(int i=0; i<new_array.length();i++){
                        HashMap<String, String> map = new HashMap<String, String>();
                        //volumes_array = new ArrayList<HashMap<String, String>>();
                        JSONObject temp = new_array.getJSONObject(i);
                        System.out.println("flavours json is " + temp);

                        map.put(CommonUtils.skuVolumes,temp.getString(CommonUtils.skuVolumes));
                        map.put(CommonUtils.skuIsSelected,temp.getString(CommonUtils.skuIsSelected));
                        map.put(CommonUtils.skuIsAvaliable,temp.getString(CommonUtils.skuIsAvaliable));
                        map.put(CommonUtils.skuId,temp.getString(CommonUtils.skuId));
                        volumes_array.add(map);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vol = new RecyclerViewAdapter(context,volumes_array);
                volumes.setAdapter(vol);
                //volumes.notify();
                //notifyDataSetChanged();


            }
        });


        //holder.circle.setBackgroundColor(Color.parseColor(resultp.get(CommonUtils.skuCode)));
        //holder.image.setBackgroundColor(Color.parseColor(resultp.get(CommonUtils.skuCode)));
        holder.image.setColorFilter(Color.parseColor(resultp.get(CommonUtils.skuCode)));
        holder.linearLayout.setBackgroundResource(R.drawable.circle);

        GradientDrawable drawable = (GradientDrawable) holder.linearLayout.getBackground();
       drawable.setColor(Color.parseColor(resultp.get(CommonUtils.skuCode)));
       // holder.linearLayout.setBackgroundColor(Color.parseColor(resultp.get(CommonUtils.skuCode)));

    }

    @Override
    public int getItemCount() {
        return image_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView name;
        ImageView image;
        CircleImageView circle;
        LinearLayout linearLayout;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            name = (TextView)  mView.findViewById(R.id.name);
            image = (ImageView) mView.findViewById(R.id.image);
            linearLayout = (LinearLayout) mView.findViewById(R.id.linear);
           // circle = (CircleImageView) mView.findViewById(R.id.circle_image);

        }
    }
}

