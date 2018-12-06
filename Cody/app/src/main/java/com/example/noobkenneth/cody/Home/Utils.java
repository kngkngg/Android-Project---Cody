package com.example.noobkenneth.cody.Home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.noobkenneth.cody.models.FashionArticle;
import com.example.noobkenneth.cody.models.Source;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Utils {

    public static List<FashionArticle> getJSON(String url) {

        StringBuilder stringBuilder = new StringBuilder();
        String result;

        try {
            URL Url = new URL(url);
            URLConnection yc = Url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            //StringBuilder stringBuilder = new StringBuilder();

            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                stringBuilder.append(inputLine + "\n");
            }

            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        result = stringBuilder.toString();
        System.out.println(result);

        JsonParser jsonParser = new JsonParser();

        JsonElement jsonTree = jsonParser.parse(result);
        JsonObject jsonObject = jsonTree.getAsJsonObject();
        JsonArray JSONarticles = jsonObject.getAsJsonArray("articles");
        //System.out.println("article: " + articles.get(0));
        List<FashionArticle> articles = new ArrayList<>();
        for (JsonElement jsonArticle : JSONarticles) {
            FashionArticle article = new FashionArticle();
            article.setTitle(jsonArticle.getAsJsonObject().get("title").toString());
            //System.out.println(jsonArticle.getAsJsonObject().get("title").toString());
            article.setDescription(jsonArticle.getAsJsonObject().get("description").toString());
            article.setDate(jsonArticle.getAsJsonObject().get("date").toString());
            article.setTopic(jsonArticle.getAsJsonObject().get("topic").toString());
            article.setUrl(jsonArticle.getAsJsonObject().get("url").toString());
            article.setUrlToImage(jsonArticle.getAsJsonObject().get("urlToImg").toString());
            Source source = new Source();
            JsonElement JSONSource = jsonArticle.getAsJsonObject().get("source");
            source.setId(JSONSource.getAsJsonObject().get("id").toString());
            source.setName(JSONSource.getAsJsonObject().get("name").toString());

            article.setSource(source);
            articles.add(article);
        }

        return articles;

    }

    public static ColorDrawable[] vibrantLightColorList =
            {
                    new ColorDrawable(Color.parseColor("#ffeead")),
                    new ColorDrawable(Color.parseColor("#93cfb3")),
                    new ColorDrawable(Color.parseColor("#fd7a7a")),
                    new ColorDrawable(Color.parseColor("#faca5f")),
                    new ColorDrawable(Color.parseColor("#1ba798")),
                    new ColorDrawable(Color.parseColor("#6aa9ae")),
                    new ColorDrawable(Color.parseColor("#ffbf27")),
                    new ColorDrawable(Color.parseColor("#d93947"))
            };

    public static ColorDrawable getRandomDrawbleColor() {
        int idx = new Random().nextInt(vibrantLightColorList.length);
        return vibrantLightColorList[idx];
    }

    public static String DateToTimeFormat(String oldstringDate){
        PrettyTime p = new PrettyTime(new Locale(getCountry()));
        String isTime = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'",
                    Locale.ENGLISH);
            Date date = sdf.parse(oldstringDate);
            isTime = p.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isTime;
    }

    public static String DateFormat(String oldstringDate){
        String newDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy", new Locale(getCountry()));
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(oldstringDate);
            newDate = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            newDate = oldstringDate;
        }

        return newDate;
    }

    public static String getCountry(){
        Locale locale = Locale.getDefault();
        String country = String.valueOf(locale.getCountry());
        return country.toLowerCase();
    }
}