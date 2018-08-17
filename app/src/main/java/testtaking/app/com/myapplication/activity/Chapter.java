package testtaking.app.com.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;

import testtaking.app.com.myapplication.adapter.ChapterAdapter;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.model.BeanChapter;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.network.URL_Mapping;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by pawan on 10/26/2016.
 */

public class Chapter extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    List<BeanChapter> beanChapterslist;
    private int idbasedonsubject;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar loader;
    private TextView errorText;
    String sub_name;
    TextView crd_subname, chapter;
    int chapter_id;
    String chapter_name;
    ImageView img_profile;
    String userID;
    String fname;
    String lname;
    String board;
    String emailID;
    String ClassId;
    String Class;
    String section;
    SharedPreferences shared;
    String registration_type;

    private Dialog dialogue_custom;
    LinearLayout llTopics, llTest, llPracticeTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_n);

        getCustomDialogue();
        crd_subname = (TextView) findViewById(R.id.crd_sub_name);
        chapter = (TextView) findViewById(R.id.chp);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/ts_normal.ttf");
        chapter.setTypeface(type);
        initviews();
        Intent intent = getIntent();
        idbasedonsubject = intent.getIntExtra("subject_id", 0);
        sub_name = intent.getStringExtra("subject_name");
        userID = intent.getStringExtra("user_id");


        crd_subname.setText(sub_name);


        shared = getSharedPreferences("MyPrefs", 0);
        Class = shared.getString("Classs", null);
        section = shared.getString("Section", null);
        fname = shared.getString("fname", null);
        lname = shared.getString("lname", null);
        board = shared.getString("Board", null);
        emailID = shared.getString("emailID", null);
        ClassId = shared.getString("ClassId", null);
        registration_type = shared.getString("registration_type", null);

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


       /* img_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                Intent i = new Intent(getApplicationContext(), Topic.class);
                i.putExtra("chapter_id", chapter_id);
                i.putExtra("chapter_name", chapter_name);
                i.putExtra("subject_name", sub_name);
                i.putExtra("subject_id", idbasedonsubject);
                i.putExtra("ClassId", ClassId);
                startActivity(i);
                //finish();

            }
        });*/


        /*img_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }


                Intent i=new Intent(getApplicationContext(),TestList.class);
                i.putExtra("level_id",chapter_id);
                i.putExtra("level_type","X");
                i.putExtra("Classs", Class);
                i.putExtra("Section", section);
                i.putExtra("ClassId", ClassId);
                startActivity(i);


                //get_demoTests(chapter_id,"X");

                //Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();
            }
        });
        img_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();
            }
        });
        img_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();
            }
        });
        img_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();

            }
        });*/
       /* img_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();

              *//*  Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.setView(layout);//setting the view of custom toast layout
                toast.show();*//*


            }
        });*/


        //sharedprefrence

        if (isNetworkAvailable()) {
            getChapter();
            loader.setVisibility(VISIBLE);
            errorText.setVisibility(GONE);
            swipeContainer.setRefreshing(false);
        } else {
            errorText.setText(R.string.error_string);
            errorText.setVisibility(VISIBLE);
            loader.setVisibility(GONE);
        }


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        chapter_id = beanChapterslist.get(position).getId();
                        chapter_name = beanChapterslist.get(position).getChapterName();
                        dialogue_custom.show();

                        /*if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        } else {
                            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }*/

                        //Toast.makeText(getApplicationContext(),beanChapterslist.get(position).getId()+"",Toast.LENGTH_LONG).show();
                        /*Intent i = new Intent(getApplicationContext(), Topic.class);
                        i.putExtra("chapter_id", beanChapterslist.get(position).getId());
                        i.putExtra("chapter_name", beanChapterslist.get(position).getChapterName());
                        i.putExtra("subject_name", sub_name);
                        i.putExtra("subject_id", idbasedonsubject);
                        startActivity(i);
                        finish();*/

                    }
                })
        );

    }

    public void initviews() {

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.chapter_swipe_refresh_layout);
        errorText = (TextView) findViewById(R.id.error_text);
        loader = (ProgressBar) findViewById(R.id.feed_loading);
        swipeContainer.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_chapter);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void getChapter() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);
        final BeanChapter beanChapter = new BeanChapter();
        beanChapter.setId(idbasedonsubject);
        Log.e("idbasedonsubject", idbasedonsubject + "");
        Call<List<BeanChapter>> call1 = service1.getchapterlist(idbasedonsubject);
        call1.enqueue(new Callback<List<BeanChapter>>() {
            @Override
            public void onResponse(Call<List<BeanChapter>> call, Response<List<BeanChapter>> response) {

                loader.setVisibility(GONE);
                swipeContainer.setRefreshing(false);
                errorText.setVisibility(GONE);
                beanChapterslist = response.body();
                Log.e("ChapterBody:", response.body().toString());
                //Log.e("chapter_test", beanChapterslist.get(i).getChapterName());
                Log.e("message_body", response.message());

                setAdapterRecyclerview();
            }

            @Override
            public void onFailure(Call<List<BeanChapter>> call, Throwable t) {
                Log.e("problemm", t.toString());
                Toast.makeText(Chapter.this, "Connection Timeout", Toast.LENGTH_LONG).show();
                loader.setVisibility(GONE);

            }
        });
    }

    private void setAdapterRecyclerview() {

        adapter = new ChapterAdapter(getApplicationContext(),beanChapterslist);
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onRefresh() {
        setAdapterRecyclerview();
        swipeContainer.setRefreshing(false);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_setting) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(Chapter.this);
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

            Toast.makeText(getApplicationContext(), "To Activate this module, please call us on our Helpline No. +91 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_expertshere) {

            Toast.makeText(getApplicationContext(), "To Activate this module, please call us on our Helpline No. +91 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    public void getCustomDialogue() {

        dialogue_custom = new Dialog(Chapter.this);
        dialogue_custom.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogue_custom.setContentView(R.layout.custom_chapter_grid);
        dialogue_custom.setCanceledOnTouchOutside(false);
        llTopics = (LinearLayout) dialogue_custom.findViewById(R.id.llTopics);
        llTopics.setOnClickListener(this);
        llTest = (LinearLayout) dialogue_custom.findViewById(R.id.llTest);
        llTest.setOnClickListener(this);

        llPracticeTest = (LinearLayout) dialogue_custom.findViewById(R.id.llPracticeTest);
        llPracticeTest.setOnClickListener(this);
        /*llReport = (LinearLayout) dialogue_custom.findViewById(R.id.llReport);
        llReport.setOnClickListener(this);
        llVideos = (LinearLayout) dialogue_custom.findViewById(R.id.llVideo);
        llVideos.setOnClickListener(this);
        llTeacher = (LinearLayout) dialogue_custom.findViewById(R.id.llTeacher);
        llTeacher.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.llTopics:

                Intent i = new Intent(getApplicationContext(), Topic.class);
                i.putExtra("chapter_id", chapter_id);
                i.putExtra("chapter_name", chapter_name);
                i.putExtra("subject_name", sub_name);
                i.putExtra("subject_id", idbasedonsubject);
                i.putExtra("ClassId", ClassId);
                startActivity(i);
                dialogue_custom.dismiss();
                break;

            case R.id.llTest:
                Intent ii = new Intent(getApplicationContext(), TestList.class);
                ii.putExtra("level_id", chapter_id);
                ii.putExtra("level_type", "X");
                ii.putExtra("subject_id", idbasedonsubject);
                ii.putExtra("Classs", Class);
                ii.putExtra("Section", section);
                ii.putExtra("ClassId", ClassId);
                startActivity(ii);

                dialogue_custom.dismiss();
                break;

            case R.id.llPracticeTest:

                Intent practiceIntent = new Intent(getApplicationContext(), PracticeTest.class);
                //Toast.makeText(getApplicationContext(),chapter_id+"",Toast.LENGTH_LONG).show();


                practiceIntent.putExtra("level_id", chapter_id);
                practiceIntent.putExtra("level_type", "T");
                startActivity(practiceIntent);
                dialogue_custom.dismiss();
                break;


        }
    }
}
