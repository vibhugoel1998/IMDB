package com.example.vibhu.imdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StartActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView1,recyclerView2,recyclerView3;
    UsesRecyclerAdapter usesRecyclerAdapter;
    UsesRecyclerAdapter2 usesRecyclerAdapter2;
    UsesRecyclerAdapter3 usesRecyclerAdapter3;
    ArrayList<NowPlayingHeirarchy.ResultsBean> users;
    ArrayList<PopularHeirarchy.ResultsBean> users2;
    ArrayList<TopRatedHeirarchy.ResultsBean> users3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        recyclerView1=findViewById(R.id.mainrecyclerview1);
        recyclerView2=findViewById(R.id.mainrecyclerview2);
        recyclerView3=findViewById(R.id.mainrecyclerview3);
        users=new ArrayList<>();
        users2=new ArrayList<>();
        users3=new ArrayList<>();
        usesRecyclerAdapter=new UsesRecyclerAdapter(users, this, new UsesRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(StartActivity.this,NowPlayingMovieDetails.class);
                NowPlayingHeirarchy.ResultsBean getObj=users.get(position);
                String title=getObj.getTitle();
                intent.putExtra("title",title);
                String PosterPath=getObj.getPoster_path();
                intent.putExtra("posterpath",PosterPath);
                String description=getObj.getOverview();
                intent.putExtra("overview",description);
                intent.putExtra("release",getObj.getRelease_date());
                intent.putExtra("id",getObj.getId());
                intent.putExtra("voteavg",getObj.getVote_average()+"");
                Log.d("check1",getObj.getVote_average()+"");
                startActivity(intent);
            }
        });
        usesRecyclerAdapter2=new UsesRecyclerAdapter2(users2, this, new UsesRecyclerAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(StartActivity.this,NowPlayingMovieDetails.class);
                PopularHeirarchy.ResultsBean getObj=users2.get(position);
                String title=getObj.getTitle();
                intent.putExtra("title",title);
                String PosterPath=getObj.getPoster_path();
                intent.putExtra("posterpath",PosterPath);
                String description=getObj.getOverview();
                intent.putExtra("overview",description);
                intent.putExtra("release",getObj.getRelease_date());
                intent.putExtra("id",getObj.getId());
                intent.putExtra("voteavg",getObj.getVote_average()+"");
                Log.d("check1",getObj.getVote_average()+"");
                startActivity(intent);
            }
        });
        usesRecyclerAdapter3=new UsesRecyclerAdapter3(users3, this, new UsesRecyclerAdapter3.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent=new Intent(StartActivity.this,NowPlayingMovieDetails.class);
                TopRatedHeirarchy.ResultsBean getObj=users3.get(position);
                String title=getObj.getTitle();
                intent.putExtra("title",title);
                String PosterPath=getObj.getPoster_path();
                intent.putExtra("posterpath",PosterPath);
                String description=getObj.getOverview();
                intent.putExtra("overview",description);
                intent.putExtra("release",getObj.getRelease_date());
                intent.putExtra("id",getObj.getId());
                intent.putExtra("voteavg",getObj.getVote_average()+"");
                Log.d("check1",getObj.getVote_average()+"");
                startActivity(intent);
            }
        });
        recyclerView1.setAdapter(usesRecyclerAdapter);
        recyclerView2.setAdapter(usesRecyclerAdapter2);
        recyclerView3.setAdapter(usesRecyclerAdapter3);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView1.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        fetchdata1();
        fetchdata2();
        fetchdata3();

    }

    private void fetchdata3() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CustomApi customApi=retrofit.create(CustomApi.class);
        Call<TopRatedHeirarchy> call=customApi.getTopRated(1+"");
        call.enqueue(new Callback<TopRatedHeirarchy>() {
            @Override
            public void onResponse(Call<TopRatedHeirarchy> call, Response<TopRatedHeirarchy> response) {
                TopRatedHeirarchy newObj=response.body();
                if(response!=null)
                {
                    users3.addAll(newObj.getResults());
                }
                usesRecyclerAdapter3.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<TopRatedHeirarchy> call, Throwable t) {

                Toast.makeText(StartActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchdata2() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CustomApi customApi=retrofit.create(CustomApi.class);
        Call<PopularHeirarchy> call=customApi.getPopular(1+"");
        call.enqueue(new Callback<PopularHeirarchy>() {
            @Override
            public void onResponse(Call<PopularHeirarchy> call, Response<PopularHeirarchy> response) {
                PopularHeirarchy newObj=response.body();
                if(response!=null)
                {
                    users2.addAll(newObj.getResults());
                }
                usesRecyclerAdapter2.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<PopularHeirarchy> call, Throwable t) {
                Toast.makeText(StartActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchdata1() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CustomApi customApi=retrofit.create(CustomApi.class);
        Call<NowPlayingHeirarchy> call=customApi.getFollowersResponse(1+"");
        call.enqueue(new Callback<NowPlayingHeirarchy>() {
            @Override
            public void onResponse(Call<NowPlayingHeirarchy> call, Response<NowPlayingHeirarchy> response) {
                NowPlayingHeirarchy newObj=response.body();
                if(response!=null)
                {
                    users.clear();
                    users.addAll(newObj.getResults());
                }
                usesRecyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NowPlayingHeirarchy> call, Throwable t) {

                Toast.makeText(StartActivity.this, "TRY AGain", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void ViewAll1(View view)
    {
        Intent intent=new Intent(this,ViewAllActivity.class);
        intent.putExtra("nowplaying",1);
        intent.putExtra("newstring","Now Showing");
        startActivity(intent);
    }
    public void ViewAll2(View view)
    {
        Intent intent=new Intent(this,ViewAllActivity.class);
        intent.putExtra("popular",2);
        intent.putExtra("newstring","Popular");
        startActivity(intent);
    }
    public void ViewAll3(View view)
    {
        Intent intent=new Intent(this,ViewAllActivity.class);
        intent.putExtra("toprated",1);
        intent.putExtra("newstring","Top Rated");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.Movies) {
            // Handle the camera action
        } else if (id == R.id.TvShows) {
            Intent intent=new Intent(StartActivity.this,TvStartActivity.class);
            startActivity(intent);


        } else if (id == R.id.AboutUs) {
            Intent intent=new Intent(this,AbouUsActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
