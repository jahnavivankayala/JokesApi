package com.example.cse.jokesapi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edit);
        button=findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String string=editText.getText().toString();
                if (string.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter any text", Toast.LENGTH_SHORT).show();
                }
                else {
                        Intent intent = new Intent(MainActivity.this, JokesDetail.class);
                        //intent.putExtra(getResources().getString(R.string.key), string);
                        intent.putExtra("jokeCount", editText.getText().toString());
                        startActivity(intent);

                    }
                }
        });
    }

}
