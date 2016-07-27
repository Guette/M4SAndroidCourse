package com.guette.menuproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Button btnmess;
    private Button btnphone;
    private Button btnmap;
    private Button btnweb;
    private Button btnshare;
    private Button btnnewActivity;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_main, container, false);

        btnmess= (Button) rootView.findViewById(R.id.btnsms);
        btnmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                final Intent intentmsg = new Intent(Intent.ACTION_SENDTO);
                intentmsg.setData(Uri.parse("smsto:+221774517879"));
                intentmsg.putExtra("sms_body", "Hello Maguette Gueye");
                startActivity(intentmsg);
            }
            });

        btnphone= (Button) rootView.findViewById(R.id.btnphone);
        btnphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intentcall = new Intent(Intent.ACTION_DIAL);
                intentcall.setData(Uri.parse("tel:+211774517879"));
                startActivity(intentcall);
            }
            });

        btnweb= (Button) rootView.findViewById(R.id.btnweb);
        btnweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intentweb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com"));
                startActivity(intentweb);
            }
            });

        btnmap = (Button) rootView.findViewById(R.id.btnmap);
        btnmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String geoUri= String.format("geo:38.899533, -77,036476");
                Uri geo = Uri.parse(geoUri);
                Intent intentmap = new Intent(Intent.ACTION_VIEW,geo);
                startActivity(intentmap);
            }
        });

        btnshare = (Button) rootView.findViewById(R.id.btnshare);
        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentshare = new Intent(Intent.ACTION_SEND);
                intentshare.setType("text/plain");
                intentshare.putExtra(Intent.EXTRA_SUBJECT,"");
                intentshare.putExtra(Intent.EXTRA_TEXT,"");
                startActivity(Intent.createChooser(intentshare,"Share the love"));
            }
        });

        btnnewActivity = (Button) rootView.findViewById(R.id.btnnewactivity);
        btnnewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentactivity = new Intent(getActivity(),NewActivity.class);
                startActivity(intentactivity);
            }
        });

            return rootView;
    }
}
