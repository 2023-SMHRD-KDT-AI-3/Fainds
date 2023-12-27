package com.example.faindsapplication.RegisterDetail;

public class RegisterDetailVO {
    private int RegisterDetailId;
    private String RegisterDetailKey;
    private String RegisterDetailValue;

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
