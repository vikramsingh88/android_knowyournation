package com.vikram.knowyournation.ui.list;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.vikram.knowyournation.R;
import com.vikram.knowyournation.dataservice.RetroResponse;
import com.vikram.knowyournation.ui.details.DetailsActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ICountryView {
    private ICountryPresenter countryPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private CountryListAdapter mAdapter;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.country_list);
        mRecyclerView.setHasFixedSize(true);

        countryPresenter = new CountryPresenter(this);
        if (isOnline()) {
            countryPresenter.fetchCountries();
        } else {
            Toast.makeText(this, R.string.offline_msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isOnline()) {
            List<RetroResponse>  response = deserialize();
            if (response != null) {
                manageList(response);
            }
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    public void showProgressBar() {
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.fetching_countries));
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    //Serialize object
    private void serialize(List<RetroResponse> response) {
        try {
            FileOutputStream f = new FileOutputStream(new File(getFilesDir(),"")+File.separator+"country_data.txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(response);
            o.close();
            f.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        }
    }
    //Deserialize object
    private List<RetroResponse> deserialize() {
        List<RetroResponse> response = null;
        try {
            FileInputStream fi = new FileInputStream(new File(getFilesDir(),"")+File.separator+"country_data.txt");
            ObjectInputStream oi = new ObjectInputStream(fi);
            response = (List<RetroResponse>) oi.readObject();
            oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error initializing stream");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void onSuccess(final List<RetroResponse> response) {
        //Serializable country response to file
        if(response != null) {
            serialize(response);
            manageList(response);
        }
    }

    private void manageList(List<RetroResponse> response) {
        mAdapter = new CountryListAdapter(MainActivity.this, response, new CountryListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final RetroResponse item) {
                Intent intentDetailsActivity = new Intent(MainActivity.this, DetailsActivity.class);
                intentDetailsActivity.putExtra("flag", item.getFlag());
                intentDetailsActivity.putExtra("countryName", item.getName());
                intentDetailsActivity.putExtra("topLevelDomain", item.getTopLevelDomain());
                intentDetailsActivity.putExtra("alpha2Code", item.getAlpha2Code());
                intentDetailsActivity.putExtra("alpha3Code", item.getAlpha3Code());
                intentDetailsActivity.putExtra("callingCodes", item.getCallingCodes());
                intentDetailsActivity.putExtra("capital", item.getCapital());
                intentDetailsActivity.putExtra("altSpellings", item.getAltSpellings());
                intentDetailsActivity.putExtra("region", item.getRegion());
                intentDetailsActivity.putExtra("subregion", item.getSubregion());
                intentDetailsActivity.putExtra("population", item.getPopulation());
                intentDetailsActivity.putExtra("latlng", item.getLatlng());
                intentDetailsActivity.putExtra("demonym", item.getDemonym());
                intentDetailsActivity.putExtra("area", item.getArea());
                intentDetailsActivity.putExtra("gini", item.getGini());
                intentDetailsActivity.putExtra("timezones", item.getTimezones());
                intentDetailsActivity.putExtra("borders", item.getBorders());
                intentDetailsActivity.putExtra("nativeName", item.getNativeName());
                intentDetailsActivity.putExtra("numericCode", item.getNumericCode());
                intentDetailsActivity.putExtra("cioc", item.getCioc());

                intentDetailsActivity.putExtra("currencies", item.getCurrencies()); //array
                intentDetailsActivity.putExtra("languages", item.getLanguages()); //array
                intentDetailsActivity.putExtra("translations", item.getTranslations());
                intentDetailsActivity.putExtra("regionalBlocs", item.getRegionalBlocs()); //array

                startActivity(intentDetailsActivity);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void onFailure(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countryPresenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mAdapter != null) {
                    mAdapter.filter(newText);
                }

                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
