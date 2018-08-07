package testtaking.app.com.myapplication.activity;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.adapter.MyCustomPagerAdapter;
import testtaking.app.com.myapplication.adapter.QuestionListAdapter;
import testtaking.app.com.myapplication.adapter.QuestionNumberAdapter;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.model.MainQuestion;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.Constant;
import testtaking.app.com.myapplication.utils.DialogsUtils;
import testtaking.app.com.myapplication.R;


public class QuizMainActivity extends AppCompatActivity {

    // public static String str_test_id, str_serial_id;
    public static String str_test_id, str_serial_id;
    public static int globlepos = 0;

    @BindView(R.id.recycler_view_questions)
    RecyclerView recyclerViewQuestions;
    @BindView(R.id.text_view_timer)
    TextView textViewTimer;
    @BindView(R.id.image_view_list)
    ImageView imageViewList;
    @BindView(R.id.image_view_grid)
    ImageView imageViewGrid;
    @BindView(R.id.recycler_view_questions_list)
    RecyclerView recyclerViewQuestionsList;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    MainQuestion mainQuestions;
    List<Question> questionList;
    ProgressDialog myDialog;
    DrawerLayout drawer;
    Button btnskip;
    Button btnnext;
    QuestionListAdapter questionListAdapter;
    QuestionNumberAdapter questionNumberAdapter;
    int id;
    ViewPager viewPager;
    MyCustomPagerAdapter myCustomPagerAdapter;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP));

    LinearLayout ll;

    public static ArrayList<Integer> color_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz_main);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        btnnext = (Button) findViewById(R.id.btn_next);
        btnskip = (Button) findViewById(R.id.btn_skip);


        myDialog = DialogsUtils.showProgressDialog(this, "Getting Questions");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.Colorblack));


        Intent i = getIntent();
        str_test_id = i.getStringExtra("str_test_id");
        str_serial_id = i.getStringExtra("str_serial_id");
        // Toast.makeText(getApplicationContext(), str_test_id + "           " + str_serial_id, Toast.LENGTH_LONG).show();

        getQuestionsList(str_test_id, str_serial_id);


        recyclerViewQuestionsList.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        id = questionList.get(position).getSECTION_QUESTION_ID();
                        viewPager.setCurrentItem(position);
                        drawer.closeDrawers();

                    }
                })
        );


        recyclerViewQuestions.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        viewPager.setCurrentItem(position);
                        drawer.closeDrawers();

                    }
                })
        );


        viewPager.beginFakeDrag();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {
                if (position == (viewPager.getAdapter().getCount() - 1)) {

                    btnnext.setText("Save");

                    Toast.makeText(getApplicationContext(), "You have Reached on Last Question!!", Toast.LENGTH_LONG).show();


                } else {
                    btnnext.setText("Save and Next");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(), MyCustomPagerAdapter.isChecked + "    " + questionList.get(viewPager.getCurrentItem()).getNAME() + "", Toast.LENGTH_LONG).show();


                if (MyCustomPagerAdapter.isChecked.equals(questionList.get(viewPager.getCurrentItem()).getNAME())) {

                    questionList.get(viewPager.getCurrentItem()).setStatus(Constant.VIEWD);

                    questionNumberAdapter.notifyDataSetChanged();
                    questionListAdapter.notifyDataSetChanged();
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                } else {
                    Toast.makeText(getApplicationContext(), "Please Choose or Skip!!", Toast.LENGTH_LONG).show();
                }


            }
        });
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                questionList.get(viewPager.getCurrentItem()).setStatus(Constant.UNANSWERD);
                questionNumberAdapter.notifyDataSetChanged();
                questionListAdapter.notifyDataSetChanged();
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @OnClick({R.id.image_view_list, R.id.image_view_grid})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_view_list:
                recyclerViewQuestions.setVisibility(View.GONE);
                recyclerViewQuestionsList.setVisibility(View.VISIBLE);
                break;
            case R.id.image_view_grid:
                recyclerViewQuestions.setVisibility(View.VISIBLE);
                recyclerViewQuestionsList.setVisibility(View.GONE);
                break;

        }

    }

    private void getQuestionsList(String s, String s1) {


        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<MainQuestion> call = apiService.getListOfQuestions(s, s1);
        call.enqueue(new Callback<MainQuestion>() {
            @Override
            public void onResponse(Call<MainQuestion> call, Response<MainQuestion> response) {

                myDialog.dismiss();

                if (response.code() == 200) {

                    mainQuestions = response.body();
                    questionList = response.body().getQuestion();


                    if (questionList.size() == 0) {
                        Toast.makeText(getApplicationContext(), "Please Enter Valid Id", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ChapterTestList.class);
                        startActivity(i);
                        finish();
                    } else {


                        myCustomPagerAdapter = new MyCustomPagerAdapter(QuizMainActivity.this, questionList, str_test_id, str_serial_id);
                        viewPager.setAdapter(myCustomPagerAdapter);
                        viewPager.setOffscreenPageLimit(questionList.size());


                        questionListAdapter = new QuestionListAdapter(getApplicationContext(), questionList);
                        /*DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewQuestionsList.getContext(),
                                LinearLayoutManager.VERTICAL);
                        recyclerViewQuestionsList.addItemDecoration(mDividerItemDecoration);*/
                        recyclerViewQuestionsList.setAdapter(questionListAdapter);


                        recyclerViewQuestions.setLayoutManager(new GridLayoutManager(getApplicationContext(), 5));
                        questionNumberAdapter = new QuestionNumberAdapter(getApplicationContext(), questionList);
                        recyclerViewQuestions.setAdapter(questionNumberAdapter);


                        final int time = Integer.parseInt(questionList.size() + "");
                        Log.e("testscreen---------->", Integer.toString(time));
                        CountDownTimer countDownTimer = new CountDownTimer(time * 60 * 1000, 1000) {

                            public void onTick(long millisUntilFinished) {

                                textViewTimer.setText("" + String.format("%d min : %d sec",
                                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            }

                            public void onFinish() {
                                textViewTimer.setText("Time Up");
                                textViewTimer.setTextColor(Color.RED);



                               /* final AlertDialog.Builder builder = new AlertDialog.Builder(QuizMainActivity.this);
                                builder.setMessage("Time is Over ..!!").setPositiveButton("Submit", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialoginterface, int i) {

                                    }

                                });
                                builder.setCancelable(false);

                                builder.create();


                                if (!isFinishing()) {
                                    builder.show(); //without thios there is a possibility that alertdialog will crash the application
                                }
*/
                            }

                        }.start();

                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Failed to connect with server", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<MainQuestion> call, Throwable t) {
                myDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    public void onAttachedToWindow() {

        //this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();


        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);

       /* Intent i = new Intent(getApplicationContext(), SubjectActivity.class);
        startActivity(i);
        finish();*/
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_MENU) {

            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {

            System.exit(0);

            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_SEARCH) {

            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_SETTINGS) {

            return false;
        }

        return super.onKeyDown(keyCode, event);
    }


}

