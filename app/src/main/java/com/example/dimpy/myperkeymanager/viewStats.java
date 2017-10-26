package com.example.dimpy.myperkeymanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class viewStats extends Fragment {


    public viewStats() {
        // Required empty public constructor
    }

    ImageView getMonday, getTuesday, getWednessday, getThursday, getFriday, getSaturday, getSunday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_stats, container, false);
        getMonday = (ImageView) view.findViewById(R.id.image_Monday);
        getTuesday = (ImageView) view.findViewById(R.id.image_Tuesday);
        getWednessday = (ImageView) view.findViewById(R.id.image_Wednesday);
        getThursday = (ImageView) view.findViewById(R.id.image_Thursday);
        getFriday = (ImageView) view.findViewById(R.id.image_Friday);
        getSaturday = (ImageView) view.findViewById(R.id.image_Saturday);
        getSunday = (ImageView) view.findViewById(R.id.image_Sunday);

        return view;
    }
}
