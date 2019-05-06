package com.example.ejemplosqlite;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class InformacionComun extends ContentProvider {
    private static final String uri =
            "content://com.example.contentproviders/tareas";
    public static final Uri CONTENT_URI = Uri.parse(uri);
    private static final int TAREAS = 1;
    private static final int TAREAS_ID = 2;
    private static final String TABLA_TAREAS = "Tareas";
    private static final UriMatcher uriMatcher;
    private TareaDbHelper tareaDbHelper;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.contentproviders", "tareas", TAREAS);
        uriMatcher.addURI("com.example.contentproviders", "tareas/#", TAREAS_ID);
    }

    @Override
    public boolean onCreate() {
        tareaDbHelper = new TareaDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        String where = selection;
        if (uriMatcher.match(uri) == TAREAS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = tareaDbHelper.getWritableDatabase();
        Cursor c = db.query(TABLA_TAREAS, projection, where,
                selectionArgs, null, null, sortOrder);
        return c;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long regId = 1;
        SQLiteDatabase db = tareaDbHelper.getWritableDatabase();
        regId = db.insert(TABLA_TAREAS, null, values);
        Uri newUri = ContentUris.withAppendedId(CONTENT_URI, regId);
        return newUri;
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String selection, String[] selectionArgs) {
        int cont;
        String where = selection;
        if (uriMatcher.match(uri) == TAREAS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = tareaDbHelper.getWritableDatabase();
        cont = db.update(TABLA_TAREAS, values, where, selectionArgs);
        return cont;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int cont;
        String where = selection;
        if (uriMatcher.match(uri) == TAREAS_ID) {
            where = "_id=" + uri.getLastPathSegment();
        }
        SQLiteDatabase db = tareaDbHelper.getWritableDatabase();
        cont = db.delete(TABLA_TAREAS, where, selectionArgs);
        return cont;
    }

    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case TAREAS:
                return "vnd.android.cursor.dir/vnd.example.tarea";
            case TAREAS_ID:
                return "vnd.android.cursor.item/vnd.example.tarea";
            default:
                return null;
        }
    }
}
