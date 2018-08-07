package testtaking.app.com.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testtaking.app.com.myapplication.activity.QuizMainActivity;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.utils.Constant;
import testtaking.app.com.myapplication.R;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.MyViewHolder> {

    int pos = 0;
    ArrayList<Integer> color_list = new ArrayList<>();
    Context context;
    List<Question> questionList;


    public QuestionListAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_list, parent, false));
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        pos = QuizMainActivity.globlepos;
        holder.setIsRecyclable(false);

        holder.textViewQuestionNumber.setText(String.valueOf(position + 1));

        if ((questionList.get(position).getStatus()) == (Constant.VIEWD)) {

            holder.textViewQuestionNumber.setBackgroundResource(R.drawable.half_rounded_circle_attempted);

        } else {
            if ((questionList.get(position).getStatus()) == (Constant.UNANSWERD)) {

                holder.textViewQuestionNumber.setBackgroundResource(R.drawable.half_rounded_circle);

            }
        }
        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;

        if ((questionList.get(position).getQuestionlist() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

            int newLineEnd = 0;

            String[] dataParts = questionList.get(position).getQuestionlist().split("#IM@Ge#B!n@RyD@T@#");

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
                            holder.linearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }

                        } else {
                            final TextView tv = addTextView();
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
                            holder.linearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {
                            final ImageView iv = addImageView();
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
                        holder.linearCompQues.addView(layoutsQues.get(linearCountQues - 1));
                        if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                            newLineEnd = 1;
                        } else {
                            newLineEnd = 0;
                        }
                    } else {
                        final TextView tv = addTextView();
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
            final TextView tv = new TextView(context);

            tv.setText((questionList.get(position).getQuestionlist()));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            holder.linearCompQues.addView(tv);
            linearCountQues++;

        }



    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_question_number)
        TextView textViewQuestionNumber;
        @BindView(R.id.ll_question_number)
        LinearLayout llQuestionNumber;
       /* @BindView(R.id.text_view_question)
        TextView textViewQuestion;*/

        @BindView(R.id.linearCompQues)
        LinearLayout linearCompQues;


        @BindView(R.id.img_question_list)
        ImageView img_quest_list;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
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
}