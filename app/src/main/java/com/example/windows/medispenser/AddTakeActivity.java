package com.example.windows.medispenser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.valueOf;

public class AddTakeActivity extends AppCompatActivity {

    EditText et_hour;
    EditText et_minute;
    Button btn_add_take;
    Button deleteTakes;
    SharedPreferences sharedPreferences;
    int level;
    List<String> takes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_takes);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt(getString(R.string.num_level));
        Boolean hasTakes = extras.getBoolean(getString(R.string.has_takes));
        et_hour = findViewById(R.id.et_hour);
        et_minute = findViewById(R.id.et_minute);
        btn_add_take = findViewById(R.id.btn_add_take);
        deleteTakes = findViewById(R.id.deleteTakes);
        sharedPreferences = getApplicationContext().getSharedPreferences(getString(
                R.string.shared_pref),MODE_PRIVATE);
        takes = new ArrayList<>();

        if(hasTakes){
            deleteTakes.setVisibility(View.VISIBLE);
            Set<String> takesSet = new HashSet<>();
            switch (level) {

                case 1:
                    takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_1),
                            null));
                    break;
                case 2:
                    takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_2),
                            null));
                    break;
                case 3:
                    takesSet = (sharedPreferences.getStringSet(getString(R.string.takes_level_3),
                            null));
                    break;
            }
            takes.addAll(takesSet);
        }

        deleteTakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromPreferences(level);
                Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                intent.putExtra(getString(R.string.num_level),level);
                startActivity(intent);
                finish();
            }
        });

        btn_add_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hour = et_hour.getText().toString();
                String minute = et_minute.getText().toString();

                if(hour==null || hour.equals("") || minute == null || minute.equals("")){
                    Toast.makeText(getApplicationContext(), getString(R.string.register_fail)
                            ,Toast.LENGTH_LONG).show();
                }else if(hour.length()!=2 || minute.length() != 2){
                    Toast.makeText(getApplicationContext(), getString(R.string.wrong_size)
                            ,Toast.LENGTH_LONG).show();

                }else if(valueOf(hour)<0 || valueOf(hour)>23
                        || valueOf(minute)<0 || valueOf(minute)>59){
                    Toast.makeText(getApplicationContext(), getString(R.string.wrong_hour)
                            ,Toast.LENGTH_LONG).show();

                }else{
                    String take = hour + ":" + minute;
                    takes.add(take);
                    Set<String> takeSet = new HashSet<>(takes);

                    switch (level){

                        case 1:
                            sharedPreferences.edit().putStringSet(getString(R.string.takes_level_1),
                                    takeSet).apply();
                            break;
                        case 2:
                            sharedPreferences.edit().putStringSet(getString(R.string.takes_level_2),
                                    takeSet).apply();
                            break;
                        case 3:
                            sharedPreferences.edit().putStringSet(getString(R.string.takes_level_3),
                                    takeSet).apply();
                            break;
                    }
                    Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                    intent.putExtra(getString(R.string.num_level),level);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void removeFromPreferences(int level) {
        switch (level) {

            case 1:
                 sharedPreferences.edit().remove(getString(R.string.takes_level_1)).apply();
                break;
            case 2:
                sharedPreferences.edit().remove(getString(R.string.takes_level_2)).apply();
                break;
            case 3:
                sharedPreferences.edit().remove(getString(R.string.takes_level_3)).apply();
                break;
        }
    }
}
