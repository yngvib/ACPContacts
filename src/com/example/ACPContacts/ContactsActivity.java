package com.example.ACPContacts;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

/**
 * Created by yngvi on 17.10.2014.
 */
public class ContactsActivity extends ListActivity {

    public Cursor mCursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the cursor.
        mCursor = this.getContentResolver().query(
                        ContactsContract.Contacts.CONTENT_URI,
                        null, null, null, null
                );
        startManagingCursor( mCursor );

        // Hook up to an adapter.
        ListAdapter adapter = new SimpleCursorAdapter(
            this,
            android.R.layout.two_line_list_item,
            mCursor,
            new String[] {ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME },
            new int[] { android.R.id.text1, android.R.id.text2 }
        );
        setListAdapter( adapter );
    }
}