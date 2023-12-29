package com.example.faindsapplication;

import org.json.JSONObject;

public interface FlaskResponseListener {
    void onResponse(JSONObject response);
    void onError(String error);
}
