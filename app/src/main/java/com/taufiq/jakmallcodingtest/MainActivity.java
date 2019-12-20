package com.taufiq.jakmallcodingtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.taufiq.jakmallcodingtest.API.ApiClient;
import com.taufiq.jakmallcodingtest.API.GetService;
import com.taufiq.jakmallcodingtest.API.JsonRespon;
import com.taufiq.jakmallcodingtest.Model.DataModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;
    private ArrayList<DataModel> dataModels;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = findViewById(R.id.swipeRf);
        getPost();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPost();
            }
        });

    }

    private void getPost() {
        swipeRefreshLayout.setRefreshing(false);
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        recyclerView = (RecyclerView) findViewById(R.id.customRecyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);

        Call<JsonRespon> call = service.getAllData();
        call.enqueue(new Callback<JsonRespon>() {
            @Override
            public void onResponse(Call<JsonRespon> call, Response<JsonRespon> response) {
                progressDoalog.dismiss();

                JsonRespon jsonRespon = response.body();
                dataModels = new ArrayList<>(Arrays.asList(jsonRespon.getDataModels()));

                adapter = new CustomAdapter(dataModels,getApplicationContext());
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<JsonRespon> call, Throwable t) {
                progressDoalog.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, "Data Cannot", Toast.LENGTH_SHORT).show();
            }
        });
        DividerItemDecoration div= new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(div);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP| ItemTouchHelper.DOWN|ItemTouchHelper.START|ItemTouchHelper.END,0) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
              int from =viewHolder.getAdapterPosition();
              int to = target.getAdapterPosition();
                Collections.swap(dataModels,from,to);
                recyclerView.getAdapter().notifyItemMoved(from,to);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
