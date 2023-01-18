package com.bini.binibankapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bini.binibankapp.Model.BankModel;
import com.bini.binibankapp.R;

import java.util.ArrayList;
import java.util.List;


public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.RecyclerViewHolder> {

    private List<BankModel> BankModels = new ArrayList<>();
    private OnBankItemClickListener onBankItemClickListener;

    public void setBankModel(List<BankModel> BankModels) {
        this.BankModels = BankModels;
        notifyDataSetChanged();
    }

    public void setItemOnClick(OnBankItemClickListener onBankItemClickListener){
        this.onBankItemClickListener = onBankItemClickListener;
    }

    public BankModel getBankModel(int position)
    {
        BankModel bankModel = BankModels.get(position);
        bankModel.setId(BankModels.get(position).getId());
        return bankModel;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_list,null);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view, onBankItemClickListener);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        BankModel currentBankModel = BankModels.get(position);
        holder.bankName.setText("Bank Name: "+ currentBankModel.getBankName());
        holder.branchName.setText("Branch Name: " + currentBankModel.getRoutingNumber());
        holder.routingNumber.setText("Routing Number: " + currentBankModel.getBranchName());

    }

    @Override
    public int getItemCount() {
        return BankModels.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView bankName;
        private TextView branchName;
        private TextView routingNumber;
        private OnBankItemClickListener bankItemClickListener;

        public RecyclerViewHolder(@NonNull View itemView, OnBankItemClickListener onBankItemClickListener) {
            super(itemView);
            this.bankItemClickListener = onBankItemClickListener;
            itemView.setOnClickListener(this);
            bankName = itemView.findViewById(R.id.tv_name);
            branchName = itemView.findViewById(R.id.tv_age);
            routingNumber = itemView.findViewById(R.id.tv_position);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            BankModel currentBankModel = BankModels.get(position);
            BankModel bankModel = new BankModel(currentBankModel.getBankName(), currentBankModel.getBranchName(), currentBankModel.getRoutingNumber());
            bankModel.setId(currentBankModel.getId());
            bankItemClickListener.onItemClick(bankModel);
        }
    }

    public interface OnBankItemClickListener {
        void onItemClick(BankModel bankModel);
    }
}
