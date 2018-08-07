package testtaking.app.com.myapplication.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.StudentSubject;


public class ChooseSubjectsAdapter extends RecyclerView.Adapter<ChooseSubjectsAdapter.MyViewHolder> {

    Context context;
    List<StudentSubject> subjectList;
    public static ArrayList<Integer> subject_ids = new ArrayList<>();

    public ChooseSubjectsAdapter(Context context, List<StudentSubject> mListNotification) {
        this.context = context;
        this.subjectList = mListNotification;
        setHasStableIds(true);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_subject_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(subjectList.get(position).getSubjectName());
        holder.chk_sub.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                subject_ids.add(subjectList.get(position).getId());

                //Toast.makeText(context, subjectList.get(position).getId() + "", Toast.LENGTH_LONG).show();
                }
        }
        );

    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public void addData(List<StudentSubject> subjectList) {
        this.subjectList.addAll(subjectList);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        CheckBox chk_sub;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.subject_list_id);
            chk_sub = (CheckBox) itemView.findViewById(R.id.checkbox_subjects);
            this.setIsRecyclable(false);

        }
    }


}

