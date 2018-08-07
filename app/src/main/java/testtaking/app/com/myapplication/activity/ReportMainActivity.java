package testtaking.app.com.myapplication.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.model.Report;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.DialogsUtils;

public class ReportMainActivity extends Activity {

    public static String str_test_id, str_serial_id;
    List<Report> reportList;
    int count_attempt = 0, count_correct = 0;
    ProgressDialog myDialog;
    List<Question> questionList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i = getIntent();

        questionList = i.getParcelableArrayListExtra("questions_list");
        str_test_id = i.getStringExtra("str_test_id");
        str_serial_id = i.getStringExtra("str_serial_id");
        //Toast.makeText(getApplicationContext(), str_test_id + str_serial_id, Toast.LENGTH_LONG).show();
        getReportList(str_test_id, str_serial_id);

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Reports....");


    }


    private void getReportList(String s, String s1) {


        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Report>> call = apiService.getReportList(s, s1);
        call.enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {

                // Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                myDialog.dismiss();


                if (response.code() == 200) {

                    reportList = response.body();
                    for (int i = 0; i < reportList.size(); i++) {
                        Log.e("RREp", reportList.get(i) + "");

                        if (reportList.get(i).getATTEMPT() == 1) {
                            count_attempt++;
                        }
                        if (reportList.get(i).getCORRECT() == 1) {
                            count_correct++;
                        }
                    }
                    Intent i = new Intent(getApplicationContext(), Piechart.class);
                    i.putExtra("q_correct", count_correct);

                    i.putExtra("questions_list", (ArrayList) questionList);
                    i.putExtra("q_attempt", count_attempt);
                    i.putExtra("q_total", reportList.size());
                    startActivity(i);
                    finish();

                }

            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
               // Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                myDialog.dismiss();

            }
        });

    }


}
