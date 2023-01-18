package com.bini.binibankapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bank_table")
public class BankModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String bankName;

    private String branchName;

    private String routingNumber;

    public BankModel(String bankName, String branchName, String routingNumber) {
        this.bankName = bankName;
        this.branchName = branchName;
        this.routingNumber = routingNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }
}
