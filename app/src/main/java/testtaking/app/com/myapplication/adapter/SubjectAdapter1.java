package testtaking.app.com.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.model.StudySubjectModel;


public class SubjectAdapter1 extends RecyclerView.Adapter<SubjectAdapter1.SubStudyChapter> {

    private ArrayList<StudySubjectModel> studySubjectModels;

    @NonNull
    @Override
    public SubStudyChapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate( R.layout.subject_list, parent, false);
        return new SubjectAdapter1.SubStudyChapter(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SubStudyChapter holder, int position) {
        StudySubjectModel studyChapterModel = studySubjectModels.get(position);
        holder.subjects.setText(studyChapterModel.getTitle());
        holder.imageView.setImageResource(studyChapterModel.getImage());
    }

    @Override
    public int getItemCount() {
        return studySubjectModels.size();
    }

    public SubjectAdapter1(ArrayList<StudySubjectModel> studyChapterModels) {
        this.studySubjectModels = studyChapterModels;
    }

    public class SubStudyChapter extends RecyclerView.ViewHolder{

        TextView subjects;
        ImageView imageView;
        public SubStudyChapter(View itemView) {
            super( itemView );

            subjects=(TextView) itemView.findViewById( R.id.txt_subjects);
            imageView=(ImageView) itemView.findViewById( R.id.icon_subjects);
        }
    }
}
