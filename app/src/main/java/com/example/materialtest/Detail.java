package com.example.materialtest;

import android.content.Intent;

import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Detail extends AppCompatActivity {
    private Attraction_detail attraction_detail=new Attraction_detail();
    private String name;
    private String http;

    Toolbar toolbar ;
    CollapsingToolbarLayout collapsingToolbar ;
    ImageView fruitImageView;
    TextView fruitContentText ;
    TextView Light;


////////////////////////
    AppBarLayout appBarLayout;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;
    FloatingActionButton floatingActionButton;

    /**
     * 内部类 采用AscyTask的异步加载的方式对抓取的信息惊醒加载
     */


    class MyTsak extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean==true){
                Log.v("sss","success!!!!");
                fruitContentText.setText("     "+attraction_detail.detail());
                Glide.with(Detail.this).load(attraction_detail.img_HD()).into(fruitImageView);
                Light.setText(attraction_detail.Highlight());
                collapsingToolbar.setTitle(name);
                progressBar.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
                appBarLayout.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            attraction_detail.Search(http);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        http=intent.getStringExtra("http");



         appBarLayout=findViewById(R.id.appBar);
         progressBar=findViewById(R.id.show);
         nestedScrollView=findViewById(R.id.Net);
         floatingActionButton=findViewById(R.id.Flo);



         toolbar = findViewById(R.id.toolbar);
         collapsingToolbar =  findViewById(R.id.collapsing_toolbar);
         fruitImageView = findViewById(R.id.fruit_image_view);
         fruitContentText =  findViewById(R.id.fruit_content_text);
         Light=findViewById(R.id.Light_1);





        new MyTsak().execute();




        fruitContentText.clearComposingText();

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
