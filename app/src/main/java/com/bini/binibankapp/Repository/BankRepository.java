package com.bini.binibankapp.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.bini.binibankapp.Database.BankDatabase;
import com.bini.binibankapp.Database.Dao.BankDao;
import com.bini.binibankapp.Model.BankModel;

import java.util.List;

public class BankRepository {

    private BankDao bankDao;
    private LiveData<List<BankModel>> allBank;

    public BankRepository(Application application)
    {
        BankDatabase bankDatabase = BankDatabase.getInstance(application);
        bankDao = bankDatabase.bankDao();

        allBank = bankDao.getAllBank();
    }

    public void insert(BankModel bankModel) {
        new InsertBankAsyncTask(bankDao).execute(bankModel);
    }

    public void update(BankModel bankModel){
        new UpdateBankAsyncTask(bankDao).execute(bankModel);
    }

    public void delete(BankModel bankModel){
        new DeleteBankAsyncTask(bankDao).execute(bankModel);
    }

    public void deleteAllBank() {
        new DeleteAllBankAsyncTask(bankDao).execute();
    }

    public LiveData<List<BankModel>> getAllBank() {
        return allBank;
    }

    //AsyncTask for create new bank
    private static class InsertBankAsyncTask extends AsyncTask<BankModel, Void, Void>{

        private BankDao bankDao;

        public InsertBankAsyncTask(BankDao bankDao) {
            this.bankDao = bankDao;
        }


        @Override
        protected Void doInBackground(BankModel... BankModels) {
            bankDao.insert(BankModels[0]);
            return null;
        }
    }

    //AsyncTask for update existing bank
    private static class UpdateBankAsyncTask extends AsyncTask<BankModel, Void, Void>{

        private BankDao bankDao;

        public UpdateBankAsyncTask(BankDao bankDao) {
            this.bankDao = bankDao;
        }

        @Override
        protected Void doInBackground(BankModel... BankModels) {
            bankDao.update(BankModels[0]);
            return null;
        }
    }

    //AsyncTask for delete existing bank
    private static class DeleteBankAsyncTask extends AsyncTask<BankModel, Void, Void>{

        private BankDao bankDao;

        public DeleteBankAsyncTask(BankDao bankDao){
            this.bankDao = bankDao;
        }

        @Override
        protected Void doInBackground(BankModel... BankModels) {
            bankDao.delete(BankModels[0]);
            return null;
        }
    }

    //AsyncTask for delete all bank
    private static class DeleteAllBankAsyncTask extends AsyncTask<Void, Void, Void>{

        private BankDao bankDao;

        public DeleteAllBankAsyncTask(BankDao bankDao){
            this.bankDao = bankDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bankDao.deleteAllBank();
            return null;
        }
    }



}
