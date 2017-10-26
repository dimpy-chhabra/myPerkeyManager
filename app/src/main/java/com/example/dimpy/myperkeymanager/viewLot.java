package com.example.dimpy.myperkeymanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class viewLot extends Fragment {

    public viewLot() {
        // Required empty public constructor
    }

    RequestQueue requestQueue;
    StringRequest stringRequest;
    ProgressDialog progressDialog;
    ArrayList<pSpot> pSpotsArrayList = new ArrayList<>();
    String resString;
    private String DataParseUrl = "http://impycapo.esy.es/display_nodes.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        volleyToFetchParkingSpots();
        View view = inflater.inflate(R.layout.fragment_view_lot, container, false);

        return view;
    }

    private void volleyToFetchParkingSpots() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching The Parking Lots");
        progressDialog.show();

        stringRequest = new StringRequest(Request.Method.POST, DataParseUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                if (response != null && response.length() > 0) {
                    resString = response;
                    pSpotsArrayList = extractData(response);
                    //Toast.makeText(getContext(), " "+response, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please Try again Later!", Toast.LENGTH_LONG).show();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        if (error != null && error.toString().length() > 0) {
                            Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            Log.e(" in  scanner_frag2 ", " error in parsing data");
                            Toast.makeText(getContext(), "Error Code " + error, Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(getContext(), "Something went terribly wrong! ", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("mall_id", "mallId");
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private ArrayList<pSpot> extractData(String str) {
        //Toast.makeText(getActivity(), " ... \n"+str, Toast.LENGTH_SHORT).show();
        View view = getView();
        ListView listView = (ListView) view.findViewById(R.id.listView);

        ArrayList<pSpot> spotsAL = new ArrayList<>();

        try {
            JSONArray baseArray = new JSONArray(str);
            for (int i = 0; i < baseArray.length(); i++) {
                JSONObject currentSpot = baseArray.getJSONObject(i);
                String park_id = currentSpot.getString("park_id");
                String trans_id = currentSpot.getString("trans_id");
                if (trans_id == null || trans_id.equalsIgnoreCase("")) {
                    Log.e("in extractData If", "Trans_id >" + trans_id);
                    pSpot spot = new pSpot(park_id, "", "0", "", "", "", "");
                } else {
                    String spry = currentSpot.getString("spry_bit");
                    String num = currentSpot.getString("usr_num");
                    String name = currentSpot.getString("usr_name");
                    String email = currentSpot.getString("usr_email");
                    String adress = currentSpot.getString("usr_adrs");
                    // park_id, trans_id, spry_bit, usr_num, usr_name, usr_email, usr_adrs
                    pSpot spot = new pSpot(park_id, trans_id, spry, num, name, email, adress);
                    spotsAL.add(spot);
                    pSpotsArrayList.add(spot);
                }
                Log.e("in ExtractData Frm JSon", " >" + spotsAL.get(i));
                //{"park_id":"00A01AAB1005","trans_id":null,"spry_bit":null,"usr_num":null,"usr_name":null,"usr_email":null,"usr_adrs":null},
            }

        } catch (JSONException e) {
            Log.e("in ExtractRides : ", "JSON TRY CATCH ERR!");
        }

        View view_ = getView();
        GridView gridview = (GridView) view_.findViewById(R.id.gridview);
        ImageAdapter adapter = new ImageAdapter(getContext(), pSpotsArrayList);
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getContext(), "You Clicked at spotNo. " +pSpotsArrayList.get(position).getId().substring(9)+" , Toast.LENGTH_SHORT).show();
                pSpot obj = pSpotsArrayList.get(position);
                int status = Integer.parseInt(obj.getStatus());
                String snackBarString;
                if (status == 0) {
                    snackBarString = "Status : New \n This spot is AVAILABLE!!";

                } else if (status == 2) {
                    snackBarString = "Status : Free \n This spot is AVAILABLE!!\n" +
                            "Previous Parker Details : \n" + obj.getUsr_name() + "\n" +
                            "" + obj.getUsr_num() + "\n" +
                            "" + obj.getUsr_email();
                } else if (status == 1) {
                    snackBarString = "Status : Occupied \n This spot is OCCUPIED!!\n" +
                            "Present Parker Details : \n" + obj.getUsr_name() + "\n" +
                            "" + obj.getUsr_num() + "\n" +
                            "" + obj.getUsr_email();
                } else {
                    snackBarString = "~~~";
                }

                Snackbar.make(getView(), snackBarString, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        return spotsAL;
    }
}
