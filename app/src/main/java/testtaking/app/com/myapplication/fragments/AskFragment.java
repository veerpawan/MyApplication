package testtaking.app.com.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import testtaking.app.com.myapplication.R;


public class AskFragment extends BottomSheetDialogFragment {

    LinearLayout linearlayout_askadoubt, linearLayout_postmcq, linearLayout_share;
    LinearLayout bottom;

    TextView textView;


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );


        linearlayout_askadoubt = (LinearLayout) getActivity().findViewById( R.id.askdoubt );
        linearLayout_postmcq = (LinearLayout) getActivity().findViewById( R.id.postmcq );
        linearLayout_share = (LinearLayout) getActivity().findViewById( R.id.share );
        bottom = (LinearLayout) getActivity().findViewById( R.id.bottom_sheet );

    }


    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }

    };


    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog( dialog, style );
        View contentView = View.inflate( getContext(), R.layout.fragment_ask, null );
        dialog.setContentView( contentView );

        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();

        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback( mBottomSheetBehaviorCallback );
        }

    }
}
