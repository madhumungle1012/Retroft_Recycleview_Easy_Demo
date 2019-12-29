package com.example.retroft_recycleview_easy_demo.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retroft_recycleview_easy_demo.Controllers.RequestInterface;
import com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples.Comment;
import com.example.retroft_recycleview_easy_demo.Model.CodingFlowExamples.CommentDataAdapter;
import com.example.retroft_recycleview_easy_demo.Model.Room.DatabaseClient;
import com.example.retroft_recycleview_easy_demo.Model.Room.Task;
import com.example.retroft_recycleview_easy_demo.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adddata_to_Room extends AppCompatActivity {

    private EditText editTextTask, editTextDesc, editTextFinishBy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddata_to__room);
        editTextTask = findViewById(R.id.editTextTask);
        editTextDesc = findViewById(R.id.editTextDesc);
        editTextFinishBy = findViewById(R.id.editTextFinishBy);

        findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }
    private void CodingInFlowLoadJson() {


    }

    private void saveTask() {



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
                    Toast.makeText(Adddata_to_Room.this,"Code: " + response.code() ,Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<Comment> jsonResponse = (ArrayList<Comment>) response.body();
//Optional*************************
                for (Comment d : jsonResponse) {
                   // String s = d.getName();

                    final String sTask= d.getName();
                    final String sDesc = d.getBody();
                    final String sFinishBy = d.getEmail();
                   System.out.println("Cooments:  "+sTask);

                    class SaveTask extends AsyncTask<Void, Void, Void> {

                        @Override
                        protected Void doInBackground(Void... voids) {

                            //creating a task
                            Task task = new Task();
                            task.setTask(sTask);
                            task.setDesc(sDesc);
                            task.setFinishBy(sFinishBy);
                            task.setFinished(true);
                            System.out.println("Cooments in Save:  "+sTask);
                            //adding to database
                            DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                                    .taskDao()
                                    .insert(task);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);
                            finish();
                            startActivity(new Intent(getApplicationContext(), ShowRoomData.class));
                            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        }
                    }
                    SaveTask st = new SaveTask();
                    st.execute();
                }


            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Toast.makeText(Adddata_to_Room.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

}