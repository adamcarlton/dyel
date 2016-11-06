package com.dyel.oysterprimrose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adamcarlton on 10/15/16.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ExerciseDatabase";
    private static final String TABLE_NAME = "Workout";

    // Workout Table Columns names
    private static final String EXERCISE = "exercise";
    private static final String IMAGE = "image";
    private static final String DESCRIPTION = "description";
    private static final String COMMENTS = "comments";
    private static final String EQUIPMENT = "equipment";
    //private static final String MUSCLEGROUP = "muscle group";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + EXERCISE + " EXERCISE KEY," + IMAGE + " TEXT,"
                + DESCRIPTION + " TEXT," + COMMENTS + " TEXT," + EQUIPMENT + " TEXT" + ")";
        db.execSQL(CREATE_WORKOUT_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + EXERCISE);

        // Create tables again
        onCreate(db);
    }

    public void addExerciseObject(ExerciseObject exercise){
//        Toast.makeText(, "fuck", Toast.LENGTH_SHORT).show();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXERCISE, exercise.get_exercise());
//        values.put(IMAGE, exercise.get_image());
        values.put(DESCRIPTION, exercise.get_description());
        values.put(COMMENTS, exercise.get_comments());
        values.put(EQUIPMENT, exercise.get_equipment());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ExerciseObject getExercise(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{EXERCISE, IMAGE, DESCRIPTION, COMMENTS, EQUIPMENT},
                EXERCISE + " = ?", new String[]{String.valueOf(EXERCISE)}, null, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        ExerciseObject exercise = new ExerciseObject(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),
        cursor.getString(4));
        return exercise;
    }

    public List<ExerciseObject> getAllExercises(){
        List<ExerciseObject> exerciseList = new ArrayList<ExerciseObject>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ExerciseObject exercise = new ExerciseObject();
                exercise.set_exercise(cursor.getString(0));
                exercise.set_image(cursor.getString(1));
                exercise.set_description(cursor.getString(2));
                exercise.set_comments(cursor.getString(3));
                exercise.set_equipment(cursor.getString(4));
                exerciseList.add(exercise);
            } while(cursor.moveToNext());
        }
        return exerciseList;
    }

    public int updateExercise(ExerciseObject exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EXERCISE, exercise.get_exercise());
        values.put(IMAGE, exercise.get_image());
        values.put(DESCRIPTION, exercise.get_description());
        values.put(COMMENTS, exercise.get_comments());
        values.put(EQUIPMENT, exercise.get_equipment());

        return db.update(TABLE_NAME, values, EXERCISE + " = ?", new String[]{String.valueOf(exercise.get_exercise())});
    }

    public void deleteExercise(ExerciseObject exercise){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, EXERCISE + " = ?", new String[]{String.valueOf(exercise.get_exercise())});
        db.close();
    }
}
