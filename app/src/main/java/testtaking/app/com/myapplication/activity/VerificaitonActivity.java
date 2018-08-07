package testtaking.app.com.myapplication.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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
import testtaking.app.com.myapplication.model.EmailIdByMobileAndDOB;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.network.URL_Mapping;

public class VerificaitonActivity extends AppCompatActivity {

    Button btn_reset_email;
    int mYear, mMonth, mDay, mHour, mMinute, nYear, nMonth, nDay, nHour, nMinute;

    EditText et_dob, et_mobile;
    Button btn_change_email;
    String str_mobile, str_dob;
    ProgressDialog pDialog;
    List<EmailIdByMobileAndDOB> successbean;
    String user_name;
    String user_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificaiton);

        btn_reset_email = (Button) findViewById(R.id.btn_change_email);
        et_dob = (EditText) findViewById(R.id.et_dob);
        et_mobile = (EditText) findViewById(R.id.et_mobile);
        btn_change_email = (Button) findViewById(R.id.btn_change_email);

        btn_reset_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irm = new Intent(VerificaitonActivity.this, ResetEmail.class);
                startActivity(irm);
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(VerificaitonActivity.this,
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
        btn_change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pDialog = new ProgressDialog(VerificaitonActivity.this);
                pDialog.setMessage("Please Wait");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();

                str_mobile = et_mobile.getText().toString().trim();
                str_dob = et_dob.getText().toString().trim();

                getemailbymobanddob();

            }
        });

    }

    public void getemailbymobanddob() {


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

        Call<List<EmailIdByMobileAndDOB>> call1 = service1.getemailbymobileanddob(str_mobile, str_dob);
        call1.enqueue(new Callback<List<EmailIdByMobileAndDOB>>() {
            @Override
            public void onResponse(Call<List<EmailIdByMobileAndDOB>> call, Response<List<EmailIdByMobileAndDOB>> response) {
                pDialog.dismiss();


                successbean=response.body();


                if (successbean == null) {
                    Toast.makeText(getApplicationContext(), "Something went wrong!!Please try after sometime", Toast.LENGTH_LONG).show();

                } else {


                    int success = successbean.get(0).getSuccess();
                    if (success == 1) {

                        user_name=successbean.get(0).getUser().get(0).getFirst_name();
                        user_email=successbean.get(0).getUser().get(0).getEmailid();
                        Intent i = new Intent(getApplicationContext(), ResetEmail.class);
                        i.putExtra("user_name",user_name);
                        i.putExtra("user_email",user_email);
                        startActivity(i);
                    } else if (success == 0) {
                        Toast.makeText(getApplicationContext(), "No Email Id Found!! Please Register", Toast.LENGTH_LONG).show();

                    }
                }


            }


            @Override
            public void onFailure(Call<List<EmailIdByMobileAndDOB>> call, Throwable t) {

                pDialog.dismiss();

                Toast.makeText(getApplicationContext(), "Not able to Connect with Server", Toast.LENGTH_LONG).show();


            }

        });


    }
}
