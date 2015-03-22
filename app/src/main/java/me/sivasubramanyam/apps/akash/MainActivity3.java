package me.sivasubramanyam.apps.akash;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity3 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
        int d = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
        int m = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int y = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        TextView tv = (TextView) findViewById(R.id.moon);
        tv.setText(Moon_phase(y,m,d));


    }
    static String Moon_phase(int year,int month,int day)
    {
        double e,jd;
        long b;
        double c = e = jd = b = 0;

        if (month < 3)

        {

            year--;

            month += 12;

        }

        ++month;

        c = 365.25 * (double) year;

        e = 30.6 * (double)month;

        jd = c + e + day - 694039.09;	//jd is total days elapsed

        jd /= 29.5305882;					//divide by the moon cycle

        b = (int) jd;						//int(jd) -> b, take integer part of jd

        jd -= b;							//subtract integer part to leave fractional part of original jd

        b = Math.round(jd * 8);				//scale fraction from 0-8 and round

        if (b >= 8 )

        {

            b = 0;//0 and 8 are the same so turn 8 into 0

        }


        switch ((int)b)

        {

            case 0:

                return "New Moon";



            case 1:

                return "Waxing Crescent Moon";



            case 2:

                return "Quarter Moon";



            case 3:

                return "Waxing Gibbous Moon";



            case 4:

                return "Full Moon";



            case 5:

                return "Waning Gibbous Moon";



            case 6:

                return "Last Quarter Moon";



            case 7:

                return "Waning Crescent Moon";



            default:

                return "Error";

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
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
}
