package com.example.retroft_recycleview_easy_demo.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.AndroidVersionJsonResponce;
import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.Android_getter_setter;
import com.example.retroft_recycleview_easy_demo.Model.AndroidVersions.DataAdapter;
import com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples.Comment;
import com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples.CommentDataAdapter;
import com.example.retroft_recycleview_easy_demo.R;
import com.example.retroft_recycleview_easy_demo.Controllers.RequestInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Comment> data1;
    private ArrayList<Android_getter_setter> data;
    private DataAdapter adapter;
    CommentDataAdapter adapter1;
    String versions = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

       // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL, false);
       // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
       // loadJSON();
        CodingInFlowLoadJson();
    }

    private void CodingInFlowLoadJson() {

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface requestInterface=retrofit.create(RequestInterface.class);
        Call<List<Comment>> call=requestInterface.getComments(1);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccess()) {
                    Toast.makeText(MainActivity.this,"Code: " + response.code() ,Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Comment> jsonResponse = (ArrayList<Comment>) response.body();
//Optional*************************
                for (Comment d : jsonResponse) {
                    String s = d.getName();

                   // versions = versions + "  , " + s;

                    Toast.makeText(MainActivity.this, versions, Toast.LENGTH_SHORT).show();

                }

                //String name=jsonResponse.get(i).getName();
              System.out.println("Android Version Data : " + new Gson().toJson(jsonResponse));
                data1 = new ArrayList<>(jsonResponse);

                adapter1 = new CommentDataAdapter(data1);
                recyclerView.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.learn2crack.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<AndroidVersionJsonResponce> call = request.getJSON();
        call.enqueue(new Callback<AndroidVersionJsonResponce>() {
            @Override
            public void onResponse(Call<AndroidVersionJsonResponce> call, Response<AndroidVersionJsonResponce> response) {

                AndroidVersionJsonResponce jsonResponse = response.body();
                System.out.println("Android Version Data : " + new Gson().toJson(response));
                // String msg=jsonResponse.getAndroidGettersetter().toString();
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();


                AndroidVersionJsonResponce myResponse = response.body();
                List<Android_getter_setter> details = myResponse.getAndroidGettersetter();
                for (Android_getter_setter d : details) {
                    String s = d.getName();

                    versions = versions + "  , " + s;

                    Toast.makeText(MainActivity.this, versions, Toast.LENGTH_SHORT).show();

                }

                data = new ArrayList<>(jsonResponse.getAndroidGettersetter());
                adapter = new DataAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<AndroidVersionJsonResponce> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                Log.d("Error", t.getMessage());
            }
        });
    }
}
