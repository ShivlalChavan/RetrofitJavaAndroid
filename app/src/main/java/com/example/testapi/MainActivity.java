package com.example.testapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testapi.Climate.ClimateActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews() {
        btnClick = findViewById(R.id.btnClick);
        btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClick:
                  Intent intent = new Intent(this, ClimateActivity.class);
                  startActivity(intent);

                break;
        }
    }
}