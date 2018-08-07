package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.R;

import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.TopicAdapter;
import testtaking.app.com.myapplication.model.BeanTopic;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.AppUtil;
import testtaking.app.com.myapplication.utils.DialogsUtils;

public class TopicActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_topic)
    RecyclerView recyclerViewTopic;
    List<BeanTopic> topics;
    ProgressDialog myDialog;
    private String chpater_id;
    private String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        ButterKnife.bind(this);
        AppUtil.setUpToolBar(toolbar, TopicActivity.this, "Select Topic");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent i = getIntent();
        chpater_id = i.getStringExtra("str_chapter_id");
        user_id = i.getStringExtra("student_id");
        Toast.makeText(getApplicationContext(), chpater_id + "", Toast.LENGTH_LONG).show();


        getTopics(Integer.parseInt(chpater_id));

        recyclerViewTopic.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), ChapterTestList.class);
                        intent.putExtra("str_topic_id", topics.get(position).getId() + "");
                        intent.putExtra("student_id", user_id + "");
                        startActivity(intent);

                    }
                })
        );

    }

    public void getTopics(int chpater_id) {

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Topic List");


        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<BeanTopic>> call1 = apiService.gettopiclist(chpater_id);


        call1.enqueue(new Callback<List<BeanTopic>>() {
            @Override

            public void onResponse(Call<List<BeanTopic>> call, Response<List<BeanTopic>> response) {
                myDialog.dismiss();
                topics = response.body();

                if (response.code() == 200) {

                    Toast.makeText(getApplicationContext(), topics.get(0).getTopicName(), Toast.LENGTH_LONG).show();
                    TopicAdapter quizTopicAdapter = new TopicAdapter(getApplicationContext(), topics);

                    RecyclerView.LayoutManager loLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerViewTopic.setLayoutManager(loLayoutManager);

                    recyclerViewTopic.setAdapter(quizTopicAdapter);

                } else {
                }

            }

            @Override
            public void onFailure(Call<List<BeanTopic>> call, Throwable t) {

                myDialog.dismiss();


            }
        });


    }
}
