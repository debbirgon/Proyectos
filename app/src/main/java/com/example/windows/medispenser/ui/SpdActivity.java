package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.util.Constants;

public class SpdActivity extends AppCompatActivity {

    TextView level_1;
    TextView level_2;
    TextView level_3;
    Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spd);

        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);

        patient = (Patient) getIntent().getExtras().getSerializable(Constants.PATIENT);

        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoadActivity(1);
            }
        });
        level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoadActivity(2);
            }
        });
        level_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoadActivity(3);
            }
        });

    }

    public void startLoadActivity(int level){
        Intent intent = new Intent(getApplicationContext(), LoadActivity.class);
        intent.putExtra(Constants.LEVEL,level);
        intent.putExtra(Constants.PATIENT,patient);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        intent.putExtra(Constants.PATIENT,patient);
        startActivity(intent);
        finish();
    }
}


