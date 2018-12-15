package com.hci.hci_project;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class TutorsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TutorsFragment.OnListFragmentInteractionListener {

    private RecyclerView mRecyclerView;
    private SearchView searchView;
    private MyTutorsRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        final Intent intent = new Intent(this, ProfileActivity.class);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavBarActivity(this, (DrawerLayout) findViewById(R.id.drawer_layout), navigationView));
//        setUpNavBarProfile(intent, navigationView);

        mRecyclerView = findViewById(R.id.list);
        mAdapter = (MyTutorsRecyclerViewAdapter) mRecyclerView.getAdapter();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String[] fonts = {"Precalculus I", "Precalculus II", "Calculus I", "Chemistry I",
                        "Chemistry Lab I", "Chemistry II", "Chemistry Lab II", "Engineering Graphics", "Basic Spanish 1",
                        "Basic Spanish 2", "Basic English 1", "Basic English 2", "Calculus II", "Calculus III",
                        "Differential Equations", "Physics I","Physics Lab I", "Physics II", "Physics Lab II",
                        "Algorithms","Advanced Spanish 1", "Advanced Spanish 2", "Advanced English 1",
                        "Advanced English 2"};

                AlertDialog.Builder builder = new AlertDialog.Builder(TutorsActivity.this);
                builder.setTitle("Select a Class to Tutor");
                builder.setItems(fonts, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final int Which = which;
                        mAdapter.mValuesFiltered.add(new Map.Entry<User, String>() {
                            @Override
                            public User getKey() {
                                return DummyAuth.getCurrentUser();
                            }

                            @Override
                            public String getValue() {
                                return fonts[Which];
                            }

                            @Override
                            public String setValue(String value) {
                                return null;
                            }
                        });
                        mAdapter.notifyDataSetChanged();
//                        if ("Small".equals(fonts[which])){
//                            Toast.makeText(TutorsActivity.this,"you nailed it", Toast.LENGTH_SHORT).show();
//                        }
//                        else if ("Medium".equals(fonts[which])){
//                            Toast.makeText(TutorsActivity.this,"you cracked it", Toast.LENGTH_SHORT).show();
//                        }
//                        else if ("Large".equals(fonts[which])){
//                            Toast.makeText(TutorsActivity.this,"you hacked it", Toast.LENGTH_SHORT).show();
//                        }
//                        else if ("Huge".equals(fonts[which])){
//                            Toast.makeText(TutorsActivity.this,"you digged it", Toast.LENGTH_SHORT).show();
//                        }
                        // the user clicked on colors[which]

                    }
                });
                builder.show();

            }
        });



    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }

    private void setUpNavBarProfile(final Intent intent, NavigationView navigationView) {
        ImageView fab = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        };

        fab.setOnClickListener(listener);


        TextView name = navigationView.getHeaderView(0).findViewById(R.id.user_name);
        name.setText(String.format("%s %s", DummyAuth.getCurrentUser().getFirst(), DummyAuth.getCurrentUser().getLast()));
        name.setOnClickListener(listener);
        TextView email = navigationView.getHeaderView(0).findViewById(R.id.user_email);
        email.setText(DummyAuth.getCurrentUser().getEmail());
        email.setOnClickListener(listener);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;    }

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
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(Map.Entry<User, String> item) {
        ProfileActivity.setTargetUser(item.getKey());
        ProfileActivity.setCourseTutoring(item.getValue());
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
//        Log.d("email: ",item.getEmail());
    }
}
