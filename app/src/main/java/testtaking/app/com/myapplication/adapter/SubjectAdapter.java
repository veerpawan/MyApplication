package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.StudentSubject;


/**
 * Created by pawan on 10/25/2016.
 */
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    Context context;
    private List<StudentSubject> subjectname;

    public SubjectAdapter(List<StudentSubject> subjectname, Context context) {
        this.subjectname = subjectname;
        this.context = context;
    }

    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subject_list_row_n, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SubjectAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv_text.setText(subjectname.get(i).getSubjectName());
        String subname = subjectname.get(i).getSubjectName();
        //Picasso.with(context).load(R.drawable.subject).into(viewHolder.imageView);
        //Picasso.with(this.context).load(R.drawable.subject).resize(100, 100).into(viewHolder.imageView4);
        //Glide.with(context).load(R.drawable.subname).into(viewHolder.imageView4);
        if (subname.equalsIgnoreCase("Accountancy")) {
            Glide.with(context).load(R.drawable.subject_accountancy).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Business Studies")) {
            Glide.with(context).load(R.drawable.subject_business_studies).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Economics")) {
            Glide.with(context).load(R.drawable.subject_economics).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Biology")) {
            Glide.with(context).load(R.drawable.subject_biology).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Chemistry")) {
            Glide.with(context).load(R.drawable.subject_chemistry).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Physics")) {
            Glide.with(context).load(R.drawable.subject_physics).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Mathematics")) {
            Glide.with(context).load(R.drawable.subject_mathematics).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Geography")) {
            Glide.with(context).load(R.drawable.subject_geography).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("History")) {
            Glide.with(context).load(R.drawable.subject_history).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Political Science")) {
            Glide.with(context).load(R.drawable.subject_political_science).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Science")) {
            Glide.with(context).load(R.drawable.subject_physics).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Social Science")) {
            Glide.with(context).load(R.drawable.subject_social_science).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("English Grammar")) {
            Glide.with(context).load(R.drawable.subject_english_grammar).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("C++")) {
            Glide.with(context).load(R.drawable.subject_cplusplus).into(viewHolder.imageView4);
        } else if (subname.equalsIgnoreCase("Hindi Grammer")||subname.equalsIgnoreCase("Hindi Grammar")) {
            Glide.with(context).load(R.drawable.subject_hindi_grammar).into(viewHolder.imageView4);
        }
        else  {
            Glide.with(context).load(R.drawable.common_subject).into(viewHolder.imageView4);
        }
    }

    @Override
    public int getItemCount()
    {
        return (null != subjectname ? subjectname.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_text;
        ImageView imageView4;

        public ViewHolder(View view) {
            super(view);
            tv_text = (TextView) view.findViewById(R.id.year);
            imageView4 = (ImageView) itemView.findViewById(R.id.imageView6);
        }

    }
}
////////////////////////////////////////////////////////////////////////////