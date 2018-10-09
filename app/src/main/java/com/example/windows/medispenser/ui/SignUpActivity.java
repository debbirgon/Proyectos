package com.example.windows.medispenser.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.PersonService;
import com.example.windows.medispenser.model.Carer;
import com.example.windows.medispenser.model.Sex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    EditText et_user_signUp;
    EditText et_pass_signUp;
    EditText et_name_signUp;
    EditText et_surname_signUp;
    EditText et_birthday_signUp;
    Button btn_signUp;
    Spinner spinner_sex_signUp;
    Carer carer;
    DatePickerDialog.OnDateSetListener myDateSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_user_signUp = findViewById(R.id.et_user_signUp);
        et_pass_signUp = findViewById(R.id.et_pass_signUp);
        et_name_signUp = findViewById(R.id.et_name_signUp);
        et_surname_signUp = findViewById(R.id.et_surname_signUp);
        et_birthday_signUp = findViewById(R.id.et_birthday_signUp);
        btn_signUp = findViewById(R.id.btn_signUp);
        spinner_sex_signUp = findViewById(R.id.spinner_sex_signUp);
        carer = new Carer();

        final List<String> listSex = new ArrayList<>();
        listSex.add(getString(R.string.select));
        listSex.add("HOMBRE");
        listSex.add("MUJER");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout
                .simple_spinner_dropdown_item,listSex);
        spinner_sex_signUp.setAdapter(spinnerAdapter);

        spinner_sex_signUp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position!=0) {
                    carer.setSex(Sex.valueOf(listSex.get(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_birthday_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,myDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        myDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String stringMonth, stringDay;
                if((month+1)<10){
                    stringMonth = "0"+(month+1);
                }else{
                    stringMonth = String.valueOf((month+1));
                }

                if(day<10){
                    stringDay = "0"+day;
                }else{
                    stringDay = String.valueOf(day);
                }
                String birthday = year+"-"+stringMonth+"-"+stringDay;
                et_birthday_signUp.setText(birthday);

            }
        };

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carer.setName(et_name_signUp.getText().toString());
                carer.setSurname(et_surname_signUp.getText().toString());
                carer.setBirthday(et_birthday_signUp.getText().toString());
                carer.setUsername(et_user_signUp.getText().toString());
                setPasswordHashed(et_pass_signUp.getText().toString());

                if(carer.getSex()==null || carer.getUsername() == null || carer.getPassword()== null
                ||carer.getName().equals("") || carer.getSurname().equals("")
                        || carer.getBirthday().equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else{
                    PersonService personService = ApiClient.getApiClient().create(PersonService.class);
                    personService.postCarer(carer).enqueue(new Callback<Carer>() {
                        @Override
                        public void onResponse(Call<Carer> call, Response<Carer> response) {

                            if(response.code()==200){
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                Toast.makeText(getApplicationContext(),getString(R.string.register_ok),
                                        Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Carer> call, Throwable t) {
                            if(t.getMessage()
                                    .equals("java.lang.IllegalStateException: " +
                                            "Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                Toast.makeText(getApplicationContext(),getString(R.string.register_ok),
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }

            }
        });
    }

    private void setPasswordHashed(String pass) {
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

        carer.setPassword(hexString.toString());

    }
}
