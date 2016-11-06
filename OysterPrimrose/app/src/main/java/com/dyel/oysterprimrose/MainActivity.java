package com.dyel.oysterprimrose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ExerciseObject> mWorkoutList = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.mWorkoutView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        WorkoutListAdapter mListAdapter = new WorkoutListAdapter(mWorkoutList);
        mAdapter = new WorkoutListAdapter(mWorkoutList);
        mRecyclerView.setAdapter(mListAdapter);
    }
    protected void onResume() {
        super.onResume();
        DatabaseHandler db = new DatabaseHandler(this);

        List<ExerciseObject> newWorkoutList = db.getAllExercises();
        for (ExerciseObject obj:
                db.getAllExercises()) {
            db.deleteExercise(obj);
        }
        mWorkoutList.addAll(newWorkoutList);


        addDemoData();

        mAdapter.notifyDataSetChanged();
        db.close();
    }
    public void handleAlternateExercise(View view) {
        // TODO: 10/15/16 Display 'changeExerciseView' with alternate exercises
    }
    
    private void addDemoData() {
        DatabaseHandler db = new DatabaseHandler(this);

        ExerciseObject object = new ExerciseObject("Bench", "image here", "Lay on bench", "Do weight you clearly can't do and have somebody come save you",
                "Bar");
        db.addExerciseObject(object);
        ExerciseObject backobj = new ExerciseObject("Deadlift", "image here", "Arch Back", "Put on 3 plates. You got this. Arch that back and pull with all your might!",
                "Bar");
        db.addExerciseObject(backobj);
        ExerciseObject legobj = new ExerciseObject("Squat", "image here", "Don't Brace Core", "Whatever you do. Don't hit parallel or below. So bad for your knees. Don't do it. I mean it",
                "Bar");
        db.addExerciseObject(legobj);
        db.close();
    }

    public void handleNewExercise(View view) {
        Intent intent = new Intent(this, addExerciseView.class);
        startActivity(intent);
    }
}
