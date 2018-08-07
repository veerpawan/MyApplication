package testtaking.app.com.myapplication.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.TestListAdapter;
import testtaking.app.com.myapplication.model.ScheduleTestBean;
import testtaking.app.com.myapplication.model.TestBean;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by pawan on 11/2/2016.
 */

public class TestList extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    int level_id;
    String level_type;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar loader;
    List<TestBean> testBeen;
    ProgressDialog pDialog;
    List<ScheduleTestBean> scheduleTestBeen;
    ImageView img_profile;
    String subject_id;
    String userID;
    String fname;
    String lname;
    String board;
    String emailID;
    String Class;
    String ClassId;
    String i_section;
    String section;
    SharedPreferences shared;
    String registration_type;
    String test_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtestlist);


        recyclerView = (RecyclerView) findViewById(R.id.testlist_recycler_view);

        recyclerView.setHasFixedSize(true);
        loader = (ProgressBar) findViewById(R.id.feed_loading);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        final Intent intent = getIntent();
        level_id = intent.getIntExtra("level_id", 0);
        level_type = intent.getStringExtra("level_type");
        Class = intent.getStringExtra("Classs");
        ClassId = intent.getStringExtra("ClassId");
        i_section = intent.getStringExtra("Section");
        subject_id = String.valueOf(intent.getIntExtra("subject_id", 0));


        shared = getSharedPreferences("MyPrefs", 0);
        userID = shared.getString("userID", null);
        fname = shared.getString("fname", null);
        lname = shared.getString("lname", null);
        board = shared.getString("Board", null);
        emailID = shared.getString("emailID", null);
        registration_type = shared.getString("registration_type", null);


        initviews();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_nav_black_24dp);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_s);
        navigationView.setItemIconTintList(null);


        navigationView.setNavigationItemSelectedListener(this);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        img_profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudentProfile.class);
                startActivity(i);
            }
        });

        if (isNetworkAvailable()) {

            get_Tests(level_id, level_type, userID);
            loader.setVisibility(VISIBLE);

        } else {
            loader.setVisibility(GONE);
        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Toast.makeText(getApplicationContext(), "thanx", Toast.LENGTH_LONG).show();


                        if (testBeen.get(position).getSuccess() == 1) {

                            Toast.makeText(getApplicationContext(), "Will Update Soon!!!", Toast.LENGTH_LONG).show();

                        } else {


                            // Toast.makeText(getApplicationContext(), testBeen.get(position).getTest_id()+"        "+testBeen.get(position).getTest_test_id()+"                 "+testBeen.get(position).getTest_id(), Toast.LENGTH_LONG).show();

                            Intent intentt = new Intent(getApplicationContext(), QuizMainActivity.class);
                            intentt.putExtra("str_test_id", testBeen.get(position).getTest_test_id() + "");
                            intentt.putExtra("str_serial_id", testBeen.get(position).getTest_id() + "");
                            startActivity(intentt);
                            finish();

                        }
                    }
                })
        );


    }

    public void initviews() {

        //recyclerView = (RecyclerView) findViewById(R.id.testlist_recycler_view);


    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public void get_Tests(int level_id, String level_type, String user_id) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<List<TestBean>> call = service.gettestlist(level_id, level_type, user_id);

        call.enqueue(new Callback<List<TestBean>>() {
            @Override
            public void onResponse(Call<List<TestBean>> call, Response<List<TestBean>> response) {
                testBeen = response.body();
                setAdapterRecyclerviewdemo();
                loader.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<TestBean>> call, Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });


    }

    private void setAdapterRecyclerviewdemo() {

        adapter = new TestListAdapter(testBeen, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_setting) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(TestList.this);
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


        } else if (id == R.id.nav_about) {

            if (!isNetworkAvailable()) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();


            } else {
                Intent i = new Intent(getApplicationContext(), AboutUs.class);
                startActivity(i);
            }


        } else if (id == R.id.nav_self_enabler) {
            if (!isNetworkAvailable()) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();


            } else {
                Intent i = new Intent(getApplicationContext(), SelfEnablerWebViewActivity.class);
                startActivity(i);
            }
        } else if (id == R.id.nav_saath_foundation) {
            if (!isNetworkAvailable()) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();


            } else {
                Intent i = new Intent(getApplicationContext(), SaathFoundationWebViewActivity.class);
                startActivity(i);
            }
        } else if (id == R.id.nav_best) {
            if (!isNetworkAvailable()) {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "No internet connection !", Snackbar.LENGTH_LONG)
                        .setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });

                snackbar.setActionTextColor(Color.RED);
                View sbView = snackbar.getView();
                TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.YELLOW);
                snackbar.show();


            } else {
                Intent i = new Intent(getApplicationContext(), BestWebViewActivity.class);
                startActivity(i);
            }
        } else if (id == R.id.nav_notification) {

            Toast.makeText(getApplicationContext(), "No New Notification", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_anytimetutor) {

            Toast.makeText(getApplicationContext(), "Please Subscribe it by contacting help line number 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_expertshere) {

            Toast.makeText(getApplicationContext(), "Please Subscribe it by contacting help line number 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


}
