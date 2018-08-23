package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.adapter.TopicAdapter;
import testtaking.app.com.myapplication.adapter.UserCommentListAdapter;
import testtaking.app.com.myapplication.model.PostCommetList;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;


public class UserCommentsList extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressBar progressBar;

    public String post_id;
    List<PostCommetList> postCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_comments_list);
        recyclerView = findViewById(R.id.comment_recycler_view);
        progressBar = findViewById(R.id.feed_loading);


        Intent i = getIntent();
        post_id = i.getStringExtra("post_id");
        Toast.makeText(getApplicationContext(),post_id,Toast.LENGTH_LONG).show();

        getAllComments(post_id);
    }

    private void getAllComments(String post_id) {


        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<PostCommetList>> calll = apiService.getCommentList(post_id);
        calll.enqueue(new Callback<List<PostCommetList>>() {
            @Override
            public void onResponse(Call<List<PostCommetList>> call, Response<List<PostCommetList>> response) {

                progressBar.setVisibility(View.GONE);


                if (response.code() == 200) {

                    postCommentList = response.body();
                    if(postCommentList.size()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Empty", Toast.LENGTH_LONG).show();

                        finish();

                    }else {

                        UserCommentListAdapter quizTopicAdapter = new UserCommentListAdapter(getApplicationContext(), postCommentList);

                        RecyclerView.LayoutManager loLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(loLayoutManager);

                        recyclerView.setAdapter(quizTopicAdapter);
                    }





                } else {
                }


            }

            @Override
            public void onFailure(Call<List<PostCommetList>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }


}
