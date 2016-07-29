package com.guette.firebasestudent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private View rootView;
    private DatabaseReference databaseReference;
    private Button btnsubmit;
    private EditText editTextId;
    private EditText editTextName;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_main, container, false);

        databaseReference= FirebaseDatabase.getInstance().getReference();
        btnsubmit=(Button) rootView.findViewById(R.id.button);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recoverData();
            }
        });
        return rootView;
    }

    public void recoverData(){
        editTextId= (EditText) rootView.findViewById(R.id.editTextId);
        String id=editTextId.getText().toString().trim();
        editTextName= (EditText) rootView.findViewById(R.id.editTextName);
        String name=editTextName.getText().toString().trim();
        if( (id.equals("")) && (name.equals(""))){
            Toast.makeText(getActivity(),"Empty fields: Id or/and Name is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            Student student= new Student(id,name);
            databaseReference.push().setValue(student);
            Toast.makeText(getActivity(),id +" "+ name +" bien enregistré." ,Toast.LENGTH_LONG).show();
        }
    }


}
