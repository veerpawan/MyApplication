package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

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
import testtaking.app.com.myapplication.network.URL_Mapping;

public class UserAddressRegistrationActivity extends AppCompatActivity {

    MaterialBetterSpinner mbsState;
    String str_state, email_id, str_city, str_locality, str_pincode, user_id;
    EditText et_city, et_locality, et_pincode;
    SharedPreferences shared;
    Button btn_submit_address_detail;
    List<SmsStatus> statusmessage;
    ProgressDialog pDialog;

    String[] arr_states = {"Andhra Pradesh", "Andaman & Nicobar Islands",
            "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Dadra & Nagar Haveli ", "Delhi", "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu & Kashmir",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Mizoram",
            "Nagaland",
            "Orissa",
            "Punjab",
            "Puducherry ",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Tripura",
            "Uttar Pradesh",
            "West Bengal",
            "Chhattisgarh",
            "Uttarakhand",
            "Jharkhand",
            "Telangana"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_address);


        Intent i = getIntent();
        email_id = i.getStringExtra("email_id");
        user_id = i.getStringExtra("user_id");
        //Log.e("inside_class", email_id);


        mbsState = (MaterialBetterSpinner) findViewById(R.id.spState);

        et_city = (EditText) findViewById(R.id.et_city);
        et_locality = (EditText) findViewById(R.id.et_locality);
        et_pincode = (EditText) findViewById(R.id.et_pincode);
        btn_submit_address_detail = (Button) findViewById(R.id.btn_submit_address_detail);

        btn_submit_address_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                str_city = et_city.getText().toString().trim();
                str_locality = et_locality.getText().toString().trim();
                str_pincode = et_pincode.getText().toString().trim();
                register_useraddress_detail();
            }
        });

        ArrayAdapter<String> state = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_states);

        state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsState.setAdapter(state);


        mbsState.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                str_state = arr_states[pos];

            }

        });
    }

    public void register_useraddress_detail() {
        pDialog = new ProgressDialog(UserAddressRegistrationActivity.this);
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

        Call<List<SmsStatus>> call1 = service1.registeraddressdetail(email_id, str_state, str_city, str_locality, str_pincode);
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
                        Intent ii = new Intent(getApplicationContext(), Subject.class);
                        ii.putExtra("user_id", user_id);
                        startActivity(ii);
                        finish();
                        snack_barmessage("Address Details Successfully Updated!!");

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
