package com.example.testapi.Climate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testapi.Model.Weather;
import com.example.testapi.R;
import com.google.gson.Gson;

public class ClimateDescription extends AppCompatActivity  implements View.OnClickListener{

    private Weather weather;
    private TextView txtClimate,txtDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_description);

       Bundle bundle = getIntent().getBundleExtra("bundle");
       if(bundle!=null){

           this.weather = (Weather) bundle.getSerializable("climateData");
           Log.e("Weather Object",":="+new Gson().toJson(weather));
       }


       initView();




    }

    private void initView() {
        txtClimate = findViewById(R.id.txtClimate);
        txtDescription = findViewById(R.id.txtDescription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(weather!=null){
            txtClimate.setText(weather.getMain());
            txtDescription.setText(weather.getDescription());

        }
    }

    @Override
    public void onClick(View v) {

    }
}
