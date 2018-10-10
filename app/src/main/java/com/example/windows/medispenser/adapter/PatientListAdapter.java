package com.example.windows.medispenser.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.PersonService;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Person;
import com.example.windows.medispenser.ui.MenuActivity;
import com.example.windows.medispenser.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by windows on 19/09/2018.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {

    private List<Patient> patientList;
    private Context mContext;
    private Finish finish;

    public PatientListAdapter(List<Patient> patientList, Context mContext, Finish finish) {
        this.patientList = patientList;
        this.mContext = mContext;
        this.finish = finish;
    }

    @NonNull
    @Override
    public PatientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient,parent,
                false);
        return new PatientListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientListViewHolder holder, final int position) {
        final Patient patient = patientList.get(position);

        PersonService personService = ApiClient.getApiClient().create(PersonService.class);
        personService.getPerson(patient.getId_person()).enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                if(response.code() == 200){
                    patient.setName(response.body().getName());
                    patient.setSurname(response.body().getSurname());
                    patient.setBirthday(response.body().getBirthday());
                    patient.setSex(response.body().getSex());

                    String birthday = holder.birthday.getText().toString()+ ": " + patient.getBirthday();

                    holder.patient_name.setText(patient.getName() + " " + patient.getSurname());
                    holder.birthday.setText(birthday);
                    holder.sex.setText(String.valueOf(patient.getSex()));
                }else{
                    Toast.makeText(mContext,"Ha habido un error",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Toast.makeText(mContext,"onFailure",
                        Toast.LENGTH_SHORT).show();

            }
        });

        holder.ll_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                intent.putExtra(Constants.PATIENT,patient);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                finish.onFinish();
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class PatientListViewHolder extends RecyclerView.ViewHolder{

        TextView patient_name;
        TextView birthday;
        LinearLayout ll_patient;
        TextView sex;

        public PatientListViewHolder(View itemView) {
            super(itemView);

            patient_name = itemView.findViewById(R.id.patient_name);
            birthday = itemView.findViewById(R.id.birthday);
            ll_patient = itemView.findViewById(R.id.ll_patient);
            sex = itemView.findViewById(R.id.sex);
        }
    }

    public interface Finish{
        void onFinish();
    }

}
