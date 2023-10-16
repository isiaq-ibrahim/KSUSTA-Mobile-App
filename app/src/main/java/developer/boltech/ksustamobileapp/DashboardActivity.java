package developer.boltech.ksustamobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//         for displaying fullname on the header section
//        final TextView fullNameTextView = (TextView) findViewById(R.id.fullName);

//        hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

//        toolbar

        setSupportActionBar(toolbar);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

//        hide or show item
//        Menu menu = navigationView.getMenu();
//        menu.findItem(R.id.nav_logout).setVisible(false);
//        menu.findItem(R.id.nav_profile).setVisible(false);


//        navigation drawer menu
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListerner);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListerner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;

                        case R.id.nav_faculty:
                            selectedFragment = new FacultyFragment();
                            break;

                        case R.id.nav_gallery:
                            selectedFragment = new GalleryFragment();
                            break;

                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;

                        case R.id.nav_settings:
                            selectedFragment = new SettingsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id==R.id.nav_home){
            HomeFragment homeFragment = new HomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, homeFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if (id==R.id.nav_faculty){
            FacultyFragment facultyFragment = new FacultyFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, facultyFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if (id==R.id.nav_gallery){
            GalleryFragment galleryFragment = new GalleryFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, galleryFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        if (id==R.id.nav_profile){
            ProfileFragment profileFragment = new ProfileFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, profileFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

//        if (id==R.id.nav_ebook){
//            Intent intent = new Intent(DashboardActivity.this, EbookAdapter.class);
//            startActivity(intent);
//        }

        if (id==R.id.nav_settings){
            SettingsFragment settingsFragment = new SettingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.fragment_container, settingsFragment);
            transaction.addToBackStack(null);
            transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        switch (menuItem.getItemId()){

            case R.id.nav_logout:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

//            case R.id.nav_ebook:
////                startActivity(new Intent(this, EbookActivity.class));
//                break;

            case R.id.nav_rate:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (Exception exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id" + getPackageName())));
                }

                break;

            case R.id.nav_share:
                Intent shareApp = new Intent(Intent.ACTION_SEND);
                shareApp.setType("text/plain");
                String shareBody = "Experience some new features on the KSUSTA Mobile app brought to you by Boltech Technologies";
                String shareSub = "Manage your KSUSTA account using the new mobile app";
                shareApp.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareApp.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareApp, "Share using"));
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}