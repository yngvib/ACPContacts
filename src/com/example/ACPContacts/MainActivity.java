package com.example.ACPContacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void buttonPressed( View view ) {
        Button button = (Button) view;
        Intent intent;
        if ( button.getId() == R.id.contacts ) {
            intent = new Intent(this, ContactsActivity.class );
            startActivity( intent );
        }
        else if ( button.getId() == R.id.contactsLoader ) {

        }
        else {

        }

    }
}
