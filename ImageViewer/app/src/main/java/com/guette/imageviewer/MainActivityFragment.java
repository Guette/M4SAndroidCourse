package com.guette.imageviewer;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public static final String MAIN_ACTIVITY_FRAGMENT = "MainActivityFragment";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);

        ImageView image1= (ImageView) rootView.findViewById(R.id.imageView1);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MAIN_ACTIVITY_FRAGMENT, "The button is clicked!");
                Toast t = Toast.makeText(getActivity().getApplicationContext(), " When Ziggy was a baby !!!", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        ImageView image2= (ImageView) rootView.findViewById(R.id.imageView2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MAIN_ACTIVITY_FRAGMENT, "The button is clicked!");
                Toast t = Toast.makeText(getActivity().getApplicationContext(), "Ziggy in the contryside !!!", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        ImageView image3= (ImageView) rootView.findViewById(R.id.imageView3);
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(MAIN_ACTIVITY_FRAGMENT, "The button is clicked!");
                Toast t = Toast.makeText(getActivity().getApplicationContext(), "Ziggy in the montain!!!", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        return rootView;
    }
}
