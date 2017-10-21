package com.example.dimpy.myperkeymanager;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class addNode extends Fragment {


    public addNode() {
        // Required empty public constructor
    }

    ImageButton scan_button;
    String code;
    FragmentManager fragmentManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getFragmentManager();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_node, container, false);

        LinearLayout llc = (LinearLayout) view.findViewById(R.id.LinearLayoutClickable);
        llc.setVisibility(View.VISIBLE);
        LinearLayout llps = (LinearLayout) view.findViewById(R.id.LinearLayoutPostScan);
        llps.setVisibility(View.GONE);
        scan_button = (ImageButton) view.findViewById(R.id.scan_button);
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanFromFragment();
            }
        });

        return view;
    }

    private void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String toast;
        if (result != null) {
            if (result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
            }
            code = result.getContents();
            Log.e(" in onactivityres", "  " + toast);
            // At this point we may or may not have a reference to the activity
            if (getActivity() != null && toast != null) {
                Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
                toast = null;
                Snackbar.make(getView(), "-->" + code, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                updateViewWithNewNode(code);

            }
        }
    }

    private void updateViewWithNewNode(String code) {
        View view = getView();

        int serial = VolleyModuleToAddNewNode(code);
        if (serial != -1) {
            LinearLayout llc = (LinearLayout) view.findViewById(R.id.LinearLayoutClickable);
            llc.setVisibility(View.GONE);

            LinearLayout llps = (LinearLayout) view.findViewById(R.id.LinearLayoutPostScan);
            llps.setVisibility(View.VISIBLE);

            TextView textViewPostScan = (TextView) view.findViewById(R.id.textViewPostScan);
            textViewPostScan.setText("Node sucessfully added with serial number ... " + code);
        } else {

            new AlertDialog.Builder(view.getContext())
                    .setTitle("Error")
                    .setMessage("Please check if you already have this node registered!\nif not so, please try again later. If " +
                            "problem persists, send an enquiry via the contact us option in the menu")
                    .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getContext(), "Sorry!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(getActivity().getApplicationContext(), "Sorry!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        }

    }

    private int VolleyModuleToAddNewNode(String code) {

        return 0;
    }
}
