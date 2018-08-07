package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.BeanChapter;


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.MyViewHolder> {

    private Context context;
    private List<BeanChapter> notification;

    public ChapterAdapter(Context context, List<BeanChapter> mListNotification) {
        this.context = context;
        this.notification = mListNotification;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chapter_search, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewTopicName.setText(notification.get(position).getChapterName());

    }

    @Override
    public int getItemCount() {
        return notification.size();
    }

    public void addData(List<BeanChapter> notification) {
        this.notification.addAll(notification);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_topic_name)
        TextView textViewTopicName;
        @BindView(R.id.ll_layout)
        LinearLayout llLayout;
        @BindView(R.id.card_view_class)
        CardView cardViewClass;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}