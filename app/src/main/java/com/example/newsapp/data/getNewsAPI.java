package com.example.newsapp.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.newsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TimeZone;

public class getNewsAPI extends AsyncTask<Void, Void, Void> {
    LinkedList<newsModel> dataIntern = new LinkedList<>();
    LinkedList<newsModel> newsList = new LinkedList<>();
    Activity activity;

    public getNewsAPI(Activity activityContext, LinkedList<newsModel> dataIntern) {
        this.activity = activityContext;
        this.dataIntern = dataIntern;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        StringBuilder news = new StringBuilder();
        try {
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=14f70045a6184c80ac2aa986d2f53a17");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                news.append(scanner.next());
            }

            JSONObject getArticles = new JSONObject(news.toString());
            JSONArray listNews = getArticles.getJSONArray("articles");

            for (int i = 0; i < listNews.length(); i++) {
                JSONObject newsData = listNews.getJSONObject(i);
                this.newsList.add(this.mapNews(newsData));
            }
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private newsModel mapNews(JSONObject data) throws JSONException, ParseException {
        JSONObject source = data.getJSONObject("source");

        DateFormat input = new SimpleDateFormat("yyyy-MM-dd");


        return new newsModel(
                new source(source.getString("id"), source.getString("name")),
                data.getString("author"),
                data.getString("title"),
                data.getString("description"),
                data.getString("url"),
                data.getString("urlToImage"),
                input.parse(data.getString("publishedAt").substring(0,10)),
                data.getString("content")
        );
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        RecyclerView list;
        newsAdapter adapter = new newsAdapter(this.activity.getBaseContext(), newsList);
        list = activity.findViewById(R.id.listView_News);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this.activity.getBaseContext()));
    }
}
