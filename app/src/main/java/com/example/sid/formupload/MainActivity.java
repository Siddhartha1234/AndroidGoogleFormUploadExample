package com.example.sid.formupload;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



    }

    public void addResponse(View v)
    {
        EditText firstEdit = (EditText) findViewById(R.id.editText);
        EditText secondEdit = (EditText) findViewById(R.id.editText2);
        String firstEntry="", secondEntry="";
        try {
            firstEntry = URLEncoder.encode(firstEdit.getText().toString(), "UTF-8");
            secondEntry = URLEncoder.encode(secondEdit.getText().toString(), "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            Log.d("URL","Couldn't encode");
        }
        String dataToPost = "entry.1627197280="+firstEntry+"&"+"entry.1677900093="+secondEntry;

        new formPostClient().execute("https://docs.google.com/forms/d/1ExkAYvMHUpTc5Jk0FHVUGuEjo9-TDPAg7guOas0QTlU/formResponse",dataToPost);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
