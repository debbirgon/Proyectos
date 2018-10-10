package com.example.windows.medispenser.ui;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.DoseService;
import com.example.windows.medispenser.model.Dose;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.util.Constants;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.min;
import static java.lang.Integer.valueOf;

public class AddMedActivity extends AppCompatActivity {

    EditText et_name_of_med;
    EditText et_init_hour;
    EditText et_times_day;
    Button btn_addMedication;
    TimePickerDialog timePickerDialog;
    Dose dose;
    Treatment treatment;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);

        et_name_of_med = findViewById(R.id.et_name_of_med);
        et_init_hour = findViewById(R.id.et_init_hour);
        et_times_day = findViewById(R.id.et_times_day);
        btn_addMedication = findViewById(R.id.btn_addMedication);

        dose = new Dose();
        treatment = (Treatment) getIntent().getExtras().getSerializable(Constants.TREATMENT);
        patient = (Patient) getIntent().getExtras().getSerializable(Constants.PATIENT);

        et_init_hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                timePickerDialog = new TimePickerDialog(AddMedActivity.this, AlertDialog.THEME_HOLO_LIGHT,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                String finalHour;
                                String finalMinute;
                                if(hour<10){
                                    finalHour = "0"+hour;
                                }else{
                                    finalHour = String.valueOf(hour);
                                }
                                if(minute<10){
                                    finalMinute = "0"+minute;
                                }else{
                                    finalMinute = String.valueOf(minute);
                                }

                                String stringHour = finalHour+":"+finalMinute+":00";
                                et_init_hour.setText(stringHour);
                            }
                        },calendar.HOUR_OF_DAY, calendar.MINUTE, false);
                timePickerDialog.show();
            }
        });

        btn_addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nameMed = et_name_of_med.getText().toString();
                String hourInit = et_init_hour.getText().toString();
                String timesDay = et_times_day.getText().toString();

                if(nameMed==null || nameMed.equals("") || hourInit==null ||hourInit.equals("")
                        ||timesDay==null || timesDay.equals("")){
                    Toast.makeText(getApplicationContext(),getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else {
                    dose.setHora_inicio(hourInit);
                    dose.setVeces_dia(Integer.valueOf(timesDay));
                    dose.setNombre_medicamento(nameMed);
                    dose.setId_tratamiento(treatment.getId());
                    DoseService doseService = ApiClient.getApiClient().create(DoseService.class);
                    doseService.postDose(dose).enqueue(new Callback<Dose>() {
                        @Override
                        public void onResponse(Call<Dose> call, Response<Dose> response) {
                            if(response.code()==200){
                                Intent intent = new Intent(getApplicationContext(), TreatmentDetailActivity.class);
                                intent.putExtra(Constants.TREATMENT,treatment);
                                startActivity(intent);
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Conflict",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Dose> call, Throwable t) {

                            if(t.getMessage()
                                    .equals("java.lang.IllegalStateException: " +
                                            "Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
                                Intent intent = new Intent(getApplicationContext(), TreatmentDetailActivity.class);
                                intent.putExtra(Constants.TREATMENT,treatment);
                                startActivity(intent);
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
        Intent intent = new Intent(getApplicationContext(), TreatmentDetailActivity.class);
        intent.putExtra(Constants.TREATMENT,treatment);
        intent.putExtra(Constants.PATIENT,patient);
        startActivity(intent);
        finish();
    }
}
