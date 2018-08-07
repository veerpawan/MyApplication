package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import testtaking.app.com.myapplication.R;

public class PracticeTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_test);

        Intent i = getIntent();
        String import_level_id = i.getStringExtra("level_id");
        String import_level_type = i.getStringExtra("level_type");
    }
}
