package com.bini.binibankapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bini.binibankapp.Model.BankModel;
import com.bini.binibankapp.Repository.BankRepository;

import java.util.List;

public class BankViewModel extends AndroidViewModel {

    private BankRepository repository;
    private LiveData<List<BankModel>> allBank;

    public BankViewModel(@NonNull Application application) {
        super(application);

        repository = new BankRepository(application);
        allBank = repository.getAllBank();
    }

    public void insert(BankModel bankModel){
        repository.insert(bankModel);
    }

    public void update(BankModel bankModel){
        repository.update(bankModel);
    }

    public void delete(BankModel bankModel){
        repository.delete(bankModel);
    }

    public void deleteAllBank(){
        repository.deleteAllBank();
    }

    public LiveData<List<BankModel>> getAllBank(){
        return allBank;
    }
}
