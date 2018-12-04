package com.hci.hci_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NavBarActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppCompatActivity currentActivity;
    private DrawerLayout drawer;

    public NavBarActivity(AppCompatActivity currentActivity, DrawerLayout drawer){
        this.currentActivity = currentActivity;
        this.drawer = drawer;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.chat) {
            Intent intent = new Intent(currentActivity, MainActivity.class);
            currentActivity.startActivity(intent);
        } else if (id == R.id.courses) {
//            Intent intent = new Intent(currentActivity, CoursesActivity.class);
        } else if (id == R.id.resources) {
//            Intent intent = new Intent(currentActivity, ResourcesActivity.class);
        } else if (id == R.id.tutors) {
//            Intent intent = new Intent(currentActivity, TutorsActivity.class);
        } else if (id == R.id.settings) {
//            Intent intent = new Intent(currentActivity, SettingsActivity.class);
        } else if (id == R.id.log_out) {
            DummyAuth.logout();
            Intent intent = new Intent(currentActivity, LoginActivity.class);
            currentActivity.startActivity(intent);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
