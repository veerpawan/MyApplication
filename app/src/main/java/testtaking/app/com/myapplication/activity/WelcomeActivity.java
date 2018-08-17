package testtaking.app.com.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

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
import testtaking.app.com.myapplication.model.ScheduleTestBean;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.model.CheckAppUpdate;
import testtaking.app.com.myapplication.model.OtpStatus;
import testtaking.app.com.myapplication.network.URL_Mapping;

/**
 * Created by umesh on 25-02-2017.
 */
public class WelcomeActivity extends AppCompatActivity {
    private ViewPager viewPager;

    //private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext, btn_login, btn_signup;
    private PreferenceManager prefManager;
    private static final int REQUEST = 112;
    Context mContext = this;
    String macAddress;
    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    String serialnumber, operatorname, imeinumber;
    List<ScheduleTestBean> scheduleTestBeen;
    String mobile_number;
    List<OtpStatus> smsStatuses;
    TextView imgg_1;
    int versionCode;
    CheckAppUpdate checkAppUpdates;
    public int appcode;
    ProgressDialog pDialog;
    private AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (Build.VERSION.SDK_INT >= 23) {
            Log.d("TAG", "@@@ IN IF Build.VERSION.SDK_INT >= 23");
            String[] PERMISSIONS = {
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    //android.Manifest.permission.INTERNET,
                    android.Manifest.permission.ACCESS_NETWORK_STATE,
                    android.Manifest.permission.ACCESS_WIFI_STATE,
                    //android.Manifest.permission.NFC,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    //Manifest.permission.ACCESS_COARSE_LOCATION,
                    //Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };


            if (!hasPermissions(mContext, PERMISSIONS)) {
                Log.d("TAG", "@@@ IN IF hasPermissions");
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST);
            } else {
                Log.d("TAG", "@@@ IN ELSE hasPermissions");
                callNextActivity();
            }
        } else {
            Log.d("TAG", "@@@ IN ELSE  Build.VERSION.SDK_INT >= 23");
            callNextActivity();
        }

        versionCode = BuildConfig.VERSION_CODE;
        Log.e("versionn_code", versionCode + "");
        // Checking for first time launch - before calling setContentView()
        prefManager = new PreferenceManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_welcome);


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


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        // btnSkip = (Button) findViewById(R.id.btn_skip);
        //btnNext = (Button) findViewById(R.id.btn_next);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkAvailable()) {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    //i.putExtra("device_imei", imeinumber);
                    startActivity(i);
                    finish();
                } else
                    snack_barmessage("no internet connection");
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkAvailable()) {
/*
                */


                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.custom_alert_walkins, null);
                    final ImageView img_walkins = (ImageView) alertLayout.findViewById(R.id.walkins);
                    final ImageView img_student = (ImageView) alertLayout.findViewById(R.id.student);


                    android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(WelcomeActivity.this);

                    alert.setView(alertLayout);


                    img_walkins.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent i = new Intent(getApplicationContext(), CollegeStudentRegistrationActivity.class);
                            startActivity(i);

                            //Toast.makeText(getApplicationContext(), "walk", Toast.LENGTH_LONG).show();
                        }
                    });


                    img_student.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            LayoutInflater inflater = getLayoutInflater();
                            View alertLayout = inflater.inflate(R.layout.custom_alert_input_mobile, null);
                            final EditText etmobile = (EditText) alertLayout.findViewById(R.id.ed_mobile);
                            final Button btn_mobile = (Button) alertLayout.findViewById(R.id.btn_submit);


                            android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(WelcomeActivity.this);

                            alert.setView(alertLayout);


                            btn_mobile.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    mobile_number = etmobile.getText().toString();
                                    if (mobile_number.equals("") || mobile_number.length() < 10) {
                                        //pDialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Please Enter correct Mobile number", Toast.LENGTH_LONG).show();
                                    } else {
                                        send_otp();
                                    }
                                }
                            });

                            android.support.v7.app.AlertDialog dialog = alert.create();
                            dialog.show();


                            //Toast.makeText(getApplicationContext(), "student", Toast.LENGTH_LONG).show();

                        }
                    });
                    android.support.v7.app.AlertDialog dialog = alert.create();
                    dialog.show();

                } else {

                    snack_barmessage("no internet connection");
                }


                /**/

            }
        });


        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.layout.slide_screen6,
                R.layout.slide_screen1,
                R.layout.slide_screen2,
                R.layout.slide_screen3,
                R.layout.slide_screen4,
                R.layout.slide_screen5,};


        // adding bottom dots
        addBottomDots(0);

        // making notification bar transparent
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

       /* btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });*/
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        /*startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
        finish();*/
    }

    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
               /* btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);*/
            } else {
                // still pages are left
            /*    btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);*/
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void callNextActivity() {


        if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)) {
            loadIMEI();
        } else {


            ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.READ_PHONE_STATE);
            ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.INTERNET);
            ContextCompat.checkSelfPermission(WelcomeActivity.this, Manifest.permission.BIND_TELECOM_CONNECTION_SERVICE);
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            macAddress = wInfo.getMacAddress();
            //Log.e("mac_address", macAddress);




            // Log.e("noMarsh-getPhoneEMINo:", imeinumber);
        }


    }

    public void loadIMEI() {
        // Check if the READ_PHONE_STATE permission is already available.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // READ_PHONE_STATE permission has not been granted.
            requestReadPhoneStatePermission();
        } else {
            // READ_PHONE_STATE permission is already been granted.
            doPermissionGrantedStuffs();
        }
    }

    private void requestReadPhoneStatePermission() {
        // READ_PHONE_STATE permission has not been granted yet. Request it directly.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
//        }
    }

    public void doPermissionGrantedStuffs() {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }


        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();
        /*Log.e("mac_address", macAddress);


        Log.e("Marsh-getSimID:", serialnumber);
        Log.e("Marsh-getSimCompany:", operatorname);
        Log.e("Marsh-getPhoneEMINo:", imeinumber);*/


    }


    public void send_otp() {
        pDialog = new ProgressDialog(WelcomeActivity.this);
        pDialog.setMessage("Loading Please Wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);

        Call<List<OtpStatus>> call1 = service1.sendotp(mobile_number);
        call1.enqueue(new Callback<List<OtpStatus>>() {
            @Override
            public void onResponse(Call<List<OtpStatus>> call, Response<List<OtpStatus>> response) {
                smsStatuses = response.body();
                pDialog.dismiss();
                if (smsStatuses == null) {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong!! Try after SomeTime!!", Toast.LENGTH_LONG).show();
                } else {

                    int success = smsStatuses.get(0).getSuccess();

                    int otp = smsStatuses.get(0).getOtp();

                    if (success == 1) {

                        Intent i = new Intent(getApplicationContext(), Acitivity_otp.class);
                        i.putExtra("user_mobile", mobile_number);
                        i.putExtra("user_otp", otp);
                        startActivity(i);
                        finish();
                    } else if (success == 2) {
                        Toast.makeText(getApplicationContext(), "mobile Alreasy Exist!!", Toast.LENGTH_LONG).show();

                    }
                }
            }


            @Override
            public void onFailure(Call<List<OtpStatus>> call, Throwable t) {

                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Something Went Wrong!! Try after SomeTime!!", Toast.LENGTH_LONG).show();

            }
        });


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

   /* class CheckAppUpdate extends AsyncTask<String, String, String> {
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
                params.add(new BasicNameValuePair("app_id", "1"));
                Log.d("request!", "starting");
                JSONObject json = jsonParser.makeHttpRequest(CHECK_VERSION_UPDATE_URL, "POST", params);

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
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(WelcomeActivity.this);

                    builder2.setView(alertLayout);
                    // disallow cancel of AlertDialog on click of back button and outside touch
                    builder2.setCancelable(false);
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

                   *//* builder2.setMessage("New Version of App Found.").setPositiveButton("Update Now", new DialogInterface.OnClickListener() {

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
    }*/

    public void checkappupdate() {


        pDialog = new ProgressDialog(WelcomeActivity.this);
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
                        final android.app.AlertDialog.Builder builder2 = new android.app.AlertDialog.Builder(WelcomeActivity.this);
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

    public void snack_barmessage(String message) {


        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
