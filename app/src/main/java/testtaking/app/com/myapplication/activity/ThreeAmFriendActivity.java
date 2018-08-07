package testtaking.app.com.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import testtaking.app.com.myapplication.R;

public class ThreeAmFriendActivity extends AppCompatActivity {

    RelativeLayout rlBack;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_am_friend);
        rlBack = (RelativeLayout)findViewById(R.id.rlBack);
        ivBack = (ImageView)findViewById(R.id.ivTAFBack);
    }

    @Override
    protected void onResume() {
        super.onResume();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
