package com.example.materialtest.Food;

import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.materialtest.R;

import java.util.ArrayList;
import java.util.List;

public class FoodMianActivity extends AppCompatActivity {
    private foodList foodList_1=new foodList();
    private List<Food> mlist=new ArrayList<>();
    private ProgressBar progressBar;
    private AppBarLayout appBarLayout;
    private RecyclerView recyclerView;

    /**
     * 对显示的美食进行初始化
     * @param mlist
     */
    public void Init(List<Food> mlist){
        mlist.clear();
        for (int i=0;i<foodList_1.Size();i++){
            Food food=new Food(foodList_1.getImgList().get(i),foodList_1.name().get(i),
                    foodList_1.recommend().get(i),foodList_1.describe().get(i));
           mlist.add(food);
        }
    }


    class AsuncT extends AsyncTask<Void,Integer,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean==true){
                Init(mlist);
                Log.v("sss", String.valueOf(mlist.size()));
                progressBar.setVisibility(View.GONE);
                appBarLayout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            foodList_1.Search("https://you.ctrip.com/fooditem/chongqing158.html");
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_mian);
        progressBar=findViewById(R.id.Loading);
        appBarLayout=findViewById(R.id.app_food);
        recyclerView=findViewById(R.id.recycler_view_food);
        Toolbar toolbar=findViewById(R.id.toobar_food);
        toolbar.setTitle("美食");
        setSupportActionBar(toolbar);

        AsuncT asuncT=new AsuncT();
        asuncT.execute();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(FoodMianActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FoodAdapter adapter=new FoodAdapter(mlist);
        recyclerView.setAdapter(adapter);


    }
}
