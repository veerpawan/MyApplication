package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import testtaking.app.com.myapplication.R;

public class splash extends AppCompatActivity {
    ProgressBar progressBar;
    TextView textView;
    private int progressStatus = 0;
    String userID;
    String role, bde_name, bde_id;
    SharedPreferences sharedPreferences;
    private Handler handler = new Handler();

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        sharedPreferences = getSharedPreferences("MyPrefs", 0);
        userID = sharedPreferences.getString("userID", null);
        role = sharedPreferences.getString("role", null);
        bde_name = sharedPreferences.getString("bde_name", null);
        bde_id = sharedPreferences.getString("bde_id", null);


        //Log.e("useridvalue",userID);

        //textView = (TextView) findViewById(R.id.textView1);

       /* Thread thread = new Thread()
        {
            public void run()
            {
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                Intent i = new Intent(getApplicationContext(),LogIn.class);
                startActivity(i);

            }
        };
        thread.start();
     */
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 5;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            //textView.setText(progressStatus + "/" + progressBar.getMax());
                            progressBar.getProgressDrawable().setColorFilter(Color.rgb(0, 124, 194), PorterDuff.Mode.SRC_IN);
                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }


                // username = sharedPreferences.getString("username", null);
                //  password = sharedPreferences.getString("password", null);
                if (userID == null && role == null) {

                    //Intent i = new Intent(getApplicationContext(), Home.class);
                    Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                    finish();
                    startActivity(i);

                } else if (userID != null) {

                    Intent i = new Intent(getApplicationContext(), Subject.class);
                    i.putExtra("user_id", userID);

                    startActivity(i);
                    finish();
                }
            }
        }).start();
    }
}