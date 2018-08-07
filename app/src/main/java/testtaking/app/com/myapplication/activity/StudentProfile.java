package testtaking.app.com.myapplication.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import testtaking.app.com.myapplication.R;

/**
 * Created by pawan on 1/16/2017.
 */

public class StudentProfile extends AppCompatActivity {


    private Toolbar toolbar;
    private ImageView imageView, tabBg;
    CollapsingToolbarLayout collapsingToolbar;
    String Class, fname, lname, board, emailid, userID, emailID;
    TextView tvpFname, tvpLname, tvpClass, tvpBoard, tvpUserId, tvpEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_profile);


        imageView = (ImageView) findViewById(R.id.backdrop);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setToolbar();
        setImage();


        SharedPreferences shared = getSharedPreferences("MyPrefs", 0);




        Class = shared.getString("Classs", null);
        fname = shared.getString("fname", null);
        lname = shared.getString("lname", null);
        board = shared.getString("Board", null);
        userID = shared.getString("userID", null);
        emailID = shared.getString("emailID", null);


        tvpFname = (TextView) findViewById(R.id.tvpFname);
        tvpLname = (TextView) findViewById(R.id.tvpLname);
        tvpClass = (TextView) findViewById(R.id.tvpClass);
        tvpBoard = (TextView) findViewById(R.id.tvpBoard);
        tvpUserId = (TextView) findViewById(R.id.tvpUserId);
        tvpEmailId = (TextView) findViewById(R.id.tvpEmailId);


        tvpClass.setText(Class);
        tvpFname.setText(fname);
        tvpLname.setText(lname);
        tvpBoard.setText(board);
        tvpUserId.setText(userID);
        tvpEmailId.setText(emailID);

    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
        }
    }

    private void setImage() {
        Glide.with(this).load(R.drawable.onet).into(imageView);
        Glide.with(StudentProfile.this).load(R.drawable.onet);
    }


}
