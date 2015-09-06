package com.example.aleks.brickwall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.net.HttpCookie;

public class MailActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etSubject;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etSubject = (EditText)findViewById(R.id.etSubject);
        etMessage = (EditText)findViewById(R.id.etMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mail, menu);
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

    public void onBtnSendClick(View view) {
        String email = etEmail.getText().toString();
        String subject = etSubject.getText().toString();
        String message = etMessage.getText().toString();
        Intent implicitEmailIntent = new Intent(Intent.ACTION_SEND);
//        implicitEmailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
        implicitEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        implicitEmailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        implicitEmailIntent.putExtra(Intent.EXTRA_TEXT, message);

    }
}
