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

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageViewOccupancy);

        TextView textViewSerialNumber = (TextView) listItemView.findViewById(R.id.textViewSerialNumber);
        textViewSerialNumber.setText("Parking Id : " + currentParkingSpot.getId().substring(9));

        TextView textViewInformationExtra = (TextView) listItemView.findViewById(R.id.textView_Information_Extra);
        textViewInformationExtra.setText("");

        TextView textView_Information = (TextView) listItemView.findViewById(R.id.textView_Information);
        if (status == 0) {
            textView_Information.setText("Status : New");
            imageView.setImageResource(R.drawable.ic_done_all_black_36dp);
            textViewInformationExtra.setText("This spot is AVAILABLE!!");

        } else if (status == 2) {
            textView_Information.setText("Status : Free");
            imageView.setImageResource(R.drawable.ic_done_black_36dp);
            textViewInformationExtra.setText("This spot is AVAILABLE!!\n" +
                    "Previous Parker Details : \n" + currentParkingSpot.getUsr_name() + "\n" +
                    "" + currentParkingSpot.getUsr_num() + "\n" +
                    "" + currentParkingSpot.getUsr_email());
        } else if (status == 1) {
            textView_Information.setText("Status : Occupied");
            imageView.setImageResource(R.drawable.ic_donut_large_black_36dp);
            textViewInformationExtra.setText("This spot is OCCUPIED!!\n" +
                    "Present Parker Details : \n" + currentParkingSpot.getUsr_name() + "\n" +
                    "" + currentParkingSpot.getUsr_num() + "\n" +
                    "" + currentParkingSpot.getUsr_email());
        } else {
            textView_Information.setText("~~~");
        }
        //status 1 ==> occupied
        //else ==> available

        return listItemView;
    }

}
