package testtaking.app.com.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.fragments.Home;
import testtaking.app.com.myapplication.fragments.Notification;
import testtaking.app.com.myapplication.fragments.Profile;
import testtaking.app.com.myapplication.fragments.Study;
import testtaking.app.com.myapplication.utils.BottomNavigationViewHelper;


public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //toolbar = getSupportActionBar();
        toolbar = getSupportActionBar();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        // toolbar.setTitle("Home");
        loadFragment(new Home());

    }


    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_tab_layout, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                toolbar.setTitle("SelfEnabler");
                fragment = new Home();
                loadFragment(fragment);
                return true;


            case R.id.navigation_dashboard:
                toolbar.setTitle("Study");
                fragment = new Study();
                loadFragment(fragment);
                return true;

            case R.id.navigation_notifications:
                toolbar.setTitle("Notification");
                fragment = new Notification();
                loadFragment(fragment);
                return true;

            case R.id.navigation_profile:
                toolbar.setTitle("Profile");
                fragment = new Profile();
                loadFragment(fragment);
                return true;

        }

        return loadFragment(fragment);


    }
}
