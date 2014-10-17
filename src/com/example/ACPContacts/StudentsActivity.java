package com.example.ACPContacts;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

/**
 * Created by yngvi on 17.10.2014.
 */
public class StudentsActivity extends ListActivity implements
        LoaderManager.LoaderCallbacks<Cursor>
{
    private final static int STUDENTS_LOADER = 1;

    SimpleCursorAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(STUDENTS_LOADER, null, this);

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put( StudentsCP.NAME, "N.N." + System.currentTimeMillis() );
                getContentResolver().insert( StudentsCP.STUDENTS_URI, values);
            }
        };
        handler.postDelayed( runnable, 5000 );
        handler.postDelayed( runnable, 10000 );

        // Hook up to an adapter.
        mAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                null,
                new String[] {StudentsCP._ID, StudentsCP.NAME },
                new int[] { android.R.id.text1, android.R.id.text2 },
                0  // Must be 0, when using a cursor loader to manage data.
        );

        setListAdapter( mAdapter );

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        switch ( id ) {
            case STUDENTS_LOADER:
                return new CursorLoader(
                        getApplicationContext(),
                        StudentsCP.STUDENTS_URI,
                        null,
                        null,
                        null,
                        null );
            // break;
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor( data );
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor( null );
    }

}