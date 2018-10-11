package com.example.windows.medispenser.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadActivity extends AppCompatActivity {

    private TextView tv_level;
    private TextView tv_med_name_load;
    private TextView tv_amount_left;
    private Button btn_add_load;
    private Button btn_delete_load;
    private Patient patient;
    private int level;
    private Load load;
    private LoadService loadService;
    private Map<String,Integer> queryMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        patient = (Patient) getIntent().getExtras().getSerializable(Constants.PATIENT);
        queryMap = new HashMap<>();
        level = getIntent().getExtras().getInt(Constants.LEVEL);
        queryMap.put("id", patient.getId_patient());
        queryMap.put("nivel", level);

        tv_level = findViewById(R.id.tv_level);
        tv_med_name_load = findViewById(R.id.tv_med_name_load);
        tv_amount_left = findViewById(R.id.tv_amount_left);
        btn_add_load = findViewById(R.id.btn_add_load);
        btn_delete_load = findViewById(R.id.btn_delete_load);

        tv_level.setText(getString(R.string.level)+" "+level);

        loadService = ApiClient.getApiClient().create(LoadService.class);
        loadService.getLoadsByLevel(queryMap)
        .enqueue(new Callback<List<Load>>() {
            @Override
            public void onResponse(Call<List<Load>> call, Response<List<Load>> response) {
                if(response.code()==200){
                    List<Load> loadList = response.body();
                    if(loadList.size()==0){
                        btn_add_load.setVisibility(View.VISIBLE);
                        btn_delete_load.setVisibility(View.GONE);
                    }else{
                        btn_add_load.setVisibility(View.GONE);
                        btn_delete_load.setVisibility(View.VISIBLE);
                        load = loadList.get(0);

                        String amount = getString(R.string.amount_lef)+" "+load.getAmount();
                        tv_amount_left.setText(amount);
                        getMed(load);

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Load>> call, Throwable t) {

            }
        });

        btn_delete_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadService.deleteLoad(load.getId()).enqueue(new Callback<ResponseBody>() {
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
        });

        btn_add_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddLoadActivity.class);
                intent.putExtra(Constants.PATIENT, patient);
                intent.putExtra(Constants.LEVEL, level);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getMed(Load load) {
        MedService medService = ApiClient.getApiClient().create(MedService.class);
        medService.getMedByLoad(load.getId()).enqueue(new Callback<Med>() {
            @Override
            public void onResponse(Call<Med> call, Response<Med> response) {
                tv_med_name_load.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<Med> call, Throwable t) {

            }
        });
    }
}
