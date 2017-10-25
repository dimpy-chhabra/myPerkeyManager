package com.example.dimpy.myperkeymanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static FragmentManager fragmentManager;
    ActionBar actionBar;
    SharedPreferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here you add a new Node to your lot", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.fragmentHolder,
                                new addNode(),
                                "addNode").commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        //", "Nelson Mandela Road, Vasant Kunj, New Delhi, Delhi"
        editor.putBoolean("logged_in", true); // Storing boolean - true/false
        editor.putString("institution", "DLF Emporio"); // Storing string
        editor.putString("adress", "Nelson Mandela Road, Vasant Kunj, New Delhi, Delhi"); // Storing string
        editor.putString("key", "AA010B120"); // Storing string
        editor.putBoolean("animate_bit", false); //Storing Boolean
        editor.putInt("tans_id", -1); //Storing Integer
        editor.commit(); // commit changes

        fragmentManager
                .beginTransaction()
                .replace(R.id.fragmentHolder,
                        new mainFragment(),
                        "mainFragment").commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainFragment _frag = (mainFragment) fragmentManager.findFragmentByTag("mainFragment");
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (_frag != null && _frag.isVisible()) {
            super.onBackPressed();
        } else {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new mainFragment(),
                            "mainFragment").commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.viewLot) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new viewLot(),
                            "viewLot").commit();
        } else if (id == R.id.viewDetails) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new viewDetails(),
                            "viewDetails").commit();

        } else if (id == R.id.addNode) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new addNode(),
                            "addNode").commit();

        } else if (id == R.id.viewStats) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new viewStats(),
                            "viewStats").commit();

        } else if (id == R.id.aboutMe) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentHolder,
                            new aboutMe(),
                            "aboutMe").commit();

        } else if (id == R.id.nav_share) {

            String shareBody = "Perkey, the smart parking App! Join us " +
                    "now! \n-DimpyChhabra\n( linkedin.com/in/dimpy-chhabra )";
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Greeting from Perkey");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
        } else if (id == R.id.nav_send) {

            Intent send = new Intent(Intent.ACTION_SENDTO);
            String uriText = "mailto:" + Uri.encode("dimpy.perkey@gmail.com") +
                    "?subject=" + Uri.encode("Enquiry via PerkeyManager : " + pref.getString("institution", " ")) +
                    "&body=" + Uri.encode("the body of the message");
            Uri uri = Uri.parse(uriText);

            send.setData(uri);
            startActivity(Intent.createChooser(send, "Send mail..."));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
