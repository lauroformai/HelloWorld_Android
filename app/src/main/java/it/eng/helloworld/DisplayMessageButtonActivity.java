package it.eng.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayMessageButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra("message_key");

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        ViewGroup layout = (ViewGroup) findViewById(R.id.contenitore);
        layout.addView(textView);




    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DisplayMessage", "onStart") ;
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("DisplayMessage", "onResume") ;
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i("DisplayMessage", "onRestart") ;
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("DisplayMessage", "onPause") ;
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("DisplayMessage", "onStop") ;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("DisplayMessage", "onDestroy") ;
    }
}


