package com.example.aleks.brickwall;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity {

    private WebView wvMyWebView;
    private EditText etAddressBar;
    private Button btnGo;
    private String url;
//    private WebViewClient myWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        url = "http://www.google.com";
        wvMyWebView = (WebView)findViewById(R.id.wvWebView);
        etAddressBar = (EditText)findViewById(R.id.etAddressBar);
        btnGo = (Button)findViewById(R.id.btnGo);

        Intent newIntent = this.getIntent();
        String action = newIntent.getAction();
        Uri uri = newIntent.getData();
        Log.i("wafl", "Action: " + action);
        Log.i("wafl", "Uri: " + uri);

        if (uri != null)
            url = uri.toString();

        wvMyWebView.loadUrl(url);
        wvMyWebView.getSettings().setJavaScriptEnabled(true);

        wvMyWebView.setWebViewClient(new WebViewClient()
//                                     {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                super.shouldOverrideUrlLoading(view,url);
////                if(Uri.parse(url).getHost().endsWith("html5rocks.com")) {
////                    return false;
////                }
////
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
////                view.getContext().startActivity(intent);
//                view.loadUrl(url);
//                return true;
//            }

//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon)
//            {
//                super.onPageStarted(view,url,favicon);
//                Log.d("onPageStared", "=== onPageStared ===");
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                super.onPageFinished(view, url);
//                Log.d("onPageFinished", "=== onPageFinished ===");
//            }
//        }
        );
        wvMyWebView.setWebChromeClient(new WebChromeClient());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if(wvMyWebView.canGoBack()) {
            wvMyWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onBtnGoClick(View view) {
        wvMyWebView.loadUrl(etAddressBar.getText().toString());
    }
}
