package com.example.windows.medispenser.ui;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.windows.medispenser.R;

import java.util.Calendar;

public class AddPatientActivity extends AppCompatActivity {

    EditText et_patient_name;
    EditText et_patient_surname;
    EditText et_patient_birthday;
    Button btn_addPatient;
    DatePickerDialog.OnDateSetListener myDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        et_patient_name = findViewById(R.id.et_patient_name);
        et_patient_surname = findViewById(R.id.et_patient_surname);
        et_patient_birthday = findViewById(R.id.et_patient_birthday);
        btn_addPatient = findViewById(R.id.btn_addPatient);

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

                String birthday = day+"/"+(month+1)+"/"+year;
                et_patient_birthday.setText(birthday);

            }
        };

        btn_addPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
