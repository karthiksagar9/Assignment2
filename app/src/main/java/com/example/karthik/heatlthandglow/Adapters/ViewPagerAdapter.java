package com.example.karthik.heatlthandglow.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.karthik.heatlthandglow.Helpers.CommonUtils;
import com.example.karthik.heatlthandglow.R;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by karthik on 07/10/16.
 */

public class ViewPagerAdapter extends PagerAdapter {


    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> image_list;
    HashMap<String, String> resultp = new HashMap<String, String>();
    ImageView sku1Image,sku2Image;
    TextView skuTotalPrice,skuOfferPrice,dis,sku1Name,sku2Name,sku1Vol,sku2Vol;


    public ViewPagerAdapter(Context context, ArrayList<HashMap<String, String>> image_list) {
        this.context = context;
        this.image_list = image_list;
    }

    @Override
    public int getCount() {
        return image_list.size();
    }



    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public Object instantiateItem(final ViewGroup container, final int position) {
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        resultp = image_list.get(position);
        View convertView = inflater.inflate(R.layout.pager_combo, null, false);
        //System.out.print("tetstsstts");
        // Get the position

        sku1Image = (ImageView) convertView.findViewById(R.id.sku1Image);
        sku2Image = (ImageView) convertView.findViewById(R.id.sku2Image);
        sku1Name = (TextView) convertView.findViewById(R.id.sku1Name);
        sku2Name = (TextView) convertView.findViewById(R.id.sku2Name);
        sku1Vol = (TextView) convertView.findViewById(R.id.sku1Vol);
        sku2Vol = (TextView) convertView.findViewById(R.id.sku2Vol);
        skuTotalPrice = (TextView) convertView.findViewById(R.id.price);
        skuOfferPrice = (TextView) convertView.findViewById(R.id.dis_price);
        dis = (TextView) convertView.findViewById(R.id.dis);
        Glide.with(context)
                .load(resultp.get(CommonUtils.sku1ImageUrl))
                //.placeholder(R.drawable.image_placeholder)
                .dontAnimate()
                .into(sku1Image);

        Glide.with(context)
                .load(resultp.get(CommonUtils.sku2ImageUrl))
                //.placeholder(R.drawable.image_placeholder)
                .dontAnimate()
                .into(sku2Image);

        sku1Name.setText(resultp.get(CommonUtils.sku1Name));
        sku2Name.setText(resultp.get(CommonUtils.sku2Name));

        skuTotalPrice.setText("Rs." + " " + resultp.get(CommonUtils.totalPrice));
        skuOfferPrice.setText("Rs." + " " + resultp.get(CommonUtils.finalPrice));




        ((ViewPager) container).addView(convertView);
        return convertView;
    }
    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}
