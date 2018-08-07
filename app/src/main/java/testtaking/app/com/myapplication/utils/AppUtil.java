package testtaking.app.com.myapplication.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;

import testtaking.app.com.myapplication.R;


@SuppressLint("SimpleDateFormat")
public class AppUtil {
    public static void setUpToolBar(Toolbar toolbar, Context context, String title) {

        if (toolbar != null) {
            toolbar.setLogo(null);
            toolbar.setTitle(Html.fromHtml("<font color=#000>" + title + "</font>"));
        }
        //toolbar.setLogo(R.drawable.white_logo);
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        if (((AppCompatActivity) context).getSupportActionBar() != null) {
            ((AppCompatActivity) context).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) context).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        final Drawable upArrow = ContextCompat.getDrawable(context, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(ContextCompat.getColor(context, R.color.Colorblack), PorterDuff.Mode.SRC_ATOP);
        ((AppCompatActivity) context).getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }

}
