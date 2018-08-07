package testtaking.app.com.myapplication.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.TopicAdapter;
import testtaking.app.com.myapplication.model.BeanTopic;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by pawan on 10/27/2016.
 */
public class Topic extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    List<BeanTopic> beanTopiclistlist;
    int idbasedonchapter, subject_id;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Context context;
    String userID;
    String fname;
    String lname;
    String board;
    String emailID;
    String ClassId;
    String Class;
    String section;
    String chapter_name;
    Button topic_btn_video, topic_btn_test;
    private SwipeRefreshLayout swipeContainer;
    private ProgressBar loader;
    private TextView errorText;
    SharedPreferences shared;
    TextView tv_chaptername;
    TextView tv_subjectname, tv_error;
    String subject_name;

    ImageView img_test, img_report, img_video, img_teacher, img_notes;
    int topic_id;
    String topic_name;
    ImageView img_profile;

    private Dialog dialogue_custom;
    LinearLayout llTest, llPractice_test, llVideos, llTeacher, llNotes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_n);
        //topic_btn_test = (Button) findViewById(R.id.btn_topic_test);

        getCustomDialogue();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.topic_swipe_refresh_layout);
        errorText = (TextView) findViewById(R.id.error_text);
        loader = (ProgressBar) findViewById(R.id.feed_loading);
        tv_chaptername = (TextView) findViewById(R.id.crd_main_topic);
        tv_subjectname = (TextView) findViewById(R.id.crd_main_sub);
        swipeContainer.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_topic);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        shared = getSharedPreferences("MyPrefs", 0);
        userID = shared.getString("userID", null);
        Class = shared.getString("Classs", null);
        section = shared.getString("Section", null);
        fname = shared.getString("fname", null);
        lname = shared.getString("lname", null);
        board = shared.getString("Board", null);
        emailID = shared.getString("emailID", null);
        //Log.e("ssection_t", userID);


        img_test = (ImageView) findViewById(R.id.img_t_test);
        img_report = (ImageView) findViewById(R.id.img_t_report);


        Intent intent = getIntent();
        ClassId = intent.getStringExtra("ClassId");
        idbasedonchapter = intent.getIntExtra("chapter_id", 0);
        chapter_name = intent.getStringExtra("chapter_name");
        subject_name = intent.getStringExtra("subject_name");
        subject_id = intent.getIntExtra("subject_id", 0);
        Typeface type = Typeface.createFromAsset(getAssets(), "fonts/ts_normal.ttf");

        tv_chaptername.setText(chapter_name);
        tv_chaptername.setTypeface(type);
        tv_chaptername.setGravity(Gravity.CENTER);
        tv_subjectname.setText(subject_name);

/*

        View bottomSheet = findViewById(R.id.design_bottom_sheet1);
        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_SETTLING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Log.i("BottomSheetCallback", "BottomSheetBehavior.STATE_HIDDEN");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Log.i("BottomSheetCallback", "slideOffset: " + slideOffset);
            }
        });
*/


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

      /*  img_profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudentProfile.class);
                startActivity(i);
            }
        });
        img_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               *//* if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }*//*


                Intent i = new Intent(getApplicationContext(), TestList.class);
                i.putExtra("level_id", topic_id);
                i.putExtra("subject_id", subject_id);
                i.putExtra("level_type", "T");
                i.putExtra("Classs", Class);
                i.putExtra("Section", section);
                i.putExtra("ClassId", ClassId);
                startActivity(i);

                //Intent intent = new Intent(getApplicationContext(), getTestId.class);
                //Intent intent = new Intent(v.getContext(), TestListNumber.class);

                *//*i.putExtra("Classs", Class);
                i.putExtra("fname", fname);
                i.putExtra("lname", lname);
                i.putExtra("Board", board);
                i.putExtra("userID", userID);
                i.putExtra("emailID", emailID);
                i.putExtra("topic_id", topic_id);
                i.putExtra("type", "T");
                i.putExtra("studentid", userID);
                i.putExtra("stu_sub", subject_name);
                i.putExtra("chapter_name", chapter_name);
                i.putExtra("topic_name", topic_name);
                i.putExtra("subject_id",subject_id);
                startActivity(i);*//*
                //finish();

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
        });
        img_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();

            }
        });
*/

        if (isNetworkAvailable()) {
            getTopic();
            loader.setVisibility(VISIBLE);
            errorText.setVisibility(GONE);
            swipeContainer.setRefreshing(false);
        } else {
            errorText.setText(R.string.error_string);
            errorText.setVisibility(VISIBLE);
            loader.setVisibility(GONE);

        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        topic_id = beanTopiclistlist.get(position).getId();
                        topic_name = beanTopiclistlist.get(position).getTopicName();


                       /* topic_id = beanTopiclistlist.get(position).getId();
                        topic_name = beanTopiclistlist.get(position).getTopicName();


                        Intent i = new Intent(getApplicationContext(), TestList.class);
                        i.putExtra("level_id", topic_id);
                        i.putExtra("subject_id", subject_id);
                        i.putExtra("level_type", "T");
                        i.putExtra("Classs", Class);
                        i.putExtra("Section", section);
                        i.putExtra("ClassId", ClassId);
                        startActivity(i);*/


                        dialogue_custom.show();

                    }
                })
        );
    }

    void getTopic() {


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl(URL_Mapping.SUBJECTS_URL)
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);
        final BeanTopic beanChapter = new BeanTopic();
        beanChapter.setId(idbasedonchapter);
        Log.e("idbasedonchapter", idbasedonchapter + "");
        Call<List<BeanTopic>> call1 = service1.gettopiclist(idbasedonchapter);
        call1.enqueue(new Callback<List<BeanTopic>>() {
            @Override
            public void onResponse(Call<List<BeanTopic>> call, Response<List<BeanTopic>> response) {

                loader.setVisibility(GONE);
                swipeContainer.setRefreshing(false);
                errorText.setVisibility(GONE);
                beanTopiclistlist = response.body();
                Log.e("message_body", response.message());

                if (beanTopiclistlist.isEmpty())

                {
                    errorText.setText("Topic Does not Exist");
                    errorText.setVisibility(VISIBLE);
                    errorText.setTextSize(20);


                } else {
                    setAdapterRecyclerview();
                }

            }

            @Override
            public void onFailure(Call<List<BeanTopic>> call, Throwable t) {
                Log.e("problemm", t.toString());
                Toast.makeText(Topic.this, "Connection Timeout", Toast.LENGTH_LONG).show();
                loader.setVisibility(GONE);
            }
        });
    }

    private void setAdapterRecyclerview() {
        adapter = new TopicAdapter(getApplicationContext(),beanTopiclistlist);
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


            final AlertDialog.Builder builder = new AlertDialog.Builder(Topic.this);
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

    /**
     *
     */
    public void getCustomDialogue() {

        dialogue_custom = new Dialog(Topic.this);
        dialogue_custom.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogue_custom.setContentView(R.layout.custom_topic_grid);
        dialogue_custom.setCanceledOnTouchOutside(false);
        llTest = (LinearLayout) dialogue_custom.findViewById(R.id.llTest);
        llTest.setOnClickListener(this);
        llPractice_test = (LinearLayout) dialogue_custom.findViewById(R.id.llPractice_test);
        llPractice_test.setOnClickListener(this);
       /* llVideos = (LinearLayout) dialogue_custom.findViewById(R.id.llVideo);
        llVideos.setOnClickListener(this);
        llTeacher = (LinearLayout) dialogue_custom.findViewById(R.id.llTeacher);
        llTeacher.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.llTest:






                Intent i = new Intent(getApplicationContext(), TestList.class);
                i.putExtra("level_id", topic_id);
                i.putExtra("subject_id", subject_id);
                i.putExtra("level_type", "T");
                i.putExtra("Classs", Class);
                i.putExtra("Section", section);
                i.putExtra("ClassId", ClassId);
                startActivity(i);


                dialogue_custom.dismiss();
                break;


            case R.id.llPractice_test:

                Toast.makeText(getApplicationContext(),"SSSS",Toast.LENGTH_LONG).show();

                Intent practiceIntent = new Intent(getApplicationContext(), PracticeTest.class);
                practiceIntent.putExtra("level_id", topic_id);
                practiceIntent.putExtra("level_type", "T");
                startActivity(practiceIntent);
                dialogue_custom.dismiss();
                break;


          /*  case R.id.llVideo:
                Toast.makeText(getApplicationContext(), "Please Subscribe It By Contacting Helpline Number-8882622000", Toast.LENGTH_LONG).show();
                dialogue_custom.dismiss();
                break;

            case R.id.llTeacher:
                Toast.makeText(getApplicationContext(), "Please Subscribe It By Contacting Helpline Number-8882622000", Toast.LENGTH_LONG).show();
                dialogue_custom.dismiss();
                break;*/


            default:
                break;
        }
    }
}
