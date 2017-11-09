package com.vikram.knowyournation.request;

import com.vikram.knowyournation.dataservice.AppDataClient;
import com.vikram.knowyournation.dataservice.IAppDataAPIs;
import com.vikram.knowyournation.dataservice.RetroResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by M1032130 on 10/26/2017.
 */

public class BasicRequest<T> {

    private final AppDataClient.OnDataReceived mCallBack;
    IAppDataAPIs mClient;
    AppDataClient mService;

    public BasicRequest(AppDataClient.OnDataReceived<T> callback) {
        mClient = AppDataClient.getClient();
        mService = AppDataClient.getService();
        mCallBack = callback;
    }

    public void callService() {
        Call<List<RetroResponse>> call = mClient.getCountries();

        call.enqueue(new Callback<List<RetroResponse>>() {
            @Override
            public void onResponse(Call<List<RetroResponse>> call, Response<List<RetroResponse>> response) {
                mService.onResponse(response, mCallBack);
            }

            @Override
            public void onFailure(Call<List<RetroResponse>> call, Throwable t) {
                mCallBack.onDataFailure(t.getMessage());
            }
        });
    }
}