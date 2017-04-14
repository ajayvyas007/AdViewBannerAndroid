package com.android.best.be.best.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.best.be.best.R;
import com.android.best.be.best.sharedprefrence.PrefManager;
import com.android.volley.RequestQueue;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class HomeScreen extends AppCompatActivity {
    private Boolean exit = false;
    private ProgressDialog mProgress;
    private AdView mAdView;
    private PrefManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        new BackGroundTask().execute();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session=new PrefManager(getApplicationContext());


        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_screen, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
            return true;
        }
        else if(id == R.id.action_email_us)
        {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","abc@abc.xyz", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "About Sonam Gupta App.");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "your msg...");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class BackGroundTask extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgress = new ProgressDialog(HomeScreen.this);
            mProgress.setMessage("Please Wait...");
            mProgress.setCancelable(false);
            mProgress.show();
        }

        @Override
        protected Void doInBackground(Void... arg) {


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (mProgress.isShowing())
                mProgress.dismiss();

        }
    }




    @Override
    protected void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }

}
