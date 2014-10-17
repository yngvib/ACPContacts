package com.example.ACPContacts;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by yngvi on 17.10.2014.
 */
public class StudentsCP extends ContentProvider {

    private ArrayList<String> mData = new ArrayList<String>();
    private static final String AUTHORITY = "com.example.ACPContacts";
    private static final String BASE_PATH = "students";
    private String[] mColumns = new String[] { _ID, NAME };

    public static final Uri STUDENTS_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + BASE_PATH);

    public static final String _ID = "_id";
    public static final String NAME = "name";

    @Override
    public boolean onCreate() {
        mData.add( "Yngvi B." );
        mData.add( "John S." );
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor( mColumns );
        for ( int i=0; i<mData.size(); ++i ) {
            cursor.addRow( new Object[] { i+1, mData.get(i)} );
        }
        cursor.setNotificationUri( getContext().getContentResolver(), STUDENTS_URI);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mData.add( values.getAsString( NAME ) );
        getContext().getContentResolver().notifyChange(uri,null);
        return Uri.parse( BASE_PATH + "/" + mData.size() );
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
