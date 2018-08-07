package testtaking.app.com.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import testtaking.app.com.myapplication.R;

/**
 * Created by PAWAN on 8/2/2017.
 */

public class AboutSelfEnabler extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_selfenabler);
        mToolbar = (Toolbar) findViewById(R.id.toolbarSubject);
        setSupportActionBar(mToolbar);

        WebView mywebview = (WebView) findViewById(R.id.webViewselfenabler);

        mywebview.getSettings().setJavaScriptEnabled(true);


        mywebview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt) {
                AboutSelfEnabler.this.setTitle("Loading...");
                AboutSelfEnabler.this.setProgress(paramAnonymousInt * 100);
                if (paramAnonymousInt == 100) {
                    AboutSelfEnabler.this.setTitle("SelfEnabler");
                }
            }
        });
        mywebview.loadUrl("https://selfenabler.com/");

    }
}
