package me.sivasubramanyam.apps.akash;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class Launcher extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ImageButton ib1 = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton ib2 = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton ib3 = (ImageButton) findViewById(R.id.imageButton4);
        ib1.setOnClickListener(this);
        ib2.setOnClickListener(this);
        ib3.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
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

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.imageButton2:
             startActivity(new Intent(Launcher.this, MainActivity.class));
             break;
         case R.id.imageButton3:
             startActivity(new Intent(Launcher.this, MainActivity3.class));
             break;
         case R.id.imageButton4:
             startActivity(new Intent(Launcher.this, MainActivityActivity.class));
             break;
     }
    }
}
