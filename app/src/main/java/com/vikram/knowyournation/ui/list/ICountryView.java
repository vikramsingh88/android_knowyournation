package com.vikram.knowyournation.ui.list;

import com.vikram.knowyournation.dataservice.RetroResponse;

import java.util.List;

/**
 * Created by M1032130 on 11/2/2017.
 */

public interface ICountryView {
    void showProgressBar();
    void hideProgressBar();
    void onSuccess(List<RetroResponse> response);
    void onFailure(String error);
}
