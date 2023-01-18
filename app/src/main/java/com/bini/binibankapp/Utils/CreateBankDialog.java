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


public class CreateBankDialog extends AppCompatDialogFragment {

    private EditText bankName;
    private EditText branchName;
    private EditText routingNumber;
    private Button mSaveBtn;
    private CreateBankListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_create_update_dialog,null);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Add New Bank");

        bankName = view.findViewById(R.id.et_bank_name);
        branchName = view.findViewById(R.id.et_branch_name);
        routingNumber = view.findViewById(R.id.et_routing_number);
        mSaveBtn = view.findViewById(R.id.btn_save);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bankName.getText().toString();
                String branch = branchName.getText().toString();
                String routing = routingNumber.getText().toString();

                if(name.isEmpty()||branch.isEmpty()||routing.isEmpty()) {
                    return;
                }
                else {
                    BankModel bankModel = new BankModel(name,routing,branch);
                    mListener.saveNewBank(bankModel);
                    dismiss();
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateBankListener)context;
    }

    public interface CreateBankListener {
        void saveNewBank(BankModel bankModel);
    }
}
