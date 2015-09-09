package com.example.aleks.brickwall;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WebActivity extends AppCompatActivity {

    private WebView wvMyWebView;
    private EditText etAddressBar;
    private LinearLayout browserAddressBarLayout;
    private Button btnGo;
    private String url;

    final int MENU_VISIBILITY = 0;
    final int MENU_ABOUT = 1;
    final int MENU_EXIT = 2;
    final int MENU_BACKFORD = 3;
    final int MENU_RELOAD = 4;
    final int MENU_FORWARD = 5;
//    private WebViewClient myWebViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        url = "http://www.google.com";
        wvMyWebView = (WebView)findViewById(R.id.wvWebView);
        etAddressBar = (EditText)findViewById(R.id.etAddressBar);
        browserAddressBarLayout = (LinearLayout)findViewById(R.id.browserAddressBarLayout);
        btnGo = (Button)findViewById(R.id.btnGo);

        Intent newIntent = this.getIntent();
        String action = newIntent.getAction();
        Uri uri = newIntent.getData();
        Log.i("wafl", "Action: " + action);
        Log.i("wafl", "Uri: " + uri);

        if (uri != null)
            url = uri.toString();

        wvMyWebView.loadUrl(url);
        etAddressBar.setText(url);
        wvMyWebView.getSettings().setJavaScriptEnabled(true);

        wvMyWebView.setWebViewClient(new WebViewClient()
//                                     {
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
        
        menu.add(0, MENU_VISIBILITY, 0, R.string.wv_url_bar);
        menu.add(0, MENU_RELOAD, 0, R.string.wv_reload);
        menu.add(0, MENU_BACKFORD, 0, R.string.wv_back);
        menu.add(0, MENU_FORWARD, 0, R.string.wv_forward);
        menu.add(0, MENU_ABOUT, 0, R.string.wv_about);
        menu.add(0, MENU_EXIT, 0, R.string.wv_exit_title);

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

        switch(item.getItemId())
        {
            case MENU_VISIBILITY:
                ToggleAddressBarVisibility();
                break;
            case MENU_ABOUT:
                openAboutBoxInfo();
                break;
            case MENU_EXIT:
                openExitActivityBox();
                break;
            case MENU_BACKFORD:
                if(wvMyWebView.canGoBack())
                    wvMyWebView.goBack();
                break;
            case MENU_RELOAD:
                wvMyWebView.reload();
                break;
            case MENU_FORWARD:
                if(wvMyWebView.canGoForward())
                    wvMyWebView.goForward();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void ToggleAddressBarVisibility()
    {
        if (browserAddressBarLayout.getVisibility() == View.GONE)
        {
            browserAddressBarLayout.setVisibility(View.VISIBLE);
        }
        else
        {
            browserAddressBarLayout.setVisibility(View.GONE);
        }
    }

    private void openAboutBoxInfo()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.wv_about)
                .setMessage(R.string.wv_about_msg)
                .setPositiveButton(R.string.wv_yes,
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialoginterface, int i)
                            {}
                        })
                .show();
    }

    private void openExitActivityBox()
    {
        new AlertDialog.Builder(this)
                .setTitle(R.string.wv_exit_title)
                .setMessage(R.string.wv_exit)
                .setNegativeButton(R.string.wv_no,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                            }
                        })
                .setPositiveButton(R.string.wv_yes,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialoginterface, int i) {
                                finish();
                            }
                        })
                .show();
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
