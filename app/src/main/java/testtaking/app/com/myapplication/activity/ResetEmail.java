package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by PAWAN on 8/3/2017.
 */

public class ResetEmail extends AppCompatActivity {

    String emailId, user_name, user_email;
    EditText et_oldemail, et_newemail;
    Button btn_reset;
    String new_email, old_email;
    List<SmsStatus> emailstatus;
    AlertDialog alertDialog;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_email);

        Intent i = getIntent();
        user_name = i.getStringExtra("user_name");
        user_email = i.getStringExtra("user_email");
       // Log.e("uu_n", user_name);

        et_oldemail = (EditText) findViewById(R.id.et_oldemail);
        et_oldemail.setText(user_email);
        et_newemail = (EditText) findViewById(R.id.et_newemail);
        btn_reset = (Button) findViewById(R.id.btn_reset);


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new_email = et_newemail.getText().toString().trim();


                old_email = et_oldemail.getText().toString().trim();
                pDialog = new ProgressDialog(ResetEmail.this);
                pDialog.setMessage("Loading....Please Wait");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();

                resetemail();

            }
        });


    }

    public void resetemail() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface service = retrofit.create(RequestInterface.class);
        Call<List<SmsStatus>> call = service.reset_email(user_name, old_email, new_email);
        call.enqueue(new Callback<List<SmsStatus>>() {
            @Override
            public void onResponse(Call<List<SmsStatus>> call, Response<List<SmsStatus>> response) {
                pDialog.dismiss();

                emailstatus = response.body();
                if (emailstatus == null) {

                    Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something Went Wrong,try after some time" +
                            "" +
                            "", Snackbar.LENGTH_LONG);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();
                } else {

                    int success = emailstatus.get(0).getSuccess();
                    Log.e("user_e", success + "");
                    if (success == 1) {

                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Update Successfully", Snackbar.LENGTH_LONG);
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        textView.setTextColor(Color.YELLOW);
                        snackbar.show();
                        et_oldemail.setText("");
                        et_newemail.setText("");


                        AlertDialog.Builder alert = new AlertDialog.Builder(ResetEmail.this);

                        LayoutInflater inflater = getLayoutInflater();
                        View alertLayout = inflater.inflate(R.layout.custom_dialog_resetemail, null);
                        alert.setView(alertLayout);
                        Button btnok = (Button) alertLayout.findViewById(R.id.btn_okem);

                        final AlertDialog dialog1 = alert.create();

                        btnok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog1.dismiss();

                                Intent i=new Intent(getApplicationContext(),Login.class);
                                startActivity(i);
                                finishAffinity();


                            }

                        });
                        dialog1.show();


                    } else if (success == 2) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "EmailId is wrong", Snackbar.LENGTH_LONG);
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        textView.setTextColor(Color.YELLOW);
                        snackbar.show();
                    } else if (success == 0) {
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Email Id Already Exist!!", Snackbar.LENGTH_LONG);
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
                        textView.setTextColor(Color.YELLOW);
                        snackbar.show();

                    }

                }
            }

            @Override
            public void onFailure(Call<List<SmsStatus>> call, Throwable t) {


                pDialog.dismiss();
                Log.e("user_e", t.toString());
            }

        });


    }
}
