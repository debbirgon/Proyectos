package com.example.windows.medispenser.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.adapter.TreatmentDetailAdapter;
import com.example.windows.medispenser.adapter.TreatmentListAdapter;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.DoseService;
import com.example.windows.medispenser.api.TreatmentService;
import com.example.windows.medispenser.model.Dose;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentDetailActivity extends AppCompatActivity implements TreatmentDetailAdapter.OnDeleteDose{

    TextView tv_treatment_alias;
    RecyclerView rv_med_list;
    Button btn_add_medication;
    Button btn_delete_treatment;
    Treatment treatment;
    List<Dose> doseList;
    TreatmentDetailAdapter treatmentDetailAdapter;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment_detail);

        treatment = (Treatment) getIntent().getExtras().getSerializable(Constants.TREATMENT);
        patient = (Patient) getIntent().getExtras().getSerializable(Constants.PATIENT);
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
                    treatmentDetailAdapter = new TreatmentDetailAdapter(doseList,
                            getApplicationContext(), TreatmentDetailActivity.this);
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
                intent.putExtra(Constants.PATIENT,patient);
                startActivity(intent);
                finish();
            }
        });

        btn_delete_treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(TreatmentDetailActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
                alertBuilder.setMessage((getString(R.string.delete)+" "+
                        getString(R.string.all_treatment)+" "+ treatment.getName())+"?")
                        .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TreatmentService treatmentService = ApiClient.getApiClient().create(TreatmentService.class);
                                treatmentService.deleteTreatment(treatment.getId()).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if(response.code() == 200){
                                            Toast.makeText(getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
                                            onBackPressed();
                                        }else{
                                            Toast.makeText(getApplicationContext(),"Error onResponse", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Toast.makeText(getApplicationContext(),"Error onFailure", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton(getString(R.string.cancel), null);
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public void onDeleteDoseClicked(final Dose dose) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this, android.R.style
        .Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        alertBuilder.setMessage((getString(R.string.delete)+" "+ dose.getNombre_medicamento())+"?")
        .setPositiveButton(getString(R.string.accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DoseService doseService = ApiClient.getApiClient().create(DoseService.class);
                doseService.deleteDose(dose.getId()).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code()== 200){
                            Toast.makeText(getApplicationContext(),"Eliminado", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


            }
        }).setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), TreatmentListActivity.class);
        intent.putExtra(Constants.PATIENT,patient);
        startActivity(intent);
        finish();
    }
}
