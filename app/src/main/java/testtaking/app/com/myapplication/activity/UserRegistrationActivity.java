package testtaking.app.com.myapplication.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.SelfEnablerRegistration;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.network.URL_Mapping;

public class UserRegistrationActivity extends AppCompatActivity {

    EditText etUFName, etULName, etUMName, etUEmail, etUPassword, etUConfPassword;
    Button btn_submit;
    String user_mobile;

    String os_version, mobile_model, mobile_brand;
    String serialnumber, operatorname, imeinumber;

    private static final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    String[] bdelist;
    String macAddress;
    ProgressDialog pDialog;
    String str_fname, str_mname, str_lname, str_email, str_password, str_conf_password;
    AwesomeValidation awesomeValidation;
    List<SelfEnablerRegistration> enablerRegistrationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.etUserEmail, Patterns.EMAIL_ADDRESS, R.string.emailerror);


        os_version = android.os.Build.VERSION.SDK;    // API Level
        mobile_model = android.os.Build.MODEL;          // Model
        mobile_brand = android.os.Build.BRAND;


        if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP)) {
            loadIMEI();
        } else {


            ContextCompat.checkSelfPermission(UserRegistrationActivity.this, android.Manifest.permission.READ_PHONE_STATE);
            ContextCompat.checkSelfPermission(UserRegistrationActivity.this, android.Manifest.permission.INTERNET);
            ContextCompat.checkSelfPermission(UserRegistrationActivity.this, android.Manifest.permission.BIND_TELECOM_CONNECTION_SERVICE);
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wInfo = wifiManager.getConnectionInfo();
            macAddress = wInfo.getMacAddress();
            //Log.e("mac_address", macAddress);

        }

        Intent i = getIntent();
        user_mobile = i.getStringExtra("user_mobile");

        etUFName = (EditText) findViewById(R.id.etFName);
        etULName = (EditText) findViewById(R.id.etLName);
        etUMName = (EditText) findViewById(R.id.etMName);
        etUEmail = (EditText) findViewById(R.id.etUserEmail);
        etUPassword = (EditText) findViewById(R.id.etPassword);
        etUConfPassword = (EditText) findViewById(R.id.etConfPassword);
        btn_submit = (Button) findViewById(R.id.btn_signInn);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_fname = etUFName.getText().toString().trim();
                str_mname = etUMName.getText().toString().trim();
                str_lname = etULName.getText().toString().trim();
                str_email = etUEmail.getText().toString().trim();
                str_password = etUPassword.getText().toString().trim();
                str_conf_password = etUConfPassword.getText().toString().trim();

                if (str_password.equals(str_conf_password)) {

                    pDialog = new ProgressDialog(UserRegistrationActivity.this);
                    pDialog.setMessage("Loading....Please Wait");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                    if (awesomeValidation.validate()) {

                        if ((str_fname.equals("")) && (str_password.equals(""))) {
                            pDialog.dismiss();
                            snack_barmessage("Please Enter Correct Fields!!");
                        } else {

                            register_user();
                        }
                    } else {
                        pDialog.dismiss();
                        snack_barmessage("Please Enter Correct Email!!");
                    }

                } else {
                    pDialog.dismiss();
                    snack_barmessage("Password doesn't match!!");
                }


            }
        });


    }

    public void register_user() {


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

        Call<List<SelfEnablerRegistration>> call1 = service1.registerselfenablerstudent(str_fname, str_mname, str_lname, str_email, str_password, user_mobile, imeinumber, os_version, mobile_model, mobile_brand);
        call1.enqueue(new Callback<List<SelfEnablerRegistration>>() {
            @Override
            public void onResponse(Call<List<SelfEnablerRegistration>> call, Response<List<SelfEnablerRegistration>> response) {

                pDialog.dismiss();
                if (response.isSuccessful()) {

                    enablerRegistrationList = response.body();

                    int success = enablerRegistrationList.get(0).getSuccess();
                    if (success == 1) {

                        snack_barmessage("Successfully Register!!");

                        LayoutInflater inflater = getLayoutInflater();
                        View alertLayout = inflater.inflate(R.layout.custom_dialog, null);
                        final Button btnok = (Button) alertLayout.findViewById(R.id.btn_ok);
                        AlertDialog.Builder alert = new AlertDialog.Builder(UserRegistrationActivity.this);
                        alert.setView(alertLayout);
                        alert.setCancelable(false);

                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                                finishAffinity();
                            }
                        });

                        AlertDialog dialog = alert.create();
                        dialog.show();


                    } else if (success == 3) {
                        snack_barmessage("EmailId Already Exist!!");

                    } else {
                        snack_barmessage("Something went Wrong. Try After Sometime!!");

                    }


                } else {
                    snack_barmessage("Not able to Connect with server.Try After Some time");
                }
            }

            @Override
            public void onFailure(Call<List<SelfEnablerRegistration>> call, Throwable t) {
                pDialog.dismiss();
                snack_barmessage("Not able to Connect with server.Try After Sometime");

            }
        });


    }

    public void loadIMEI() {
        // Check if the READ_PHONE_STATE permission is already available.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE)
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
         WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        macAddress = wInfo.getMacAddress();


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
