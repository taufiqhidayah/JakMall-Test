package com.taufiq.jakmallcodingtest;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.taufiq.jakmallcodingtest.Model.DataModel;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>  {

    private ArrayList<DataModel> dataList;
    private Context context;

//    public CustomAdapter(ArrayList<DataModel> dataModels) {
//        this.dataList = dataModels;
//    }

//    public CustomAdapter(Callback<JsonRespon> context) {
//       this.context=context;
//        dataList=new ArrayList<>();
//    }

    public CustomAdapter(ArrayList<DataModel> dataModels,Context context) {
     this.dataList=dataModels;
     this.context= context;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder  {
        public final View mView;
        private ImageView coverImage;
        private TextView tvStat;
        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            tvStat = mView.findViewById(R.id.tvStatemen);
           // tvUp = mView.findViewById(R.id.tvUp);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Hold and Move", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {

        holder.tvStat.setText(dataList.get(position).getJoke());
    }
    @Override
    public int getItemCount() {
        return  dataList.size();
    }
}