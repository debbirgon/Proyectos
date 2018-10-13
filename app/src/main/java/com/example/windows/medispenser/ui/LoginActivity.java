package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.util.Constants;

public class LoginActivity extends AppCompatActivity {

    EditText et_user;
    EditText et_pass;
    Button btn_access;
    Button btn_createUser;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        btn_access = findViewById(R.id.btn_access);
        btn_createUser = findViewById(R.id.btn_createUser);
        sharedPreferences = getApplicationContext()
                .getSharedPreferences(Constants.SHARED_PREF, MODE_PRIVATE);

        Boolean keepSession = sharedPreferences.getBoolean(Constants.KEEP,false);

        if(keepSession){
            startActivity(new Intent(getApplicationContext(), PatientListActivity.class));
            finish();
        }

        btn_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_pref = sharedPreferences.getString(Constants.USER_PREF,"");
                String pass_pref = sharedPreferences.getString(Constants.PASS_PREF,"");

                if(user_pref == null || user_pref ==""){
                    Toast.makeText(getApplicationContext(), getString(R.string.must_create_user),
                            Toast.LENGTH_SHORT).show();
                }else if(user_pref.equals(et_user.getText().toString())&&
                        pass_pref.equals(et_pass.getText().toString())){
                    startActivity(new Intent(getApplicationContext(), PatientListActivity.class));
                    sharedPreferences.edit().putBoolean(Constants.KEEP,true).apply();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.login_fail),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));

            }
        });


    }
}
