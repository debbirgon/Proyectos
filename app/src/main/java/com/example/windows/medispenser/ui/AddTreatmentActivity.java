package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.TreatmentService;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTreatmentActivity extends AppCompatActivity {

    EditText et_treatment_name;
    Button btn_addTreatment;
    Treatment treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_treatment);

        Bundle extraPatient = getIntent().getExtras();
        Patient patient = (Patient) extraPatient.getSerializable(Constants.PATIENT);

        et_treatment_name = findViewById(R.id.et_treatment_name);
        btn_addTreatment = findViewById(R.id.btn_addTreatment);

        treatment = new Treatment();
        treatment.setId_patient(patient.getId_patient());
        treatment.setId_spd(2);

        btn_addTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String treatmentName = et_treatment_name.getText().toString();
                if(treatmentName==null || treatmentName.equals("")){
                    Toast.makeText(getApplicationContext(), getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else{
                    treatment.setName(treatmentName);
                    TreatmentService treatmentService = ApiClient.getApiClient().create(TreatmentService.class);
                    treatmentService.postTreatment(treatment).enqueue(new Callback<Treatment>() {
                        @Override
                        public void onResponse(Call<Treatment> call, Response<Treatment> response) {
                            if(response.code()==200){
                                startActivity(new Intent(getApplicationContext(), TreatmentListActivity.class));
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), "Conflict",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Treatment> call, Throwable t) {
                            if(t.getMessage()
                                    .equals("java.lang.IllegalStateException: " +
                                            "Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
                                startActivity(new Intent(getApplicationContext(),TreatmentListActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });



    }
}
