package com.hci.hci_project;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NavBarActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppCompatActivity currentActivity;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    public NavBarActivity(AppCompatActivity currentActivity, DrawerLayout drawer, NavigationView navigationView){
        this.currentActivity = currentActivity;
        this.drawer = drawer;
        this.navigationView = navigationView;
        setUpNavBarProfile();
    }

    private void setUpNavBarProfile() {
        final Intent intent = new Intent(currentActivity, ProfileActivity.class);
        ImageView fab = navigationView.getHeaderView(0).findViewById(R.id.imageView);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity.setCourseTutoring(null);
                ProfileActivity.setTargetUser(null);
                currentActivity.startActivity(intent);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.chat) {
            Intent intent = new Intent(currentActivity, MainActivity.class);
            currentActivity.startActivity(intent);
        } else if (id == R.id.past_courses) {
            Intent intent = new Intent(currentActivity, CoursesTakenActivity.class);
            currentActivity.startActivity(intent);
        } else if (id == R.id.current_courses) {
            Intent intent = new Intent(currentActivity, CurrentCoursesActivity.class);
            currentActivity.startActivity(intent);
        } else if (id == R.id.tutors) {
            Intent intent = new Intent(currentActivity, TutorsActivity.class);
            currentActivity.startActivity(intent);
        } else if (id == R.id.log_out) {
            DummyAuth.logout();
            Intent intent = new Intent(currentActivity, LoginActivity.class);
            currentActivity.startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
