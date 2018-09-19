package com.example.windows.medispenser.UI;

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

public class LoginActivity extends AppCompatActivity {

    EditText et_user;
    EditText et_pass;
    Button btn_access;
    Button btn_createUser;
    CheckBox cb_keepSession;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        btn_access = findViewById(R.id.btn_access);
        btn_createUser = findViewById(R.id.btn_createUser);
        cb_keepSession = findViewById(R.id.cb_keepSession);
        sharedPreferences = getApplicationContext()
                .getSharedPreferences(getString(R.string.shared_pref), MODE_PRIVATE);

        Boolean keepSession = sharedPreferences.getBoolean(getString(R.string.keep),false);

        if(keepSession){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        btn_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_pref = sharedPreferences.getString(getString(R.string.user_pref),"");
                String pass_pref = sharedPreferences.getString(getString(R.string.pass_pref),"");

                if(user_pref == null || user_pref ==""){
                    Toast.makeText(getApplicationContext(), getString(R.string.must_create_user),
                            Toast.LENGTH_SHORT).show();
                }else if(user_pref.equals(et_user.getText().toString())&&
                        pass_pref.equals(et_pass.getText().toString())){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();
                if((user == null || user.equals(""))
                        || (pass == null || pass.equals(""))){
                    Toast.makeText(getApplicationContext(),getString(R.string.register_fail),
                            Toast.LENGTH_SHORT).show();
                }else{
                    sharedPreferences.edit().putString(getString(R.string.user_pref), user).apply();
                    sharedPreferences.edit().putString(getString(R.string.pass_pref), pass).apply();
                    Toast.makeText(getApplicationContext(), getString(R.string.register_ok),
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        cb_keepSession.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharedPreferences.edit().putBoolean(getString(R.string.keep),true).apply();
                }else{
                    sharedPreferences.edit().putBoolean(getString(R.string.keep),false).apply();
                }
            }
        });

    }
}
