package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;

import testtaking.app.com.myapplication.model.LoginSuccess;
import testtaking.app.com.myapplication.network.URL_Mapping;

/**
 * Created by PAWAN on 10/6/2017.
 */

public class Login extends AppCompatActivity {

    EditText et_username, user_password;
    Button btn_login, btn_reset_password, btn_reset_email;
    List<LoginSuccess> loginSuccess;
    String str_email, str_password;
    ProgressDialog progressDialog;
    String email_id;
    String user_id;
    String class_id, classs_name, section, fname, lname, registration_type, board;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_log_in);

        sharedPreferences = getSharedPreferences("MyPrefs", 0);

        et_username = (EditText) findViewById(R.id.etEmail);
        et_username.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        user_password = (EditText) findViewById(R.id.etPass);
        btn_login = (Button) findViewById(R.id.btn_signInn);
        btn_reset_password = (Button) findViewById(R.id.btnRestPassword);
        btn_reset_email = (Button) findViewById(R.id.btn_resetemail);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Logging...");
                progressDialog.show();
                str_email = et_username.getText().toString().trim();
                str_password = user_password.getText().toString();

                login_user();

            }
        });


        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irp = new Intent(Login.this, ResetPasswordActivity.class);
                startActivity(irp);
            }
        });


        btn_reset_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(getApplicationContext(), VerificaitonActivity.class);
                startActivity(i);

            }
        });

    }

    public void login_user() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface service = retrofit.create(RequestInterface.class);


        Call<List<LoginSuccess>> call = service.userLogin(str_email, str_password);

        call.enqueue(new Callback<List<LoginSuccess>>() {
            @Override
            public void onResponse(Call<List<LoginSuccess>> call, Response<List<LoginSuccess>> response) {
                progressDialog.dismiss();
                loginSuccess = response.body();
                String msg = response.message();
                if (response.isSuccessful()) {

                        email_id = loginSuccess.get(0).getEmail_id();
                        user_id = loginSuccess.get(0).getUser_id();
                        String mssg = loginSuccess.get(0).getMessage();
                        Toast.makeText(getApplicationContext(), mssg, Toast.LENGTH_LONG).show();
                        int successvalue = loginSuccess.get(0).getSuccess();


                        if (successvalue == 1) {


                        class_id = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getId());
                        classs_name = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getClassName();
                        section = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getSection();
                        fname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getFirstName();
                        lname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getLastName();
                        registration_type = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getRegistrationType());
                        board = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getBoard().getBoardName();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ClassId", class_id);
                        editor.putString("Classs", classs_name);
                        editor.putString("Section", section);
                        editor.putString("fname", fname);
                        editor.putString("lname", lname);
                        editor.putString("registration_type", registration_type);
                        editor.putString("Board", board);
                        editor.putString("emailID", email_id);
                        editor.putString("userID", user_id);
                        editor.commit();


                        //Intent i = new Intent(getApplicationContext(), Subject.class);
                        Intent i = new Intent(getApplicationContext(),BottomNavigationActivity.class);
                        i.putExtra("email_id", email_id);
                        i.putExtra("user_id", user_id);
                        startActivity(i);
                        finishAffinity();
                    } else if (successvalue == 2) {
                        snack_barmessage("Please Varify EmailId");
                    } else if (successvalue == 4) {
                        Intent i = new Intent(getApplicationContext(), UserClassRegistrationActivity.class);
                        i.putExtra("email_id", email_id);
                        i.putExtra("user_id", user_id);
                        startActivity(i);
                        finish();
                    } else if (successvalue == 5) {


                        class_id = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getId());
                        classs_name = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getClassName();
                        section = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getSection();
                        fname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getFirstName();
                        lname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getLastName();
                        registration_type = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getRegistrationType());
                        board = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getBoard().getBoardName();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ClassId", class_id);
                        editor.putString("Classs", classs_name);
                        editor.putString("Section", section);
                        editor.putString("fname", fname);
                        editor.putString("lname", lname);
                        editor.putString("registration_type", registration_type);
                        editor.putString("Board", board);
                        editor.putString("emailID", email_id);
                        editor.putString("userID", user_id);
                        editor.commit();

                        Intent i = new Intent(getApplicationContext(), UserPersonalDetailRegistrationActivity.class);
                        i.putExtra("email_id", email_id);
                        i.putExtra("user_id", user_id);
                        startActivity(i);
                        finish();
                    } else if (successvalue == 6) {

                        class_id = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getId());
                        classs_name = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getClassName();
                        section = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getSection();
                        fname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getFirstName();
                        lname = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getLastName();
                        registration_type = String.valueOf(loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getRegistrationType());
                        board = loginSuccess.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getBoard().getBoardName();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("ClassId", class_id);
                        editor.putString("Classs", classs_name);
                        editor.putString("Section", section);
                        editor.putString("fname", fname);
                        editor.putString("lname", lname);
                        editor.putString("registration_type", registration_type);
                        editor.putString("Board", board);
                        editor.putString("emailID", email_id);
                        editor.putString("userID", user_id);
                        editor.commit();


                        Intent i = new Intent(getApplicationContext(), UserAddressRegistrationActivity.class);
                        i.putExtra("email_id", email_id);
                        i.putExtra("user_id", user_id);
                        startActivity(i);
                        finish();
                    } else if (successvalue == 7) {

                        snack_barmessage("registration status o!!!");

                    } else if (successvalue == 0) {

                        snack_barmessage("Wrong EmailId or Password!!!");
                    } else {
                        snack_barmessage("Something Went Wrong!!");
                    }

                }else{

                    Toast.makeText(getApplicationContext(),"Not Able to Connect with WebServer", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoginSuccess>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Failed to Connect with WebServer", Toast.LENGTH_LONG).show();

                //Log.e("EXX", t.getMessage());
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
