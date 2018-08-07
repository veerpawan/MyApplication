package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

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

public class ResetPasswordActivity extends AppCompatActivity {

    EditText et_email;
    Button btn_resetpassword;
    ProgressDialog pDialog;
    String email;
    List<SmsStatus> emailstatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        this.setFinishOnTouchOutside(false);

        et_email = (EditText) findViewById(R.id.et_email);
        btn_resetpassword = (Button) findViewById(R.id.btn_resetpassword);
        btn_resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = et_email.getText().toString().trim();


                pDialog = new ProgressDialog(ResetPasswordActivity.this);
                pDialog.setMessage("Loading....Please Wait");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();

                reset_passord();
            }


        });

    }

    public void reset_passord() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<List<SmsStatus>> call = service.reset_password(email);
        call.enqueue(new Callback<List<SmsStatus>>() {
            @Override
            public void onResponse(Call<List<SmsStatus>> call, Response<List<SmsStatus>> response) {
                pDialog.dismiss();

                emailstatus = response.body();


                if (emailstatus == null) {
                    snackbar("Something went Wrong.Try After Some Time");

                } else {

                    int success = emailstatus.get(0).getSuccess();
                    Log.e("user_e", success + "");
                    if (success == 1) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(ResetPasswordActivity.this);

                        LayoutInflater inflater = getLayoutInflater();
                        View alertLayout = inflater.inflate(R.layout.custom_dialog_resetemail, null);
                        alert.setView(alertLayout);
                        Button btnok = (Button) alertLayout.findViewById(R.id.btn_okem);

                        final AlertDialog dialog1 = alert.create();

                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();

                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                                finishAffinity();


                            }

                        });
                        dialog1.show();
                    } else if (success == 2) {
                        snackbar("EmailId Does not Exist!!");

                    } else {
                        snackbar("Something went Wrong.Try After Some Time");
                    }
                }
            }


            @Override
            public void onFailure(Call<List<SmsStatus>> call, Throwable t) {


                pDialog.dismiss();
                snackbar(t.toString());
                Log.e("user_e", t.toString());
            }

        });

    }

    public void snackbar(String message) {


        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

}
