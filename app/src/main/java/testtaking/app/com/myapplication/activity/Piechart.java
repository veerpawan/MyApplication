package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.R;


public class Piechart extends AppCompatActivity implements OnChartValueSelectedListener {

    int total, correct, attempted, skipped;
    TextView tvv_skipped;
    TextView tvv_correct;
    TextView tvv_wrong;
    Button btn_answers;
    List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);
        tvv_skipped = (TextView) findViewById(R.id.tv_skipped);
        tvv_correct = (TextView) findViewById(R.id.tv_correct);
        tvv_wrong = (TextView) findViewById(R.id.tv_wrong);
        btn_answers =(Button) findViewById(R.id.btn_answers);


        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        Intent i = getIntent();

        questionList = i.getParcelableArrayListExtra("questions_list");
        total = i.getIntExtra("q_total", 0);
        attempted = i.getIntExtra("q_attempt", 0);
        correct = i.getIntExtra("q_correct", 0);

        tvv_skipped.setText(total - attempted + "");
        tvv_correct.setText(correct + "");
        tvv_wrong.setText(attempted - correct + "");
        btn_answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ViewAnswersList.class);
                i.putExtra("questions_list", (ArrayList) questionList);
                startActivity(i);

            }
        });


        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<Entry> yvalues = new ArrayList<Entry>();
        yvalues.add(new Entry(total - attempted, 0));
        yvalues.add(new Entry(attempted - correct, 1));
        yvalues.add(new Entry(correct, 2));

        PieDataSet dataSet = new PieDataSet(yvalues, "Results");
        ArrayList<String> xVals = new ArrayList<String>();

        xVals.add("Skipped");
        xVals.add("Wrong");
        xVals.add("Correct");


        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new LargeValueFormatter());
        pieChart.setData(data);
        pieChart.setDescription("This is your Result");

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(0f);
        pieChart.setHoleRadius(0f);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.setOnChartValueSelectedListener(this);


        pieChart.animateXY(1400, 1400);


    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
