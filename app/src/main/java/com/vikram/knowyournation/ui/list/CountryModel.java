package com.vikram.knowyournation.ui.list;

import com.vikram.knowyournation.dataservice.AppDataClient;
import com.vikram.knowyournation.dataservice.RetroResponse;
import com.vikram.knowyournation.request.BasicRequest;

import java.util.List;

/**
 * Created by M1032130 on 11/2/2017.
 */

public class CountryModel implements ICountryModel {

    @Override
    public void fetchCountries(final CountryListFetchListener listener) {
        new BasicRequest(new AppDataClient
                .OnDataReceived<List<RetroResponse>>() {

            @Override
            public void onDataSuccess(List<RetroResponse> response) {
                listener.onSuccess(response);
            }

            @Override
            public void onDataFailure(String error) {
                listener.onFailure(error);
            }
        }).callService();
    }
}
