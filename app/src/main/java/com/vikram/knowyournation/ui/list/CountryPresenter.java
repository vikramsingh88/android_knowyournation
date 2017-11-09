package com.vikram.knowyournation.ui.list;

import com.vikram.knowyournation.dataservice.RetroResponse;

import java.util.List;

/**
 * Created by M1032130 on 11/2/2017.
 */

public class CountryPresenter implements ICountryPresenter, ICountryModel.CountryListFetchListener {
    private ICountryView countryView;
    private ICountryModel countryModel;

    CountryPresenter(ICountryView countryView) {
        this.countryView = countryView;
        countryModel = new CountryModel();
    }

    @Override
    public void onSuccess(List<RetroResponse> response) {
        if (countryView != null) {
            countryView.hideProgressBar();
            countryView.onSuccess(response);
        }
    }

    @Override
    public void onFailure(String error) {
        if (countryView != null) {
            countryView.hideProgressBar();
            countryView.onFailure(error);
        }
    }

    @Override
    public void fetchCountries() {
        if (countryView != null) {
            countryView.showProgressBar();
            countryModel.fetchCountries(this);
        }
    }

    @Override
    public void onDestroy() {
        countryView = null;
    }
}
