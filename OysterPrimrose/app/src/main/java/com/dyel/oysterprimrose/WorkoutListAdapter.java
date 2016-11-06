package com.dyel.oysterprimrose;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by ReverendCode on 10/15/16.
 */
class WorkoutListAdapter extends RecyclerView.Adapter<WorkoutListAdapter.ViewHolder> {
    private List<ExerciseObject> exerciseObjects;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title, description, equipType, comments;
        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_title);
            description = (TextView) v.findViewById(R.id.descriptionTextView);
            equipType = (TextView) v.findViewById(R.id.text_equipment_type);
            comments = (TextView) v.findViewById(R.id.text_comment);
            image = (ImageView) v.findViewById(R.id.imageView);


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public WorkoutListAdapter(List<ExerciseObject> objects) {
        exerciseObjects = objects;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WorkoutListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_card_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(exerciseObjects.get(position).get_exercise());
        holder.description.setText(exerciseObjects.get(position).get_description());
        holder.equipType.setText("Equipment: " + exerciseObjects.get(position).get_equipment());
        holder.comments.setText(exerciseObjects.get(position).get_comments());
        holder.image.setImageDrawable(loadImageFromWebOperations(exerciseObjects.get(position).get_image()));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return exerciseObjects.size();
    }
    public static Drawable loadImageFromWebOperations(String url){
        try{
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }
        catch(Exception e){
            return null;
        }
    }
}




