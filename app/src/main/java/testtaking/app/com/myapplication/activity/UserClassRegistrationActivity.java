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
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.Classs;
import testtaking.app.com.myapplication.model.StudentSubject;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.network.URL_Mapping;
import testtaking.app.com.myapplication.utils.DialogsUtils;

public class UserClassRegistrationActivity extends AppCompatActivity {

    MaterialBetterSpinner mbsBoard, mbsClass, mbsStream, mbsMedium;
    Button btn_submit_classdetail;
    String selected_class="", selected_medium="", selected_board_id="", str_stream="", email_id="", user_id="";
    String chk_selected_class;
    String selected_class_name, selected_board_name;

    SharedPreferences sharedPreferences;
    String class_id, classs_name, section, fname, lname, registration_type, board;
    ProgressDialog pDialog;
    List<Classs> classsList = new ArrayList<>();
    String[] classlist;
    ProgressDialog myDialog;
    public static List<StudentSubject> studentSubjects;

    //String[] classselect = {"Please Select", "12", "11", "10", "9", "8", "7", "6", "SSC - CGL", "SSC - CHSL", "IBPS - PO", "IBPS - Clerk", "SBI - PO", "SBI - Clerk"};

    String[] arr_stream = {"Arts (History & Geography)",
            "Commerce (Accounts & Maths)",
            "Commerce (Accounts & Economics)",
            "Science (Biology & Maths)",
            "Science (Biology & Physics)",
            "Science (Maths & Physics)"};

    String[] arr_medium = {"English", "Hindi", "Other"};

    String[] arr_board = {"Select Board",
            "Central Board of Secondary Education (CBSE), Delhi",
            "Competitive Examinations (SSC & Bank)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_class);


        sharedPreferences = getSharedPreferences("MyPrefs", 0);


        Intent i = getIntent();
        email_id = i.getStringExtra("email_id");
        user_id = i.getStringExtra("user_id");


        mbsBoard = (MaterialBetterSpinner) findViewById(R.id.spBoard);
        mbsClass = (MaterialBetterSpinner) findViewById(R.id.spClass);
        mbsClass.setVisibility(View.GONE);
        mbsStream = (MaterialBetterSpinner) findViewById(R.id.spStream);
        mbsStream.setVisibility(View.INVISIBLE);
        mbsMedium = (MaterialBetterSpinner) findViewById(R.id.spMedium);
        btn_submit_classdetail = (Button) findViewById(R.id.btn_classdetail);


        ArrayAdapter adpter_board = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arr_board);

        adpter_board.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsBoard.setAdapter(adpter_board);

        ArrayAdapter<String> stream = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_stream);

        stream.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsStream.setAdapter(stream);

        ArrayAdapter<String> aa_medium = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_medium);

        aa_medium.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsMedium.setAdapter(aa_medium);


        mbsBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                selected_board_id = i + "";
                selected_board_name = arr_board[i];
                if (selected_board_name.equalsIgnoreCase("Central Board of Secondary Education (CBSE), Delhi")) {
                    mbsClass.setVisibility(View.VISIBLE);
                    selected_board_id = String.valueOf(10);

                    getClassByBoardId(selected_board_id);
                } else if (selected_board_name.equalsIgnoreCase("Competitive Examinations (SSC & Bank)")) {

                    mbsClass.setVisibility(View.VISIBLE);
                    selected_board_id = String.valueOf(57);
                    getClassByBoardId(selected_board_id);
                }


            }


        });


        mbsClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chk_selected_class = classsList.get(i).getId() + "";
                //sendsubjectIntent(Integer.parseInt(chk_selected_class));


                if (classsList.get(i).getId() < 3) {
                    mbsStream.setEnabled(true);

                    mbsStream.setVisibility(View.VISIBLE);
                } else {
                    mbsStream.setEnabled(false);
                    mbsStream.setVisibility(View.INVISIBLE);
                }


            }


        });


        mbsStream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                str_stream = arr_stream[pos].toString();
            }

        });

        mbsMedium.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected_medium = adapterView.getItemAtPosition(i).toString();

            }


        });


        btn_submit_classdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSubjectList(Integer.parseInt(chk_selected_class));


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


    public void getClassByBoardId(String boardId) {

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Classes....");


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

        Call<List<Classs>> call1 = service1.getAllBoards(boardId);
        call1.enqueue(new Callback<List<Classs>>() {
            @Override
            public void onResponse(Call<List<Classs>> call, Response<List<Classs>> response) {
                myDialog.dismiss();

                classsList = response.body();
                if (response.code() == 200) {

                    classs_list_adapter();

                }


            }

            @Override
            public void onFailure(Call<List<Classs>> call, Throwable t) {
                myDialog.dismiss();

                snack_barmessage("Not able to Connect with server.Try After Some time");

            }
        });

    }

    public void classs_list_adapter() {
        classlist = new String[classsList.size()];
        for (int i = 0; i < classsList.size(); i++) {

            classlist[i] = classsList.get(i).getClassName();

        }


        ArrayAdapter<String> aa1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, classlist);

        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mbsClass.setAdapter(aa1);

    }


    public void getSubjectList(int classId) {

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Subjects....");


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

        Call<List<StudentSubject>> call1 = service1.getAllSubjectBasedClassId(classId);
        call1.enqueue(new Callback<List<StudentSubject>>() {
            @Override
            public void onResponse(Call<List<StudentSubject>> call, Response<List<StudentSubject>> response) {
                myDialog.dismiss();

                Toast.makeText(getApplicationContext(), response.message() + "", Toast.LENGTH_LONG).show();


                if (response.code() == 200) {
                    studentSubjects = response.body();

                    Intent i = new Intent(getApplicationContext(), ChooseSubjectsList.class);
                    i.putExtra("email_id", email_id);
                    i.putExtra("selected_board_id", selected_board_id);
                    i.putExtra("chk_selected_class", chk_selected_class);
                    i.putExtra("str_stream", str_stream);
                    i.putExtra("selected_medium", selected_medium);
                    i.putExtra("user_id", user_id);

                    startActivity(i);

                }


            }

            @Override
            public void onFailure(Call<List<StudentSubject>> call, Throwable t) {
                myDialog.dismiss();

                snack_barmessage("Not able to Connect with server.Try After Some time");

            }
        });

    }

}
