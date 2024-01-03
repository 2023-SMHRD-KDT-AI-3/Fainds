package com.example.faindsapplication.Register;

public class RegisterVO {
    private String contractName; // 계약서 이름
    private String contractExample; // 계약서 사용자 예시
    private int contractImg; // 계약서 이미지

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
