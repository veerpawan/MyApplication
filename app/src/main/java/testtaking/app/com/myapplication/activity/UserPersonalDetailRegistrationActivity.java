package testtaking.app.com.myapplication.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.SmsStatus;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.model.BdeList;
import testtaking.app.com.myapplication.network.URL_Mapping;

public class UserPersonalDetailRegistrationActivity extends AppCompatActivity {

    MaterialBetterSpinner mbsGender, spReferencetype, sp_bde_list;
    String[] arr_gender = {"Male", "Female", "Other"};
    EditText et_dob, et_school_instuteName, et_metroName;
    int mYear, mMonth, mDay, mHour, mMinute;
    String str_gender, selected_bdeemail, str_reference_type, user_id;
    ProgressDialog pDialog;
    String strSch, strMetro, email_id, str_dob;
    String[] bdelist;
    List<BdeList> bdeLists;
    String[] spReferenceTypeArr = {"Facebook", "Google", "School/Institute", "Metro Station", "News Paper", "Employee Referral"};
    SharedPreferences shared;
    Button submit_personaldetail;
    List<SmsStatus> statusmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_personal);


        Intent i = getIntent();
        email_id = i.getStringExtra("email_id");
        user_id = i.getStringExtra("user_id");
        Log.e("inside_class", email_id);


        mbsGender = (MaterialBetterSpinner) findViewById(R.id.spGender);
        spReferencetype = (MaterialBetterSpinner) findViewById(R.id.spReferenceType);
        sp_bde_list = (MaterialBetterSpinner) findViewById(R.id.spinner_b_referred_by);
        et_dob = (EditText) findViewById(R.id.et_dob);
        et_school_instuteName = (EditText) findViewById(R.id.etSchInsName);
        et_metroName = (EditText) findViewById(R.id.etMetroName);
        submit_personaldetail = (Button) findViewById(R.id.btn_submit_personal_detail);


        submit_personaldetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_dob = et_dob.getText().toString().trim();


                register_userpersonal_detail();

            }
        });


        ArrayAdapter adp_reference_type = new ArrayAdapter(this, android.R.layout.simple_list_item_1, spReferenceTypeArr);

        adp_reference_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spReferencetype.setAdapter(adp_reference_type);


        ArrayAdapter<String> gender = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_gender);

        gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsGender.setAdapter(gender);


        getbdelist();


        mbsGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                str_gender = arr_gender[pos];

            }


        });


        spReferencetype.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 2) {
                    et_school_instuteName.setVisibility(View.VISIBLE);

                } else {
                    et_school_instuteName.setVisibility(View.GONE);
                }
                if (i == 3) {
                    et_metroName.setVisibility(View.VISIBLE);


                } else {
                    et_metroName.setVisibility(View.GONE);
                }

                if (i == 5) {
                    sp_bde_list.setVisibility(View.VISIBLE);

                } else {
                    sp_bde_list.setVisibility(View.GONE);

                }
                selected_bdeemail = spReferenceTypeArr[i];

                strSch = spReferenceTypeArr[2];

                strMetro = spReferenceTypeArr[3];

                str_reference_type = spReferenceTypeArr[i];

            }
        });
        et_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(UserPersonalDetailRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                et_dob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }


    public void getbdelist() {
        pDialog = new ProgressDialog(UserPersonalDetailRegistrationActivity.this);
        pDialog.setMessage("Loading....Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);

        Call<List<BdeList>> call1 = service1.bdelist("26");
        call1.enqueue(new Callback<List<BdeList>>() {
            @Override
            public void onResponse(Call<List<BdeList>> call, Response<List<BdeList>> response) {
                pDialog.dismiss();
                bdeLists = response.body();

                if (bdeLists == null) {
                    Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(i);
                    finish();

                }

                bde_list_adapter();

            }


            @Override
            public void onFailure(Call<List<BdeList>> call, Throwable t) {
                pDialog.dismiss();
                Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(i);
                finish();
                Toast.makeText(getApplicationContext(), "Something Went Wrong!!Please try after some time.", Toast.LENGTH_LONG).show();


            }
        });


    }

    public void bde_list_adapter() {
        bdelist = new String[bdeLists.size()];
        for (int i = 0; i < bdeLists.size(); i++) {

            bdelist[i] = bdeLists.get(i).getFirst_name() + " " + bdeLists.get(i).getMiddle_name() + " " + bdeLists.get(i).getLast_name();

        }


        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bdelist);


        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_bde_list.setAdapter(aa);
    }

    public void register_userpersonal_detail() {

        Log.e("hg_g", email_id);

        Log.e("hg_g", str_dob);
        Log.e("hg_g", str_gender);
        Log.e("hg_g", selected_bdeemail);


        pDialog = new ProgressDialog(UserPersonalDetailRegistrationActivity.this);
        pDialog.setMessage("Loading....Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


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

        Call<List<SmsStatus>> call1 = service1.registerpersonaldetail(email_id, str_dob, str_gender, selected_bdeemail);
        call1.enqueue(new Callback<List<SmsStatus>>() {
            @Override
            public void onResponse(Call<List<SmsStatus>> call, Response<List<SmsStatus>> response) {

                pDialog.dismiss();

                statusmessage = response.body();


                if (statusmessage == null) {

                    snack_barmessage("something went wrong. try after some time");

                } else {


                    int success = statusmessage.get(0).getSuccess();
                    if (success == 1) {
                        snack_barmessage("Personal Details Successfully Updated!!");
                        Intent ii = new Intent(getApplicationContext(), Subject.class);
                        ii.putExtra("user_id", user_id);
                        startActivity(ii);
                        finish();

                    } else {

                        snack_barmessage("something went wrong. try after some time");
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SmsStatus>> call, Throwable t) {
                pDialog.dismiss();
                snack_barmessage("Not able to Connect with server.Try After Some time");

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
