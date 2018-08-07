package testtaking.app.com.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.adapter.ChapterAdapter;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.model.BeanChapter;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.AppUtil;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.utils.DialogsUtils;


public class ChapterActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_chapters)
    RecyclerView recyclerViewTopic;

    List<BeanChapter> Chapterslist;
    ProgressDialog myDialog;

    String user_id;
    int subject_id;


    private List<String> mList = new ArrayList<>();

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
        setContentView(R.layout.activity_quiz_topic);
        ButterKnife.bind(this);
        AppUtil.setUpToolBar(toolbar, ChapterActivity.this, "Select Chapter");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent i = getIntent();
        subject_id = i.getIntExtra("subject_id", 0);
        user_id = i.getStringExtra("student_id");
        getChapters();

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Chapter List");

        recyclerViewTopic.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), TopicActivity.class);
                        intent.putExtra("str_chapter_id", Chapterslist.get(position).getId() + "");
                        intent.putExtra("student_id", user_id + "");
                        startActivity(intent);

                    }
                })
        );


    }


    public void getChapters() {

        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<BeanChapter>> call1 = apiService.getChaptersList(String.valueOf(subject_id));


        call1.enqueue(new Callback<List<BeanChapter>>() {
            @Override

            public void onResponse(Call<List<BeanChapter>> call, Response<List<BeanChapter>> response) {
                myDialog.dismiss();
                Chapterslist = response.body();

                if (response.code() == 200) {
                    ChapterAdapter quizClassAdapter = new ChapterAdapter(getApplicationContext(), Chapterslist);
                    recyclerViewTopic.setAdapter(quizClassAdapter);

                } else {
                }

            }

            @Override
            public void onFailure(Call<List<BeanChapter>> call, Throwable t) {

                myDialog.dismiss();


            }
        });


    }
}
