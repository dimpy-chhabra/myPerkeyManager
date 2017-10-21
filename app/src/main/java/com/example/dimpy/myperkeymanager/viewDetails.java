package com.example.dimpy.myperkeymanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class viewDetails extends Fragment {


    public viewDetails() {
        // Required empty public constructor
    }

    int resultString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_details, container, false);
        ListView listView = (ListView) view.findViewById(R.id.listView);

        volleyToFetchParkingSpots();

        final ArrayList<pSpot> pSpotsArrayList = extractRides();
        pSpotsArrayList.add(new pSpot("001", "1"));
        pSpotsArrayList.add(new pSpot("002", "0"));
        pSpotsArrayList.add(new pSpot("003", "1"));

        pSpotListAdapter adapter = new pSpotListAdapter(getContext(), pSpotsArrayList);
        listView.setAdapter(adapter);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 }
            });


         */
        return view;
    }

    private ArrayList<pSpot> extractRides() {
        /*
        ArrayList<pSpot> ridesAL = new ArrayList<>();

        try {
            JSONArray baseArray = new JSONArray(resultString);
            for (int i = 0; i < baseArray.length(); i++) {
                JSONObject currentRide = baseArray.getJSONObject(i);
                String from = currentRide.getString("from_loc");
                String seats = currentRide.getString("num_seats");
                String ftime = currentRide.getString("from_time");
                String rtime = currentRide.getString("reach_time");
                String rdate = currentRide.getString("r_date");
                String r_id = currentRide.getString("r_id");
                SharedPreferences spref = getActivity().getSharedPreferences(BaseActivity.MyPref, Context.MODE_PRIVATE);
                String college = spref.getString(BaseActivity.College, null); // getting String

                Ride ride = new Ride(from, college, seats, ftime, rtime, rdate, r_id);
                ridesAL.add(ride);
            }


        } catch (JSONException e) {
            Log.e("in ExtractRides : ", "JSON TRY CATCH ERR!");
        }*/
        return new ArrayList<pSpot>();
    }

    private void volleyToFetchParkingSpots() {
    }

}
