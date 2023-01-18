package com.bini.binibankapp.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.bini.binibankapp.Model.BankModel;
import com.bini.binibankapp.R;


public class UpdateBankDialog extends AppCompatDialogFragment {

    private EditText bankName;
    private EditText branchName;
    private EditText routingNumber;
    private Button mSaveBtn;
    private UpdateBankListener mListener;
    private BankModel bankModel;

    public void setBank(BankModel bankModel) {
        this.bankModel = bankModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_create_update_dialog,null);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Update Bank");

        bankName = view.findViewById(R.id.et_bank_name);
        branchName = view.findViewById(R.id.et_branch_name);
        routingNumber = view.findViewById(R.id.et_routing_number);
        mSaveBtn = view.findViewById(R.id.btn_save);

        bankName.setText(bankModel.getBankName());
        branchName.setText(bankModel.getRoutingNumber());
        routingNumber.setText(bankModel.getBranchName());

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bankName.getText().toString();
                String age = branchName.getText().toString();
                String position = routingNumber.getText().toString();

                if(name.isEmpty()||age.isEmpty()||position.isEmpty()) {
                    return;
                }
                else {
                    BankModel currentBankModel = new BankModel(name,position,age);
                    currentBankModel.setId(bankModel.getId());
                    mListener.updateBank(currentBankModel);
                    dismiss();
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (UpdateBankListener)context;
    }

    public interface UpdateBankListener {
        void updateBank(BankModel bankModel);
    }
}
