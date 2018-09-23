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

import com.example.windows.medispenser.R;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.ui.SpdActivity;
import com.example.windows.medispenser.util.Constants;

import java.util.List;

/**
 * Created by windows on 19/09/2018.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.PatientListViewHolder> {

    private List<Patient> patientList;
    private Context mContext;

    public PatientListAdapter(List<Patient> patientList, Context mContext) {
        this.patientList = patientList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PatientListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patient,parent,
                false);
        return new PatientListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientListViewHolder holder, final int position) {
        final Patient patient = patientList.get(position);
        String dni = holder.dni.getText().toString()+ " " + patient.getDni();
        String birthday = holder.birthday.getText().toString()+ "\n" + patient.getBirthday();

        holder.patient_name.setText(patient.getName());
        holder.dni.setText(dni);
        holder.birthday.setText(birthday);

        holder.ll_patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SpdActivity.class);
                intent.putExtra(Constants.PATIENT,patient);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    public static class PatientListViewHolder extends RecyclerView.ViewHolder{

        TextView patient_name;
        TextView dni;
        TextView birthday;
        LinearLayout ll_patient;

        public PatientListViewHolder(View itemView) {
            super(itemView);

            patient_name = itemView.findViewById(R.id.patient_name);
            dni = itemView.findViewById(R.id.dni);
            birthday = itemView.findViewById(R.id.birthday);
            ll_patient = itemView.findViewById(R.id.ll_patient);
        }
    }

}
