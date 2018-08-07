package testtaking.app.com.myapplication.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.SmsStatus;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.model.VenueList;
import testtaking.app.com.myapplication.network.URL_Mapping;



public class CollegeStudentRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    Button btNext, btRRegister, btPrevious, btnPickImage, btnUpload;
    int mYear, mMonth, mDay, mHour, mMinute;
    String mediaPath, mediaPath1;
    ImageView imgView;
    String[] mediaColumns = {MediaStore.Video.Media._ID};

    List<VenueList> venueLists;

    String walk_in_role,
            walk_in_venue,
            walk_in_name,
            walk_in_dob,
            walk_in_email,
            walk_in_mobile,
            walk_in_gender,
            walk_in_address,
            walk_in_state,
            walk_in_pin,
            walk_in_X_board,
            walk_in_X_school,
            walk_in_X_yop,
            walk_in_X_percentage,
            walk_in_XII_board,
            walk_in_XII_school,
            walk_in_XII_yop,
            walk_in_XII_percentage,
            walk_in_XII_stream,
            walk_in_grad_univ,
            walk_in_grad_clgname,
            walk_in_grad_degree,
            walk_in_grad_stream,
            walk_in_grad_yop,
            walk_in_grad_percentage,
            walk_in_post_grad_uniname,
            walk_in_post_grad_clgname,
            walk_in_post_grad_degree,
            walk_in_post_grad_stream,
            walk_in_post_grad_yop,
            walk_in_post_grad_percentage,
            walk_in_father_name,
            walk_in_father_education,
            walk_in_father_occupation,
            walk_in_mother_name,
            walk_in_mother_education,
            walk_in_mother_occupation,
            walk_in_sibling_1_name,
            walk_in_sibling_1_education,
            walk_in_sibling_1_occupation,
            walk_in_sibling_2_name,
            walk_in_sibling_2_education,
            walk_in_sibling_2_occupation,
            walk_in_sibling_3_name,
            walk_in_sibling_3_education,
            walk_in_sibling_3_occupation,
            walk_in_sibling_4_name,
            walk_in_sibling_4_education,
            walk_in_sibling_4_occupation;


    EditText

            etUserName,
            etEmailId,
            etDob,
            etContactNumer,
            etAddress,
            etPinCode,
            edt10thYearPassing,
            edt10thMarks,
            edt10thSchool,
            edt12thYearPassing,
            edt12thMarks,
            edt12thSchool,
            edtUgUniName,
            edtUgClgName,
            edtUgYop,
            edtUgAvgMarks,
            edtPgUniName,
            edtPgClgName,
            edtPgYop,
            edtPgAvgMarks,
            etFatherName,
            etFatherEducation,
            etFatherOccupation,
            etMotherName,
            etMotherEducation,
            etMotherOccupation,
            etSiblingOneName,
            etSiblingOneEducation,
            etSiblingOneOccupation,
            etSiblingTwoName,
            etSiblingTwoEducation,
            etSiblingTwoOccupation,
            etSiblingThreeName,
            etSiblingThreeEducation,
            etSiblingThreeOccupation,
            etSiblingFourName,
            etSiblingFourEducation,
            etSiblingFourOccupation;

    MaterialBetterSpinner
            sp_user_role,
            sp_user_walkinvenue,
            sp_s_gender,
            sp_t_board,
            sp_tw_board,
            sp_tw_stream,
            sp_state,
            sp_ug_stream,
            sp_pg_stream,
            sp_ug_degree,
            sp_pg_degree;

    ImageButton img_date;
    String[] venuelist;

    String[] arr_role = new String[]{"Business Development", "Content Writer", "Digital Marketing", "Graphics Designer", "Human Resource", "Software Developer"};
    // String[] arr_venue = new String[]{"Talent4assure", "College", "School", "JobFair", "EmployeeRefferal"};


    String[] itemState = new String[]{"Andhra Pradesh", "Andaman & Nicobar Islands",
            "Arunachal Pradesh", "Assam", "Bihar", "Chandigarh", "Dadra & Nagar Haveli ", "Delhi", "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu & Kashmir",
            "Karnataka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Mizoram",
            "Nagaland",
            "Orissa",
            "Punjab",
            "Puducherry ",
            "Rajasthan",
            "Sikkim",
            "Tamil Nadu",
            "Tripura",
            "Uttar Pradesh",
            "West Bengal",
            "Chhattisgarh",
            "Uttarakhand",
            "Jharkhand",
            "Telangana"};
    String[] itemsGender = new String[]{"Male", "Female", "Other"};
    String[] itemsTboard = new String[]{"CBSE", "ICSE",
            "Andhra Pradesh Board",
            "Andaman & Nicobar Islands Board",
            "Arunachal Pradesh Board",
            "Assam Board",
            "Bihar Board",
            "Chandigarh Board",
            "Dadra & Nagar Haveli Board ",
            "Goa Board",
            "Gujarat Board",
            "Haryana Board",
            "Himachal Pradesh Board",
            "Jammu & Kashmir Board",
            "Karnataka Board",
            "Kerala Board",
            "Madhya Pradesh Board",
            "Maharashtra Board",
            "Manipur Board",
            "Mizoram Board",
            "Nagaland Board",
            "Orissa Board",
            "Punjab Board",
            "Puducherry Board ",
            "Rajasthan Board",
            "Sikkim Board",
            "Tamil Nadu Board",
            "Tripura Board",
            "Uttar Pradesh Board",
            "West Bengal Board",
            "Chhattisgarh Board",
            "Uttarakhand Board",
            "Jharkhand Board",
            "Telangana Board", "Others",};
    String[] itemsTwboard = new String[]{"CBSE", "ICSE",
            "Andhra Pradesh Board",
            "Andaman & Nicobar Islands Board",
            "Arunachal Pradesh Board",
            "Assam Board",
            "Bihar Board",
            "Chandigarh Board",
            "Dadra & Nagar Haveli Board ",
            "Goa Board",
            "Gujarat Board",
            "Haryana Board",
            "Himachal Pradesh Board",
            "Jammu & Kashmir Board",
            "Karnataka Board",
            "Kerala Board",
            "Madhya Pradesh Board",
            "Maharashtra Board",
            "Manipur Board",
            "Mizoram Board",
            "Nagaland Board",
            "Orissa Board",
            "Punjab Board",
            "Puducherry Board ",
            "Rajasthan Board",
            "Sikkim Board",
            "Tamil Nadu Board",
            "Tripura Board",
            "Uttar Pradesh Board",
            "West Bengal Board",
            "Chhattisgarh Board",
            "Uttarakhand Board",
            "Jharkhand Board",
            "Telangana Board", "Others"};
    String[] itemsTwStream = new String[]{"Arts (History & Geography)",
            "Commerce (Accounts & Maths)",
            "Commerce (Accounts & Economics)",
            "Science (Biology & Maths)",
            "Science (Biology & Physics)",
            "Science (Maths & Physics)"};


    String[] itemsPgDeg = new String[]{
            "Integrated PG",
            "M.A.",
            "M.Arch",
            "M.Com",
            "M.Pharma",
            "M.Sc",
            "M.Tech",
            "MBA/PGDM",
            "MCA",
            "MCM",
            "ME",
            "MS",
            "MVSC",
            "PG Diploma",
            "Other"};
    String[] itemsDegree = new String[]{
            "B.Arch",
            "B.Com (H)",
            "B.Com (P)",
            "B.Pharma",
            "B.Sc (G)",
            "B.Sc (H)",
            "B.Tech",
            "BA (H)",
            "BA (P)",
            "BBA",
            "BCA",
            "BDS",
            "BE",
            "BHM",
            "BVSC",
            "Diploma",
            "MBBS",
            "Other"};
    String[] itemsPgStream = new String[]{
            "Architecture",
            "Automobile",
            "Aviation",
            "Bio-Technology",
            "Botany",
            "Chemical",
            "Chemistry",
            "Civil",
            "Computer Science",
            "Economics",
            "Electrical",
            "Electronics & Communication",
            "English",
            "Environmental",
            "Fashion Designing",
            "Food Technology",
            "Geography",
            "Hindi",
            "History",
            "Hotel Management",
            "HR/Industrial Relations",
            "Information Technology",
            "Instrumentation",
            "International Business",
            "Journalism",
            "Law",
            "Literature",
            "Marketing",
            "Mass Communication",
            "Maths",
            "Mechanical",
            "Microbiology",
            "Pharmacy",
            "Physics",
            "Political Science",
            "Production/Industrial",
            "Psychology",
            "Radiology",
            "Sanskrit",
            "Sociology",
            "Statistics",
            "Telecomunication",
            "Textile",
            "Vocational Courses",
            "Zoology"};
    String[] itemsStram = new String[]{
            "Architecture",
            "Automobile",
            "Aviation",
            "Bio-Technology",
            "Botany",
            "Chemical",
            "Chemistry",
            "Civil",
            "Computer Science",
            "Economics",
            "Electrical",
            "Electronics & Communication",
            "English",
            "Environmental",
            "Fashion Designing",
            "Food Technology",
            "Geography",
            "Hindi",
            "History",
            "Hotel Management",
            "HR/Industrial Relations",
            "Information Technology",
            "Instrumentation",
            "International Business",
            "Journalism",
            "Law",
            "Literature",
            "Marketing",
            "Mass Communication",
            "Maths",
            "Mechanical",
            "Microbiology",
            "Pharmacy",
            "Physics",
            "Political Science",
            "Production/Industrial",
            "Psychology",
            "Radiology",
            "Sanskrit",
            "Sociology",
            "Statistics",
            "Telecomunication",
            "Textile",
            "Vocational Courses",
            "Zoology"};


    ProgressDialog pDialog;
    List<SmsStatus> statusmessage;

    int previousItem;
    File compressedImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collegeregistration);


        myLayoutViewIDs();


        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 0);
            }
        });

        if (isNetworkAvailable()) {

            getwalkinvenueslist();
        }


        viewPager = (ViewPager) findViewById(R.id.viewPagerVertical);
        adapter = new ViewPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.setKeepScreenOn(true);
        viewPager.setEnabled(false);
        viewPager.setOffscreenPageLimit(18);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                Log.e("PPPP", String.valueOf(position));


                if (position == 0) {
                    if (walk_in_role == null) {


                        //Toast.makeText(getApplicationContext(), "please Select Role", Toast.LENGTH_LONG).show();
                        viewPager.setCurrentItem(0);
                    }
                    btPrevious.setVisibility(View.GONE);
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 1) {
                    if (walk_in_venue == null) {

                        //Toast.makeText(getApplicationContext(), "please Select Venue", Toast.LENGTH_LONG).show();

                        viewPager.setCurrentItem(1);
                    }
                    btPrevious.setVisibility(View.VISIBLE);
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 2) {
                    if (etUserName.getText().toString().equals("")) {
                        viewPager.setCurrentItem(2);
                    }
                    btPrevious.setVisibility(View.VISIBLE);
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 3) {
                    if (etEmailId.getText().toString().equals("")) {
                        viewPager.setCurrentItem(3);
                    }
                    btPrevious.setVisibility(View.VISIBLE);
                    btRRegister.setVisibility(View.GONE);

                } else if (position == 4) {
                    if (etContactNumer.getText().toString().equals("")) {
                        viewPager.setCurrentItem(4);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 5) {
                    if (etDob.getText().toString().equals("")) {
                        viewPager.setCurrentItem(5);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 6) {
                    if (etAddress.getText().toString().equals("") || etPinCode.getText().toString().equals("")) {
                        viewPager.setCurrentItem(6);
                    }

                    btRRegister.setVisibility(View.GONE);

                } else if (position == 7) {

                    if (edt10thMarks.getText().toString().equals("") || edt10thYearPassing.getText().toString().equals("")
                            || edt10thSchool.getText().toString().equals("")) {
                        viewPager.setCurrentItem(7);
                    }

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 8) {

                    if (edt12thMarks.getText().toString().equals("") || edt12thYearPassing.getText().toString().equals("")
                            || edt12thSchool.getText().toString().equals("")) {
                        viewPager.setCurrentItem(8);
                    }


                    btRRegister.setVisibility(View.GONE);
                } else if (position == 9) {

                    /*if (edtUgClgName.getText().toString().equals("") && edtUgYop.getText().toString().equals("")
                            && edtUgAvgMarks.getText().toString().equals("")) {
                        viewPager.setCurrentItem(6);
                    }*/

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 10) {

                    /*if ((edtPgClgName.getText().toString().equals("") && edtPgYop.getText().toString().equals("")
                            && edtPgAvgMarks.getText().toString().equals(""))) {
                        viewPager.setCurrentItem(7);
                    }*/

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 11) {

                    if (etFatherName.getText().toString().equals("") && etFatherEducation.getText().toString().equals("")
                            && etFatherOccupation.getText().toString().equals("")

                            ) {
                        viewPager.setCurrentItem(11);
                    }

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 12) {

                    if (etMotherName.getText().toString().equals("") && etMotherEducation.getText().toString().equals("")
                            && etMotherOccupation.getText().toString().equals("")

                            ) {
                        viewPager.setCurrentItem(12);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (position == 13) {

                   /* if (etSiblingOneName.getText().toString().equals("") && etSiblingOneEducation.getText().toString().equals("")
                            && etSiblingOneOccupation.getText().toString().equals("")

                            ) {
                        viewPager.setCurrentItem(13);
                    }*/


                    btRRegister.setVisibility(View.GONE);
                } else if (position == 14) {
                    /*if (etSiblingTwoName.getText().toString().equals("") && etSiblingTwoEducation.getText().toString().equals("")
                            && etSiblingTwoOccupation.getText().toString().equals("")

                            ) {
                        viewPager.setCurrentItem(14);
                    }*/

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 15) {
                   /* if (etSiblingThreeName.getText().toString().equals("")
                            && etSiblingThreeEducation.getText().toString().equals("") && etSiblingThreeOccupation.getText().toString().equals("")
                            ){
                        viewPager.setCurrentItem(15);
                    }*/


                    btRRegister.setVisibility(View.GONE);
                } else if (position == 16) {
                    /*if (etSiblingFourName.getText().toString().equals("") && etSiblingFourEducation.getText().toString().equals("")
                            && etSiblingFourOccupation.getText().toString().equals("")

                            ) {
                        viewPager.setCurrentItem(16);
                    }*/

                    btRRegister.setVisibility(View.GONE);
                } else if (position == 17) {
                    {
                        viewPager.setCurrentItem(17);
                    }

                    btRRegister.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        etDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                DatePickerDialog datePickerDialog = new DatePickerDialog(CollegeStudentRegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                etDob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsGender);
        sp_s_gender.setAdapter(adapter);

        sp_s_gender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_gender = itemsGender[i];

            }
        });

        ArrayAdapter<String> adapterrole = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_role);
        sp_user_role.setAdapter(adapterrole);

        sp_user_role.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_role = arr_role[i];

            }
        });

    /*    ArrayAdapter<VenueList> adaptervenue = new ArrayAdapter<VenueList>(this, android.R.layout.simple_list_item_1, venueLists);
        sp_user_walkinvenue.setAdapter(adaptervenue);*/

        sp_user_walkinvenue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_venue = venuelist[i];

            }
        });


        ArrayAdapter<String> adapterr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemState);
        sp_state.setAdapter(adapterr);

        sp_state.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_state = itemState[i];

            }
        });


        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsDegree);
        sp_ug_degree.setAdapter(adapter2);

        sp_ug_degree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_grad_degree = itemsDegree[i];

            }
        });


        ArrayAdapter<String> adapter2_one = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsPgDeg);
        sp_pg_degree.setAdapter(adapter2_one);

        sp_pg_degree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_post_grad_degree = itemsPgDeg[i];

            }
        });


        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsStram);
        sp_ug_stream.setAdapter(adapter3);

        sp_ug_stream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_grad_stream = itemsPgStream[i];

            }
        });


        ArrayAdapter<String> adapter3_one = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsPgStream);
        sp_pg_stream.setAdapter(adapter3_one);

        sp_pg_stream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_post_grad_stream = itemsStram[i];

            }
        });

        ArrayAdapter<String> adapter4_one = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTboard);
        sp_t_board.setAdapter(adapter4_one);
        sp_t_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_X_board = itemsTboard[i];

            }
        });

        ArrayAdapter<String> adapter5_one = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTwboard);
        sp_tw_board.setAdapter(adapter5_one);

        sp_tw_board.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_XII_board = itemsTwboard[i];

            }
        });

        ArrayAdapter<String> adapter6_one = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsTwStream);
        sp_tw_stream.setAdapter(adapter6_one);

        sp_tw_stream.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                walk_in_XII_stream = itemsTwStream[i];

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btPrevious:

                if (viewPager.getCurrentItem() == 1) {
                    viewPager.setCurrentItem(0);
                } else if (viewPager.getCurrentItem() == 2) {
                    viewPager.setCurrentItem(1);
                } else if (viewPager.getCurrentItem() == 3) {
                    viewPager.setCurrentItem(2);
                } else if (viewPager.getCurrentItem() == 4) {
                    viewPager.setCurrentItem(3);
                } else if (viewPager.getCurrentItem() == 5) {
                    viewPager.setCurrentItem(4);
                } else if (viewPager.getCurrentItem() == 6) {
                    viewPager.setCurrentItem(5);
                } else if (viewPager.getCurrentItem() == 7) {
                    viewPager.setCurrentItem(6);
                } else if (viewPager.getCurrentItem() == 8) {
                    viewPager.setCurrentItem(7);
                } else if (viewPager.getCurrentItem() == 9) {
                    viewPager.setCurrentItem(8);
                } else if (viewPager.getCurrentItem() == 10) {
                    viewPager.setCurrentItem(9);
                } else if (viewPager.getCurrentItem() == 11) {
                    if (previousItem == 8) {
                        viewPager.setCurrentItem(8);
                    }
                    if (previousItem == 9) {
                        viewPager.setCurrentItem(9);
                    }
                    if (previousItem == 10) {
                        viewPager.setCurrentItem(10);
                    }
                } else if (viewPager.getCurrentItem() == 12) {
                    viewPager.setCurrentItem(11);
                } else if (viewPager.getCurrentItem() == 13) {
                    viewPager.setCurrentItem(12);
                } else if (viewPager.getCurrentItem() == 14) {
                    viewPager.setCurrentItem(13);
                } else if (viewPager.getCurrentItem() == 15) {
                    viewPager.setCurrentItem(14);
                } else if (viewPager.getCurrentItem() == 16) {
                    viewPager.setCurrentItem(15);
                } else if (viewPager.getCurrentItem() == 17) {


                    if (previousItem == 12) {
                        viewPager.setCurrentItem(12);
                    }
                    if (previousItem == 13) {
                        viewPager.setCurrentItem(13);
                    }
                    if (previousItem == 14) {
                        viewPager.setCurrentItem(14);
                    }
                    if (previousItem == 15) {
                        viewPager.setCurrentItem(15);
                    }
                    if (previousItem == 16) {
                        viewPager.setCurrentItem(16);
                    }

                }

                break;
            case R.id.btNext:


                if (viewPager.getCurrentItem() == 0) {

                    viewPager.setCurrentItem(1);

                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 1) {

                    viewPager.setCurrentItem(2);

                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 2) {
                    if (validateName()) {
                        viewPager.setCurrentItem(3);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 3) {

                    if (validateEmailId()) {
                        //email_id = etEmailId.getText().toString();
                        viewPager.setCurrentItem(5);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 4) {

                    if (validateContactNumber()) {
                        //contact_number = etContactNumer.getText().toString();
                        viewPager.setCurrentItem(5);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 5) {

                    if (validateGenderDob()) {
                        //contact_number = etContactNumer.getText().toString();
                        viewPager.setCurrentItem(6);
                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 6) {

                    if (validateResidentialDetail()) {
                        viewPager.setCurrentItem(7);

                    }
                    btRRegister.setVisibility(View.GONE);

                } else if (viewPager.getCurrentItem() == 7) {
                    if (validateTenthEduDetail()) {
                        viewPager.setCurrentItem(8);

                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 8) {
                    previousItem = viewPager.getCurrentItem();
                    if (validateTwelthEduDetail()) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Are you Graduate or pursuing your Graduation?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(9);
                                btRRegister.setVisibility(View.GONE);
                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                viewPager.setCurrentItem(11);
                                btRRegister.setVisibility(View.GONE);


                                dialog.cancel();
                            }
                        });
                        alertDialog.show();

                    }

                } else if (viewPager.getCurrentItem() == 9) {
                    previousItem = viewPager.getCurrentItem();
                    if (validateGraduationEduDetail()) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Are you PostGraduate or pursuing your PostGraduation?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(10);
                                btRRegister.setVisibility(View.GONE);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                btRRegister.setVisibility(View.GONE);
                                viewPager.setCurrentItem(11);


                                dialog.cancel();
                            }
                        });
                        alertDialog.show();


                    }

                } else if (viewPager.getCurrentItem() == 10) {
                    previousItem = viewPager.getCurrentItem();
                    if (validatePostGraduationEduDetail()) {
                        viewPager.setCurrentItem(11);

                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 11) {

                    if (validateFatherDetail()) {
                        viewPager.setCurrentItem(12);

                    }
                    btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 12) {
                    previousItem = viewPager.getCurrentItem();

                    if (validateMotherDetail()) {

                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Do you have Siblings?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(13);
                                btRRegister.setVisibility(View.GONE);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                viewPager.setCurrentItem(17);
                                btRRegister.setVisibility(View.VISIBLE);
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();

                        //viewPager.setCurrentItem(10);

                    }
                    //btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 13) {
                    previousItem = viewPager.getCurrentItem();

                    if (validateSiblingOneDetail()) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Do you have more Siblings?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(14);
                                btRRegister.setVisibility(View.GONE);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //btRRegister.setVisibility(View.GONE);
                                viewPager.setCurrentItem(17);
                                btRRegister.setVisibility(View.VISIBLE);
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }

                } else if (viewPager.getCurrentItem() == 14) {
                    previousItem = viewPager.getCurrentItem();
                    if (validateSiblingTwoDetail()) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Do you have more Siblings?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(15);
                                btRRegister.setVisibility(View.GONE);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //btRRegister.setVisibility(View.GONE);
                                viewPager.setCurrentItem(17);
                                btRRegister.setVisibility(View.VISIBLE);
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }
                    //btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 15) {
                    previousItem = viewPager.getCurrentItem();

                    if (validateSiblingThreeDetail()) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                        alertDialog.setTitle("Confirm...");
                        alertDialog.setMessage("Do you have more Siblings?");
                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                viewPager.setCurrentItem(16);
                                btRRegister.setVisibility(View.GONE);

                            }
                        });
                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //btRRegister.setVisibility(View.GONE);
                                viewPager.setCurrentItem(17);
                                btRRegister.setVisibility(View.VISIBLE);
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();

                    }
                    //btRRegister.setVisibility(View.GONE);
                } else if (viewPager.getCurrentItem() == 16) {
                    previousItem = viewPager.getCurrentItem();

                    if (validateSiblingFourDetail()) {
                        viewPager.setCurrentItem(17);
                        btRRegister.setVisibility(View.GONE);
                    }
                } else if (viewPager.getCurrentItem() == 17) {


                    btRRegister.setVisibility(View.VISIBLE);

                }


                break;
            case R.id.btRRegister: {


                walk_in_name = etUserName.getText().toString().trim();
                walk_in_email = etEmailId.getText().toString().trim();
                walk_in_dob = etDob.getText().toString().trim();
                walk_in_mobile = etContactNumer.getText().toString().trim();
                walk_in_address = etAddress.getText().toString().trim();
                walk_in_pin = etPinCode.getText().toString().trim();

                walk_in_X_yop = edt10thYearPassing.getText().toString().trim();
                walk_in_X_school = edt10thSchool.getText().toString().trim();
                walk_in_X_percentage = edt10thMarks.getText().toString().trim();

                walk_in_XII_yop = edt12thYearPassing.getText().toString().trim();
                walk_in_XII_school = edt12thSchool.getText().toString().trim();
                walk_in_XII_percentage = edt12thMarks.getText().toString().trim();

                walk_in_grad_univ = edtUgUniName.getText().toString().trim();
                walk_in_grad_clgname = edtUgClgName.getText().toString().trim();
                walk_in_grad_yop = edtUgYop.getText().toString().trim();
                walk_in_grad_percentage = edtUgAvgMarks.getText().toString().trim();

                walk_in_post_grad_uniname = edtPgUniName.getText().toString().trim();
                walk_in_post_grad_clgname = edtPgClgName.getText().toString().trim();
                walk_in_post_grad_yop = edtPgYop.getText().toString().trim();
                walk_in_post_grad_percentage = edtPgAvgMarks.getText().toString().trim();

                walk_in_father_name = etFatherName.getText().toString().trim();
                walk_in_father_education = etFatherEducation.getText().toString().trim();
                walk_in_father_occupation = etFatherOccupation.getText().toString().trim();

                walk_in_mother_name = etMotherName.getText().toString().trim();
                walk_in_mother_education = etMotherEducation.getText().toString().trim();
                walk_in_mother_occupation = etMotherOccupation.getText().toString().trim();

                walk_in_sibling_1_name = etSiblingOneName.getText().toString().trim();
                walk_in_sibling_1_education = etSiblingOneEducation.getText().toString().trim();
                walk_in_sibling_1_occupation = etSiblingOneOccupation.getText().toString().trim();

                walk_in_sibling_2_name = etSiblingTwoName.getText().toString().trim();
                walk_in_sibling_2_education = etSiblingTwoEducation.getText().toString().trim();
                walk_in_sibling_2_occupation = etSiblingTwoOccupation.getText().toString().trim();

                walk_in_sibling_3_name = etSiblingThreeName.getText().toString().trim();
                walk_in_sibling_3_education = etSiblingThreeEducation.getText().toString().trim();
                walk_in_sibling_3_occupation = etSiblingThreeOccupation.getText().toString().trim();

                walk_in_sibling_4_name = etSiblingFourName.getText().toString().trim();
                walk_in_sibling_4_education = etSiblingFourEducation.getText().toString().trim();
                walk_in_sibling_4_occupation = etSiblingFourOccupation.getText().toString().trim();


                register_useraddress_detail();


            }
            break;

        }
    }


    class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 18;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        public Object instantiateItem(View collection, int position) {

            int resId = 0;
            switch (position) {
                case 0:
                    resId = R.id.llBase;

                    break;
                case 1:
                    resId = R.id.llStart;

                    break;


                case 2:
                    resId = R.id.llFirst;

                    break;
                case 3:
                    resId = R.id.llSecond;
                    break;
                case 4:
                    resId = R.id.llThird;
                    break;
                case 5:
                    resId = R.id.llFourth;
                    break;
                case 6:
                    resId = R.id.llFifth;
                    break;
                case 7:
                    resId = R.id.llSixth;
                    break;
                case 8:
                    resId = R.id.llSeventh;
                    break;
                case 9:
                    resId = R.id.llEight;
                    break;
                case 10:
                    resId = R.id.llNine;
                    break;
                case 11:
                    resId = R.id.llTen;
                    break;
                case 12:
                    resId = R.id.llEleven;
                    break;
                case 13:
                    resId = R.id.llTwelth;
                    break;
                case 14:
                    resId = R.id.llThirteen;
                    break;
                case 15:
                    resId = R.id.llFourteen;
                    break;
                case 16:
                    resId = R.id.llFifteen;
                    break;
                case 17:
                    resId = R.id.llsixteen;
                    break;
            }
            return findViewById(resId);
        }
    }


    public boolean validateContactNumber() {
        boolean valid = true;


        if (etContactNumer.getText().toString().trim().equals("")) {
            etContactNumer.setError("Enter Contact Number");
            valid = false;
        } else {
            final String m_no = etContactNumer.getText().toString();
            if (!isValidAContactNo(m_no)) {
                etContactNumer.setError("Please enter 10 digit Contact Number");

                valid = false;
            }
        }
        return valid;
    }

    private boolean isValidAContactNo(String mobNo) {
        Pattern pattern = Pattern.compile("[0-9]{10}");
        Matcher matcher = pattern.matcher(mobNo);
        return matcher.matches();
    }

    public boolean validateEmailId() {
        boolean valid = true;


        if (etEmailId.getText().toString().trim().equals("")) {
            etEmailId.setError("Enter Email Id");
            valid = false;
        } else {
            final String m_no = etEmailId.getText().toString();
            if (!isValidEmail(m_no)) {
                etEmailId.setError("Please enter valid email id");
                valid = false;
            }
        }
        return valid;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public boolean validateName() {
        boolean valid = true;
        if (etUserName.getText().toString().trim().equals("")) {
            etUserName.setError("Enter Your Name");
            valid = false;
        } else {
            final String fName = etUserName.getText().toString();
            if (!isValidName(fName)) {
                etUserName.setError("Enter Valid Name");
                valid = false;
            }
        }


        return valid;
    }

    public boolean validateGenderDob() {
        boolean valid = true;
        if (walk_in_gender == null) {
            Toast.makeText(getApplicationContext(), "Select Gender", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (etDob.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Select Date of birth", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private boolean isValidName(String name) {
        Pattern pattern;
        pattern = Pattern.compile("[a-zA-Z ]{1,100}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidSchoolName(String name) {
        Pattern pattern;
        pattern = Pattern.compile("[a-zA-Z ]{1,300}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }


    public boolean validateResidentialDetail() {
        boolean valid = true;


        if (etAddress.getText().toString().trim().equals("")) {
            etAddress.setError("Enter Your Address");
            valid = false;
        }

        if (etPinCode.getText().toString().trim().equals("")) {
            etPinCode.setError("Enter Pin Code");
            valid = false;
        } else {
            final String fName = etPinCode.getText().toString();
            if (!isValidPinCode(fName)) {
                etPinCode.setError("Enter Valid Pin Code of 6 digit only");
                valid = false;
            }
        }

        if (walk_in_state == null) {
            Toast.makeText(getApplicationContext(), "Select State", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }

    public boolean validateTenthEduDetail() {
        boolean valid = true;


        if (edt10thYearPassing.getText().toString().trim().equals("")) {
            edt10thYearPassing.setError("Enter Year of Passing");
            valid = false;
        } else {
            final String sYear = edt10thYearPassing.getText().toString();
            if (!isValidYear(sYear)) {
                edt10thYearPassing.setError("Enter Valid Calender Year");
                valid = false;
            }
        }

        if (edt10thMarks.getText().toString().trim().equals("")) {
            edt10thMarks.setError("Enter 10th Average % of Marks");
            valid = false;
        } else {
            final String sMarks = edt10thMarks.getText().toString();
            if (!isValidPerMakrs(sMarks)) {
                edt10thMarks.setError("Enter Valid % of Marks");
                valid = false;
            }
        }

        if (edt10thSchool.getText().toString().trim().equals("")) {
            edt10thSchool.setError("Enter School Name");
            valid = false;
        } else {
            final String sName = edt10thSchool.getText().toString();
            /*if (!isValidSchoolName(sName)) {
                edt10thSchool.setError("Enter Valid School Name");
                valid = false;
            }*/
        }

        if (walk_in_X_board == null) {
            Toast.makeText(getApplicationContext(), "Select Board", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    public boolean validateFatherDetail() {
        boolean valid = true;


        if (etFatherName.getText().toString().trim().equals("")) {
            etFatherName.setError("Enter Name");
            valid = false;
        } else {
            final String sYear = etFatherName.getText().toString();
            if (!isValidName(sYear)) {
                etFatherName.setError("Enter Valid Name");
                valid = false;
            }
        }


        if (etFatherEducation.getText().toString().trim().equals("")) {
            etFatherEducation.setError("Enter Education");
            valid = false;
        }

        if (etFatherOccupation.getText().toString().trim().equals("")) {
            etFatherOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sYear = etFatherOccupation.getText().toString();
            if (!isValidName(sYear)) {
                etFatherOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }
        return valid;
    }

    public boolean validateMotherDetail() {
        boolean valid = true;

        if (etMotherName.getText().toString().trim().equals("")) {
            etMotherName.setError("Enter Name");
            valid = false;
        } else {
            final String sName = etMotherName.getText().toString();
            if (!isValidName(sName)) {
                etMotherName.setError("Enter Valid Name");
                valid = false;
            }
        }

        if (etMotherEducation.getText().toString().trim().equals("")) {
            etMotherEducation.setError("Enter Education");
            valid = false;
        }

        if (etMotherOccupation.getText().toString().trim().equals("")) {
            etMotherOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sName = etMotherOccupation.getText().toString();
            if (!isValidName(sName)) {
                etMotherOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }

        return valid;
    }

    public boolean validateSiblingOneDetail() {
        boolean valid = true;


        if (etSiblingOneName.getText().toString().trim().equals("")) {
            etSiblingOneName.setError("Enter Name");
            valid = false;
        } else {
            final String sYear = etSiblingOneName.getText().toString();
            if (!isValidName(sYear)) {
                etSiblingOneName.setError("Enter Valid Name");
                valid = false;
            }
        }

        if (etSiblingOneEducation.getText().toString().trim().equals("")) {
            etSiblingOneEducation.setError("Enter Education");
            valid = false;
        }


        if (etSiblingOneOccupation.getText().toString().trim().equals("")) {
            etSiblingOneOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sYear = etSiblingOneOccupation.getText().toString();
            if (!isValidName(sYear)) {
                etSiblingOneOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }


        return valid;
    }

    public boolean validateSiblingTwoDetail() {
        boolean valid = true;


        if (etSiblingTwoName.getText().toString().trim().equals("")) {
            etSiblingTwoName.setError("Enter Name");
            valid = false;
        } else {
            final String sName = etSiblingTwoName.getText().toString();
            if (!isValidName(sName)) {
                etSiblingTwoName.setError("Enter Valid Name");
                valid = false;
            }
        }

        if (etSiblingTwoEducation.getText().toString().trim().equals("")) {
            etSiblingTwoEducation.setError("Enter Education");
            valid = false;
        }

        if (etSiblingTwoOccupation.getText().toString().trim().equals("")) {
            etSiblingTwoOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sName = etSiblingTwoOccupation.getText().toString();
            if (!isValidName(sName)) {
                etSiblingTwoOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }

        return valid;
    }

    public boolean validateSiblingThreeDetail() {
        boolean valid = true;


        if (etSiblingThreeName.getText().toString().trim().equals("")) {
            etSiblingThreeName.setError("Enter Name");
            valid = false;
        } else {
            final String sYear = etSiblingThreeName.getText().toString();
            if (!isValidName(sYear)) {
                etSiblingThreeName.setError("Enter Valid Name");
                valid = false;
            }
        }

        if (etSiblingThreeEducation.getText().toString().trim().equals("")) {
            etSiblingThreeEducation.setError("Enter Education");
            valid = false;
        }

        if (etSiblingThreeOccupation.getText().toString().trim().equals("")) {
            etSiblingThreeOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sYear = etSiblingThreeOccupation.getText().toString();
            if (!isValidName(sYear)) {
                etSiblingThreeOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }


        return valid;
    }


    public boolean validateSiblingFourDetail() {
        boolean valid = true;

        if (etSiblingFourName.getText().toString().trim().equals("")) {
            etSiblingFourName.setError("Enter Name");
            valid = false;
        } else {
            final String sName = etSiblingFourName.getText().toString();
            if (!isValidName(sName)) {
                etSiblingFourName.setError("Enter Valid Name");
                valid = false;
            }
        }


        if (etSiblingFourEducation.getText().toString().trim().equals("")) {
            etSiblingFourEducation.setError("Enter Education");
            valid = false;
        }
        if (etSiblingFourOccupation.getText().toString().trim().equals("")) {
            etSiblingFourOccupation.setError("Enter Occupation");
            valid = false;
        } else {
            final String sName = etSiblingFourOccupation.getText().toString();
            if (!isValidName(sName)) {
                etSiblingFourOccupation.setError("Enter Valid Occupation");
                valid = false;
            }
        }

        return valid;
    }


    public boolean validateTwelthEduDetail() {
        boolean valid = true;


        if (edt12thYearPassing.getText().toString().trim().equals("")) {
            edt12thYearPassing.setError("Enter Year of Passing");
            valid = false;
        } else {
            final String sYear = edt12thYearPassing.getText().toString();
            if (!isValidYear(sYear)) {
                edt12thYearPassing.setError("Enter Valid Calender Year");
                valid = false;
            }
        }

        if (edt12thMarks.getText().toString().trim().equals("")) {
            edt12thMarks.setError("Enter 12th Average % of Marks");
            valid = false;
        } else {
            final String sMarks = edt12thMarks.getText().toString();
            if (!isValidPerMakrs(sMarks)) {
                edt12thMarks.setError("Enter Valid % of Marks");
                valid = false;
            }
        }
        if (edt12thSchool.getText().toString().trim().equals("")) {
            edt12thSchool.setError("Enter School Name");
            valid = false;
        } else {
            final String sName = edt12thSchool.getText().toString();
           /* if (!isValidSchoolName(sName)) {
                edt12thSchool.setError("Enter Valid School Name");
                valid = false;
            }*/
        }

        if (walk_in_XII_board == null) {
            Toast.makeText(getApplicationContext(), "Select Board", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        if (walk_in_XII_stream == null) {
            Toast.makeText(getApplicationContext(), "Select Stream", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    public boolean validateGraduationEduDetail() {
        boolean valid = true;


        if (edtUgUniName.getText().toString().trim().equals("")) {
            edtUgUniName.setError("Enter University Name");
            valid = false;
        } else {
            final String sName = edtUgUniName.getText().toString();
            if (!isValidSchoolName(sName)) {
                edtUgUniName.setError("Enter Valid University Name");
                valid = false;
            }
        }


        if (edtUgClgName.getText().toString().trim().equals("")) {
            edtUgClgName.setError("Enter College Name");
            valid = false;
        } else {
            final String sName = edtUgClgName.getText().toString();
           /* if (!isValidSchoolName(sName)) {
                edtUgClgName.setError("Enter Valid College Name");
                valid = false;
            }*/
        }

        if (edtUgYop.getText().toString().trim().equals("")) {
            edtUgYop.setError("Enter Year of Passing");
            valid = false;
        } else {
            final String sYear = edtUgYop.getText().toString();
            if (!isValidYear(sYear)) {
                edtUgYop.setError("Enter Valid Calender Year");
                valid = false;
            }
        }
        if (edtUgAvgMarks.getText().toString().trim().equals("")) {
            edtUgAvgMarks.setError("Enter Graduation Average % of Marks");
            valid = false;
        } else {
            final String sMarks = edtUgAvgMarks.getText().toString();
            if (!isValidPerMakrs(sMarks)) {
                edtUgAvgMarks.setError("Enter Valid % of Marks");
                valid = false;
            }
        }

        if (walk_in_grad_degree == null) {
            Toast.makeText(getApplicationContext(), "Select Degree", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (walk_in_grad_stream == null) {
            Toast.makeText(getApplicationContext(), "Select Stream", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return valid;
    }


    public boolean validatePostGraduationEduDetail() {
        boolean valid = true;


        if (edtPgUniName.getText().toString().trim().equals("")) {
            edtPgUniName.setError("Enter University Name");
            valid = false;
        } else {
            final String sName = edtPgUniName.getText().toString();
            if (!isValidSchoolName(sName)) {
                edtPgUniName.setError("Enter Valid University Name");
                valid = false;
            }
        }


        if (edtPgClgName.getText().toString().trim().equals("")) {
            edtPgClgName.setError("Enter College Name");
            valid = false;
        } else {
            final String sName = edtPgClgName.getText().toString();
           /* if (!isValidSchoolName(sName)) {
                edtPgClgName.setError("Enter Valid College Name");
                valid = false;
            }*/
        }

        if (edtPgYop.getText().toString().trim().equals("")) {
            edtPgYop.setError("Enter Year of Passing");
            valid = false;
        } else {
            final String sYear = edtPgYop.getText().toString();
            if (!isValidYear(sYear)) {
                edtPgYop.setError("Enter Valid Calender Year");
                valid = false;
            }
        }
        if (edtPgAvgMarks.getText().toString().trim().equals("")) {
            edtPgAvgMarks.setError("Enter Graduation Average % of Marks");
            valid = false;
        } else {
            final String sMarks = edtPgAvgMarks.getText().toString();
            if (!isValidPerMakrs(sMarks)) {
                edtPgAvgMarks.setError("Enter Valid % of Marks");
                valid = false;
            }
        }
        if (walk_in_post_grad_degree == null) {
            Toast.makeText(getApplicationContext(), "Select Degree", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (walk_in_post_grad_stream == null) {
            Toast.makeText(getApplicationContext(), "Select Stream", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        return valid;
    }

    private boolean isValidAddress(String name) {
        Pattern pattern;
        pattern = Pattern.compile("[a-zA-Z0-9\\.\\-\\s\\,]{2,40}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean isValidPinCode(String pCode) {
        Pattern pattern = Pattern.compile("[0-9]{6}");
        Matcher matcher = pattern.matcher(pCode);
        return matcher.matches();
    }

    private boolean isValidYear(String pCode) {
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher matcher = pattern.matcher(pCode);
        return matcher.matches();
    }

    private boolean isValidPerMakrs(String pCode) {
        Pattern pattern = Pattern.compile("[0-9]{2}");
        Matcher matcher = pattern.matcher(pCode);
        return matcher.matches();
    }


    private boolean isValidState(String stateName) {
        Pattern pattern;
        pattern = Pattern.compile("[a-zA-Z ]{1,300}");
        Matcher matcher = pattern.matcher(stateName);
        return matcher.matches();
    }

    public void myLayoutViewIDs() {
        btNext = (Button) findViewById(R.id.btNext);
        btRRegister = (Button) findViewById(R.id.btRRegister);
        btPrevious = (Button) findViewById(R.id.btPrevious);
        btnPickImage = (Button) findViewById(R.id.pick_img);
        imgView = (ImageView) findViewById(R.id.preview);

        btNext.setOnClickListener(this);
        btRRegister.setOnClickListener(this);
        btPrevious.setOnClickListener(this);

        sp_user_role = (MaterialBetterSpinner) findViewById(R.id.sp_role);
        sp_user_walkinvenue = (MaterialBetterSpinner) findViewById(R.id.sp_venue);
        etUserName = (EditText) findViewById(R.id.edt_Name);
        sp_s_gender = (MaterialBetterSpinner) findViewById(R.id.sp_s_gender);

        etContactNumer = (EditText) findViewById(R.id.edt_phn_no);

        etEmailId = (EditText) findViewById(R.id.edt_Email_id);
        etDob = (EditText) findViewById(R.id.edt_s_dob);
        etDob.setClickable(true);

        etAddress = (EditText) findViewById(R.id.edt_Address);
        sp_state = (MaterialBetterSpinner) findViewById(R.id.spinner_state);
        etPinCode = (EditText) findViewById(R.id.edt_pincode);

        sp_t_board = (MaterialBetterSpinner) findViewById(R.id.sp_t_board);
        edt10thYearPassing = (EditText) findViewById(R.id.et10thYearOfPassing);
        edt10thMarks = (EditText) findViewById(R.id.et10thPerMarks);
        edt10thSchool = (EditText) findViewById(R.id.edt10thSchool);

        sp_tw_board = (MaterialBetterSpinner) findViewById(R.id.sp_tw_board);
        edt12thYearPassing = (EditText) findViewById(R.id.edt12thPassingYear);
        edt12thMarks = (EditText) findViewById(R.id.edt12thMarks);
        sp_tw_stream = (MaterialBetterSpinner) findViewById(R.id.sp_tw_stream);
        edt12thSchool = (EditText) findViewById(R.id.edt12thSchool);


        edtUgUniName = (EditText) findViewById(R.id.edtUgUniName);
        edtUgClgName = (EditText) findViewById(R.id.edtUgClgName);
        sp_ug_degree = (MaterialBetterSpinner) findViewById(R.id.spinner_clg_degree);
        edtUgYop = (EditText) findViewById(R.id.edtClgYearPassing);
        edtUgAvgMarks = (EditText) findViewById(R.id.edtClgAvgMarks);
        sp_ug_stream = (MaterialBetterSpinner) findViewById(R.id.spinner_clg_stream);

        edtPgUniName = (EditText) findViewById(R.id.edtPgUName);
        edtPgClgName = (EditText) findViewById(R.id.edtPgUCName);
        sp_pg_degree = (MaterialBetterSpinner) findViewById(R.id.spPgDegree);
        edtPgYop = (EditText) findViewById(R.id.edtPgYearPassing);
        edtPgAvgMarks = (EditText) findViewById(R.id.edtPgAvgMarks);
        sp_pg_stream = (MaterialBetterSpinner) findViewById(R.id.spPgStream);

        etFatherName = (EditText) findViewById(R.id.etFatherName);
        etFatherEducation = (EditText) findViewById(R.id.etFatherEducation);
        etFatherOccupation = (EditText) findViewById(R.id.etFatherOccupation);
        etMotherName = (EditText) findViewById(R.id.etMotherName);
        etMotherEducation = (EditText) findViewById(R.id.etMotherEducation);
        etMotherOccupation = (EditText) findViewById(R.id.etMotherOccupation);

        etSiblingOneName = (EditText) findViewById(R.id.etSiblingOneName);
        etSiblingOneEducation = (EditText) findViewById(R.id.etSiblingOneEducation);
        etSiblingOneOccupation = (EditText) findViewById(R.id.etSiblingOneOccupation);
        etSiblingTwoName = (EditText) findViewById(R.id.etSiblingTwoName);
        etSiblingTwoEducation = (EditText) findViewById(R.id.etSiblingTwoEducation);
        etSiblingTwoOccupation = (EditText) findViewById(R.id.etSiblingTwoOccupation);

        etSiblingThreeName = (EditText) findViewById(R.id.etSiblingThreeName);
        etSiblingThreeEducation = (EditText) findViewById(R.id.etSiblingThreeEducation);
        etSiblingThreeOccupation = (EditText) findViewById(R.id.etSiblingThreeOccupation);
        etSiblingFourName = (EditText) findViewById(R.id.etSiblingFourName);
        etSiblingFourEducation = (EditText) findViewById(R.id.etSiblingFourEducation);
        etSiblingFourOccupation = (EditText) findViewById(R.id.etSiblingFourOccupation);


    }

    public void register_useraddress_detail() {
        pDialog = new ProgressDialog(CollegeStudentRegistrationActivity.this);
        pDialog.setMessage("Loading....Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        /* Log.e("vvvvv", walk_in_role);
                Log.e("vvvvv", walk_in_venue);
                Log.e("vvvvv", walk_in_name);
                Log.e("vvvvv", walk_in_email);
                Log.e("vvvvv", walk_in_gender);

                Log.e("vvvvv", walk_in_dob);
                Log.e("vvvvv", walk_in_mobile);
                Log.e("vvvvv", walk_in_address);
                Log.e("vvvvv", walk_in_state);

                Log.e("vvvvv", walk_in_pin);
                Log.e("vvvvv", walk_in_X_board);
                Log.e("vvvvv", walk_in_X_yop);
                Log.e("vvvvv", walk_in_X_school);
                Log.e("vvvvv", walk_in_X_percentage);
                Log.e("vvvvv", walk_in_XII_board);
                Log.e("vvvvv", walk_in_XII_stream);
                Log.e("vvvvv", walk_in_XII_yop);
                Log.e("vvvvv", walk_in_XII_school);
                Log.e("vvvvv", walk_in_XII_percentage);
                Log.e("vvvvv", walk_in_grad_degree);
                Log.e("vvvvv", walk_in_grad_stream);
                Log.e("vvvvv", walk_in_grad_univ);
                Log.e("vvvvv", walk_in_grad_clgname);
                Log.e("vvvvv", walk_in_grad_yop);
                Log.e("vvvvv", walk_in_grad_percentage);
                Log.e("vvvvv", walk_in_post_grad_uniname);
                Log.e("vvvvv", walk_in_post_grad_clgname);
                Log.e("vvvvv", walk_in_post_grad_yop);
                Log.e("vvvvv", walk_in_post_grad_percentage);
                Log.e("vvvvv", walk_in_post_grad_degree);
                Log.e("vvvvv", walk_in_post_grad_stream);
                Log.e("vvvvv", walk_in_father_name);
                Log.e("vvvvv", walk_in_father_education);
                Log.e("vvvvv", walk_in_father_occupation);
                Log.e("vvvvv", walk_in_mother_name);
                Log.e("vvvvv", walk_in_mother_education);
                Log.e("vvvvv", walk_in_mother_occupation);
                Log.e("vvvvv", walk_in_sibling_1_name);
                Log.e("vvvvv", walk_in_sibling_1_education);
                Log.e("vvvvv", walk_in_sibling_1_occupation);
                Log.e("vvvvv", walk_in_sibling_2_name);
                Log.e("vvvvv", walk_in_sibling_2_education);
                Log.e("vvvvv", walk_in_sibling_2_occupation);
                Log.e("vvvvv", walk_in_sibling_3_name);
                Log.e("vvvvv", walk_in_sibling_3_education);
                Log.e("vvvvv", walk_in_sibling_3_occupation);
                Log.e("vvvvv", walk_in_sibling_4_name);
                Log.e("vvvvv", walk_in_sibling_4_education);
                Log.e("vvvvv", walk_in_sibling_4_occupation);*/


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(150, TimeUnit.SECONDS)
                .build();




        File file = new File(mediaPath);




        try {
            compressedImageFile = new Compressor(this)
                    .setMaxWidth(80)
                    .setMaxHeight(80)
                    .compressToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), compressedImageFile);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        RequestInterface service1 = retrofit.create(RequestInterface.class);

        Call<List<SmsStatus>> call1 = service1.registerwalkins(walk_in_role, walk_in_name,
                walk_in_dob,
                walk_in_email,
                walk_in_mobile,
                walk_in_gender,
                walk_in_address,
                walk_in_state,
                walk_in_pin,

                walk_in_X_board,
                walk_in_X_school,
                walk_in_X_yop,
                walk_in_X_percentage,

                walk_in_XII_stream,
                walk_in_XII_board,
                walk_in_XII_school,
                walk_in_XII_yop,
                walk_in_XII_percentage,

                walk_in_grad_univ,
                walk_in_grad_clgname,
                walk_in_grad_degree,
                walk_in_grad_stream,
                walk_in_grad_yop,
                walk_in_grad_percentage,

                walk_in_post_grad_uniname,
                walk_in_post_grad_clgname,
                walk_in_post_grad_degree,
                walk_in_post_grad_stream,
                walk_in_post_grad_yop,
                walk_in_post_grad_percentage,

                walk_in_father_name,
                walk_in_father_education,
                walk_in_father_occupation,

                walk_in_mother_name,
                walk_in_mother_education,
                walk_in_mother_occupation,

                walk_in_sibling_1_name,
                walk_in_sibling_1_education,
                walk_in_sibling_1_occupation,

                walk_in_sibling_2_name,
                walk_in_sibling_2_education,
                walk_in_sibling_2_occupation,

                walk_in_sibling_3_name,
                walk_in_sibling_3_education,
                walk_in_sibling_3_occupation,

                walk_in_sibling_4_name,
                walk_in_sibling_4_education,
                walk_in_sibling_4_occupation,

                walk_in_venue,
                fileToUpload, filename);
        call1.enqueue(new Callback<List<SmsStatus>>() {
            @Override
            public void onResponse(Call<List<SmsStatus>> call, Response<List<SmsStatus>> response) {
                pDialog.dismiss();


                statusmessage = response.body();


                if (statusmessage == null) {

                    snack_barmessage("something went wrong. try after some time");

                } else if (response.isSuccessful()) {

                    snack_barmessage("Success");

                    LayoutInflater inflater = getLayoutInflater();
                    View alertLayout = inflater.inflate(R.layout.custom_dialog_walkin, null);
                    final Button btnok = (Button) alertLayout.findViewById(R.id.btn_ok);
                    android.app.AlertDialog.Builder alert = new android.app.AlertDialog.Builder(CollegeStudentRegistrationActivity.this);
                    alert.setView(alertLayout);
                    alert.setCancelable(true);

                    btnok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            Intent i = new Intent(getApplicationContext(), WelcomeActivity.class);
                            startActivity(i);
                            finishAffinity();
                        }
                    });

                    android.app.AlertDialog dialog = alert.create();
                    dialog.show();

                }

            }

            @Override
            public void onFailure(Call<List<SmsStatus>> call, Throwable t) {
                pDialog.dismiss();
                Log.e("fff", t.toString());

                snack_barmessage("Not able to Connect with server.Try After Some time");


            }
        });


    }

    public void getwalkinvenueslist() {

        pDialog = new ProgressDialog(CollegeStudentRegistrationActivity.this);
        pDialog.setMessage("Loading....Please Wait");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(150, TimeUnit.SECONDS)
                .connectTimeout(150, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        RequestInterface service1 = retrofit.create(RequestInterface.class);

        Call<List<VenueList>> call1 = service1.getvenueslist();
        call1.enqueue(new Callback<List<VenueList>>() {
            @Override
            public void onResponse(Call<List<VenueList>> call, Response<List<VenueList>> response) {
                pDialog.dismiss();
                if (response.isSuccessful()) {

                    venueLists = response.body();

                    venue_list_adapter();
                } else {
                    snack_barmessage("Something Went Wrong");
                }


            }

            @Override
            public void onFailure(Call<List<VenueList>> call, Throwable t) {
                pDialog.dismiss();
                Log.e("ffnff", t.toString());

                snack_barmessage("Not able to Connect with server.Try After Some time");

            }
        });


    }


    public void venue_list_adapter() {
        venuelist = new String[venueLists.size()];
        for (int i = 0; i < venueLists.size(); i++) {

            venuelist[i] = venueLists.get(i).getWalk_in_venue_name();

        }


        ArrayAdapter<String> vennue = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, venuelist);


        vennue.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp_user_walkinvenue.setAdapter(vennue);
    }

    public void snack_barmessage(String message) {


        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        sbView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


    private boolean isNetworkAvailable() {
        @SuppressLint("WrongConstant") ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 0 && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                assert cursor != null;
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mediaPath = cursor.getString(columnIndex);
               /* str1.setText(mediaPath);*/
                // Set the Image in ImageView for Previewing the Media
                imgView.setImageBitmap(BitmapFactory.decodeFile(mediaPath));
                cursor.close();

            } // When an Video is picked
            else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    // Providing Thumbnail For Selected Image
    public Bitmap getThumbnailPathForLocalFile(Activity context, Uri fileUri) {
        long fileId = getFileId(context, fileUri);
        return MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(),
                fileId, MediaStore.Video.Thumbnails.MICRO_KIND, null);
    }

    // Getting Selected File ID
    public long getFileId(Activity context, Uri fileUri) {
        Cursor cursor = context.managedQuery(fileUri, mediaColumns, null, null, null);
        if (cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
            return cursor.getInt(columnIndex);
        }
        return 0;
    }
}
