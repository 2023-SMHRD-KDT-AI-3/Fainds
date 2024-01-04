package com.example.faindsapplication.ContractDetail;

public class ContractDetailVO {
    private int contractId; // 계약서 아이디
    private String contractKey; // parsing 한계약서 내용 key값
    private String contractValue; // parsing한 계약서 내용 value값

    public ContractDetailVO(int contractId, String contractKey, String contractValue) {
        this.contractId = contractId;
        this.contractKey = contractKey;
        this.contractValue = contractValue;
    }

    public ContractDetailVO() {
    }

    public int getContractId() {
        return contractId;
    }

    public String getContractKey() {
        return contractKey;
    }

    public String getContractValue() {
        return contractValue;
    }
}
