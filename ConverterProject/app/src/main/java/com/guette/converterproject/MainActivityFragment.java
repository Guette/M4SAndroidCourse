package com.guette.converterproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    EditText editText;
    TextView textView;
    Button button,buttonreset;
    double devise= 439.36;
    View rootview;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         rootview= inflater.inflate(R.layout.fragment_main, container, false);

        button=(Button) rootview.findViewById(R.id.buttonconvert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText= (EditText) rootview.findViewById(R.id.editText);
                if (editText.getText().toString().trim().equals("")){
                    Toast.makeText(getActivity(),"Empty fields",Toast.LENGTH_LONG).show();
                    Toast.makeText(getActivity(),"Please enter a number",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    String montant=editText.getText().toString();
                    double newmontant=convertStringToDouble(montant);
                    double result = convertDollarToFrancs(newmontant);
                    textView = (TextView) rootview.findViewById(R.id.textView2);
                    String lastReseult = " " + montant + "$ = " + convertDoubleToString(result) + " Francs";
                    textView.setText(lastReseult);
                    Toast.makeText(getActivity(),"Well",Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonreset=(Button) rootview.findViewById(R.id.buttonreset);
        buttonreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return rootview;
    }

    public double convertDollarToFrancs(double montant){
        double resultat=montant*devise;
        Log.i("convertDollarToFrancs",""+resultat);
        return resultat;
    }

    public String convertDoubleToString(double m){
        String a= String.valueOf(m);
        Log.i("convertDoubleToString", ""+a);
        return a;
    }

    public double convertStringToDouble(String m){
        double b=Double.parseDouble(m);
        Log.i("convertStringToDouble",""+b);
        return b;
    }
}
