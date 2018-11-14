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
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.AuthService;
import com.example.windows.medispenser.model.Carer;
import com.example.windows.medispenser.util.Constants;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText et_user;
    EditText et_pass;
    Button btn_access;
    Button btn_createUser;
    SharedPreferences sharedPreferences;
    Carer carer;
    Integer id_carer = 4;

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
        carer = new Carer();

        Boolean keepSession = sharedPreferences.getBoolean(Constants.KEEP,false);

        if(keepSession){
            startActivity(new Intent(getApplicationContext(), PatientListActivity.class));
            finish();
        }

        btn_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = et_user.getText().toString();
                String pass = et_pass.getText().toString();

                if(user == null || user.equals("") || pass == null || pass.equals("")){
                    Toast.makeText(getApplicationContext(), getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else{
                    carer.setUsername(user);
                    carer.setPassword(setPasswordHashed(pass));
                    AuthService authService = ApiClient.getApiClient().create(AuthService.class);


                    /*authService.doLogin(carer).enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            if(response.code()==200){
                                id_carer = response.body();
                            }
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });*/

                    Intent intent = new Intent(getApplicationContext(), PatientListActivity.class);
                    intent.putExtra(Constants.ID_CARER, id_carer);
                    sharedPreferences.edit().putBoolean(Constants.KEEP,true).apply();
                    startActivity(intent);
                    finish();
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
    private String setPasswordHashed(String pass) {
        MessageDigest mDigest = null;
        try {
            mDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] encodedHash = mDigest.digest(pass.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < encodedHash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedHash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();

    }
}
