package com.example.newsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.newsapp.data.getNewsAPI;
import com.example.newsapp.data.newsModel;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    public static LinkedList<newsModel> newsList = new LinkedList<>();
    RecyclerView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        new getNewsAPI(this, newsList).execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new getNewsAPI(this, newsList).execute();
    }
}
