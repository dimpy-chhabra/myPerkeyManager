package com.example.dimpy.myperkeymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dimpy on 22/10/17.
 */

public class pSpotListAdapter extends ArrayAdapter<pSpot> {

    public pSpotListAdapter(Context context, ArrayList<pSpot> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //getting the view into a variable listItemView
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);
        }
        //This is to ensure the reusablity of a view

        pSpot currentParkingSpot = getItem(position);
        int status = Integer.parseInt(currentParkingSpot.getStatus());

        TextView textViewSerialNumber = (TextView) listItemView.findViewById(R.id.textViewSerialNumber);
        textViewSerialNumber.setText("Parking Id : " + currentParkingSpot.getId());

        TextView textView_Information = (TextView) listItemView.findViewById(R.id.textView_Information);
        textView_Information.setText("Status : " + status);

        //status 1 ==> occupied
        //else ==> available

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageViewOccupancy);
        if (status == 1)
            imageView.setImageResource(android.R.drawable.btn_minus);
        else
            imageView.setImageResource(android.R.drawable.btn_star);
        return listItemView;
    }

}
