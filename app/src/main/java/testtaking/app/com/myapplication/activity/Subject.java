package testtaking.app.com.myapplication.activity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.BuildConfig;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.SubjectAdapter;
import testtaking.app.com.myapplication.model.StudentSubject;
import testtaking.app.com.myapplication.model.TestBean;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.model.AppApkDetails;
import testtaking.app.com.myapplication.model.CheckAppUpdate;
import testtaking.app.com.myapplication.network.URL_Mapping;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by pawan on 10/24/2016.
 */

public class Subject extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    String userID;
    List<StudentSubject> studentSubjects;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView errorText;
    private Context context;
    private int studentid;
    private SwipeRefreshLayout swipeContainer;
    String fname;
    String lname;
    String board;
    String emailID;
    String ClassId;
    String Class;
    String section;
    SharedPreferences shared;
    LinearLayout linearLayout_btn;
    private ProgressBar loader;
    TextView tv_image;
    String sub_name;
    ImageView img_chapter, img_test, img_report, img_video, img_teacher, img_notes;
    int sub_id;
    String registration_type;

    List<TestBean> demoTestBeen;
    ImageView img_profile;
    TextView txtProfileName;
    int user_iid;

    String appPackageName = "testtaking.app.com.myapplication";

    static final String TAG_app_package_name = "app_package_name";
    static final String TAG_app_version_code = "app_version_code";
    static final String TAG_app_version_name = "app_version_name";
    static boolean updatedStatus = true;
    String message = null;
    AppApkDetails appApkDetails = new AppApkDetails();
    ProgressDialog pDialog;
    CheckAppUpdate checkAppUpdates;
    public int appcode;
    int versionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_n);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.subject_swipe_refresh_layout);
        versionCode = BuildConfig.VERSION_CODE;

        //code for getting key
       /* try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "testtaking.app.com.myapplication",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }*/


        Intent i = getIntent();
        userID = i.getStringExtra("user_id");

        if (userID == null) {

            shared = getSharedPreferences("MyPrefs", 0);
            userID = shared.getString("userID", null);
            ClassId = shared.getString("ClassId", null);
        }
       /* Class = i.getStringExtra("Classs");
        section = i.getStringExtra("Section");
        fname = i.getStringExtra("fname");
        lname = i.getStringExtra("lname");
        board = i.getStringExtra("Board");
        userID = i.getStringExtra("user_id");
        emailID = i.getStringExtra("emailID");


        shared = getSharedPreferences("MyPrefs", 0);
        userID = shared.getString("userID", null);
        ClassId = shared.getString("ClassId", null);
        Class = shared.getString("Classs", null);
        section = shared.getString("Section", null);
        fname = shared.getString("fname", null);
        lname = shared.getString("lname", null);
        board = shared.getString("Board", null);
        emailID = shared.getString("emailID", null);
        registration_type = shared.getString("registration_type", null);
*/


     /*   View bottomSheet = findViewById(R.id.design_bottom_sheet1);
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

        if (isNetworkAvailable()) {
            checkappupdate();
        } else {
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "No internet connection!", Snackbar.LENGTH_LONG)
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

        }


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


      /*  img_chapter = (ImageView) findViewById(R.id.img_g_chapter);
        img_test = (ImageView) findViewById(R.id.img_g_test);
        img_report = (ImageView) findViewById(R.id.img_g_report);
        img_video = (ImageView) findViewById(R.id.img_g_video);
        img_teacher = (ImageView) findViewById(R.id.img_g_teacher);
        img_notes = (ImageView) findViewById(R.id.img_g_notes);


        img_profile = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.profile_image);
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), StudentProfile.class);
                startActivity(i);
            }
        });

        img_chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"ch",Toast.LENGTH_LONG).show();
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }

                Intent i = new Intent(getApplicationContext(), Chapter.class);
                //Intent i = new Intent(getApplicationContext(), gridviewofsubject.class);
                //Toast.makeText(getApplicationContext(), userID + "", Toast.LENGTH_LONG).show();
                i.putExtra("subject_id", sub_id);
                i.putExtra("subject_name", sub_name);
                i.putExtra("ClassId", ClassId);
                startActivity(i);
                //finish();


            }
        });
        img_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (behavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                Intent i = new Intent(getApplicationContext(), TestList.class);
                i.putExtra("level_id", sub_id);
                i.putExtra("level_type", "S");
                i.putExtra("Classs", Class);
                i.putExtra("Section", section);
                i.putExtra("ClassId", ClassId);
                startActivity(i);

                //get_demoTests(sub_id,"S");
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
        });
        img_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "will update soon", Toast.LENGTH_LONG).show();

            }
        });*/


        recyclerView = (RecyclerView) findViewById(R.id.subject_recycler_view);
        errorText = (TextView) findViewById(R.id.error_text);
        recyclerView.setHasFixedSize(true);
        loader = (ProgressBar) findViewById(R.id.feed_loading);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);


        recyclerView.setLayoutManager(layoutManager);
        swipeContainer.setOnRefreshListener(this);
        swipeContainer.setRefreshing(false);

        if (isNetworkAvailable()) {
            getSubject();


        } else {

            errorText.setText(R.string.error_string);
            errorText.setVisibility(VISIBLE);
            loader.setVisibility(GONE);

        }
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        sub_name = studentSubjects.get(position).getSubjectName();
                        sub_id = studentSubjects.get(position).getId();


                        Intent i = new Intent(getApplicationContext(), Chapter.class);
                        //Intent i = new Intent(getApplicationContext(), gridviewofsubject.class);
                        //Toast.makeText(getApplicationContext(), userID + "", Toast.LENGTH_LONG).show();
                        i.putExtra("subject_id", sub_id);
                        i.putExtra("subject_name", sub_name);
                        /* i.putExtra("ClassId", ClassId);*/
                        i.putExtra("user_id", userID);
                        startActivity(i);


                    }
                })
        );


    }


    public void getSubject() {
        loader.setVisibility(VISIBLE);


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
        //final StudentSubject studentSubject = new StudentSubject();
        //studentSubject.setId(Integer.parseInt(userID));

        Call<List<StudentSubject>> call1 = service1.registerUser(userID);

        call1.enqueue(new Callback<List<StudentSubject>>() {
            @Override

            public void onResponse(Call<List<StudentSubject>> call, Response<List<StudentSubject>> response) {
                loader.setVisibility(GONE);
                errorText.setVisibility(GONE);
                studentSubjects = response.body();
                String mes = response.message();


                if (!response.isSuccessful()) {

                    Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(i);
                    finish();
                    //Toast.makeText(getApplicationContext(), "Please", Toast.LENGTH_SHORT).show();

                } else {
                    Iterator<StudentSubject> studentSubjectIterator = studentSubjects.iterator();
                    while (studentSubjectIterator.hasNext()) {
                        StudentSubject subject = (StudentSubject) studentSubjectIterator.next();
                        studentid = subject.getId();

                    }
                    setAdapterRecyclerview();
                }

            }

            @Override
            public void onFailure(Call<List<StudentSubject>> call, Throwable t) {

                Toast.makeText(Subject.this, "Connection Timeout", Toast.LENGTH_LONG).show();
                swipeContainer.setRefreshing(false);
                loader.setVisibility(GONE);
            }
        });
    }

    private void setAdapterRecyclerview() {
        adapter = new SubjectAdapter(studentSubjects, getApplicationContext());
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

        getSubject();
        swipeContainer.setRefreshing(false);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_setting) {


            final AlertDialog.Builder builder = new AlertDialog.Builder(Subject.this);
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

            Intent iam = new Intent(Subject.this, ThreeAmFriendActivity.class);
            startActivity(iam);

            //Toast.makeText(getApplicationContext(), "Please Subscribe it by contacting help line number 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_expertshere) {

            Intent ies = new Intent(Subject.this, ExpertShareActivity.class);
            startActivity(ies);
            //Toast.makeText(getApplicationContext(), "Please Subscribe it by contacting help line number 8882622000", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    public void checkappupdate() {


        pDialog = new ProgressDialog(Subject.this);
        pDialog.setMessage("Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(150, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);
        Call<CheckAppUpdate> call1 = service1.checkappversion("Android");
        call1.enqueue(new Callback<CheckAppUpdate>() {
            @Override
            public void onResponse(Call<CheckAppUpdate> call, Response<CheckAppUpdate> response) {
                pDialog.dismiss();
                checkAppUpdates = response.body();
                //Log.e("res_message", response.message());


                if (checkAppUpdates == null) {

                    Toast.makeText(getApplicationContext(), "Not able to Connect with Web Server. Try Again!", Toast.LENGTH_SHORT).show();


                } else {
                    appcode = Integer.parseInt(checkAppUpdates.getApp_version_code());
                    //Log.e("app_code", appcode + "");


                    if ((versionCode == appcode)) {
                        //Toast.makeText(getApplicationContext(), "app update", Toast.LENGTH_SHORT).show();
                        //checkimei();


                    } else {
                        final android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(Subject.this);
                        builder2.setMessage("New Version of App Found.").setPositiveButton("Update Now", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialoginterface, int i) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=testtaking.app.com.myapplication&hl=en"));
                                startActivity(intent);
                            }

                        });
                        builder2.setCancelable(false);
                        builder2.create().show();
                    }
                }


            }

            @Override
            public void onFailure(Call<CheckAppUpdate> call, Throwable t) {
                pDialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishAffinity();
                Log.e("eerr", t.toString());
            }
        });


    }
/*

    class CheckAppUpdate extends AsyncTask<String, String, String> {
        boolean failure = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {
            int success;
            try {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("app_id", "Android"));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(Constants.T4A_IP + "/"+URL_Mapping.CHECKAPPUPDATE, "POST", params);

                if (json.equals("null")) {
                    Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();
                } else {
                    success = json.getInt("app_id");
                    if (success == 0) {
                        Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();

                    } else if (success == 1) {
                        message = "1";
                        appApkDetails.setApp_package_name(json.getString(TAG_app_package_name));
                        appApkDetails.setApp_version_name(json.getString(TAG_app_version_name));
                        appApkDetails.setApp_version_code(json.getString(TAG_app_version_code));
                        Log.d("Successfully Login!", json.toString());

                        message = "1";
                        return message;
                    } else {
                        message = "0";
                        return message;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String message) {
            if (message.equals("0")) {
                Toast.makeText(getApplicationContext(), "something went wrong", Toast.LENGTH_LONG).show();


            } else if (message.equals("1")) {
                String app_package_name = appApkDetails.getApp_package_name();
                String app_version_name = appApkDetails.getApp_version_name();
                String app_version_code = appApkDetails.getApp_version_code();


                String curVersion = getCurrentVersion();
                Log.e("version-->", curVersion);
                Log.e("versionOld-->", app_version_name);

                if ((value(curVersion) == value(app_version_name))) {
                    setUpdatedStatus(true);
                    Log.d("updateStatusSameif-->", isUpdatedStatus() + "");
                } else {
                    setUpdatedStatus(false);
                    Log.d("updateStatusfinalels-->", isUpdatedStatus() + "");


                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.custom_alert_update, null);
                    final Button btnok = (Button) alertLayout.findViewById(R.id.btn_ok);
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(Subject.this);

                    builder2.setView(alertLayout);
                    // disallow cancel of AlertDialog on click of back button and outside touch
                    builder2.setCancelable(true);
                    btnok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName + "&hl=en"));
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder2.create();
                    dialog.show();

                   */
/* builder2.setMessage("New Version of App Found.").setPositiveButton("Update Now", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialoginterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName + "&hl=en"));
                            startActivity(intent);
                        }

                    });
                    builder2.setCancelable(false);
                    builder2.create().show();*//*



                }

            } else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(android.R.id.content), "There is something wrong..!!", Snackbar.LENGTH_LONG)
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

            }
        }
    }
*/

    public boolean isUpdatedStatus() {
        return updatedStatus;
    }

    public void setUpdatedStatus(boolean updatedStatus) {
        this.updatedStatus = updatedStatus;
    }

    public String getCurrentVersion() {
        String curVersion = null;
        try {
            curVersion = getApplicationContext().getPackageManager().getPackageInfo(appPackageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return curVersion;
    }

    private long value(String string) {
        string = string.trim();
        if (string.contains(".")) {
            final int index = string.lastIndexOf(".");
            return value(string.substring(0, index)) * 100 + value(string.substring(index + 1));
        } else {
            return Long.valueOf(string);
        }
    }


}