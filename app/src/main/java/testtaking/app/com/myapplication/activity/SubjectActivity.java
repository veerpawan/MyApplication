package testtaking.app.com.myapplication.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.adapter.SubjectsAdapter;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.model.MainSubjects;
import testtaking.app.com.myapplication.model.Subject;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.AppUtil;
import testtaking.app.com.myapplication.utils.DialogsUtils;
import testtaking.app.com.myapplication.R;


public class SubjectActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_subjects)
    RecyclerView recyclerViewSubjects;
    List<MainSubjects> studentSubjects;
    ProgressDialog myDialog;
    static String getdata;
    List<Subject> subjectlist;
    private String TAG = SubjectActivity.class.getSimpleName();
    private String myData;
    //String user_id = 3 + "";
    String userID;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_search);
        ButterKnife.bind(this);
        AppUtil.setUpToolBar(toolbar, SubjectActivity.this, "Select Subject");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent i = getIntent();
               userID = i.getStringExtra("user_id");
        Log.e("gjj",userID);



       /* if (isStoragePermissionGranted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                readFile();
            }
        }
*/
        getSubject(userID);
        recyclerViewSubjects.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        int sub_id = subjectlist.get(position).getId();
                        // Toast.makeText(getApplicationContext(), sub_id + "", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ChapterActivity.class);
                        i.putExtra("subject_id", sub_id);
                        i.putExtra("student_id", userID);
                        startActivity(i);

                    }
                })
        );

    }

    public void getSubject(String user_id) {

        myDialog = DialogsUtils.showProgressDialog(this, "Getting Subjects....");
        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<List<MainSubjects>> call1 = apiService.getSubjectList(user_id);

        call1.enqueue(new Callback<List<MainSubjects>>() {
            @Override

            public void onResponse(Call<List<MainSubjects>> call, Response<List<MainSubjects>> response) {
                myDialog.dismiss();

                //Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();

                studentSubjects = response.body();

                if(response.code()==200) {

                    subjectlist = response.body().get(0).getSubjects();


                    if (response.isSuccessful()) {
                        // Toast.makeText(getApplicationContext(), studentSubjects.get(0).getSubjects().get(0).getSubjectName() + "", Toast.LENGTH_LONG).show();

                        SubjectsAdapter quizClassAdapter = new SubjectsAdapter(getApplicationContext(), subjectlist);
                        recyclerViewSubjects.setAdapter(quizClassAdapter);

                    } else {
                        Toast.makeText(getApplicationContext(), "Not able to get list!! Try after sometime!!!", Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(getApplicationContext(), "Not able to get list!! Try after sometime!!!", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<MainSubjects>> call, Throwable t) {
                if (t instanceof EOFException) {
                    showAlertDialog();
                }
                //  Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                               Log.e("DSF", t.toString());
                myDialog.dismiss();


            }
        });
    }

    private void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SubjectActivity.this);
        builder.setMessage("You didn't purchase test, please purchase first!!").setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i) {

                dialoginterface.dismiss();
                finish();
            }

        });
        builder.setCancelable(false);

        builder.create();


        if (!isFinishing()) {
            builder.show(); //without thios there is a possibility that alertdialog will crash the application
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void readFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "Taktii");
        try (FileInputStream fis = new FileInputStream(mediaStorageDir.getPath() + File.separator + "User.txt")) {
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                myData = strLine;
            }
        } catch (IOException e) {
            // handle exception
            Log.e("Exception", "" + e);
        }

        if (myData != null) {
            String bodydata[] = myData.split("#");
            userID = bodydata[1];
            getSubject(userID);
            // Toast.makeText(this, "user_id_recived in Actvity" + "" + user_id, Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    readFile();
                }
            }
            return;
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
