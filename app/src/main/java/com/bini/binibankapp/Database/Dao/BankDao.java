package com.bini.binibankapp.Database.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bini.binibankapp.Model.BankModel;

import java.util.List;

@Dao
public interface BankDao {

    //Insert new BankModel in table
    @Insert
    void insert(BankModel bankModel);

    //Update single BankModel in table
    @Update
    void update(BankModel bankModel);

    //Delete single BankModel from table
    @Delete
    void delete(BankModel bankModel);

    //Delete all bank from table
    @Query("DELETE FROM bank_table")
    void deleteAllBank();

    //Get all the list of bank from table by descending order
    @Query("SELECT * FROM bank_table ")
    LiveData<List<BankModel>> getAllBank();

}
