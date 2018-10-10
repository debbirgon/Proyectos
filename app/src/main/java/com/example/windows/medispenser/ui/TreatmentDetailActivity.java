package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.adapter.TreatmentDetailAdapter;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.DoseService;
import com.example.windows.medispenser.model.Dose;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentDetailActivity extends AppCompatActivity {

    TextView tv_treatment_alias;
    RecyclerView rv_med_list;
    Button btn_add_medication;
    Button btn_delete_treatment;
    Treatment treatment;
    List<Dose> doseList;
    TreatmentDetailAdapter treatmentDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_detail);

        treatment = (Treatment) getIntent().getExtras().getSerializable(Constants.TREATMENT);
        doseList = new ArrayList<>();

        tv_treatment_alias = findViewById(R.id.tv_treatment_alias);
        rv_med_list = findViewById(R.id.rv_med_list);
        btn_add_medication = findViewById(R.id.btn_add_medication);
        btn_delete_treatment = findViewById(R.id.btn_delete_treatment);

        tv_treatment_alias.setText(treatment.getName());

        rv_med_list.setHasFixedSize(true);
        rv_med_list.setLayoutManager(new LinearLayoutManager(this));

        DoseService doseService = ApiClient.getApiClient().create(DoseService.class);
        doseService.getListDoseByTreatment(treatment.getId()).enqueue(new Callback<List<Dose>>() {
            @Override
            public void onResponse(Call<List<Dose>> call, Response<List<Dose>> response) {
                if(response.code() == 200){
                    doseList = response.body();
                    treatmentDetailAdapter = new TreatmentDetailAdapter(doseList,getApplicationContext());
                    rv_med_list.setAdapter(treatmentDetailAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<Dose>> call, Throwable t) {

            }
        });

        btn_add_medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddMedActivity.class);
                intent.putExtra(Constants.TREATMENT, treatment);
                startActivity(intent);
                finish();
            }
        });
    }
}
