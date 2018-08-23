package testtaking.app.com.myapplication.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.activity.UserCommentsList;
import testtaking.app.com.myapplication.model.HomeQuestionList;
import testtaking.app.com.myapplication.model.HomeQuestionOptions;


public class HomeAddQuesAdapter extends RecyclerView.Adapter<HomeAddQuesAdapter.HomeSubAddQuestion> {
    List<HomeQuestionOptions> homeQuestionListOptions;
    List<HomeQuestionList> homeQuestionLists;
    Context context;
    LinearLayout query_layout, info_layout, mcq_layout;


    boolean isMcqImage = false;
    boolean isQueryImage = false;
    boolean isInfoImage = false;


    public HomeAddQuesAdapter(List<HomeQuestionList> homeQuestionLists, Context context) {
        this.homeQuestionLists = homeQuestionLists;
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public HomeSubAddQuestion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_add_question, parent, false);
        return new HomeSubAddQuestion(view);

    }

    @Override
    public void onBindViewHolder(final HomeSubAddQuestion holder, final int position) {
        holder.txt_add_ques_mcq.setText(homeQuestionLists.get(position).getQuestion_text());
        holder.txt_add_ques_info.setText(homeQuestionLists.get(position).getQuestion_text());
        holder.txt_add_ques_queary.setText(homeQuestionLists.get(position).getQuestion_text());

        holder.txt_user_name_queary.setText(homeQuestionLists.get(position).getPost_created_username());
        holder.txt_user_name_info.setText(homeQuestionLists.get(position).getPost_created_username());
        holder.txt_user_name_queary.setText(homeQuestionLists.get(position).getPost_created_username());

        holder.post_type_mcq.setText(homeQuestionLists.get(position).getPost_type());
        holder.post_type_info.setText(homeQuestionLists.get(position).getPost_type());
        holder.post_type_queary.setText(homeQuestionLists.get(position).getPost_type());



        holder.ll_mcq_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, UserCommentsList.class);
                i.putExtra("post_id",homeQuestionLists.get(position).getPost_id()+"");
                context.startActivity(i);





            }
        });



        holder.ll_query_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(context, UserCommentsList.class);
                i.putExtra("post_id",homeQuestionLists.get(position).getPost_id()+"");
                context.startActivity(i);



            }
        });


        holder.ll_info_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, UserCommentsList.class);
                i.putExtra("post_id",homeQuestionLists.get(position).getPost_id()+"");
                context.startActivity(i);


            }
        });






        holder.img_info_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isInfoImage) {
                    //v.setBackgroundResource(R.drawable.like);


                    holder.img_info_like.setImageResource(R.drawable.like);

                } else {
                    // v.setBackgroundResource(R.drawable.ic_address);
                    holder.img_info_like.setImageResource(R.drawable.press_like);
                }

                isInfoImage = !isInfoImage; // reverse


            }
        });

        holder.img_query_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isQueryImage) {
                    //v.setBackgroundResource(R.drawable.like);


                    holder.img_query_like.setImageResource(R.drawable.like);

                } else {
                    // v.setBackgroundResource(R.drawable.ic_address);


                    holder.img_query_like.setImageResource(R.drawable.press_like);
                }

                isQueryImage = !isQueryImage; // reverse


            }
        });
        holder.img_mcq_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isMcqImage) {
                    //v.setBackgroundResource(R.drawable.like);

                    holder.img_mcq_like.setImageResource(R.drawable.like);

                } else {
                    // v.setBackgroundResource(R.drawable.ic_address);
                    holder.img_mcq_like.setImageResource(R.drawable.press_like);
                }

                isMcqImage = !isMcqImage; // reverse

            }
        });


        String post_type = homeQuestionLists.get(position).getPost_type();
        if (post_type.equals("Query")) {


            query_layout.setVisibility(View.VISIBLE);
            info_layout.setVisibility(View.GONE);
            mcq_layout.setVisibility(View.GONE);

            holder.tv_query_comment.setText(homeQuestionLists.get(position).getCommentSize() + " comment");
            holder.tv_query_upvote.setText(homeQuestionLists.get(position).getUpvoteSize() + " upvote");
            holder.tv_query_attempt.setText(homeQuestionLists.get(position).getAttemptSize() + " attempt");
        }
        if (post_type.equals("MCQ")) {

            holder.tv_mcq_comment.setText(homeQuestionLists.get(position).getCommentSize() + " comment");
            holder.tv_mcq_upvote.setText(homeQuestionLists.get(position).getUpvoteSize() + " upvote");
            holder.tv_mcq_attempt.setText(homeQuestionLists.get(position).getAttemptSize() + " attempt");
            query_layout.setVisibility(View.GONE);
            info_layout.setVisibility(View.GONE);
            mcq_layout.setVisibility(View.VISIBLE);

            holder.option1.setText(homeQuestionLists.get(position).getHomeQuestionOptions().getOption1());
            holder.option2.setText(homeQuestionLists.get(position).getHomeQuestionOptions().getOption2());
            holder.option3.setText(homeQuestionLists.get(position).getHomeQuestionOptions().getOption3());
            holder.option4.setText(homeQuestionLists.get(position).getHomeQuestionOptions().getOption4());
        }
        if (post_type.equals("Info")) {
            query_layout.setVisibility(View.GONE);
            info_layout.setVisibility(View.VISIBLE);
            mcq_layout.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return homeQuestionLists.size();
    }

    public class HomeSubAddQuestion extends RecyclerView.ViewHolder {
        TextView txt_add_ques_mcq, txt_add_ques_info, txt_add_ques_queary, post_type_mcq, post_type_queary,
                post_type_info, txt_user_name_mcq, txt_user_name_queary, txt_user_name_info;
        TextView option1, option2, option3, option4, option5;
        ImageView img_mcq, img_info, img_query;
        TextView tv_mcq_attempt, tv_mcq_comment, tv_mcq_upvote, tv_query_attempt, tv_query_comment, tv_query_upvote;
        LinearLayout lllike;
        LinearLayout ll_mcq_like, ll_info_like, ll_query_like;
        ImageView img_mcq_like, img_query_like, img_info_like;
        LinearLayout ll_mcq_comment,ll_query_comment,ll_info_comment;

        public HomeSubAddQuestion(final View itemView) {
            super(itemView);

            txt_add_ques_mcq = itemView.findViewById(R.id.txt_enter_question_home);
            txt_add_ques_info = itemView.findViewById(R.id.txt_info_home);
            txt_add_ques_queary = itemView.findViewById(R.id.txt_queary_home);

            post_type_mcq = itemView.findViewById(R.id.txt_post_type_mcq);
            post_type_info = itemView.findViewById(R.id.txt_post_type_info);
            post_type_queary = itemView.findViewById(R.id.txt_post_type_queary);

            query_layout = itemView.findViewById(R.id.layout_home_queary);
            info_layout = itemView.findViewById(R.id.layout_home_info);
            mcq_layout = itemView.findViewById(R.id.home_mcq_layout);

            txt_user_name_mcq = itemView.findViewById(R.id.student_name_home_mcq);
            txt_user_name_queary = itemView.findViewById(R.id.student_name_home_queary);
            txt_user_name_info = itemView.findViewById(R.id.student_name_home_info);


            option1 = itemView.findViewById(R.id.txt_option1_mcq);
            option2 = itemView.findViewById(R.id.txt_option2_mcq);
            option3 = itemView.findViewById(R.id.txt_option3_mcq);
            option4 = itemView.findViewById(R.id.txt_option4_mcq);
            img_info = itemView.findViewById(R.id.image_student_info_home);
            img_mcq = itemView.findViewById(R.id.image_home_mcq_student);
            img_query = itemView.findViewById(R.id.image_student_queary_home);
            tv_mcq_attempt = itemView.findViewById(R.id.mcq_attempts);
            tv_mcq_comment = itemView.findViewById(R.id.mcq_comments);
            tv_mcq_upvote = itemView.findViewById(R.id.mcq_upvote);
            tv_query_attempt = itemView.findViewById(R.id.query_attempts);
            tv_query_comment = itemView.findViewById(R.id.query_comments);
            tv_query_upvote = itemView.findViewById(R.id.query_upvote);
            ll_mcq_like = itemView.findViewById(R.id.ll_mcq_like);
            ll_info_like = itemView.findViewById(R.id.ll_info_like);
            ll_query_like = itemView.findViewById(R.id.ll_query_like);
            img_mcq_like = itemView.findViewById(R.id.mcq_like_image);
            img_query_like = itemView.findViewById(R.id.query_like_image);
            img_info_like = itemView.findViewById(R.id.info_like_image);
            ll_mcq_comment= itemView.findViewById(R.id.ll_mcq_comment);
            ll_query_comment= itemView.findViewById(R.id.ll_query_comment);
            ll_info_comment= itemView.findViewById(R.id.ll_info_comment);

        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }




}
