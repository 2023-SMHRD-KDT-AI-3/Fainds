package com.example.faindsapplication.ContractDetail;

public class ContractDetailVO {
    private int contractId;
    private String contractKey;
    private String contractValue;

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
