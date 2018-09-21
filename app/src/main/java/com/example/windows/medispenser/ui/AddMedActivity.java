package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.util.Constants;

import static java.lang.Integer.valueOf;

public class AddMedActivity extends AppCompatActivity {

    EditText et_name_of_med;
    EditText et_amount;
    Button btn_addMed_and_amount;
    Button deleteMed;
    SharedPreferences sharedPreferences;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        Bundle extras = getIntent().getExtras();
        level = extras.getInt(Constants.LEVEL);
        Boolean hasMed = extras.getBoolean(getString(R.string.has_med));
        et_name_of_med = findViewById(R.id.et_name_of_med);
        et_amount = findViewById(R.id.et_amount);
        btn_addMed_and_amount = findViewById(R.id.btn_addMed_and_amount);
        deleteMed = findViewById(R.id.deleteMed);
        sharedPreferences = getApplicationContext().getSharedPreferences(getString(
                R.string.shared_pref),MODE_PRIVATE);

        if(hasMed){
            deleteMed.setVisibility(View.VISIBLE);
        }

        deleteMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeFromPreferences(level);
                Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                intent.putExtra(Constants.LEVEL,level);
                startActivity(intent);
                finish();
            }
        });




        btn_addMed_and_amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name_of_med.getText().toString();
                String amountString = et_amount.getText().toString();
                int amount = 0;

                if(name==null || name.equals("") || amountString == null || amountString.equals("")){
                    Toast.makeText(getApplicationContext(), getString(R.string.register_fail)
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    amount = valueOf(amountString);
                }

                if(!(amount<8) || !(amount>0)){
                    Toast.makeText(getApplicationContext(), getString(R.string.wrong_number)
                            ,Toast.LENGTH_LONG).show();

                }else{
                    switch (level){

                        case 1:
                            sharedPreferences.edit().putString(getString(R.string.med_level_1),name).apply();
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_1), amount).apply();
                            break;
                        case 2:
                            sharedPreferences.edit().putString(getString(R.string.med_level_2),name).apply();
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_2), amount).apply();
                            break;
                        case 3:
                            sharedPreferences.edit().putString(getString(R.string.med_level_3),name).apply();
                            sharedPreferences.edit().putInt(getString(R.string.pills_level_3), amount).apply();
                            break;
                    }
                    Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                    intent.putExtra(Constants.LEVEL,level);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void removeFromPreferences(int level) {

        switch (level) {

            case 1:
                sharedPreferences.edit().remove(getString(R.string.med_level_1)).apply();
                sharedPreferences.edit().remove(getString(R.string.pills_level_1)).apply();
                sharedPreferences.edit().remove(getString(R.string.takes_level_1)).apply();
                break;
            case 2:
                sharedPreferences.edit().remove(getString(R.string.med_level_2)).apply();
                sharedPreferences.edit().remove(getString(R.string.pills_level_2)).apply();
                sharedPreferences.edit().remove(getString(R.string.takes_level_2)).apply();
                break;
            case 3:
                sharedPreferences.edit().remove(getString(R.string.med_level_3)).apply();
                sharedPreferences.edit().remove(getString(R.string.pills_level_3)).apply();
                sharedPreferences.edit().remove(getString(R.string.takes_level_3)).apply();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
        intent.putExtra(Constants.LEVEL,level);
        startActivity(intent);
        finish();
    }
}
