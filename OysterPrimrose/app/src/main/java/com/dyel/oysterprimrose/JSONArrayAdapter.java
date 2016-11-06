package com.dyel.oysterprimrose;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by secomd on 10/15/2016.
 */

public class JSONArrayAdapter extends RecyclerView.Adapter<JSONArrayAdapter.ViewHolder  > {

    JSONArray data;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public JSONArrayAdapter(Context context,JSONArray data) {
        super();
        this.context = context;
        this.data = data;

    }

    @Override
    public int getItemCount(){
        return data.length();
    }

    @Override
    public JSONArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitems, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        LayoutInflater inflater =  (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mainView = inflater.inflate(R.layout.listitems,null);
        TextView textView1 = (TextView) mainView.findViewById(R.id.txt1);
        TextView textView2 = (TextView) mainView.findViewById(R.id.txt2);
        try {textView1.setText(data.getJSONObject(position).getString("name"));
            textView2.setText(data.getJSONObject(position).getString("status"));}
        catch(JSONException e) {

        }

    }

}
