package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jorgegiance.displayview.DisplayActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.OnJokerTaskCompleted;
import com.udacity.gradle.builditbigger.R;

import org.apache.commons.logging.LogFactory;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokerTaskCompleted {


    Context context;
    private Button jokerButton;
    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        spinner = (ProgressBar) root.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        jokerButton = (Button) root.findViewById(R.id.joker_button);
        setJokerButtonListener();


        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getString(R.string.interstitial_ad_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        setInterstialListener();


        return root;
    }

    private void setInterstialListener() {
        mInterstitialAd.setAdListener(new AdListener() {


            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.d("TAG", "onAdFailedToLoad: failed to load ad");
                tellJoke();
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                tellJoke();
            }
        });
    }

    private void setJokerButtonListener() {
        jokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                    tellJoke();
                }

            }
        });
    }


    @Override
    public void onAttach( Context context ) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onTaskCompleted( String response ) {
        spinner.setVisibility(View.GONE);
        if (response != null){
            Intent intent = new Intent(context, DisplayActivity.class);
            intent.putExtra(context.getString(R.string.joke_key), response);
            startActivity(intent);
        }else {
            Toast.makeText(context, "Server was not found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaskInit() {
        spinner.setVisibility(View.VISIBLE);
    }

    public void tellJoke() {
        //  Toast.makeText(this, Joker.getJoke(), Toast.LENGTH_SHORT).show();
        new EndpointsAsyncTask(this).execute();

    }





}
