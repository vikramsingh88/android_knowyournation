package com.vikram.knowyournation.ui.details;

import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.vikram.knowyournation.R;
import com.vikram.knowyournation.dataservice.RetroResponse;
import com.vikram.knowyournation.util.SvgDecoder;
import com.vikram.knowyournation.util.SvgDrawableTranscoder;
import com.vikram.knowyournation.util.SvgSoftwareLayerSetter;
import com.vikram.knowyournation.util.data.Currencies;
import com.vikram.knowyournation.util.data.Languages;
import com.vikram.knowyournation.util.data.RegionalBlocs;
import com.vikram.knowyournation.util.data.Translations;

import java.io.InputStream;

public class DetailsActivity extends AppCompatActivity {
    private ImageView mImageFlag;
    private TextView mTextViewCName;
    private TextView mTextViewCapital;
    private TextView mTextViewTopLevelDomain;
    private TextView mTextViewAlpha2Code;
    private TextView mTextViewAlpha3Code;
    private TextView mTextViewCallingCodes;
    private TextView mTextViewAltSpellings;
    private TextView mTextViewRegion;
    private TextView mTextViewSubregion;
    private TextView mTextViewPopulation;
    private TextView mTextViewLatlng;
    private TextView mTextViewDemonym;
    private TextView mTextViewArea;
    private TextView mTextViewGini;
    private TextView mTextViewTimezones;
    private TextView mTextViewBorders;
    private TextView mTextViewNativeName;
    private TextView mTextViewNumericCode;
    private TextView mTextViewCurrencies;
    private TextView mTextViewLanguages;
    private TextView mTextViewTranslations;
    private TextView mTextViewRegionalBlocs;
    private TextView mTextViewCioc;

    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mImageFlag = (ImageView) findViewById(R.id.image_paralax);
        mTextViewCName = (TextView) findViewById(R.id.txt_cname);
        mTextViewCapital = (TextView) findViewById(R.id.txt_capital);
        mTextViewTopLevelDomain = (TextView) findViewById(R.id.txt_topLevelDomain);
        mTextViewAlpha2Code = (TextView) findViewById(R.id.txt_alpha2Code);
        mTextViewAlpha3Code = (TextView) findViewById(R.id.txt_alpha3Code);
        mTextViewCallingCodes = (TextView) findViewById(R.id.txt_callingCodes);
        mTextViewAltSpellings = (TextView) findViewById(R.id.txt_altSpellings);
        mTextViewRegion = (TextView) findViewById(R.id.txt_region);
        mTextViewSubregion = (TextView) findViewById(R.id.txt_subregion);
        mTextViewPopulation = (TextView) findViewById(R.id.txt_population);
        mTextViewLatlng = (TextView) findViewById(R.id.txt_latlng);
        mTextViewDemonym = (TextView) findViewById(R.id.txt_demonym);
        mTextViewArea = (TextView) findViewById(R.id.txt_area);
        mTextViewGini = (TextView) findViewById(R.id.txt_gini);
        mTextViewTimezones = (TextView) findViewById(R.id.txt_timezones);
        mTextViewBorders = (TextView) findViewById(R.id.txt_borders);
        mTextViewNativeName = (TextView) findViewById(R.id.txt_nativeName);
        mTextViewNumericCode = (TextView) findViewById(R.id.txt_numericCode);
        mTextViewCurrencies = (TextView) findViewById(R.id.txt_currencies);
        mTextViewLanguages = (TextView) findViewById(R.id.txt_languages);
        mTextViewTranslations = (TextView) findViewById(R.id.txt_translations);
        mTextViewRegionalBlocs = (TextView) findViewById(R.id.txt_regionalBlocs);
        mTextViewCioc = (TextView) findViewById(R.id.txt_cioc);

        Intent intent = getIntent();
        String flag = intent.getStringExtra("flag");
        String countryName = intent.getStringExtra("countryName");
        String topLevelDomain [] = intent.getStringArrayExtra("topLevelDomain");
        String alpha2Code = intent.getStringExtra("alpha2Code");
        String alpha3Code = intent.getStringExtra("alpha3Code");
        String callingCodes [] = intent.getStringArrayExtra("callingCodes");
        String capital = intent.getStringExtra("capital");
        String altSpellings [] = intent.getStringArrayExtra("altSpellings");
        String region = intent.getStringExtra("region");
        String subregion = intent.getStringExtra("subregion");
        String population = intent.getStringExtra("population");
        String latlng [] = intent.getStringArrayExtra("latlng");
        String demonym = intent.getStringExtra("demonym");
        String area = intent.getStringExtra("area");
        String gini = intent.getStringExtra("gini");
        String timezones [] = intent.getStringArrayExtra("timezones");
        String borders [] = intent.getStringArrayExtra("borders");
        String nativeName = intent.getStringExtra("nativeName");
        String numericCode = intent.getStringExtra("numericCode");
        String cioc = intent.getStringExtra("cioc");

        Parcelable[] currenciesParcelables = intent.getParcelableArrayExtra("currencies");
        Currencies [] currencies = new Currencies[currenciesParcelables.length];
        for (int i = 0 ; i < currenciesParcelables.length; i++) {
            currencies[i] = (Currencies)currenciesParcelables[i];
        }

        Parcelable[] languagesParcelables = intent.getParcelableArrayExtra("languages");
        Languages[] languages = new Languages[languagesParcelables.length];
        for (int i = 0 ; i < languagesParcelables.length; i++) {
            languages[i] = (Languages)languagesParcelables[i];
        }

        Translations translationses = (Translations) intent.getSerializableExtra("translations");

        Parcelable[] regionalBlocsesParcelables = intent.getParcelableArrayExtra("regionalBlocs");
        RegionalBlocs[] regionalBlocses = new RegionalBlocs[regionalBlocsesParcelables.length];
        for (int i = 0 ; i < regionalBlocsesParcelables.length; i++) {
            regionalBlocses[i] = (RegionalBlocs)regionalBlocsesParcelables[i];
        }

        mTextViewCName.setText(countryName);
        mTextViewCapital.setText(capital);
        for (String tld : topLevelDomain) {
            mTextViewTopLevelDomain.append(tld+"\n");
        }
        mTextViewAlpha2Code.setText(alpha2Code);
        mTextViewAlpha3Code.setText(alpha3Code);
        for (String cc : callingCodes) {
            mTextViewCallingCodes.append(cc+"\n");
        }
        for (String as : altSpellings) {
            mTextViewAltSpellings.append(as+"\n");
        }
        mTextViewRegion.setText(region);
        mTextViewSubregion.setText(subregion);
        mTextViewPopulation.setText(population);
        for (String ll : latlng) {
            mTextViewLatlng.append(ll+"\n");
        }
        mTextViewDemonym.setText(demonym);
        mTextViewArea.setText(area);
        mTextViewGini.setText(gini);
        for (String tz : timezones) {
            mTextViewTimezones.append(tz+"\n");
        }
        for (String b : borders) {
            mTextViewBorders.append(b+"\n");
        }
        mTextViewNativeName.setText(nativeName);
        mTextViewNumericCode.setText(numericCode);
        for (Currencies c : currencies) {
            mTextViewCurrencies.append(c.getName()+"\n" +c.getCode()+"\n"+c.getSymbol());
        }
        for(Languages l : languages) {
            mTextViewLanguages.append(l.getName()+"\n"+l.getNativeName()+"\n"+l.getIso639_1()+"\n"+l.getIso639_2());
        }
        mTextViewTranslations.append(translationses.getBr()+"\n"+translationses.getDe()+"\n"
                +translationses.getEs()+"\n"+translationses.getFa()+"\n"
                +translationses.getFr()+"\n"+translationses.getHr()+"\n"
                +translationses.getIt()+"\n"+translationses.getJa()+"\n"
                +translationses.getNl()+"\n"+translationses.getPt()+"\n"
        );
        for (RegionalBlocs rb : regionalBlocses) {
            StringBuilder sb1 = new StringBuilder();
            for (String str  : rb.getOtherNames()) {
                sb1.append(str);
            }
            StringBuilder sb2 = new StringBuilder();
            for (String str  : rb.getOtherAcronyms()) {
                sb2.append(str);
            }
            mTextViewRegionalBlocs.append(rb.getName()+"\n"+rb.getAcronym()+"\n"
            +sb1+"\n"+sb2
            );
        }
        mTextViewCioc.setText(cioc);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(countryName);

        requestBuilder = Glide.with(this)
                .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.default_placeholder)
                .error(R.drawable.default_placeholder)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());

        loadFlag(flag);
    }

    private void loadFlag(String url) {
        Uri uri = Uri.parse(url);
        requestBuilder
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                // SVG cannot be serialized so it's not worth to cache it
                .load(uri)
                .into(mImageFlag);
    }
}
