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
import testtaking.app.com.myapplication.model.BeanTopic;


public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> {

    private Context context;
    private List<BeanTopic> topics;

    public TopicAdapter(Context context, List<BeanTopic> topics) {
        this.context = context;
        this.topics = topics;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_topic_search, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textViewTopicName.setText(topics.get(position).getTopicName());

    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    public void addData(List<BeanTopic> topics) {
        this.topics.addAll(topics);
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
