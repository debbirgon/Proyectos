package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.adapter.PatientListAdapter;
import com.example.windows.medispenser.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    private RecyclerView rv_patient_list;
    private PatientListAdapter patientListAdapter;
    private List<Patient> patientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_patient_list);
        rv_patient_list = (RecyclerView) findViewById(R.id.rv_patient_list);
        patientList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            patientList.add(new Patient("Paciente " + i, "DeEneI " + i,
                    i + "" + i + "/" + i + "" + i + "/" + i + "" + i + "" + i + "" + i));
        }


        rv_patient_list.setHasFixedSize(true);

        // use a linear layout manager
        rv_patient_list.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter (see also next example)
        patientListAdapter = new PatientListAdapter(patientList,this);
        rv_patient_list.setAdapter(patientListAdapter);

    }
}