package testtaking.app.com.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import testtaking.app.com.myapplication.model.TestBean;
import testtaking.app.com.myapplication.R;


/**
 * Created by sourav on 7/7/2017.
 */

public class TestListAdapter extends RecyclerView.Adapter<TestListAdapter.ViewHolder> {
    private List<TestBean> demoTestBeen;
    View vieww;

    Context mContext;

    public TestListAdapter(List<TestBean> demoTestBeen, Context context) {
        this.demoTestBeen = demoTestBeen;
        this.mContext = context;
    }

    @Override
    public TestListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_demo_testlist, viewGroup, false);
        return new TestListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.tv_desc.setText(String.valueOf(demoTestBeen.get(i).getDescription()));
    }


    @Override
    public int getItemCount() {

        return (null != demoTestBeen ? demoTestBeen.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_desc;


        public ViewHolder(View view) {
            super(view);

            tv_desc = (TextView) view.findViewById(R.id.tv_test_d_description);
        }

    }

    public void schedule_test() {


    }
}