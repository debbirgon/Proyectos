package com.example.windows.medispenser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView ic_back;
    TextView user_name;
    TextView level_1;
    TextView level_2;
    TextView level_3;
    Button btn_exit;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ic_back = findViewById(R.id.ic_back);
        user_name = findViewById(R.id.user_name);
        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        btn_exit = findViewById(R.id.btn_exit);
        sharedPreferences
                = getApplicationContext()
                .getSharedPreferences(getString(R.string.shared_pref), MODE_PRIVATE);

        String userNameText = getString(R.string.user_name)+" "+
                sharedPreferences.getString(getString(R.string.user_pref),"");

        user_name.setText(userNameText);

        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().putBoolean(getString(R.string.keep),false).apply();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                intent.putExtra(getString(R.string.num_level),1);
                startActivity(intent);
            }
        });
        level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                intent.putExtra(getString(R.string.num_level),2);
                startActivity(intent);
            }
        });
        level_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LevelActivity.class);
                intent.putExtra(getString(R.string.num_level),3);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
