package com.bini.binibankapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bini.binibankapp.Adapter.BankListAdapter;
import com.bini.binibankapp.Model.BankModel;
import com.bini.binibankapp.R;
import com.bini.binibankapp.Utils.CreateBankDialog;
import com.bini.binibankapp.Utils.UpdateBankDialog;
import com.bini.binibankapp.ViewModel.BankViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CreateBankDialog.CreateBankListener,
        BankListAdapter.OnBankItemClickListener,
        UpdateBankDialog.UpdateBankListener {

    private BankViewModel bankViewModel;
    private RecyclerView mRecyclerView;
    private TextView noBankFound;
    private BankListAdapter bankListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intView();

        //Observe BankModel add
        bankViewModel = ViewModelProviders.of(this).get(BankViewModel.class);
        bankViewModel.getAllBank().observe(this, new Observer<List<BankModel>>() {
            @Override
            public void onChanged(List<BankModel> bankModels) {
                bankListAdapter.setBankModel(bankModels);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                bankViewModel.delete(bankListAdapter.getBankModel(viewHolder.getAdapterPosition()));
                snackBar("BankModel Deleted");
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    private void intView() {

        FloatingActionButton fab = findViewById(R.id.fab);
        mRecyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        //Set adapter to RecyclerView
        bankListAdapter = new BankListAdapter();
        bankListAdapter.setItemOnClick(this);
        mRecyclerView.setAdapter(bankListAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateBankDialog();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_allBank:
                bankViewModel.deleteAllBank();
                snackBar("All Bank Deleted");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openCreateBankDialog() {
        CreateBankDialog createBankDialog = new CreateBankDialog();
        createBankDialog.show(getSupportFragmentManager(),"create Bank");
    }

    @Override
    public void saveNewBank(BankModel bankModel) {
        bankViewModel.insert(bankModel);
        snackBar("Bank Saved");
    }

    public void snackBar(String message) {
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(BankModel bankModel) {
        Log.d("MainActivity_Log",""+ bankModel.getId());
        openUpdateBankDialog(bankModel);
    }

    private void openUpdateBankDialog(BankModel bankModel) {
        UpdateBankDialog updateBankDialog = new UpdateBankDialog();
        updateBankDialog.setBank(bankModel);
        updateBankDialog.show(getSupportFragmentManager(),"Update Bank");
    }

    @Override
    public void updateBank(BankModel bankModel) {
        bankViewModel.update(bankModel);
        snackBar("Bank Updated");
    }
}
