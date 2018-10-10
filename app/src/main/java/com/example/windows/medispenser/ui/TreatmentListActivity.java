package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.adapter.TreatmentListAdapter;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.TreatmentService;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentListActivity extends AppCompatActivity {
    
    RecyclerView rv_treatment_list;
    Button btn_add_treatment;
    List<Treatment> treatmentList;
    TreatmentListAdapter treatmentListAdapter;
    Patient patient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_list);

        Bundle extraPatient = getIntent().getExtras();
        patient = (Patient) extraPatient.getSerializable(Constants.PATIENT);


        rv_treatment_list = (RecyclerView) findViewById(R.id.rv_treatment_list);
        btn_add_treatment = findViewById(R.id.btn_add_treatment);


        treatmentList = new ArrayList<>();

        rv_treatment_list.setHasFixedSize(true);
        // use a linear layout manager
        rv_treatment_list.setLayoutManager(new LinearLayoutManager(this));

        TreatmentService treatmentService = ApiClient.getApiClient().create(TreatmentService.class);

        treatmentService.getTreatments(1/*patient.getId_patient()*/).enqueue(new Callback<List<Treatment>>() {
            @Override
            public void onResponse(Call<List<Treatment>> call, Response<List<Treatment>> response) {
                if(response.code()==200){
                    treatmentList = response.body();
                    // specify an adapter (see also next example)
                    treatmentListAdapter = new TreatmentListAdapter(treatmentList,getApplicationContext());
                    rv_treatment_list.setAdapter(treatmentListAdapter);

                }else{
                    Toast.makeText(getApplicationContext(),"Ha habido un error",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Treatment>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailure",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_add_treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddTreatmentActivity.class);
                intent.putExtra(Constants.PATIENT,patient);
                startActivity(intent);
                finish();
            }
        });


    }
}
