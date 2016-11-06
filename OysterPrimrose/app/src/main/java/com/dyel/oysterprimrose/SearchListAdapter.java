package com.dyel.oysterprimrose;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ReverendCode on 10/15/16.
 */
class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolder>  {
    private List<ExerciseObject> exerciseObjects;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.text_title_search);
        }
    }
    public SearchListAdapter(List<ExerciseObject> objects) {
        exerciseObjects = objects;
    }

    @Override
    public SearchListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_list_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(exerciseObjects.get(position).get_exercise());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO: 10/15/16 get ExerciseObject located at position, push into DB
                int position = holder.getAdapterPosition();
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseObjects.size();
    }
}