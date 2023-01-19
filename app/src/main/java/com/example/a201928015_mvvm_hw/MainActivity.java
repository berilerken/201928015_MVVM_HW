package com.example.a201928015_mvvm_hw;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a201928015_mvvm_hw.Adapters.Adapter;
import com.example.a201928015_mvvm_hw.Database.Model;
import com.example.a201928015_mvvm_hw.Interfaces.CatAPI;
import com.example.a201928015_mvvm_hw.Repository.Repository;
import com.example.a201928015_mvvm_hw.ViewModel.CatViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Repository repository;
    private CatViewModel catViewModel;

    private Adapter adapter;

    private List<Model> getCats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        repository=new Repository(getApplication());
        getCats=new ArrayList<>();


        catViewModel=new ViewModelProvider(this).get(CatViewModel.class);

        adapter =new Adapter(this, getCats);
        makeRequest();
        catViewModel.getAllCats().observe(this, new Observer<List<Model>>() {
            @Override
            public void onChanged(List<Model> cats) {
                recyclerView.setAdapter(adapter);
                adapter.getAllDatas(cats);
                Log.d("main", "onChanged: "+cats);
            }
        });
    }

    private void makeRequest() {
        Retrofit retrofit=new Retrofit.Builder()
                //api url
                .baseUrl("https://api.thecatapi.com/v1/images/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CatAPI api=retrofit.create(CatAPI.class);
        Call<List<Model>> call=api.getImgs(5);
        call.enqueue(new retrofit2.Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                if(response.isSuccessful()) {
                    repository.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Log.d("main", "onFailure: "+t.getMessage());
            }
        });
    }
}