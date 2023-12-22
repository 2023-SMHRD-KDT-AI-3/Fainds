package com.example.faindsapplication.Register;

public class RegisterVO {
    private String contractName;
    private String contractExample;
    private int contractImg;

    public RegisterVO(String contractName, String contractExample, int contractImg) {
        this.contractName = contractName;
        this.contractExample = contractExample;
        this.contractImg = contractImg;
    }

    public String getContractName() {
        return contractName;
    }

    public String getContractExample() {
        return contractExample;
    }

    public int getContractImg() {
        return contractImg;
    }
}
