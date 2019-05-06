package com.example.ejemplosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

public class TareaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tareas.db";
    public TareaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TareaBaseColumns.TABLE_NAME + "  ("
                + TareaBaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TareaBaseColumns.TITLE + " TEXT,"
                + TareaBaseColumns.DONEORNOT + " TEXT,"
                + TareaBaseColumns.DESCRIPTION + " TEXT"
                + ");";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put(TareaBaseColumns.TITLE, "Este es un título de ejemplo");
        values.put(TareaBaseColumns.DONEORNOT, 0);
        values.put(TareaBaseColumns.DESCRIPTION, "Este es una descripción de ejemplo que puede ser tan " +
                "larga como permita nuestra base de datos");

        db.insert(TareaBaseColumns.TABLE_NAME, null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.w("TAG", "Actualizando DB desde la versión " + oldVersion + " a "
                    + newVersion + ", que destruirá todos los datos antiguos");
            db.execSQL("DROP TABLE IF EXISTS " + TareaBaseColumns.TABLE_NAME);
            onCreate(db);
        }
    }

    public ArrayList returnAllTask() {
        ArrayList taskslist = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TareaBaseColumns.TABLE_NAME, null, null, null, null, null, TareaBaseColumns._ID + " DESC");
        while (cursor.moveToNext()) {
            Tarea task = new Tarea();
            task.setId(cursor.getInt(0));
            task.setTitulo(cursor.getString(1));
            task.setRealizada(cursor.getInt(2) == 1 ? true : false);
            task.setDescripcion(cursor.getString(3));
            taskslist.add(task);

            Log.d("aino",task.getRealizada() + "");
        }
        db.close();
        return taskslist;
    }
    public void editTaskList(Tarea tarea, String[] params) {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("UPDATE " + TareaBaseColumns.TABLE_NAME +
                " SET " + TareaBaseColumns.TITLE + " =?," + TareaBaseColumns.DONEORNOT + " =?," + TareaBaseColumns.DESCRIPTION + "=? " +
                "WHERE " + TareaBaseColumns._ID + " =?");
        statement.bindString(1, params[0]);
        statement.bindString(2, params[1]);
        statement.bindString(3, params[2]);
        statement.bindString(4, Integer.toString(tarea.getId()));
        statement.execute();
        statement.close();
        db.close();
    }
    public void deleteTask(int taskid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TareaBaseColumns.TABLE_NAME,
                TareaBaseColumns._ID + " =?",
                new String[]{Integer.toString(taskid)});
        db.close();
    }
}
