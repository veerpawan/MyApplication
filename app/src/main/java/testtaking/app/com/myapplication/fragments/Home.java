package testtaking.app.com.myapplication.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.adapter.HomeAddQuesAdapter;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.model.HomeQuestionList;
import testtaking.app.com.myapplication.model.HomeQuestionOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    RecyclerView recyclerView;
    HomeAddQuesAdapter homeAddQuesAdapter;
    List<HomeQuestionList> homeQuestionListList;
    int studentid, studentrole;
    List<HomeQuestionOptions> homeQuestionListOptions;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView =getActivity().findViewById(R.id.home_add_question_recycle);

        Intent intent = getActivity().getIntent();
        studentid = intent.getIntExtra("student_id", 0);
        studentrole = intent.getIntExtra("student_role", 0);
        getdataofallstudentsHome();

    }

    private void getdataofallstudentsHome() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface service = retrofit.create(RequestInterface.class);


        // Call<List<HomeQuestionList>> call = service.fbquestionpost( studentid, studentrole );
        Call<List<HomeQuestionList>> call = service.fbquestionpost(4, 28);
        call.enqueue(new Callback<List<HomeQuestionList>>() {
            @Override
            public void onResponse(Call<List<HomeQuestionList>> call, Response<List<HomeQuestionList>> response) {
                progressDialog.dismiss();
                homeQuestionListList = response.body();

                Log.e("hhh", String.valueOf(response));
                if (response.isSuccessful()) {
                    homeAddQuesAdapter = new HomeAddQuesAdapter(homeQuestionListList, getContext());
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(homeAddQuesAdapter);


                } else {
                    Toast.makeText(getActivity(), "No Post here", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<HomeQuestionList>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), "Not able to get Connection!", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }

}