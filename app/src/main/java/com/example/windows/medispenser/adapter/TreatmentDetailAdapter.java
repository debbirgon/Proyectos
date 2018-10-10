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
import com.example.windows.medispenser.api.ApiClient;
import com.example.windows.medispenser.api.MedService;
import com.example.windows.medispenser.model.Dose;
import com.example.windows.medispenser.model.Med;
import com.example.windows.medispenser.ui.TreatmentDetailActivity;
import com.example.windows.medispenser.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TreatmentDetailAdapter extends RecyclerView.Adapter<TreatmentDetailAdapter.TreatmentDetailViewHolder>{

    private List<Dose> doseList;
    private Context mContext;

    public TreatmentDetailAdapter(List<Dose> doseList, Context mContext) {
        this.doseList = doseList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TreatmentDetailAdapter.TreatmentDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dose,parent,
                false);
        return new TreatmentDetailAdapter.TreatmentDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TreatmentDetailAdapter.TreatmentDetailViewHolder holder, final int position) {
        final Dose dose = doseList.get(position);

        String initHour = mContext.getString(R.string.init_hour)+": " + dose.getHora_inicio();
        holder.init_time.setText(initHour);
        String timesADay = dose.getVeces_dia() + " " + mContext.getString(R.string.times_day);
        holder.times_a_day.setText(timesADay);
        MedService medService = ApiClient.getApiClient().create(MedService.class);
        medService.getMedByID(dose.getId_medicamento()).enqueue(new Callback<Med>() {
            @Override
            public void onResponse(Call<Med> call, Response<Med> response) {
                if(response.code() == 200){
                    holder.med_name.setText(response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<Med> call, Throwable t) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return doseList.size();
    }

    public static class TreatmentDetailViewHolder extends RecyclerView.ViewHolder{

        TextView med_name;
        TextView init_time;
        TextView times_a_day;

        public TreatmentDetailViewHolder(View itemView) {
            super(itemView);

            med_name = itemView.findViewById(R.id.med_name);
            init_time = itemView.findViewById(R.id.init_time);
            times_a_day = itemView.findViewById(R.id.times_a_day);
        }
    }
}
