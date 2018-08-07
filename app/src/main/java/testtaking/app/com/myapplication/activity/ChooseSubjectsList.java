package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.StudentSubject;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.model.LoginSuccess;
import testtaking.app.com.myapplication.network.URL_Mapping;
import testtaking.app.com.myapplication.utils.DialogsUtils;

public class ChooseSubjectsList extends AppCompatActivity {

    List<StudentSubject> studentSubjects;

    List<LoginSuccess> statusmessage;
    ProgressDialog pDialog;
    private String email_id, selected_board_id, chk_selected_class, str_stream, selected_medium;

    SharedPreferences sharedPreferences;
    String class_id, classs_name, section, fname, lname, registration_type, board, user_id;


    ChooseSubjectsAdapter adapter;
    RecyclerView recycler_view_subject_list;
    Button btn_submit_subjects;
    List<Integer> subjectIds = ChooseSubjectsAdapter.subject_ids;
    JSONArray jsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_choose_subject_list);

        initviews();

        btn_submit_subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsArray = new JSONArray(subjectIds);

                register_userclassdetail();

            }
        });


    }

    public void register_userclassdetail() {

        pDialog = DialogsUtils.showProgressDialog(this, "scheduling Subjects....");

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

        Call<List<LoginSuccess>> call1 = service1.registerclassdetails(email_id, selected_board_id, chk_selected_class, str_stream, selected_medium, jsArray.toString());
        call1.enqueue(new Callback<List<LoginSuccess>>() {
            @Override
            public void onResponse(Call<List<LoginSuccess>> call, Response<List<LoginSuccess>> response) {

                statusmessage = response.body();
                pDialog.dismiss();

                if (statusmessage == null) {

                    snack_barmessage("something went wrong. try after some time");

                } else {

                    email_id = statusmessage.get(0).getEmail_id();
                    user_id = statusmessage.get(0).getUser_id();
                    int success = statusmessage.get(0).getSuccess();
                    if (success == 1) {
                       /* email_id = statusmessage.get(0).getEmail_id();
                        ;*/


                        user_id = String.valueOf(statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getId());
                        class_id = String.valueOf(statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getId());
                        classs_name = statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getClassName();
                        section = statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getSection();
                        fname = statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getFirstName();
                        lname = statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getLastName();
                        //registration_type = String.valueOf(statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getRegistrationType());
                        board = statusmessage.get(0).getStudentClassSubjects().get(0).getStudentId().getClasss().getBoard().getBoardName();


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


                        snack_barmessage("Class Details Successfully Updated!!");

                        //Toast.makeText(getApplicationContext(), user_id, Toast.LENGTH_LONG).show();


                        Intent ii = new Intent(getApplicationContext(), Subject.class);
                        ii.putExtra("user_id", user_id);
                        startActivity(ii);
                        finishAffinity();


                    } else {

                        snack_barmessage("something went wrong. try after some time");
                    }
                }

            }

            @Override
            public void onFailure(Call<List<LoginSuccess>> call, Throwable t) {
                pDialog.dismiss();
                snack_barmessage(t.toString());
                //snack_barmessage("Not able to Connect with server.Try After Some time");

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

    public void initviews() {
        studentSubjects = UserClassRegistrationActivity.studentSubjects;

        // Toast.makeText(getApplicationContext(), studentSubjects.get(3).getSubjectName(), Toast.LENGTH_LONG).show();

        sharedPreferences = getSharedPreferences("MyPrefs", 0);

        Intent i = getIntent();
        email_id = i.getStringExtra("email_id");
        selected_board_id = i.getStringExtra("selected_board_id");
        chk_selected_class = i.getStringExtra("chk_selected_class");
        str_stream = i.getStringExtra("str_stream");
        selected_medium = i.getStringExtra("selected_medium");
        user_id = i.getStringExtra("user_id");
        recycler_view_subject_list = (RecyclerView) findViewById(R.id.recycler_view_subject_list);
        btn_submit_subjects = (Button) findViewById(R.id.btn_submit_subjects);
        initadapter(studentSubjects);


    }

    public void initadapter(List<StudentSubject> subjectlist) {


        adapter = new ChooseSubjectsAdapter(getApplicationContext(), subjectlist);
        RecyclerView.LayoutManager loLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view_subject_list.setLayoutManager(loLayoutManager);
        recycler_view_subject_list.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {

        subjectIds.clear();

        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
