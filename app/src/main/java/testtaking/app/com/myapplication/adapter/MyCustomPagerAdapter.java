package testtaking.app.com.myapplication.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apmem.tools.layouts.FlowLayout;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import testtaking.app.com.myapplication.activity.ReportMainActivity;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.model.Success;
import testtaking.app.com.myapplication.network.ApiClient;
import testtaking.app.com.myapplication.network.RequestInterface;
import testtaking.app.com.myapplication.utils.DialogsUtils;
import testtaking.app.com.myapplication.R;


public class MyCustomPagerAdapter extends PagerAdapter {


    public static String isChecked = "null";
    private Context context;
    public static List<Question> questionList;
    private LayoutInflater layoutInflater;
    private RadioButton rd_o_1, rd_o_2, rd_o_3, rd_o_4;
    // private TextView tv_quest;
    private RadioGroup rd_yesno;
    private RadioGroup rd_truefalse;
    private RadioGroup rd_options;
    ImageView imgg_quest, img_opt1, img_opt2, img_opt3, img_opt4;
    TextView quest_number;

    ProgressDialog myDialog;
    private Button btn_ssubmit;
    String str_test_id, str_serial_id;
    JSONObject questionmain = new JSONObject();
    JSONObject questions = new JSONObject();
    JSONObject test = new JSONObject();
    JSONObject question;
    JSONObject optionobj;
    public static Map<Integer, Integer> question_ansewer_list = new HashMap<Integer, Integer>();
    LinearLayout LinearCompQues;
    LinearLayout lll_opt_1;

    public MyCustomPagerAdapter(final Context context, List<Question> questionList, String serial_id, String test_id) {

        this.context = context;
        this.questionList = questionList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.str_test_id = serial_id;
        this.str_serial_id = test_id;

    }


    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {


        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);


        LinearCompQues = (LinearLayout) itemView.findViewById(R.id.linearCompQues);
        // lll_opt_1 = itemView.findViewById(R.id.llopt_1);


        // tv_quest = itemView.findViewById(R.id.tv_question);
        rd_o_1 = (RadioButton) itemView.findViewById(R.id.rd_o_1);
        rd_o_2 = (RadioButton) itemView.findViewById(R.id.rd_o_2);
        rd_o_3 = (RadioButton) itemView.findViewById(R.id.rd_o_3);
        rd_o_4 = (RadioButton) itemView.findViewById(R.id.rd_o_4);
        imgg_quest = (ImageView) itemView.findViewById(R.id.img_quest);
        quest_number = (TextView) itemView.findViewById(R.id.question_number);
        img_opt1 = (ImageView) itemView.findViewById(R.id.img_opt_1);
        img_opt2 = (ImageView) itemView.findViewById(R.id.img_opt_2);
        img_opt3 = (ImageView) itemView.findViewById(R.id.img_opt_3);
        img_opt4 = (ImageView) itemView.findViewById(R.id.img_opt_4);


        rd_yesno = (RadioGroup) itemView.findViewById(R.id.rd_yn);
        rd_yesno.clearCheck();
        rd_truefalse = (RadioGroup) itemView.findViewById(R.id.rd_tf);
        rd_options = (RadioGroup) itemView.findViewById(R.id.rd_options);
        rd_truefalse.clearCheck();
        rd_options.clearCheck();
        rd_yesno.setVisibility(View.GONE);
        rd_truefalse.setVisibility(View.GONE);
        btn_ssubmit = (Button) itemView.findViewById(R.id.btn_submit);
        quest_number.setText((position + 1) + "");

        try {

            questions.put("question_count", questionList.size());
            for (int i = 0; i <= questionList.size(); i++) {
                question = new JSONObject();

                question.put("type" + position, (questionList.get(position).getType()));
                question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                question.put("attempted" + position, 0);
                question.put("question_id" + position, (questionList.get(position).getO_ID()));
                question.put("subquestion_id" + position, 0);
                question.put("sequence" + position, position + 1);
                question.put("student_id" + position, str_serial_id);
                question.put("correct_answer" + position, questionList.get(position).getANSWER());

                if (questionList.get(position).getType() == 2) {

                    optionobj = new JSONObject();
                    question.put("selected_option_count" + position, 1);


                    optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions1().getOPTION_ID());
                    optionobj.put("correct" + 0, 0);

                    question.put("selected_option" + 0, optionobj);
                } else if (questionList.get(position).getType() == 3 || questionList.get(position).getType() == 9) {
                    question.put("correct" + position, 0);
                }

                questions.put("question" + position, question);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        rd_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {


                isChecked = questionList.get(position).getNAME();

                try {
                    question = new JSONObject();
                    optionobj = new JSONObject();
                    question.put("type" + position, (questionList.get(position).getType()));
                    question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                    question.put("attempted" + position, 1);
                    question.put("question_id" + position, (questionList.get(position).getO_ID()));
                    question.put("subquestion_id" + position, 0);
                    question.put("sequence" + position, position + 1);
                    question.put("student_id" + position, str_serial_id);

                    question.put("selected_option_count" + position, 1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                if (checkedId == R.id.rd_o_1) {

                    questionList.get(position).setOptiontype(1);
                    try {
                        if (questionList.get(position).getOptions1().getOPTION_ANSWER() == 1) {
                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions1().getOPTION_ID());
                            optionobj.put("correct" + 0, 1);
                        } else {

                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions1().getOPTION_ID());
                            optionobj.put("correct" + 0, 0);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if (checkedId == R.id.rd_o_2) {

                    questionList.get(position).setOptiontype(2);

                    try {
                        if (questionList.get(position).getOptions2().getOPTION_ANSWER() == 1) {

                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions2().getOPTION_ID());
                            optionobj.put("correct" + 0, 1);
                        } else {

                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions2().getOPTION_ID());
                            optionobj.put("correct" + 0, 0);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (checkedId == R.id.rd_o_3) {
                    questionList.get(position).setOptiontype(3);

                    try {
                        if (questionList.get(position).getOptions3().getOPTION_ANSWER() == 1) {
                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions3().getOPTION_ID());
                            optionobj.put("correct" + 0, 1);
                        } else {

                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions3().getOPTION_ID());
                            optionobj.put("correct" + 0, 0);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else if (checkedId == R.id.rd_o_4) {
                    questionList.get(position).setOptiontype(4);

                    try {
                        if (questionList.get(position).getOptions4().getOPTION_ANSWER() == 1) {
                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions4().getOPTION_ID());
                            optionobj.put("correct" + 0, 1);
                        } else {

                            optionobj.put("selected_option_id" + 0, questionList.get(position).getOptions4().getOPTION_ID());
                            optionobj.put("correct" + 0, 0);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                try {
                    question.put("selected_option" + 0, optionobj);
                    question.put("correct_answer" + position, questionList.get(position).getANSWER());
                    questions.put("question" + position, question);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        rd_yesno.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                isChecked = questionList.get(position).getNAME();
                try {

                    question = new JSONObject();
                    optionobj = new JSONObject();

                    if (checkedId == R.id.rd_yes) {
                        questionList.get(position).setOptiontype(111);


                        try {

                            question.put("type" + position, (questionList.get(position).getType()));
                            question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                            question.put("attempted" + position, 1);
                            question.put("question_id" + position, (questionList.get(position).getO_ID()));
                            question.put("subquestion_id" + position, 0);
                            question.put("sequence" + position, position + 1);
                            question.put("student_id" + position, str_serial_id);
                            question.put("correct_answer" + position, questionList.get(position).getANSWER());


                            if (questionList.get(position).getANSWER() == 1) {
                                question.put("correct" + position, 1);
                            } else {
                                question.put("correct" + position, 0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else if (checkedId == R.id.rd_no) {
                        questionList.get(position).setOptiontype(222);
                        try {

                            question.put("type" + position, (questionList.get(position).getType()));
                            question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                            question.put("attempted" + position, 1);
                            question.put("question_id" + position, (questionList.get(position).getO_ID()));
                            question.put("subquestion_id" + position, 0);
                            question.put("sequence" + position, position + 1);
                            question.put("student_id" + position, str_serial_id);
                            question.put("correct_answer" + position, questionList.get(position).getANSWER());
                            if (questionList.get(position).getANSWER() == 1) {
                                question.put("correct" + position, 1);
                            } else {
                                question.put("correct" + position, 0);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    questions.put("question" + position, question);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        rd_truefalse.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                isChecked = questionList.get(position).getNAME();
                try {

                    question = new JSONObject();
                    optionobj = new JSONObject();

                    if (checkedId == R.id.rd_true) {
                        questionList.get(position).setOptiontype(11);
                        try {

                            question.put("type" + position, (questionList.get(position).getType()));
                            question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                            question.put("attempted" + position, 1);
                            question.put("question_id" + position, (questionList.get(position).getO_ID()));
                            question.put("subquestion_id" + position, 0);
                            question.put("sequence" + position, position + 1);
                            question.put("student_id" + position, str_serial_id);
                            question.put("correct_answer" + position, questionList.get(position).getANSWER());


                            if (questionList.get(position).getANSWER() == 1) {
                                question.put("correct" + position, 1);
                            } else {
                                question.put("correct" + position, 0);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    } else if (checkedId == R.id.rd_false) {
                        questionList.get(position).setOptiontype(22);
                        try {

                            question.put("type" + position, (questionList.get(position).getType()));
                            question.put("section_question_id" + position, (questionList.get(position).getSECTION_QUESTION_ID()));
                            question.put("attempted" + position, 1);
                            question.put("question_id" + position, (questionList.get(position).getO_ID()));
                            question.put("subquestion_id" + position, 0);
                            question.put("sequence" + position, position + 1);
                            question.put("student_id" + position, str_serial_id);
                            question.put("correct_answer" + position, questionList.get(position).getANSWER());
                            if (questionList.get(position).getANSWER() == 1) {
                                question.put("correct" + position, 1);
                            } else {
                                question.put("correct" + position, 0);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                    questions.put("question" + position, question);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;


        if ((questionList.get(position).getQuestionlist() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = (questionList.get(position).getQuestionlist()).split("#IM@Ge#B!n@RyD@T@#");

            for (int i = 0; i < dataParts.length; i++) {

                int newLineStart = 0;
                if (dataParts[i].indexOf("\n") == 0) {
                    newLineStart = 1;
                }
                String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";

                if (dataParts[i].toString().matches(regex) || dataParts[i].toString().startsWith("/")) {
                    Bitmap bitmap;
                    try {
                        byte[] data1 = android.util.Base64.decode(dataParts[i], android.util.Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(data1, 0, data1.length);
                    } catch (Exception e) {
                        bitmap = null;
                    }

                    if (bitmap == null) {
                        if (linearCountQues == 0 || newLineEnd == 1 || newLineStart == 1) {
                            layoutsQues.add(new FlowLayout(context));
                            linearCountQues++;

                            layoutsQues.get(linearCountQues - 1).setOrientation(FlowLayout.HORIZONTAL);
                            layoutsQues.get(linearCountQues - 1).setGravity(Gravity.NO_GRAVITY);

                            layoutsQues.get(linearCountQues - 1).setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                            // layouts.get(linearCount-1).setId(linearCount);
                            TextView tv = addTextView();
                            tv.setText(dataParts[i]);
                            layoutsQues.get(linearCountQues - 1).addView(tv);
                            layoutsQues.get(linearCountQues - 1).setPadding(10, 0, 10, 0);
                            LinearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {
                            TextView tv = addTextView();
                            tv.setText(dataParts[i]);
                            layoutsQues.get(linearCountQues - 1).addView(tv);
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {

                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        }

                    } else {
                        if (linearCountQues == 0 || newLineEnd == 1 || newLineStart == 1) {

                            layoutsQues.add(new FlowLayout(context));
                            linearCountQues++;

                            layoutsQues.get(linearCountQues - 1).setOrientation(FlowLayout.HORIZONTAL);
                            layoutsQues.get(linearCountQues - 1).setGravity(Gravity.NO_GRAVITY);

                            layoutsQues.get(linearCountQues - 1).setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                            layoutsQues.get(linearCountQues - 1).setPadding(10, 0, 10, 0);
                            //  layouts.get(linearCount - 1).setId(linearCount);
                            ImageView iv = addImageView();
                            iv.setImageBitmap(bitmap);
                            layoutsQues.get(linearCountQues - 1).addView(iv);
                            LinearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {
                            ImageView iv = addImageView();
                            iv.setImageBitmap(bitmap);
                            layoutsQues.get(linearCountQues - 1).addView(iv);
//                            layout.addView(layouts.get(linearCount - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        }
                        //layout.addView(iv);


                    }
                } else {
                    if (linearCountQues == 0 || newLineEnd == 1 || newLineStart == 1) {
                        layoutsQues.add(new FlowLayout(context));
                        linearCountQues++;

                        layoutsQues.get(linearCountQues - 1).setOrientation(FlowLayout.HORIZONTAL);
                        layoutsQues.get(linearCountQues - 1).setGravity(Gravity.NO_GRAVITY);

                        layoutsQues.get(linearCountQues - 1).setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                        // layouts.get(linearCount-1).setId(linearCount);
                        TextView tv = addTextView();
                        tv.setText(dataParts[i]);
                        layoutsQues.get(linearCountQues - 1).addView(tv);
                        layoutsQues.get(linearCountQues - 1).setPadding(10, 0, 10, 0);
                        LinearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {
                        TextView tv = addTextView();
                        tv.setText(dataParts[i]);

                        //Log.d("Debug", dataParts[i].toString());
                        //Log.d("DebugLength",""+dataParts[i].toString().length()+" of "+dataParts[i]);
                        layoutsQues.get(linearCountQues - 1).addView(tv);
                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    }
                }
            }
        } else {
            TextView tv = new TextView(context);

            tv.setText((questionList.get(position).getQuestionlist()));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            LinearCompQues.addView(tv);
            linearCountQues++;

        }


        if (questionList.get(position).getType() == 2) {


            if (questionList.get(position).getOPTION_COUNT() == 2) {


                rd_o_3.setVisibility(View.GONE);
                rd_o_4.setVisibility(View.GONE);

                setOption1(position);
                setOption2(position);

            } else if (questionList.get(position).getOPTION_COUNT() == 3) {


                rd_o_4.setVisibility(View.GONE);


                setOption1(position);
                setOption2(position);
                setOption3(position);
            } else {


                setOption1(position);
                setOption2(position);
                setOption3(position);
                setOption4(position);

            }

        } else if (questionList.get(position).getType() == 3) {

            rd_options.setVisibility(View.GONE);
            rd_truefalse.setVisibility(View.VISIBLE);
        } else if (questionList.get(position).getType() == 9) {
            rd_options.setVisibility(View.GONE);
            rd_yesno.setVisibility(View.VISIBLE);
        }


        btn_ssubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(context);
                }
                builder.setTitle("Submit Test")
                        .setMessage("Are you sure you want to submit this test?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    questionmain.put("question", questions);
                                    test.put("Test", questionmain);
                                    Log.e("JSON_RESULT_VAL", test.toString());
                                    sendquestionlist();
                                    for (int counter = 0; counter < question_ansewer_list.size(); counter++) {

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


        container.removeView((LinearLayout) object);
    }


    public void sendquestionlist() {

        myDialog = DialogsUtils.showProgressDialog(context, "Submitting Please Wait...");
        RequestInterface apiService = ApiClient.getClient().create(RequestInterface.class);
        Call<Success> call = apiService.sendquestiondata(str_serial_id, test.toString());
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                myDialog.dismiss();
                Success successList = response.body();
                if (response.code() == 200) {

                    if (successList.getSuccess() == 1) {


                        Toast.makeText(context, "Test Submitted Successfully!!!", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(context, ReportMainActivity.class);
                        i.putExtra("questions_list", (ArrayList) questionList);
                        i.putExtra("str_test_id", str_test_id);
                        i.putExtra("str_serial_id", str_serial_id);

                        context.startActivity(i);
                        ((Activity) context).finish();
                    } else {
                        Toast.makeText(context, "Something went Wrong!!!", Toast.LENGTH_LONG).show();
                    }

                } else {
                    Toast.makeText(context, "Something went Wrong!!!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                myDialog.dismiss();


            }
        });


    }

    public TextView addTextView() {
        TextView tv = new TextView(context);

        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        //tv.setTypeface(null, Typeface.BOLD);
        tv.setTextColor(Color.parseColor("#000000"));
        return tv;
    }

    public ImageView addImageView() {
        ImageView iv = new ImageView(context);
        // iv.setPadding(10, 0, 10, 0);
        FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(lp);

        return iv;
    }

    public void setOption1(int position) {

        int linearCountQues_o1 = 0;

        if ((questionList.get(position).getOptions1().getOPTION() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = (questionList.get(position).getOptions1().getOPTION() + "").split("#IM@Ge#B!n@RyD@T@#");

            for (int i = 0; i < dataParts.length; i++) {

                int newLineStart = 0;
                if (dataParts[i].indexOf("\n") == 0) {
                    newLineStart = 1;
                }

                String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
                if (dataParts[i].toString().matches(regex) || dataParts[i].toString().startsWith("/")) {
                    Bitmap bitmap;
                    try {
                        byte[] data1 = android.util.Base64.decode(dataParts[i], android.util.Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(data1, 0, data1.length);
                    } catch (Exception e) {
                        bitmap = null;
                    }

                    if (bitmap == null) {
                        if (linearCountQues_o1 == 0 || newLineEnd == 1 || newLineStart == 1) {

                            rd_o_1.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {

                            rd_o_1.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {

                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        }

                    } else {
                        if (linearCountQues_o1 == 0 || newLineEnd == 1 || newLineStart == 1) {


                            img_opt1.getLayoutParams().height = 150;
                            img_opt1.getLayoutParams().width = 200;
                            img_opt1.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt1.setImageBitmap(bitmap);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {

                            img_opt1.getLayoutParams().height = 150;

                            img_opt1.getLayoutParams().width = 200;


                            img_opt1.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt1.setImageBitmap(bitmap);
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        }
                    }
                } else {
                    if (linearCountQues_o1 == 0 || newLineEnd == 1 || newLineStart == 1) {

                        rd_o_1.setText(dataParts[i]);

                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {

                        rd_o_1.setText(dataParts[i]);


                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    }
                }
            }
        } else {

            rd_o_1.setText(questionList.get(position).getOptions1().getOPTION() + "");


        }


    }

    public void setOption2(int position) {
        int linearCountQues_o2 = 0;

        if ((questionList.get(position).getOptions2().getOPTION() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = (questionList.get(position).getOptions2().getOPTION() + "").split("#IM@Ge#B!n@RyD@T@#");

            for (int i = 0; i < dataParts.length; i++) {

                int newLineStart = 0;
                if (dataParts[i].indexOf("\n") == 0) {
                    newLineStart = 1;
                }

                String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
                if (dataParts[i].toString().matches(regex) || dataParts[i].toString().startsWith("/")) {
                    Bitmap bitmap;
                    try {
                        byte[] data1 = android.util.Base64.decode(dataParts[i], android.util.Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(data1, 0, data1.length);
                    } catch (Exception e) {
                        bitmap = null;
                    }

                    if (bitmap == null) {
                        if (linearCountQues_o2 == 0 || newLineEnd == 1 || newLineStart == 1) {

                            rd_o_2.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {

                            rd_o_2.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {

                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        }

                    } else {
                        if (linearCountQues_o2 == 0 || newLineEnd == 1 || newLineStart == 1) {


                            img_opt2.getLayoutParams().height = 150;

                            img_opt2.getLayoutParams().width = 200;


                            img_opt2.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt2.setImageBitmap(bitmap);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {


                            img_opt2.getLayoutParams().height = 150;

                            img_opt2.getLayoutParams().width = 200;

                            img_opt2.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt2.setImageBitmap(bitmap);
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        }
                    }
                } else {
                    if (linearCountQues_o2 == 0 || newLineEnd == 1 || newLineStart == 1) {

                        rd_o_2.setText(dataParts[i]);

                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {

                        rd_o_2.setText(dataParts[i]);


                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    }
                }
            }
        } else {

            rd_o_2.setText(questionList.get(position).getOptions2().getOPTION() + "");


        }
    }

    public void setOption3(int position) {
        int linearCountQues_o3 = 0;

        if ((questionList.get(position).getOptions3().getOPTION() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = (questionList.get(position).getOptions3().getOPTION() + "").split("#IM@Ge#B!n@RyD@T@#");

            for (int i = 0; i < dataParts.length; i++) {

                int newLineStart = 0;
                if (dataParts[i].indexOf("\n") == 0) {
                    newLineStart = 1;
                }

                String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
                if (dataParts[i].toString().matches(regex) || dataParts[i].toString().startsWith("/")) {
                    Bitmap bitmap;
                    try {
                        byte[] data1 = android.util.Base64.decode(dataParts[i], android.util.Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(data1, 0, data1.length);
                    } catch (Exception e) {
                        bitmap = null;
                    }

                    if (bitmap == null) {
                        if (linearCountQues_o3 == 0 || newLineEnd == 1 || newLineStart == 1) {

                            rd_o_3.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {

                            rd_o_3.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {

                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        }

                    } else {
                        if (linearCountQues_o3 == 0 || newLineEnd == 1 || newLineStart == 1) {


                            img_opt3.getLayoutParams().height = 150;
                            img_opt3.getLayoutParams().width = 200;
                            img_opt3.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt3.setImageBitmap(bitmap);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {

                            img_opt3.getLayoutParams().height = 150;
                            img_opt3.getLayoutParams().width = 200;
                            img_opt3.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt3.setImageBitmap(bitmap);
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        }
                    }
                } else {
                    if (linearCountQues_o3 == 0 || newLineEnd == 1 || newLineStart == 1) {

                        rd_o_3.setText(dataParts[i]);

                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {

                        rd_o_3.setText(dataParts[i]);


                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    }
                }
            }
        } else {

            rd_o_3.setText(questionList.get(position).getOptions3().getOPTION() + "");


        }


    }

    public void setOption4(int position) {
        int linearCountQues_o4 = 0;

        if ((questionList.get(position).getOptions4().getOPTION() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = (questionList.get(position).getOptions4().getOPTION() + "").split("#IM@Ge#B!n@RyD@T@#");

            for (int i = 0; i < dataParts.length; i++) {

                int newLineStart = 0;
                if (dataParts[i].indexOf("\n") == 0) {
                    newLineStart = 1;
                }

                String regex = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
                if (dataParts[i].toString().matches(regex) || dataParts[i].toString().startsWith("/")) {
                    Bitmap bitmap;
                    try {
                        byte[] data1 = android.util.Base64.decode(dataParts[i], android.util.Base64.DEFAULT);
                        bitmap = BitmapFactory.decodeByteArray(data1, 0, data1.length);
                    } catch (Exception e) {
                        bitmap = null;
                    }

                    if (bitmap == null) {
                        if (linearCountQues_o4 == 0 || newLineEnd == 1 || newLineStart == 1) {

                            rd_o_4.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {

                            rd_o_4.setText(dataParts[i]);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {

                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        }

                    } else {
                        if (linearCountQues_o4 == 0 || newLineEnd == 1 || newLineStart == 1) {


                            img_opt4.getLayoutParams().height = 150;
                            img_opt4.getLayoutParams().width = 200;
                            img_opt4.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt4.setImageBitmap(bitmap);

                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {

                            img_opt4.getLayoutParams().height = 150;
                            img_opt4.getLayoutParams().width = 200;
                            img_opt4.setScaleType(ImageView.ScaleType.FIT_XY);
                            img_opt4.setImageBitmap(bitmap);
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        }
                    }
                } else {
                    if (linearCountQues_o4 == 0 || newLineEnd == 1 || newLineStart == 1) {

                        rd_o_4.setText(dataParts[i]);

                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {

                        rd_o_4.setText(dataParts[i]);


                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    }
                }
            }
        } else {

            rd_o_4.setText(questionList.get(position).getOptions4().getOPTION() + "");


        }

    }
}
