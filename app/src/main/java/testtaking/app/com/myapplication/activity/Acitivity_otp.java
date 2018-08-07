package testtaking.app.com.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import testtaking.app.com.myapplication.R;

/**
 * Created by sourav on 7/16/2017.
 */

public class Acitivity_otp extends AppCompatActivity {
    Button btn_reg;
    String otp_message, user_otp;
    TextView tv_skip;
    String user_mobilenumber;
    String userr_otp;

    public static EditText et_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);
        et_otp = (EditText) findViewById(R.id.ed_otp);
        btn_reg = (Button) findViewById(R.id.btn_reg);

        Intent intent = getIntent();
        user_mobilenumber = intent.getStringExtra("user_mobile");

        userr_otp = String.valueOf(intent.getIntExtra("user_otp", 0));

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                otp_message = et_otp.getText().toString();


                if (otp_message.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Fields Cannot be Empty", Toast.LENGTH_LONG).show();
                } else if (otp_message.equals(userr_otp)) {
                    Intent mainIntent = new Intent(Acitivity_otp.this, UserRegistrationActivity.class);
                    mainIntent.putExtra("user_mobile", user_mobilenumber);
                    Acitivity_otp.this.startActivity(mainIntent);
                    Acitivity_otp.this.finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Please Enter Correct OTP", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void recivedSms(String message) {
        try {
            et_otp.setText("" + message);
        } catch (Exception e) {
        }

    }
}
