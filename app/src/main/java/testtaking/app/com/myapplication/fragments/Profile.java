package testtaking.app.com.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import testtaking.app.com.myapplication.R;


public class Profile extends Fragment {

    LinearLayout studentprofile_layout;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_profile, container, false );
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        studentprofile_layout=(LinearLayout) getActivity().findViewById( R.id.layout_prfile_student );

        studentprofile_layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent=new Intent( getActivity(), MainprofileActivity.class );
                startActivity( intent );*/
            }
        } );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

    }

}


