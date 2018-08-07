package testtaking.app.com.myapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;

import testtaking.app.com.myapplication.R;
import testtaking.app.com.myapplication.adapter.RecyclerItemClickListener;
import testtaking.app.com.myapplication.adapter.SubjectAdapter1;
import testtaking.app.com.myapplication.data.SubjectData;
import testtaking.app.com.myapplication.model.StudySubjectModel;


public class Study extends Fragment {

    RecyclerView recyclerView;
    private static ArrayList<StudySubjectModel> studySubjectModels;
    private SubjectAdapter1 studyChapter;
    private RecyclerView.LayoutManager layoutManager;

    public Study() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        recyclerView = (RecyclerView) getActivity().findViewById( R.id.study_recycle );
        recyclerView.setHasFixedSize( true );
        layoutManager = new GridLayoutManager( getActivity(), 2 );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        studySubjectModels = new ArrayList<StudySubjectModel>();
        for (int i = 0; i < SubjectData.movie_name.length; i++) {
            studySubjectModels.add( new StudySubjectModel( SubjectData.movie_name[i], SubjectData.image_taken[i]));
        }
        studyChapter = new SubjectAdapter1( studySubjectModels );
        recyclerView.setAdapter( studyChapter );

        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener( getActivity(),

                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        /*Intent intent = new Intent( getActivity(), TablayoutActivity.class );
                        startActivity( intent );*/
                        //return true;
                    }
                } )
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_study, container, false );
    }

}
