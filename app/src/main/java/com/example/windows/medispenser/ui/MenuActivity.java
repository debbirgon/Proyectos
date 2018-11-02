package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.util.Constants;

public class MenuActivity extends AppCompatActivity {

    TextView tv_patient_data;
    Button btn_treatments;
    Button btn_spd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle extraPatient = getIntent().getExtras();
        final Patient patient = (Patient) extraPatient.getSerializable(Constants.PATIENT);

        tv_patient_data = findViewById(R.id.tv_patient_data);
        btn_treatments = findViewById(R.id.btn_treatments);
        btn_spd = findViewById(R.id.btn_spd);

        String data = tv_patient_data.getText().toString() + "\n" + patient.toString();
        tv_patient_data.setText(data);

        btn_treatments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTreatment = new Intent(getApplicationContext(), TreatmentListActivity.class);
                goTreatment.putExtra(Constants.PATIENT,patient);
                startActivity(goTreatment);
                finish();
            }
        });

        btn_spd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goSpd = new Intent(getApplicationContext(), SpdActivity.class);
                goSpd.putExtra(Constants.PATIENT,patient);
                startActivity(goSpd);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), PatientListActivity.class);
        startActivity(intent);
        finish();
    }
}
