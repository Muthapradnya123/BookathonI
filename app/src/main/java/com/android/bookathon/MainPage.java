package com.android.bookathon;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainPage extends AppCompatActivity {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FragmentManager fm;
    FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame_layout, new TabFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();

                if(item.getItemId()==R.id.home_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new TabFragment()).commit();

                }

                if(item.getItemId()==R.id.request_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new post_request()).commit();
                    getSupportActionBar().setTitle("Request A Book");
                }

                if(item.getItemId()==R.id.help_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new HelpFragment()).commit();
                    getSupportActionBar().setTitle("Help");
                }

                if(item.getItemId()==R.id.invit_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new InviteFragment()).commit();
                    getSupportActionBar().setTitle("Invite");
                }

                if(item.getItemId()==R.id.profile_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new UserProfile()).commit();
                    getSupportActionBar().setTitle("Profile");
                }
                if(item.getItemId()==R.id.settings_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new Settings()).commit();
                    getSupportActionBar().setTitle("Settings");
                }

                if(item.getItemId()==R.id.logout)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new post_request()).commit();
                    getSupportActionBar().setTitle("Logout");
                }

                if(item.getItemId()==R.id.about_id)
                {
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout, new Aboutus()).commit();
                    getSupportActionBar().setTitle("About Us");
                }

                return false;
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return false;
    }

}