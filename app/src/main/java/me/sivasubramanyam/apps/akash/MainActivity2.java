package me.sivasubramanyam.apps.akash;

import android.app.NotificationManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MainActivity2 extends ActionBarActivity implements LocationListener, TextToSpeech.OnInitListener{
    TextView tv;
    TextToSpeech tts ;

    public String readJSONFeed(String URL) {
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return stringBuilder.toString();
    }

    @Override
    public void onLocationChanged(Location location) {

    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onInit(int i) {

    }

    public class ReadWeatherJSONFeedTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            return readJSONFeed(urls[0]);
        }

        protected void onPostExecute(String result) {
            try {
                String value = getIntent().getStringExtra("TIME");

                JSONObject jsonObject = new JSONObject(result);
                JSONArray coords = (JSONArray) jsonObject.get("weather");
                int leng = coords.length();

                String res;
                JSONObject pass = (JSONObject) coords.get(0);
                res = pass.getString("main").toString();
                //Toast.makeText(MainActivity2.this, res+ "fadfaef", Toast.LENGTH_SHORT).show();
                tv = (TextView) findViewById(R.id.textView);
                tv.setText(value);
                TextView tv2 = (TextView) findViewById(R.id.textView2);
                tv2.setText(" Weather : " + res);
                TextView tv4 = (TextView) findViewById(R.id.textView4);
                tv4.setText("");


                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity2.this);
                mBuilder.setSmallIcon(R.drawable.giphy);
                mBuilder.setContentTitle("ISS is on the way");
                mBuilder.setContentText("Next pass at " + value);

                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// notificationID allows you to update the notification later on.
                int notificationID;
                mNotificationManager.notify(5, mBuilder.build());



            } catch (Exception e) {
                //Toast.makeText(MainActivity2.this, e.toString(), Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) this);
        Criteria c = new Criteria();
        String s = locationManager.getBestProvider(c,false);
        Location l  = locationManager.getLastKnownLocation(s);
        String lat = Double.toString(l.getLatitude());
        String lon = Double.toString(l.getLongitude());
        TextView t4 = (TextView) findViewById(R.id.textView4);
        t4.setText("Please wait");
        new ReadWeatherJSONFeedTask().execute(
                "http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon);
        Button b = (Button) findViewById(R.id.button);
       b.setVisibility(View.INVISIBLE);

    }
}