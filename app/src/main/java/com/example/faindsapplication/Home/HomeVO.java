package com.example.faindsapplication.Home;

public class HomeVO {
    private int contractId;
    private String contractName;
    private String contractType;
    private int contractIcon;

    public HomeVO(int contractId, String contractName, String contractType, int contractIcon) {
        this.contractId = contractId;
        this.contractName = contractName;
        this.contractType = contractType;
        this.contractIcon = contractIcon;
    }

    public HomeVO() {
    }

    public int getContractId() {
        return contractId;
    }

    public String getContractName() {
        return contractName;
    }

    public String getContractType() {
        return contractType;
    }

    public int getContractIcon() {
        return contractIcon;
    }
}
