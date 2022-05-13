package com.example.fact_o;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.fact_o.adapter.RecyclerAdapter;
import com.example.fact_o.api.ApiCalls;
import com.example.fact_o.client.FactClient;
import com.example.fact_o.model.Fact;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);



        ApiCalls api = FactClient.getInstance().create(ApiCalls.class);
        Call<List<Fact>> call = api.getFacts();
        call.enqueue(new Callback<List<Fact>>() {
            @Override
            public void onResponse(Call<List<Fact>> call, Response<List<Fact>> response) {
                generateList(response.body());
            }

            @Override
            public void onFailure(Call<List<Fact>> call, Throwable t) {

            }
        });


    }
    private void generateList(List<Fact> list){
        Log.d("TAG", "generateList: content loaded");
        viewPager.setAdapter(new RecyclerAdapter(list,this));

    }

}