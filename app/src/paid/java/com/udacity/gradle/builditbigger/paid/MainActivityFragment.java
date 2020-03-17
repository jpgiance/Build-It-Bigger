package com.udacity.gradle.builditbigger.paid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jorgegiance.displayview.DisplayActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask;
import com.udacity.gradle.builditbigger.OnJokerTaskCompleted;
import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements OnJokerTaskCompleted {

    Context context;
    private Button jokerButton;
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

        return root;
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

    private void setJokerButtonListener() {
        jokerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {

                tellJoke();

            }
        });
    }

    public void tellJoke() {
        //  Toast.makeText(this, Joker.getJoke(), Toast.LENGTH_SHORT).show();
        new EndpointsAsyncTask(this).execute();

    }

}
