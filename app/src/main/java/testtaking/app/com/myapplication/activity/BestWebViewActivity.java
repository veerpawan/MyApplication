package testtaking.app.com.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.github.florent37.materialviewpager.MaterialViewPager;

import testtaking.app.com.myapplication.R;


public class BestWebViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    MaterialViewPager mViewPager;
    String Class, fname, lname, board, userID, emailID;
    ProgressBar pbLoadingProgress;
    ImageView ivBack;
    RelativeLayout rlBack;
    @Override
    public void onBackPressed() {
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_web_view);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pbLoadingProgress = (ProgressBar)findViewById(R.id.progressBar);

        ivBack = (ImageView)toolbar.findViewById(R.id.ivBestBack);
        rlBack = (RelativeLayout)toolbar.findViewById(R.id.rlBack);


        WebView mywebview = (WebView) findViewById(R.id.wvBest);


        mywebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt) {
                BestWebViewActivity.this.setTitle("Loading...");
                BestWebViewActivity.this.setProgress(paramAnonymousInt * 100);
                if (paramAnonymousInt == 100) {
                    BestWebViewActivity.this.setTitle("We4Coding");
                }
            }
        });
        mywebview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mywebview.setWebViewClient(new WebViewClient());
        mywebview.loadUrl("http://we4coding.com/");

        mywebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                pbLoadingProgress.setProgress(progress);
                if (progress == 100) {
                    pbLoadingProgress.setVisibility(View.GONE);

                } else {
                    pbLoadingProgress.setVisibility(View.VISIBLE);

                }
            }
        });

        mToolbar = (Toolbar) findViewById(R.id.toolbarSubject);




    }

    @Override
    protected void onResume() {
        super.onResume();
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivitymanager = (ConnectivityManager) getApplicationContext().getSystemService("connectivity");
        if (connectivitymanager != null) {
            NetworkInfo anetworkinfo[] = connectivitymanager.getAllNetworkInfo();
            if (anetworkinfo != null) {
                for (int i = 0; i < anetworkinfo.length; i++) {
                    if (anetworkinfo[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }




}

