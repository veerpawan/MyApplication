package testtaking.app.com.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.fragments.Home;
import testtaking.app.com.myapplication.fragments.Notification;
import testtaking.app.com.myapplication.fragments.Profile;
import testtaking.app.com.myapplication.fragments.Study;
import testtaking.app.com.myapplication.utils.BottomNavigationViewHelper;


public class BottomNavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //toolbar = getSupportActionBar();

        Intent i = getIntent();
        user_id = i.getStringExtra("user_id");
       // Toast.makeText(getApplicationContext(),user_id,Toast.LENGTH_LONG).show();

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


                Bundle bundle = new Bundle();
                bundle.putString("user_id", user_id);
                fragment = new Home();
                fragment.setArguments(bundle);

                loadFragment(fragment);
                return true;


            case R.id.navigation_dashboard:


                Intent i = new Intent(getApplicationContext(), Subject.class);
                startActivity(i);

               /* toolbar.setTitle("Study");
                fragment = new Study();
                loadFragment(fragment);*/
                return true;

            case R.id.navigation_notifications:

                fragment = new Notification();
                loadFragment(fragment);
                return true;


            case R.id.navigation_logout:


                final AlertDialog.Builder builder = new AlertDialog.Builder(BottomNavigationActivity.this);
                Log.d("Debug", "We have created alert");
                builder.setMessage("Are you sure you want to logout ?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialoginterface, int i) {

                        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", 0);
                        SharedPreferences.Editor editor = sharedpreferences.edit();

                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), splash.class);

                        startActivity(intent);
                        finishAffinity();

                    }


                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();


                return true;


            case R.id.navigation_profile:


                Intent s_profile = new Intent(getApplicationContext(), StudentProfile.class);
                startActivity(s_profile);
               /* toolbar.setTitle("Profile");
                fragment = new Profile();
                loadFragment(fragment);*/
                return true;

        }

        return loadFragment(fragment);


    }
}
