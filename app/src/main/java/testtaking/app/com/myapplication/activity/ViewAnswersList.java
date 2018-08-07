package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import testtaking.app.com.myapplication.adapter.MyCustomPagerAdapter;
import testtaking.app.com.myapplication.adapter.ReportQuestionListAdapter;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.R;


public class ViewAnswersList extends AppCompatActivity {
    List<Question> questionList;

    @BindView(R.id.recycler_view_questions_list)
    RecyclerView recyclerViewQuestionsList;

    ReportQuestionListAdapter questionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_answers_list);

        ButterKnife.bind(this);


        Intent i = getIntent();

       // questionList = i.getParcelableArrayListExtra("questions_list");

        questionList = MyCustomPagerAdapter.questionList;



        questionListAdapter = new ReportQuestionListAdapter(getApplicationContext(), questionList);
       /* DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerViewQuestionsList.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerViewQuestionsList.addItemDecoration(mDividerItemDecoration);*/

        recyclerViewQuestionsList.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewQuestionsList.setAdapter(questionListAdapter);
        recyclerViewQuestionsList.hasFixedSize();
        recyclerViewQuestionsList.setHasFixedSize(true);


    }
}
