package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



import java.util.List;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.HomeQuestionList;
import testtaking.app.com.myapplication.model.HomeQuestionOptions;


public class HomeAddQuesAdapter extends RecyclerView.Adapter<HomeAddQuesAdapter.HomeSubAddQuestion> {
    List<HomeQuestionOptions> homeQuestionListOptions;
    List<HomeQuestionList> homeQuestionLists;
    Context context;
    LinearLayout query_layout, info_layout, mcq_layout;

    public HomeAddQuesAdapter(List<HomeQuestionList> homeQuestionLists, Context context) {
        this.homeQuestionLists = homeQuestionLists;
        this.context = context;
    }

    @Override
    public HomeSubAddQuestion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.home_list_add_question, parent, false );
        return new HomeSubAddQuestion( view );

    }

    @Override
    public void onBindViewHolder(HomeSubAddQuestion holder, final int position) {
        holder.txt_add_ques_mcq.setText( homeQuestionLists.get( position ).getQuestion_text() );
        holder.txt_add_ques_info.setText( homeQuestionLists.get( position ).getQuestion_text() );
        holder.txt_add_ques_queary.setText( homeQuestionLists.get( position ).getQuestion_text() );

        holder.txt_user_name_queary.setText( homeQuestionLists.get( position ).getPost_created_username() );
        holder.txt_user_name_info.setText( homeQuestionLists.get( position ).getPost_created_username() );
        holder.txt_user_name_queary.setText( homeQuestionLists.get( position ).getPost_created_username() );

        holder.post_type_mcq.setText( homeQuestionLists.get( position ).getPost_type() );
        holder.post_type_info.setText( homeQuestionLists.get( position ).getPost_type() );
        holder.post_type_queary.setText( homeQuestionLists.get( position ).getPost_type() );


        String post_type = homeQuestionLists.get( position ).getPost_type();
        if (post_type.equals( "Query" )) {
            query_layout.setVisibility( View.VISIBLE );
            info_layout.setVisibility( View.GONE );
            mcq_layout.setVisibility( View.GONE );
        }
        if (post_type.equals( "MCQ" )) {
            query_layout.setVisibility( View.GONE );
            info_layout.setVisibility( View.GONE );
            mcq_layout.setVisibility( View.VISIBLE );

            holder.option1.setText( homeQuestionLists.get( position ).getHomeQuestionOptions().getOption1());
            holder.option2.setText( homeQuestionLists.get( position ).getHomeQuestionOptions().getOption2() );
            holder.option3.setText( homeQuestionLists.get( position ).getHomeQuestionOptions().getOption3() );
            holder.option4.setText( homeQuestionLists.get( position ).getHomeQuestionOptions().getOption4() );
        }
        if (post_type.equals( "Info" )) {
            query_layout.setVisibility( View.GONE );
            info_layout.setVisibility( View.VISIBLE );
            mcq_layout.setVisibility( View.GONE );
        }


    }

    @Override
    public int getItemCount() {
        return homeQuestionLists.size();
    }

    public class HomeSubAddQuestion extends RecyclerView.ViewHolder {
        TextView txt_add_ques_mcq, txt_add_ques_info, txt_add_ques_queary, post_type_mcq, post_type_queary,
                post_type_info,txt_user_name_mcq,txt_user_name_queary,txt_user_name_info;
        TextView option1, option2, option3, option4, option5;


        public HomeSubAddQuestion(final View itemView) {
            super( itemView );

            txt_add_ques_mcq = (TextView) itemView.findViewById( R.id.txt_enter_question_home );
            txt_add_ques_info = (TextView) itemView.findViewById( R.id.txt_info_home );
            txt_add_ques_queary = (TextView) itemView.findViewById( R.id.txt_queary_home );

            post_type_mcq = (TextView) itemView.findViewById( R.id.txt_post_type_mcq );
            post_type_info = (TextView) itemView.findViewById( R.id.txt_post_type_info );
            post_type_queary = (TextView) itemView.findViewById( R.id.txt_post_type_queary );

            query_layout = itemView.findViewById( R.id.layout_home_queary );
            info_layout = itemView.findViewById( R.id.layout_home_info );
            mcq_layout = itemView.findViewById( R.id.home_mcq_layout );

            txt_user_name_mcq=itemView.findViewById( R.id.student_name_home_mcq );
            txt_user_name_queary=itemView.findViewById( R.id.student_name_home_queary);
            txt_user_name_info=itemView.findViewById( R.id.student_name_home_info);


            option1 = itemView.findViewById( R.id.txt_option1_mcq );
            option2 = itemView.findViewById( R.id.txt_option2_mcq );
            option3 = itemView.findViewById( R.id.txt_option3_mcq );
            option4 = itemView.findViewById( R.id.txt_option4_mcq );
        }
    }
}
