package com.example.aleks.brickwall;

import android.content.Intent;
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

public class WebActivity extends AppCompatActivity {

    private WebView wvMyWebView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        url = "http://www.google.com";
        wvMyWebView = (WebView)findViewById(R.id.wvWebView);
        wvMyWebView.loadUrl(url);

//        wvMyWebView.setWebViewClient(new WebViewClient());
//        wvMyWebView.setWebChromeClient(new WebChromeClient());

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

    public void onWvClick(View view) {
        Intent newIntent = new Intent();
        String action = newIntent.getAction();
        Uri uri = newIntent.getData();
        Log.i("wafl", "Action: " + action);
        Log.i("wafl", "Uri: " + uri);
    }
}
