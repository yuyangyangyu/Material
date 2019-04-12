package com.example.materialtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.materialtest.Food.FoodMianActivity;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private static int count=1;
    private DrawerLayout mDrawaerlayout;
    private List<City> cityList =new ArrayList<>();
    private FruitAdapter adapter;
    //判断子线程是否执行完成
    private static Attractions attractions=new Attractions();



    PullLoadMoreRecyclerView recyclerView;


    class Task extends AsyncTask<Void ,Integer,Boolean> {

        private String strings;
        public Task(String string){
            this.strings=string;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            attractions.Search(strings);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean==true){
                Log.v("sss", String.valueOf(attractions.size()));
                initFruits();
                adapter.notifyDataSetChanged();
                recyclerView.setPullLoadMoreCompleted();
                CoordinatorLayout coordinatorLayout=findViewById(R.id.main);
                coordinatorLayout.setVisibility(View.VISIBLE);

            }
        }
    }



    /**
     * 初始化函数
     */

    public void initFruits(){
        cityList.clear();
        for (int i=0;i<attractions.size();i++){
            City city =new City(attractions.name().get(i),attractions.img().get(i),attractions.detail().get(i),attractions.http().get(i));
            cityList.add(city);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Task task=new Task("https://you.ctrip.com/sight/chongqing158.html");
        count++;
        task.execute();


        Log.v("sss", String.valueOf(cityList));

        initFruits();
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toobar);
        toolbar.setTitle("主页");
        setSupportActionBar(toolbar);
        mDrawaerlayout=findViewById(R.id.drawable_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        FloatingActionButton fat=findViewById(R.id.fab);
        final ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //navigationView.setCheckedItem(R.id.nav_call);//默认选项
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //mDrawaerlayout.closeDrawers();
                switch (menuItem.getItemId()){
                    case R.id.nav_call:
                        Intent intent =new Intent(MainActivity.this,FoodMianActivity.class);
                        startActivity(intent);
                }
                return true;
            }
        });
        fat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Data delete",Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(v.getContext(),"Data delete",Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
        recyclerView =findViewById(R.id.recycler_view);
        recyclerView.setLinearLayout();
        recyclerView.setPullRefreshEnable(false);
        adapter=new FruitAdapter(cityList);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                if (count<4){
                    Task task1 =new Task(String.format("https://you.ctrip.com/sight/chongqing158/s0-p%d.html#sightname",count));
                    Log.v("sss","加载更多");
                    count++;
                    task1.execute();


                }

            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               mDrawaerlayout.openDrawer(GravityCompat.START);
               break;
        }
        return true;
    }
}