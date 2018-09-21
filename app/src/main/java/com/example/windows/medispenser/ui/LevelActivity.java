package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LevelActivity extends AppCompatActivity {

    TextView tv_level;
    LinearLayout ll_info;
    LinearLayout ll_displayInfo;
    TextView tv_info;
    TextView tv_takes;
    Button btn_addMed;
    Button btn_addTake;
    SharedPreferences sharedPreferences;
    List<String> takes;
    int level;
    int pills_left;
    Boolean hasMed;
    Boolean hasTakes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        initView();

        Bundle extras = getIntent().getExtras();
        level = extras.getInt(Constants.LEVEL);
        tv_level.setText(getString(R.string.level)+" "+ level);

        String medicine = null;
        Set<String> takesSet;

        switch (level){

            case 1:
                medicine = sharedPreferences.getString(getString(R.string.med_level_1),null);
                pills_left = sharedPreferences.getInt(getString(R.string.pills_level_1),0);
                takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_1),
                        null));
                if(takesSet!=null){
                    takes.addAll(takesSet);
                }
                break;
            case 2:
                medicine = sharedPreferences.getString(getString(R.string.med_level_2),null);
                pills_left = sharedPreferences.getInt(getString(R.string.pills_level_2),0);
                takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_2),
                        null));
                if(takesSet!=null){
                    takes.addAll(takesSet);
                }
                break;
            case 3:
                medicine = sharedPreferences.getString(getString(R.string.med_level_3),null);
                pills_left = sharedPreferences.getInt(getString(R.string.pills_level_3),0);
                takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_3),
                        null));
                if(takesSet!=null){
                    takes.addAll(takesSet);
                }
                break;
        }


        if(medicine!=null){
            ll_info.setVisibility(View.VISIBLE);
            ll_displayInfo.setVisibility(View.VISIBLE);
            hasMed = true;

            String info = "\n" + getString(R.string.name_of_med)+"\n"+medicine+"\n\nQuedan "+ pills_left+
                    " pastillas";

            tv_info.setText(info);
            btn_addTake.setVisibility(View.VISIBLE);
            btn_addMed.setText(getString(R.string.edit_med));
        }
        if(takes!= null && takes.size()!=0){
            hasTakes = true;
            btn_addTake.setText(getString(R.string.edit_takes));
            String horas="";
            for(int i=0;i<takes.size();i++){
                if(i==0){
                    horas = takes.get(i);
                }else{
                    horas = horas +"\n" + takes.get(i);
                }
            }
            tv_takes.setText(horas);
        }

        btn_addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMedActivity.class);
                intent.putExtra(Constants.LEVEL,level);
                intent.putExtra(getString(R.string.has_med),hasMed);
                startActivity(intent);
                finish();

            }
        });

        btn_addTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTakeActivity.class);
                intent.putExtra(Constants.LEVEL,level);
                intent.putExtra(getString(R.string.has_takes),hasTakes);
                startActivity(intent);
                finish();
            }
        });

        tv_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pills_left!=0){
                    pills_left--;

                    switch (level){

                        case 1:
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_1), pills_left).apply();
                            break;
                        case 2:
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_2), pills_left).apply();
                            break;
                        case 3:
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_3), pills_left).apply();
                            break;
                    }

                    finish();
                    startActivity(getIntent());
                }



            }
        });



    }

    private void initView() {
        takes = new ArrayList<>();
        tv_level = findViewById(R.id.tv_level);
        ll_info = findViewById(R.id.ll_info);
        ll_displayInfo = findViewById(R.id.ll_displayInfo);
        tv_info = findViewById(R.id.tv_info);
        tv_takes = findViewById(R.id.tv_takes);
        btn_addMed = findViewById(R.id.btn_addMed);
        btn_addTake = findViewById(R.id.btn_addTake);
        hasMed = false;
        hasTakes = false;
        sharedPreferences = getApplicationContext().getSharedPreferences(getString
                (R.string.shared_pref), MODE_PRIVATE);
    }
}
