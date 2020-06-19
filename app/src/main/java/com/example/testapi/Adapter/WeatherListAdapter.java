package com.example.testapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapi.Climate.ClimateDescription;
import com.example.testapi.Model.Weather;
import com.example.testapi.R;

import java.util.ArrayList;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {

    private ArrayList<Weather> weatherData;
    private Context context;

    public WeatherListAdapter(Context context, ArrayList<Weather> list){
        this.context = context;
        this.weatherData = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context  = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView =  inflater.inflate(R.layout.row_layout_for_climatelist_item , parent,false);

         WeatherListAdapter.ViewHolder viewHolder = new WeatherListAdapter.ViewHolder(itemView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(!weatherData.isEmpty()){

            Weather weather = weatherData.get(position);
            if(!weather.getMain().isEmpty()){
                holder.txtClimate.setText(weather.getMain());
            }

            if (!weather.getDescription().isEmpty()){
                holder.txtDescription.setText(weather.getDescription());
            }

        }else{

        }

    }

    @Override
    public int getItemCount() {
        return weatherData.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtClimate,txtDescription;
        private LinearLayout llParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClimate = itemView.findViewById(R.id.txtClimate);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            llParent = itemView.findViewById(R.id.llParent);

            llParent.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.llParent:
                    Log.e("ITEM CLICK","list:" +weatherData.get(getAbsoluteAdapterPosition()));
                    Weather weather = weatherData.get(getAbsoluteAdapterPosition());
                    Intent intent = new Intent(context, ClimateDescription.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("climateData",weather);

                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);


                    break;

            }

        }
    }

}
