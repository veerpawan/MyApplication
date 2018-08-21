package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.PracticeQuestionList;
import testtaking.app.com.myapplication.model.SiglePracticeQuestion;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.DialogsUtils;


public class PracticeTest extends AppCompatActivity {

    int import_level_id;
    String import_level_type;

    CheckBox ch1, ch2, ch3, ch4;


    TextView tv_question, tv_opt_1, tv_opt_2, tv_opt_3, tv_opt_4;
    Button btn_check, btn_next, btn_exit;
    private int correct_answer;


    public static int q_number = 0;

    List<Integer> questions_list = new ArrayList<>();
    List<PracticeQuestionList> practiceQuestionLists;

    List<SiglePracticeQuestion> siglePracticeQuestions;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test);


        initviews();


        getQuestionsList(import_level_id, import_level_type);


        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getApplicationContext(), correct_answer + "", Toast.LENGTH_LONG).show();


            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getPerticularQuestion(practiceQuestionLists.get(0).getQUESTIONIDLIST().get(q_number));

            }
        });


        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Subject.class);
                startActivity(i);
                finish();
            }
        });


        ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Toast.makeText(getApplicationContext(), tv_opt_1.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        ch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });

        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });

        ch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            }
        });


    }

    public void getQuestionsList(int import_level_id, String import_level_type) {

        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<PracticeQuestionList>> call1 = apiService.getQuestionsList(import_level_id, import_level_type);


        call1.enqueue(new Callback<List<PracticeQuestionList>>() {
            @Override

            public void onResponse(Call<List<PracticeQuestionList>> call, Response<List<PracticeQuestionList>> response) {


                if (response.code() == 200) {
                    practiceQuestionLists = response.body();
                    questions_list = response.body().get(0).getQUESTIONIDLIST();
                    getPerticularQuestion(practiceQuestionLists.get(0).getQUESTIONIDLIST().get(0));

                } else {


                }

            }

            @Override
            public void onFailure(Call<List<PracticeQuestionList>> call, Throwable t) {


            }
        });


    }

    public void getPerticularQuestion(int val) {


        pDialog= DialogsUtils.showProgressDialog(this,"Getting next...");





        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<SiglePracticeQuestion>> call1 = apiService.getIndivisualQuestion(val);


        call1.enqueue(new Callback<List<SiglePracticeQuestion>>() {
            @Override

            public void onResponse(Call<List<SiglePracticeQuestion>> call, Response<List<SiglePracticeQuestion>> response) {
                pDialog.dismiss();
                if (response.code() == 200) {


                     siglePracticeQuestions = response.body();
                    for (int i = 0; i < siglePracticeQuestions.size(); i++) {
                        tv_opt_1.setText(siglePracticeQuestions.get(i).getOptionGroup().getOption1() + "");
                        tv_opt_2.setText(siglePracticeQuestions.get(i).getOptionGroup().getOption2() + "");
                        tv_opt_3.setText(siglePracticeQuestions.get(i).getOptionGroup().getOption3() + "");
                        tv_opt_4.setText(siglePracticeQuestions.get(i).getOptionGroup().getOption4() + "");
                        tv_question.setText(siglePracticeQuestions.get(i).getQuestion_text());

                        correct_answer = siglePracticeQuestions.get(i).getOptionGroup().getCorrect();

                    }

                    q_number++;

                } else {

                    Toast.makeText(getApplicationContext(), "Not able to connect with web server", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<SiglePracticeQuestion>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Not able to connect with web server", Toast.LENGTH_LONG).show();
                pDialog.dismiss();

            }
        });


    }

    public void initviews() {

        tv_question = findViewById(R.id.tv_question);
        tv_opt_1 = findViewById(R.id.tv_opt1);
        tv_opt_2 = findViewById(R.id.tv_opt2);
        tv_opt_3 = findViewById(R.id.tv_opt3);
        tv_opt_4 = findViewById(R.id.tv_opt4);
        btn_check = findViewById(R.id.btn_check);
        btn_next = findViewById(R.id.btn_next);
        btn_exit = findViewById(R.id.btn_close);
        ch1 = findViewById(R.id.rd_1);
        ch2 = findViewById(R.id.rd_2);
        ch3 = findViewById(R.id.rd_3);
        ch4 = findViewById(R.id.rd_4);



        Intent i = getIntent();
        import_level_id = i.getIntExtra("level_id", 0);
        import_level_type = i.getStringExtra("level_type");
        // Toast.makeText(getApplicationContext(), import_level_id + "      " + import_level_type, Toast.LENGTH_LONG).show();


    }
}
