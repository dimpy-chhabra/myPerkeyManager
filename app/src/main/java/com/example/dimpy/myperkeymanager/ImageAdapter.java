package com.example.dimpy.myperkeymanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dimpy on 22/10/17.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    //private final String[] web;
    //private final int[] Imageid;
    private ArrayList<pSpot> list;

    /*public ImageAdapter(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
    }*/
    public ImageAdapter(Context c, ArrayList<pSpot> list) {
        mContext = c;
        this.list = list;
        //this.web = web;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
            String str = "Parking Sno. " + list.get(position).getId().substring(9);
            textView.setText(str);
            int status = Integer.parseInt(list.get(position).getStatus());
            //if(stat==0)
            imageView.setImageResource(R.drawable.avail);
            if (status == 0) {
                imageView.setImageResource(R.drawable.avail);
            } else if (status == 2) {
                imageView.setImageResource(R.drawable.avail);
            } else if (status == 1) {
                imageView.setImageResource(R.drawable.occu);
            }

            ///*Imageid[position]*/
        } else {
            grid = convertView;
        }

        return grid;
    }

    /*
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            android.R.drawable.ic_btn_speak_now, android.R.drawable.ic_input_delete,
            android.R.drawable.ic_dialog_alert, android.R.drawable.ic_menu_sort_alphabetically,
            android.R.drawable.ic_popup_sync, android.R.drawable.ic_menu_week,
            android.R.drawable.ic_menu_search, android.R.drawable.ic_media_pause,

    };
    */
}