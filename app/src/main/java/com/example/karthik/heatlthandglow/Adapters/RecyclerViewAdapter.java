package com.example.karthik.heatlthandglow.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.karthik.heatlthandglow.Helpers.CommonUtils;
import com.example.karthik.heatlthandglow.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by karthik on 08/10/16.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> image_list;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public RecyclerViewAdapter(Context context,ArrayList<HashMap<String, String>> image_list) {
        this.context = context;
        this.image_list = image_list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.volumes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        resultp = image_list.get(position);
        //System.out.println("sixep[" + image_list);
        holder.name.setTag(position);
        holder.name.setText(resultp.get(CommonUtils.skuVolumes));

    }

    @Override
    public int getItemCount() {
        return image_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView name;
        ImageView image;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            name = (TextView)  mView.findViewById(R.id.name);
            image = (ImageView) mView.findViewById(R.id.image);

        }
    }
}
