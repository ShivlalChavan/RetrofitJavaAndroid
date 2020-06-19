package com.example.testapi.Climate;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapi.Adapter.WeatherListAdapter;
import com.example.testapi.Common.ShowLoader;
import com.example.testapi.Common.SpaceItemDecoration;
import com.example.testapi.Common.ToolbarSetup;
import com.example.testapi.Model.Weather;
import com.example.testapi.Model.WeatherData;
import com.example.testapi.R;
import com.example.testapi.Rest.ApiInterface;
import com.example.testapi.Rest.ApiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClimateActivity extends AppCompatActivity  implements View.OnClickListener{

    private Toolbar toolbar;
    private ToolbarSetup toolbarSetup;
    private ShowLoader showLoader;

    private ApiInterface mApiService;
    private RecyclerView climateListRecyclerview;
    private TextView emptyTextView;
    private ProgressBar progressBar;
    private ArrayList<Weather> weatherData = new ArrayList<>();
    private WeatherListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_climate_list);

        initView();

    }

    private void initView() {

        toolbar = findViewById(R.id.toolbar);

        toolbarSetup =new ToolbarSetup(this,toolbar,"Climate Details", R.drawable.back_icon);

        showLoader = new ShowLoader(ClimateActivity.this);

        mApiService = ApiUtils.getApiService();
        //progressBar = new ProgressBar();
        climateListRecyclerview = findViewById(R.id.climateListRecyclerview);
        climateListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        climateListRecyclerview.addItemDecoration(new SpaceItemDecoration(10));
        climateListRecyclerview.setHasFixedSize(true);
        climateListRecyclerview.setItemAnimator(new DefaultItemAnimator());



    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoader.showDialog();
        callApiForWeatherData();
    }

    private void callApiForWeatherData() {

        mApiService.getWeatherData().enqueue(new Callback<WeatherData>()
        {
            @Override
            public void onResponse(Call<WeatherData> call,
                                   Response<WeatherData> response)
            {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        weatherData = response.body().getWeather();
                        if(!weatherData.isEmpty()){
                            showLoader.dismissDialog();
                            setUpData();
                        }

                    }
                }
                else {
                    showLoader.dismissDialog();
                }

            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t)
            {
               Log.e("in error:","weather Api" +t.getMessage());
            }
        });

    }

    private void setUpData() {
        adapter = new WeatherListAdapter(this,weatherData);
        climateListRecyclerview.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
