package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import testtaking.app.com.myapplication.model.Subject;
import testtaking.app.com.myapplication.R;


public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.MyViewHolder> {

    private Context context;
    private List<Subject> notification;
    Typeface tf;


    public SubjectsAdapter(Context context, List<Subject> mListNotification) {
        this.context = context;
        this.notification = mListNotification;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_subject_search, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {


        viewHolder.textViewSubjectName.setText(notification.get(position).getSubjectName());

        String subname = notification.get(position).getSubjectName();

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
        } else if (subname.equalsIgnoreCase("Hindi Grammar")||subname.equalsIgnoreCase("Hindi Grammer")) {
            Glide.with(context).load(R.drawable.subject_hindi_grammar).into(viewHolder.imageView4);
        }


    }

    @Override
    public int getItemCount() {
        return notification.size();
    }

    public void addData(List<Subject> notification) {
        this.notification.addAll(notification);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_subject_name)
        TextView textViewSubjectName;
        @BindView(R.id.ll_layout)
        LinearLayout llLayout;
        @BindView(R.id.card_view_class)
        CardView cardViewClass;
        @BindView(R.id.imageView6)
        ImageView imageView4;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


          /*  itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,ChapterActivity.class);
                    context.startActivity(intent);
                }
            });*/
        }
    }

}