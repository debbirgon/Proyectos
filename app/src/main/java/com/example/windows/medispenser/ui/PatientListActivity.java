package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.adapter.PatientListAdapter;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.PersonService;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientListActivity extends AppCompatActivity implements PatientListAdapter.Finish{

    private RecyclerView rv_patient_list;
    private PatientListAdapter patientListAdapter;
    private List<Patient> patientList;
    private Button btn_add_patient;
    private Button btn_exit;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_list);

        sharedPreferences = getApplication().getSharedPreferences(Constants.SHARED_PREF,MODE_PRIVATE);

        rv_patient_list = (RecyclerView) findViewById(R.id.rv_patient_list);
        btn_add_patient = findViewById(R.id.btn_add_patient);
        btn_exit = findViewById(R.id.btn_exit);
        patientList = new ArrayList<>();

        rv_patient_list.setHasFixedSize(true);
        // use a linear layout manager
        rv_patient_list.setLayoutManager(new LinearLayoutManager(this));

        PersonService patientService = ApiClient.getApiClient().create(PersonService.class);

        patientService.getAllPatients(3).enqueue(new Callback<List<Patient>>() {
            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                if(response.code()==200){
                    patientList = response.body();
                    // specify an adapter (see also next example)
                    patientListAdapter = new PatientListAdapter(patientList,getApplicationContext(),
                            PatientListActivity.this);
                    rv_patient_list.setAdapter(patientListAdapter);

                }else{
                    Toast.makeText(getApplicationContext(),"Ha habido un error",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailure",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                sharedPreferences.edit().putBoolean(Constants.KEEP,false).apply();
                finish();
            }
        });

        btn_add_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddPatientActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onFinish() {
        finish();
    }
}