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
 * Created by karthik on 09/10/16.
 */

public class RecyclerViewAdapterQuestions extends RecyclerView.Adapter<RecyclerViewAdapterQuestions.ViewHolder>{

    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> ques_list;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public RecyclerViewAdapterQuestions(Context context,ArrayList<HashMap<String, String>> ques_list) {
        this.context = context;
        this.ques_list = ques_list;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.question_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        resultp = ques_list.get(position);
        System.out.println("sixep[" + ques_list);
        holder.ques.setTag(position);
        holder.ques.setText(resultp.get(CommonUtils.question));
        holder.ans.setText(resultp.get(CommonUtils.defaultAnswer));
        //holder.ans.setText(resultp.get(CommonUtils.skuVolumes));

    }

    @Override
    public int getItemCount() {
        if (ques_list.size() >4){
            return 4;
        }
        else{
            return ques_list.size();

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView ques,ans;
        //ImageView image;
        public ViewHolder(View mView) {
            super(mView);
            this.mView = mView;
            ques = (TextView)  mView.findViewById(R.id.ques);
            ans = (TextView) mView.findViewById(R.id.ans);

        }
    }
}

