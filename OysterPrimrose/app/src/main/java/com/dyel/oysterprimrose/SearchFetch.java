package com.dyel.oysterprimrose;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by secomd on 10/14/2016.
 */

public class SearchFetch extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater =  (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.activity_add_change_exercise,null);
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.addExerciseView);

        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            JSONObject searchJSON = getJSON(this,query);
            try {
                JSONArray searchJSONArray = searchJSON.getJSONArray("Results");
                JSONArrayAdapter searchJSONAdapter = new JSONArrayAdapter(this,searchJSONArray);
                mRecyclerView.setAdapter(searchJSONAdapter);
            }
            catch(JSONException e) {
            }
        }
    }

    public static JSONObject getJSON(Context context, String city){
        try {
            URL url = new URL("https://wger.de/api/v2/workout");
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }

            return data;
        }catch(Exception e){
            return null;
        }
    }
}
