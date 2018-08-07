package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.TestListAdapter;
import testtaking.app.com.myapplication.model.ScheduleTestBean;
import testtaking.app.com.myapplication.model.TestBean;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.AppUtil;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.utils.DialogsUtils;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


/**
 * Created by pawan on 11/2/2016.
 */

public class ChapterTestList extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ProgressBar loader;
    List<TestBean> testBeen;
    ProgressDialog pDialog;
    List<ScheduleTestBean> scheduleTestBeen;
    String test_id;
    private int chapter_id;
    int user_id;
    ProgressDialog myDialog;
   // private Test tests;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtestlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        AppUtil.setUpToolBar(toolbar, ChapterTestList.this, "Select Test");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent i = getIntent();
        chapter_id = Integer.parseInt(i.getStringExtra("str_topic_id"));
        user_id = Integer.parseInt(i.getStringExtra("student_id"));
        //Toast.makeText(getApplicationContext(), user_id + "", Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView) findViewById(R.id.testlist_recycler_view);

        recyclerView.setHasFixedSize(true);
        loader = (ProgressBar) findViewById(R.id.feed_loading);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        if (isNetworkAvailable()) {

            get_Tests(chapter_id);
            loader.setVisibility(VISIBLE);

        } else {
            loader.setVisibility(GONE);
        }

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        test_id = String.valueOf(testBeen.get(position).getTest_id());


                        schedule_test();
                       // Toast.makeText(getApplicationContext(),test_id+"     test and user id   "+user_id+"",Toast.LENGTH_LONG).show();



                    }
                })
        );


    }


    public void get_Tests(int chapter_id) {


        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<TestBean>> call = apiService.gettestlist(chapter_id);


        call.enqueue(new Callback<List<TestBean>>() {
            @Override
            public void onResponse(Call<List<TestBean>> call, Response<List<TestBean>> response) {
                testBeen = response.body();
                setAdapterRecyclerviewdemo();
                loader.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<TestBean>> call, Throwable t) {
                loader.setVisibility(View.GONE);
            }
        });


    }

    private void setAdapterRecyclerviewdemo() {

        adapter = new TestListAdapter(testBeen, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }


    public void schedule_test() {
        myDialog = DialogsUtils.showProgressDialog(this, "Scheduling Test....");

        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<ScheduleTestBean>> call = apiService.scheduletestlist(test_id, user_id + "");

        call.enqueue(new Callback<List<ScheduleTestBean>>() {
            @Override
            public void onResponse(Call<List<ScheduleTestBean>> call, Response<List<ScheduleTestBean>> response) {
                myDialog.dismiss();
                scheduleTestBeen = response.body();
                //tests = response.body().get(0).getTest();
                if (response.code() == 200) {

                    if (scheduleTestBeen.get(0).getSUCCESS() != 0) {
                        // Toast.makeText(getApplicationContext(), tests.getID() + "        " + tests.getTEST_ID() + "", Toast.LENGTH_LONG).show();
                        if ((scheduleTestBeen.get(0).getTest_id() == 0) || scheduleTestBeen.get(0).getTest_id() == 0) {

                            Toast.makeText(getApplicationContext(), "Please Start Again!!!", Toast.LENGTH_LONG).show();
                        }

                        Intent intent = new Intent(getApplicationContext(), QuizMainActivity.class);
                        intent.putExtra("str_test_id", scheduleTestBeen.get(0).getTest_id() + "");
                        intent.putExtra("str_serial_id", scheduleTestBeen.get(0).getTest_id() + "");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Test Already attempted!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "No test Schedule", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ScheduleTestBean>> call, Throwable t) {

                myDialog.dismiss();
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }


}
