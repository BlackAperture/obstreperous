package com.example.obstreperous.URLSchemes;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.obstreperous.SettingsActivity;
import com.example.obstreperous.R;

public class CustomURL extends AppCompatActivity implements View.OnClickListener {


    private Toolbar toolbar;

    final Intent intent = getIntent();
    final String action = intent.getAction();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_url);

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);


        if ("com.example.obstreperous.action.VULNERABLE_SCHEME".equals(action)) {
            Log.i(getClass().getSimpleName(), "EXTRA: "+intent.getExtras().getString("myextra"));
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
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
            Intent Settings = new Intent(CustomURL.this, SettingsActivity.class);
            startActivity(Settings);
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button5:

                Intent i = new Intent();

                i.setAction("com.example.obstreperous.action.VULNERABLE_SCHEME");
                i.setPackage("com.example.obstreperous");
                i.putExtra("vulnerable", "123456");

                Log.d("ezpz", i.toUri(Intent.URI_INTENT_SCHEME));

                break;


        }
    }
}
