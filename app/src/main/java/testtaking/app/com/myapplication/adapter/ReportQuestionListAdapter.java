package testtaking.app.com.myapplication.adapter;

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
import testtaking.app.com.myapplication.R;

public class ReportQuestionListAdapter extends RecyclerView.Adapter<ReportQuestionListAdapter.MyViewHolder> {

    int pos = 0;
    ArrayList<Integer> color_list = new ArrayList<>();
    Context context;
    List<Question> questionList;


    public ReportQuestionListAdapter(Context context, List<Question> questionList) {
        this.context = context;
        this.questionList = questionList;
        setHasStableIds(true);


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_report_question_list, parent, false));
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        pos = QuizMainActivity.globlepos;


        holder.textViewQuestionNumber.setText(String.valueOf(position + 1));
        // holder.textViewQuestion.setText(questionList.get(position).getQuestionlist());


        holder.setIsRecyclable(false);


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
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
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
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
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


        if (questionList.get(position).getType() == 2) {


            if (questionList.get(position).getOPTION_COUNT() == 4) {


                if (questionList.get(position).getOptiontype() == 1) {
                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 2) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 3) {
                    holder.opt_3.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 4) {
                    holder.opt_4.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                }


            } else if (questionList.get(position).getOPTION_COUNT() == 3) {


                if (questionList.get(position).getOptiontype() == 1) {
                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 2) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 3) {
                    holder.opt_3.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                }


            } else if (questionList.get(position).getOPTION_COUNT() == 2) {


                if (questionList.get(position).getOptiontype() == 1) {
                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                } else if (questionList.get(position).getOptiontype() == 2) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
                }


            }
        } else if (questionList.get(position).getType() == 3) {


            if (questionList.get(position).getOptiontype() == 11) {
                holder.tv_true.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            } else if (questionList.get(position).getOptiontype() == 22) {
                holder.tv_false.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            }


        } else if (questionList.get(position).getType() == 9) {


            if (questionList.get(position).getOptiontype() == 111) {
                holder.tv_yes.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            } else if (questionList.get(position).getOptiontype() == 222) {
                holder.tv_no.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
            }


        }


        if (questionList.get(position).getType() == 2) {


            if (questionList.get(position).getOPTION_COUNT() == 4) {


                holder.opt_1.setVisibility(View.VISIBLE);
                holder.opt_2.setVisibility(View.VISIBLE);
                holder.opt_3.setVisibility(View.VISIBLE);
                holder.opt_4.setVisibility(View.VISIBLE);


                option1(holder, position);
                option2(holder, position);
                option3(holder, position);
                option4(holder, position);


                if (questionList.get(position).getOptions1().getOPTION_ANSWER() == 1) {


                    //holder.opt_1.setTextColor(context.getResources().getColor(R.color.new_green_color_no_alpha));
                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));


                } else if (questionList.get(position).getOptions2().getOPTION_ANSWER() == 1) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                } else if (questionList.get(position).getOptions3().getOPTION_ANSWER() == 1) {
                    holder.opt_3.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                } else if (questionList.get(position).getOptions4().getOPTION_ANSWER() == 1) {
                    holder.opt_4.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                }


            } else if (questionList.get(position).getOPTION_COUNT() == 3) {
                holder.opt_1.setVisibility(View.VISIBLE);
                holder.opt_2.setVisibility(View.VISIBLE);
                holder.opt_3.setVisibility(View.VISIBLE);

                option1(holder, position);
                option2(holder, position);
                option3(holder, position);

                if (questionList.get(position).getOptions1().getOPTION_ANSWER() == 1) {


                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));


                } else if (questionList.get(position).getOptions2().getOPTION_ANSWER() == 1) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                } else if (questionList.get(position).getOptions3().getOPTION_ANSWER() == 1) {
                    holder.opt_3.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                }


            } else if (questionList.get(position).getOPTION_COUNT() == 2) {

                holder.opt_1.setVisibility(View.VISIBLE);
                holder.opt_2.setVisibility(View.VISIBLE);


                option1(holder, position);
                option2(holder, position);

                if (questionList.get(position).getOptions1().getOPTION_ANSWER() == 1) {


                    holder.opt_1.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));


                } else if (questionList.get(position).getOptions2().getOPTION_ANSWER() == 1) {
                    holder.opt_2.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
                }


            }
        } else if (questionList.get(position).getType() == 3) {
            holder.tv_true.setVisibility(View.VISIBLE);
            holder.tv_false.setVisibility(View.VISIBLE);

            holder.tv_true.setText("True");
            holder.tv_false.setText("False");


            if (questionList.get(position).getANSWER() == 1) {
                holder.tv_true.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
            } else {
                holder.tv_false.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
            }

        } else if (questionList.get(position).getType() == 9) {

            holder.tv_yes.setVisibility(View.VISIBLE);
            holder.tv_no.setVisibility(View.VISIBLE);
            holder.tv_yes.setText("Yes");
            holder.tv_no.setText("No");


            if (questionList.get(position).getANSWER() == 1) {
                holder.tv_yes.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
            } else {
                holder.tv_no.setBackgroundColor(context.getResources().getColor(R.color.dot_dark_screen5));
            }

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
        @BindView(R.id.llopt1)
        LinearLayout opt_1;
        @BindView(R.id.llopt2)
        LinearLayout opt_2;
        @BindView(R.id.llopt3)
        LinearLayout opt_3;
        @BindView(R.id.llopt4)
        LinearLayout opt_4;
        @BindView(R.id.tv_yes)
        TextView tv_yes;
        @BindView(R.id.tv_no)
        TextView tv_no;
        @BindView(R.id.tv_true)
        TextView tv_true;
        @BindView(R.id.tv_false)
        TextView tv_false;
        @BindView(R.id.img_quest_report)
        ImageView img_quest_report;

        @BindView(R.id.linearCompQues)
        LinearLayout linearCompQues;


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

    public void option1(final MyViewHolder holder, final int position) {
        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;


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
                            holder.opt_1.addView(layoutsQues.get(linearCountQues - 1));
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
                            holder.opt_1.addView(layoutsQues.get(linearCountQues - 1));
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
                        holder.opt_1.addView(layoutsQues.get(linearCountQues - 1));
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

            tv.setText((questionList.get(position).getOptions1().getOPTION() + ""));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            holder.opt_1.addView(tv);
            linearCountQues++;

        }
    }

    public void option2(final MyViewHolder holder, final int position) {

        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;


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
                            holder.opt_2.addView(layoutsQues.get(linearCountQues - 1));
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
                            holder.opt_2.addView(layoutsQues.get(linearCountQues - 1));
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
                        holder.opt_2.addView(layoutsQues.get(linearCountQues - 1));
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

            tv.setText((questionList.get(position).getOptions2().getOPTION() + ""));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            holder.opt_2.addView(tv);
            linearCountQues++;

        }


    }

    public void option3(final MyViewHolder holder, final int position) {

        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;


        if ((questionList.get(position).getOptions3().getOPTION() + "").contains("#IM@Ge#B!n@RyD@T@#")) {

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
                            holder.opt_3.addView(layoutsQues.get(linearCountQues - 1));
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
                            holder.opt_3.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {
                            final ImageView iv = addImageView();
                            iv.setImageBitmap(bitmap);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
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
                        holder.opt_3.addView(layoutsQues.get(linearCountQues - 1));
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

            tv.setText((questionList.get(position).getOptions3().getOPTION() + ""));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            holder.opt_3.addView(tv);
            linearCountQues++;

        }
    }

    public void option4(final MyViewHolder holder, final int position) {
        ArrayList<FlowLayout> layoutsQues = new ArrayList<FlowLayout>();
        int linearCountQues = 0;


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
                            holder.opt_4.addView(layoutsQues.get(linearCountQues - 1));
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
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
                            layoutsQues.get(linearCountQues - 1).addView(iv);
                            holder.opt_4.addView(layoutsQues.get(linearCountQues - 1));
                            if (dataParts[i].replace(" ", "").lastIndexOf("\n") == dataParts[i].replace(" ", "").length() - 1) {
                                newLineEnd = 1;
                            } else {
                                newLineEnd = 0;
                            }
                        } else {
                            final ImageView iv = addImageView();
                            iv.setImageBitmap(bitmap);
                            iv.setScaleType(ImageView.ScaleType.FIT_XY);
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
                        holder.opt_4.addView(layoutsQues.get(linearCountQues - 1));
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

            tv.setText((questionList.get(position).getOptions4().getOPTION() + ""));
            tv.setTextColor(Color.BLACK);
            tv.setPadding(10, 0, 10, 0);
            holder.opt_4.addView(tv);
            linearCountQues++;

        }

    }
}