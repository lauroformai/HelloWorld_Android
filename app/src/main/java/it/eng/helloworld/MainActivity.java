package it.eng.helloworld;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendMessage(View view)
    {
        // Versione 1.0 =  Questo è il codice per far uscire la dialog
        /*AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        EditText testoObj = (EditText) findViewById(R.id.main_activity_name) ; // ho recuperato l'oggetto EditText già dicharato

        //ora recupero il valore inserito nella text
        String text_to_view ;
        String text = testoObj.getText().toString() ;

        if (text.length() > 0)
            text_to_view = text ;
        else
            text_to_view = "Bel" ;




        builder.setMessage("Bravo " + text_to_view + "!!! Hai pigiato")
                .setTitle("Feedback");

        AlertDialog dialog = builder.create();
        dialog.show();

        Log.i("Main_activity","Ho cliccato sul pulsnte") ; */



        // Versione 1.1 facciamo una nuova Activity... devo dichiare l'intent di far partire l'activity
        Intent the_intent = new Intent(this, DisplayMessageButtonActivity.class) ;

        EditText testoObj = (EditText) findViewById(R.id.main_activity_name) ; // ho recuperato l'oggetto EditText già dicharato

        //ora recupero il valore inserito nella text
        String text = testoObj.getText().toString() ;


        the_intent.putExtra("message_key", text) ;
        startActivity(the_intent);

    }

    public void takePhoto (View view)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Verifico che l'intent che ho chiesto esista...
        // Primo modo per farlo...
        //if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
        //    startActivityForResult(takePictureIntent,1);
        //
        //}

        // secondo modo per farlo.. in questo modo ritorna il numero di applicazioni che possono risolvere l'intento scelto
        // nel mio caso.. ho 2 app per fare foto.. quindi activities.size() = 2
        PackageManager packageManager = getPackageManager() ;
        List activities = packageManager.queryIntentActivities(takePictureIntent,PackageManager.MATCH_DEFAULT_ONLY) ;
        boolean isIntentSafe = activities.size() > 0 ;

        if (isIntentSafe)
            startActivityForResult(takePictureIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");

            Intent the_intent_image = new Intent(this, DisplayPhotoDialogActivity.class) ;

            the_intent_image.putExtra("extra_key", extras) ;
            startActivity(the_intent_image);


        }
    }
}
