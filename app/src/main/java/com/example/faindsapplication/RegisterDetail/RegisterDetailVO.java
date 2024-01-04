package com.example.faindsapplication.RegisterDetail;

public class RegisterDetailVO {
    private int RegisterDetailId; // 계약서 상세 id
    private String RegisterDetailKey; // parsing한 계약서 상세 key값
    private String RegisterDetailValue; // parsing한 계약서 상세 value값

    public RegisterDetailVO(int registerDetailId, String registerDetailKey, String registerDetailValue) {
        RegisterDetailId = registerDetailId;
        RegisterDetailKey = registerDetailKey;
        RegisterDetailValue = registerDetailValue;
    }

    public RegisterDetailVO() {
    }

    public int getRegisterDetailId() {
        return RegisterDetailId;
    }

    public String getRegisterDetailKey() {
        return RegisterDetailKey;
    }

    public String getRegisterDetailValue() {
        return RegisterDetailValue;
    }
}
