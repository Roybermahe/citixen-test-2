package com.example.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class detailNews extends AppCompatActivity {

    ImageView image;
    TextView authorVw, DateVw, TitleVw, DescriptionVw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Intent dataIntent = getIntent();
        Bundle b = dataIntent.getExtras();
        setContentView(R.layout.activity_detail_news);
        if(b != null) {
            this.LoadData(b);
        }
    }

    private void LoadData(Bundle b) {
        image = this.findViewById(R.id.image_article);
        authorVw = this.findViewById(R.id.author_article);
        DateVw = this.findViewById(R.id.date_article);
        TitleVw = this.findViewById(R.id.title_article);
        DescriptionVw = this.findViewById(R.id.description_article);

        Picasso.with(this).load(b.getString("image")).into(image);
        authorVw.setText(b.getString("author"));
        DateVw.setText(b.getString("date"));
        TitleVw.setText(b.getString("title"));
        DescriptionVw.setText(b.getString("description"));
    }

    public void backOption(View view) {
        this.finish();
    }
}
