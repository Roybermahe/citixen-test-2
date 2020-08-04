package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.data.newsModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsViewHolder> {
    private Context contextNow;
    private LayoutInflater mInflater;
    View mNewsItemView;
    private final LinkedList<newsModel> listNews ;
    public newsAdapter(Context context, LinkedList<newsModel> listNews) {
        this.contextNow =  context;
        this.mInflater = LayoutInflater.from(context);
        this.listNews = listNews;
    }

    @NonNull
    @Override
    public newsAdapter.newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        this.mNewsItemView = mInflater.inflate(R.layout.news_layout, parent, false);
        return new newsViewHolder(mNewsItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull newsAdapter.newsViewHolder newsViewHolder, final int i) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final newsModel news = this.listNews.get(i);
        newsViewHolder.authorView.setText(news.source.name);
        newsViewHolder.description.setText(news.description);
        newsViewHolder.dateAt.setText(formatter.format(news.publishedAt));
        Picasso.with(this.contextNow).load(news.urlToImage).priority(Picasso.Priority.HIGH).into(newsViewHolder.imageNotice);
        this.mNewsItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Intent sendData = new Intent(contextNow, detailNews.class);
                sendData.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendData.putExtra("author", news.source.name);
                sendData.putExtra("title", news.title);
                sendData.putExtra("image", news.urlToImage);
                sendData.putExtra("date", formatter.format(news.publishedAt));
                sendData.putExtra("description", news.description);
                contextNow.startActivity(sendData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    class newsViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView authorView;
        private TextView description;
        private TextView dateAt;
        private ImageView imageNotice;
        private newsAdapter adapter;
        private newsModel news;
        public newsViewHolder(@NonNull View itemView, newsAdapter adapter)
        {
            super(itemView);
            this.authorView = itemView.findViewById(R.id.card_author);
            this.description = itemView.findViewById(R.id.text_detail);
            this.dateAt = itemView.findViewById(R.id.date_new);
            this.imageNotice = itemView.findViewById(R.id.news_photo);
            this.adapter =  adapter;
        }

        @Override
        public void onClick(final View v) {

        }
    }
}

