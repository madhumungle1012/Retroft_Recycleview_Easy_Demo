package com.example.retroft_recycleview_easy_demo.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.retroft_recycleview_easy_demo.Model.Room.DatabaseClient;
import com.example.retroft_recycleview_easy_demo.Model.Room.Task;
import com.example.retroft_recycleview_easy_demo.Model.Room.TasksAdapter;
import com.example.retroft_recycleview_easy_demo.R;

import java.util.List;

public class ShowRoomData extends AppCompatActivity {
   // private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_room_data);
        recyclerView = findViewById(R.id.card_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        buttonAddTask = findViewById(R.id.floating_button_add);
//        buttonAddTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
//                startActivity(intent);
//            }
//        });


        getTasks();

    }


    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(ShowRoomData.this, tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}