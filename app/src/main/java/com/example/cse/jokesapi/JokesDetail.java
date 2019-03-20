package com.example.cse.jokesapi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class JokesDetail extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<String> {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<JokeModel> arrayList;
    String string;
    String jokes;
    String joke;

    String str;
    public static final int loaderid=111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes_detail);
        recyclerView=findViewById(R.id.recycler);
        progressBar=findViewById(R.id.progress);

        Intent intent=getIntent();
        String res = intent.getStringExtra("jokeCount");
        arrayList = new ArrayList<>();
        str = "http://api.icndb.com/jokes/random/" + res;
        internetCheck();

    }
    public void internetCheck() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(connectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(connectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
          getLoaderManager().initLoader(loaderid, null, this);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("JokesApp");
            builder.setMessage("Internet connection not available!");
            builder.setCancelable(false);
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            builder.show();
        }
    }
     @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(jokes,joke);
    }

    @Override
    public android.content.Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new android.content.AsyncTaskLoader<String>(this) {
            @Override
            public String loadInBackground() {

                try {
                    URL url = new URL(str);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.connect();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    Scanner scanner = new Scanner(inputStream);
                    scanner.useDelimiter("\\A");
                    if (scanner.hasNext()) {
                        return scanner.next();
                    } else return null;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onStartLoading() {
                super.onStartLoading();
                progressBar.setVisibility(View.VISIBLE);
                forceLoad();
            }
        };
    }

    @Override
    public void onLoadFinished(android.content.Loader<String> loader, String s) {
        progressBar.setVisibility(View.GONE);
        if (s != null) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("value");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    joke = jsonObject1.getString("joke");
                    arrayList.add(new JokeModel(joke));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(JokesDetail.this));
            recyclerView.setAdapter(new JokeAdapter(JokesDetail.this,arrayList));

        }

    }

    @Override
    public void onLoaderReset(android.content.Loader<String> loader) {

    }
}
