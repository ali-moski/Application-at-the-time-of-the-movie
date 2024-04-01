package com.example.timemovie.Activity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.timemovie.Adaptor.FilmListAdaptor;
import com.example.timemovie.Domain.ListFilm;
import com.example.timemovie.InternetReceiver;
import com.example.timemovie.R;
import com.example.timemovie.databinding.ActivityMainBinding;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BroadcastReceiver broadcastReceiver = null;
    private RecyclerView.Adapter adapterNewMovies, adapterUpComming;
    private RecyclerView recyclerViewNewMovies, recyclerViewUpComming;
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest, mStringRequest2;
    private ProgressBar loading1, loading2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setBackground(null);

        initView();
        sendRequest1();
        sendRequest2();
        broadcastReceiver = new InternetReceiver();
        Internetstatus();
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    public void Internetstatus(){
        registerReceiver(broadcastReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private void sendRequest1() {
        mRequestQueue=Volley.newRequestQueue(this);
        loading1.setVisibility(View.VISIBLE);
        mStringRequest=new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=1", response -> {
        Gson gson=new Gson();
        loading1.setVisibility(View.GONE);

            ListFilm item=gson.fromJson(response,ListFilm.class);
            adapterNewMovies=new FilmListAdaptor(item);
            recyclerViewNewMovies.setAdapter(adapterNewMovies);
        }, error -> {
            Log.i("uilover", "sendRequest1: "+error.toString());
             loading1.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest);
    }
    private void sendRequest2() {
        mRequestQueue=Volley.newRequestQueue(this);
        loading2.setVisibility(View.VISIBLE);
        mStringRequest2 = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies?page=3", response -> {
            Gson gson=new Gson();
            loading2.setVisibility(View.GONE);

            ListFilm item=gson.fromJson(response,ListFilm.class);
            adapterUpComming=new FilmListAdaptor(item);
            recyclerViewUpComming.setAdapter(adapterUpComming);
        }, error -> {
            loading2.setVisibility(View.GONE);
        });
        mRequestQueue.add(mStringRequest2);
    }
    private void initView() {
        recyclerViewNewMovies=findViewById(R.id.view1);
        recyclerViewNewMovies.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpComming=findViewById(R.id.view2);
        recyclerViewUpComming.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        loading1=findViewById(R.id.loding1);
        loading2=findViewById(R.id.loding2);

    }
}