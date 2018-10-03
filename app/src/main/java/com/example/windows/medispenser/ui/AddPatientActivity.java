package com.example.windows.medispenser.ui;

import android.app.DatePickerDialog;
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
import com.example.windows.medispenser.model.Person;
import com.example.windows.medispenser.model.Sexo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddPatientActivity extends AppCompatActivity {

    EditText et_patient_name;
    EditText et_patient_surname;
    EditText et_patient_birthday;
    Button btn_addPatient;
    Spinner spinner_sex;
    Person patient;
    DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        et_patient_name = findViewById(R.id.et_patient_name);
        et_patient_surname = findViewById(R.id.et_patient_surname);
        et_patient_birthday = findViewById(R.id.et_patient_birthday);
        btn_addPatient = findViewById(R.id.btn_addPatient);
        spinner_sex = findViewById(R.id.spinner_sex);
        patient = new Person();

        final List<String> listSex = new ArrayList<>();
        listSex.add("HOMBRE");
        listSex.add("MUJER");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout
        .simple_spinner_dropdown_item,listSex);
        spinner_sex.setAdapter(spinnerAdapter);

        spinner_sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                patient.setSex(Sexo.valueOf(listSex.get(position)));

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

                String birthday = year+"-"+(month+1)+"-"+day;
                et_patient_birthday.setText(birthday);

            }
        };

        btn_addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                patient.setName(et_patient_name.getText().toString());
                patient.setSurname(et_patient_surname.getText().toString());
                patient.setBirthday(et_patient_birthday.getText().toString());

                if(patient.getSex()==null || patient.getName().equals("")
                        || patient.getSurname().equals("")|| patient.getBirthday().equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else{

                }

            }
        });
    }

}
