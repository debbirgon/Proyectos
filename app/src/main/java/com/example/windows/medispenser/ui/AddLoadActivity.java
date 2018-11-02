package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.LoadService;
import com.example.windows.medispenser.api.MedService;
import com.example.windows.medispenser.model.Load;
import com.example.windows.medispenser.model.Med;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.util.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddLoadActivity extends AppCompatActivity {

    private TextView tv_level_addLoad;
    private Spinner spinner_meds;
    private EditText et_amount;
    private Button btn_load;
    private Patient patient;
    private List<Med> medList;
    private List<String> stringMeds;
    private Load load;
    private int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_load);

        medList = new ArrayList<>();
        stringMeds = new ArrayList<>();
        patient = (Patient) getIntent().getExtras().getSerializable(Constants.PATIENT);
        level = getIntent().getExtras().getInt(Constants.LEVEL);
        load = new Load();
        load.setId_patient(patient.getId_patient());
        load.setLevel(level);
        load.setId_spd(2);

        tv_level_addLoad = findViewById(R.id.tv_level_addLoad);
        spinner_meds = findViewById(R.id.spinner_meds);
        et_amount = findViewById(R.id.et_amount);
        btn_load = findViewById(R.id.btn_load);

        tv_level_addLoad.setText(getString(R.string.level)+" "+1);

        MedService medService = ApiClient.getApiClient().create(MedService.class);
        medService.getListMed(patient.getId_patient()).enqueue(new Callback<List<Med>>() {
            @Override
            public void onResponse(Call<List<Med>> call, Response<List<Med>> response) {
                if(response.code()== 200){
                    if(response.body().size()==0){
                        stringMeds.add(getString(R.string.no_med));
                        setAdapter();
                    }else{
                        stringMeds.add(getString(R.string.select));
                        medList = response.body();
                        for(Med m: medList){
                            stringMeds.add(m.getName());
                            setAdapter();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Med>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"onFailure",
                        Toast.LENGTH_SHORT).show();


            }
        });

        spinner_meds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                if(position!=0) {
                    load.setId_med(medList.get(position-1).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = et_amount.getText().toString();
                if(amount== null||amount.equals("")||load.getId_med()==null){
                    Toast.makeText(getApplicationContext(),getString(R.string.must_fill),
                            Toast.LENGTH_SHORT).show();
                }else if(Integer.valueOf(amount)<1 ||Integer.valueOf(amount)>7){
                    Toast.makeText(getApplicationContext(),getString(R.string.wrong_number),
                            Toast.LENGTH_SHORT).show();

                }else{
                    load.setAmount(Integer.valueOf(amount));
                    LoadService loadService = ApiClient.getApiClient().create(LoadService.class);
                    loadService.postLoad(load).enqueue(new Callback<Load>() {
                        @Override
                        public void onResponse(Call<Load> call, Response<Load> response) {
                            if(response.code()==200){
                                Intent intent = new Intent(getApplicationContext(), LoadActivity.class);
                                intent.putExtra(Constants.PATIENT,patient);
                                intent.putExtra(Constants.LEVEL,level);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),"Conflict",
                                        Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Load> call, Throwable t) {

                            if(t.getMessage()
                                    .equals("java.lang.IllegalStateException: " +
                                            "Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
                                Intent intent = new Intent(getApplicationContext(), LoadActivity.class);
                                intent.putExtra(Constants.PATIENT,patient);
                                intent.putExtra(Constants.LEVEL,level);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });
                }
            }
        });
    }

    public void setAdapter(){
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout
                .simple_spinner_dropdown_item,stringMeds);
        spinner_meds.setAdapter(spinnerAdapter);
    }
}
