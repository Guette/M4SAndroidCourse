package com.guette.stateslistproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
     StatesDataSource statesDataSource;
    private final String TAG_IDSTATE="idStates";
    private final String TAG_NAMESTATES="nameStates";
    private final String TAG_CAPITALSTATES="capitalStates";
    private final String TAG_LATITUDE="latitude";
    private final String TAG_LONGITUDE="longitude";
    private long idStates;
    private String stateName;
    private String latitude;
    private String longitude;
    private String capitalNames;
    String[] nameStatesList =  {
            "Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia",
            "Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland",
            "Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey",
            "New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina",
            "South Dakota","Tennessee","Texas","Utah","Vermont","Viriginia","Washington","West Virginia","Wisconsin","Wynoming"
    };
    String[] capitalList ={
            "Montgomery","Juneau","Phoenix","Little Rock","Sacramento","Denver","Hartford","Dover","Tallahassee","Atlanta",
            "Honolulu","Boise","Springfield","Indianapolis","Des Moines","Topeka","Frankfort","Baton Rouge","Augusta","Annapolis",
            "Boston","Lansing","St. Paul","Jackson","Jefferson City","Helena","Lincoln","Carson City","Concord","Trenton",
            "Santa Fe","Albany","Raleigh","Bismarck","Columbus","Oklahoma City","Salem","Harrisburg","Providence","Columbia",
            "Pierre","Nashville","Austin","Salt Lake City","Montpelier","Richmond","Olympia","Charleston","Madison","Cheyenne"
    };
    String[] latitudeList = {
            "32.3666667","58.3019444","33.4483333","34.7463889","38.5816667","39.7391667","41.9841667","39.1580556","30.4380556","33.7488889",
            "21.3069444","43.6136111111","42.1013889","39.7683333","41.6005556","39.0483333","28.4833333","30.4505556","33.4709702","38.9783333",
            "42.3583333","42.7325","44.9444444","32.2986111","38.5766667","46.5927778","40.8","39.1638889","37.9780556","40.2169444",
            "-31.6333333","42.6525","35.7719444","46.8083333","39.9611111","35.4675","11.65","40.2736111","41.8238889","34.0005556",
            "44.3683333","36.1658333","30.2669444","40.7608333","44.26","37.5536111","47.3917","32.7763889","43.0730556","41.14"
    };
    String[] longitudeList = {
            "-86.3","-134.4197222","-112.0733333","-92.2894444","-121.4933333","-104.9841667","-72.6855556","-75.5247222","-84.2808333","-84.3880556",
            "-157.85833331","-116.237777778","-72.5902778","-86.1580556","-93.6088889","-95.6777778","-27.2666667","-91.1544444","-81.9748379","-76.4925",
            "-71.0602778","84.5555556","-93.0930556","-90.1847222","-92.1733333","-112.0352778","-96.6666667","-119.7663889","-122.03","-74.7433333",
            "-60.7","73.7566667","-78.6388889","-100.7833333","-82.9988889","-97.5161111","78.166667","-76.8847222","-71.4133333","-81.035",
            "-100.3505556","-86.7844444","-97.7427778","-111.8902778","-72.5758333","-77.4605556","-121.5708","-79.9311111","-89.4011111","-104.8197222"
    };
    private View rootView;
    private ListView listView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView= inflater.inflate(R.layout.fragment_main, container, false);

        statesDataSource= new StatesDataSource(this.getActivity());
        statesDataSource.open();

        for (int i=0; i<nameStatesList.length;i++){
            statesDataSource.createState(nameStatesList[i],capitalList[i],
                    latitudeList[i],longitudeList[i]);
            Log.d("New line",""+nameStatesList[i]+", "+capitalList[i]+", "+latitudeList[i]+", "+longitudeList[i]);
        }

        List<State> stateList= statesDataSource.getAllStates();
        List<String> newStateList= new ArrayList<String>();
        Log.d("***stateList.size()*** ",""+ stateList.size());
        for (int j=0;j<stateList.size();j++) {
            newStateList.add(stateList.get(j).getNameStates());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this.getActivity(),android.R.layout.simple_list_item_1, newStateList);
        listView= (ListView) rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(),MapsActivity.class);

                State state= statesDataSource.getStateById(i+1);
                    idStates=state.getIdStates();
                    stateName=state.getNameStates();
                    capitalNames=state.getCapitalStates();
                    latitude=state.getLatitude();
                    longitude=state.getLongitude();
                    System.out.println("****" +idStates +"," + stateName +","+capitalNames+","+latitude+","+longitude);
                String id= String.valueOf(idStates);

                intent.putExtra("TAG_IDSTATE", id);
                intent.putExtra("TAG_NAMESTATES", stateName);
                intent.putExtra("TAG_CAPITALSTATES",capitalNames);
                intent.putExtra("TAG_LATITUDE",latitude);
                intent.putExtra("TAG_LONGITUDE",longitude);
                startActivity(intent);
            }
        }
        );
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        statesDataSource.close();
    }
}
