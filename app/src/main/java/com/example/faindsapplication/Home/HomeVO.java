package com.example.faindsapplication.Home;

public class HomeVO {
    private int contractId; // 계약서 id
    private String contractName; // 계약서 이름
    private String contractType; // 계약서 종류
    private int contractIcon; // 계약서 아이콘 이미지

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
