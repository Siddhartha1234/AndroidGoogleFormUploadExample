package com.example.sid.formupload;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import cz.msebera.android.httpclient.client.params.CookiePolicy;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpConnectionParams;
import cz.msebera.android.httpclient.params.HttpParams;
import cz.msebera.android.httpclient.protocol.BasicHttpContext;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.EntityUtils;


/**
 * Created by sid on 15/11/16.
 */

class formPostClient extends AsyncTask<String,Integer,Integer> {

    private DefaultHttpClient httpClient;
    private HttpContext localContext;



    @Override
    protected void onPostExecute(Integer aLong) {
        super.onPostExecute(aLong);
    }

    @Override
    protected Integer doInBackground(String... urls) {
        HttpResponse response = null;
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        HttpParams myParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        httpClient = new DefaultHttpClient(myParams);
        localContext = new BasicHttpContext();
        String ret = null;
        String url = urls[0];

        httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.RFC_2109);

        httpPost = new HttpPost(urls[0]);
        response = null;

        StringEntity tmp = null;

        httpPost.setHeader("User-Agent", "Sid");
        httpPost.setHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*;q=0.5");


        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        String data = urls[1];
        tmp = new StringEntity(data,"UTF-8");

        httpPost.setEntity(tmp);

        Log.d("Form Upload", url + "?" + data);

        try {
            response = httpClient.execute(httpPost,localContext);

            if (response != null) {
                ret = EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            Log.e("Form upload", "HttpUtils: " + e);
        }

        Log.d("Form upload" ,"HttpUtils:"+ ret);

        return ret.length();

    }


}
