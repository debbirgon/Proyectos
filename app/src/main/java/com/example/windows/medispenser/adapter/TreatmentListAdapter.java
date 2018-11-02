package com.example.windows.medispenser.adapter;

import android.app.Activity;
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
import com.example.windows.medispenser.api.DoseService;
import com.example.windows.medispenser.model.Dose;
import com.example.windows.medispenser.model.Patient;
import com.example.windows.medispenser.model.Treatment;
import com.example.windows.medispenser.ui.TreatmentDetailActivity;
import com.example.windows.medispenser.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TreatmentListAdapter extends RecyclerView.Adapter<TreatmentListAdapter.TreatmentListViewHolder>{

    private List<Treatment> treatmentList;
    private Context mContext;
    private Patient patient;
    private Finish finish;

    public TreatmentListAdapter(List<Treatment> treatmentList, Context mContext, Patient patient, Finish finish) {
        this.treatmentList = treatmentList;
        this.mContext = mContext;
        this.patient = patient;
        this.finish = finish;
    }

    @NonNull
    @Override
    public TreatmentListAdapter.TreatmentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_treatment,parent,
                false);
        return new TreatmentListAdapter.TreatmentListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TreatmentListAdapter.TreatmentListViewHolder holder, final int position) {
        final Treatment treatment = treatmentList.get(position);

        DoseService doseService = ApiClient.getApiClient().create(DoseService.class);
        doseService.getListDoseByTreatment(treatment.getId()).enqueue(new Callback<List<Dose>>() {
            @Override
            public void onResponse(Call<List<Dose>> call, Response<List<Dose>> response) {
                if(response.code()==200){
                    int amount = response.body().size();
                    String sAmount;
                    if(amount != 1) {
                        sAmount = amount +" " +mContext.getString(R.string.meds);
                    }else{
                        sAmount = amount + " " +mContext.getString(R.string.med);
                    }
                    String name;
                    String[] nameSplit = treatment.getName().split("-");
                    if(nameSplit.length>1){
                        name = nameSplit[1].trim();
                    }else{
                        name = nameSplit[0].trim();
                    }
                    treatment.setName(name);
                    holder.tv_treatment_name.setText(name);
                    holder.tv_amount_of_meds.setText(sAmount);
                }
            }

            @Override
            public void onFailure(Call<List<Dose>> call, Throwable t) {

            }
        });

        holder.ll_treatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, TreatmentDetailActivity.class);
                intent.putExtra(Constants.TREATMENT,treatment);
                intent.putExtra(Constants.PATIENT,patient);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
                finish.onFinish();


            }
        });
    }

    @Override
    public int getItemCount() {
        return treatmentList.size();
    }

    public static class TreatmentListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_treatment_name;
        TextView tv_amount_of_meds;
        LinearLayout ll_treatment;

        public TreatmentListViewHolder(View itemView) {
            super(itemView);

            tv_treatment_name = itemView.findViewById(R.id.tv_treatment_name);
            tv_amount_of_meds = itemView.findViewById(R.id.tv_amount_of_meds);
            ll_treatment = itemView.findViewById(R.id.ll_treatment);
        }
    }

    public interface Finish{
        void onFinish();
    }
}
