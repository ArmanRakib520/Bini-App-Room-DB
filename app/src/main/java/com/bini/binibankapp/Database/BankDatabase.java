package com.bini.binibankapp.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bini.binibankapp.Database.Dao.BankDao;
import com.bini.binibankapp.Model.BankModel;

@Database(entities = {BankModel.class},version = 1)
public abstract class BankDatabase extends RoomDatabase {

    private static BankDatabase instance;

    public abstract BankDao bankDao();

    public static synchronized BankDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            //If instance is null that's mean database is not created and create a new database instance
            instance = Room.databaseBuilder(context.getApplicationContext(), BankDatabase.class,"bank_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
//            new PopulateDbAsyncTask(instance).execute();
        }
    };

}
