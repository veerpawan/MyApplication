package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testtaking.app.com.myapplication.model.Question;
import testtaking.app.com.myapplication.utils.Constant;
import testtaking.app.com.myapplication.R;


public class QuestionNumberAdapter extends RecyclerView.Adapter<QuestionNumberAdapter.MyViewHolder> {

    private Context context;
    private List<Question> notification = new ArrayList<>();

    public QuestionNumberAdapter(Context context, List<Question> mListNotification) {
        this.context = context;
        this.notification = mListNotification;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_question_number, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textQuestionNumber.setText(String.valueOf(position + 1));

        if ((notification.get(position).getStatus()) == (Constant.VIEWD)) {

            // holder.textQuestionNumber.

            // Toast.makeText(context,position+"",Toast.LENGTH_LONG).show();

            //holder.textQuestionNumber.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            holder.textQuestionNumber.setBackgroundResource(R.drawable.rounded_button_answered);

        } else {
            if ((notification.get(position).getStatus()) == (Constant.UNANSWERD)) {

                // holder.textQuestionNumber.

                // Toast.makeText(context,position+"",Toast.LENGTH_LONG).show();

                //holder.textQuestionNumber.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
                holder.textQuestionNumber.setBackgroundResource(R.drawable.rounded_button_unanswered);

            }
        }


        //
    }

    @Override
    public int getItemCount() {
        return notification.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void addData(List<Question> notification) {
        this.notification.addAll(notification);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_question_number)
        TextView textQuestionNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


        }
    }

}