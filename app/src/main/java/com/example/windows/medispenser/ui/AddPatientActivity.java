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
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Sex;
import com.example.windows.medispenser.util.Constants;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPatientActivity extends AppCompatActivity {

    EditText et_patient_name;
    EditText et_patient_surname;
    EditText et_patient_birthday;
    Button btn_addPatient;
    Spinner spinner_sex;
    Patient patient;
    DatePickerDialog.OnDateSetListener myDateSetListener;
    int id_carer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        id_carer = getIntent().getExtras().getInt(Constants.ID_CARER);

        et_patient_name = findViewById(R.id.et_patient_name);
        et_patient_surname = findViewById(R.id.et_patient_surname);
        et_patient_birthday = findViewById(R.id.et_patient_birthday);
        btn_addPatient = findViewById(R.id.btn_addPatient);
        spinner_sex = findViewById(R.id.spinner_sex);
        patient = new Patient();

        final List<String> listSex = new ArrayList<>();
        listSex.add(getString(R.string.select));
        listSex.add("HOMBRE");
        listSex.add("MUJER");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout
        .simple_spinner_dropdown_item,listSex);
        spinner_sex.setAdapter(spinnerAdapter);

        spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position!=0) {
                    patient.setSex(Sex.valueOf(listSex.get(position)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        et_patient_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddPatientActivity.this,
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
                et_patient_birthday.setText(birthday);

            }
        };

        btn_addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setName(et_patient_name.getText().toString());
                patient.setSurname(et_patient_surname.getText().toString());
                patient.setBirthday(et_patient_birthday.getText().toString());
                patient.setId_carer(id_carer);
                patient.setAlias(RandomStringUtils.random(5,true,false));

                if(patient.getSex()==null || patient.getName().equals("")
                        || patient.getSurname().equals("")|| patient.getBirthday().equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else{
                    PersonService personService = ApiClient.getApiClient().create(PersonService.class);
                    personService.postPatient(patient).enqueue(new Callback<Patient>() {
                        @Override
                        public void onResponse(Call<Patient> call, Response<Patient> response) {

                            if(response.code()==200){
                                startActivity(new Intent(getApplicationContext(), PatientListActivity.class));
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Conflict",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Patient> call, Throwable t) {
                            if(t.getMessage()
                                    .equals("java.lang.IllegalStateException: " +
                                            "Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
                                startActivity(new Intent(getApplicationContext(),PatientListActivity.class));
                                finish();
                            }

                        }
                    });
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, PatientListActivity.class));
        finish();
    }
}
