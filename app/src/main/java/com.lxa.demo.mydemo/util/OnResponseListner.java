package com.lxa.demo.mydemo.util;

import org.json.JSONException;

public interface  OnResponseListner {
    void onSucess(String response) throws JSONException;
    void onError(String error);
}
