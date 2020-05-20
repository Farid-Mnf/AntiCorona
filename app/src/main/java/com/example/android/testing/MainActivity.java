package com.example.android.testing;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final String[] COUNTRIES = {"afghanistan", "albania", "algeria", "andorra", "angola", "anguilla", "antigua-and-barbuda", "argentina", "armenia", "aruba", "australia", "austria", "azerbaijan", "bahamas", "bahrain", "bangladesh", "barbados", "belarus", "belgium", "belize", "benin", "bermuda", "bhutan", "bolivia", "bosnia-and-herzegovina", "botswana", "brazil", "british-virgin-islands", "brunei-darussalam", "bulgaria", "burkina-faso", "burundi", "cabo-verde", "cambodia", "cameroon", "canada", "caribbean-netherlands", "cayman-islands", "central-african-republic", "chad", "channel-islands", "chile", "china", "china-hong-kong-sar", "china-macao-sar", "colombia", "congo", "costa-rica", "cote-d-ivoire", "croatia", "cuba", "curacao", "cyprus", "czech-republic", "democratic-republic-of-the-congo", "denmark", "djibouti", "dominica", "dominican-republic", "ecuador", "egypt", "el-salvador", "equatorial-guinea", "eritrea", "estonia", "ethiopia", "faeroe-islands", "falkland-islands-malvinas", "fiji", "finland", "france", "french-guiana", "french-polynesia", "gabon", "gambia", "georgia", "germany", "ghana", "gibraltar", "greece", "greenland", "grenada", "guadeloupe", "guatemala", "guinea", "guinea-bissau", "guyana", "haiti", "holy-see", "honduras", "hungary", "iceland", "india", "indonesia", "iran", "iraq", "ireland", "isle-of-man", "israel", "italy", "jamaica", "japan", "jordan", "kazakhstan", "kenya", "kuwait", "kyrgyzstan", "laos", "latvia", "lebanon", "liberia", "libya", "liechtenstein", "lithuania", "luxembourg", "macedonia", "madagascar", "malawi", "malaysia", "maldives", "mali", "malta", "martinique", "mauritania", "mauritius", "mayotte", "mexico", "moldova", "monaco", "mongolia", "montenegro", "montserrat", "morocco", "mozambique", "myanmar", "namibia", "nepal", "netherlands", "new-caledonia", "new-zealand", "nicaragua", "niger", "nigeria", "norway", "oman", "pakistan", "panama", "papua-new-guinea", "paraguay", "peru", "philippines", "poland", "portugal", "qatar", "reunion", "romania", "russia", "rwanda", "saint-barthelemy", "saint-kitts-and-nevis", "saint-lucia", "saint-martin", "saint-vincent-and-the-grenadines", "san-marino", "saudi-arabia", "senegal", "serbia", "seychelles", "sierra-leone", "singapore", "sint-maarten", "slovakia", "slovenia", "somalia", "south-africa", "south-korea", "spain", "sri-lanka", "state-of-palestine", "sudan", "suriname", "swaziland", "sweden", "switzerland", "syria", "taiwan", "tanzania", "thailand", "timor-leste", "togo", "trinidad-and-tobago", "tunisia", "turkey", "turks-and-caicos-islands", "uganda", "uk", "ukraine", "united-arab-emirates", "uruguay", "us", "uzbekistan", "venezuela", "viet-nam", "zambia", "zimbabw"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.country);
        textView.setThreshold(1);
        textView.setDropDownBackgroundResource(R.color.bluelight);
        textView.setAdapter(adapter);
    }

    public String getApiData(String country) {

        try {
            URL url = new URL("https://covid19api.io/api/v1/ReportsByCountries/" + country);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            StringBuilder builder = new StringBuilder();
            while (line != null) {
                builder.append(line);
                line = bufferedReader.readLine();
            }
            return builder.toString();
        } catch (Exception e) {
            Log.e("url", "url error", e);
        }
        return null;
    }

    public String getCountry() {
        EditText editText = (EditText) findViewById(R.id.country);
        String country = editText.getText().toString().trim().toLowerCase();
        return country;
    }


    public void setViewData(View view) {
        boolean found = false;
        // check first if country exists
        String tempCountry = getCountry();
        int len = COUNTRIES.length;
        for(int i=0;i<len;++i) {
            if (tempCountry.equals(COUNTRIES[i])) {
                found = true;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        String country = getCountry();
                        String data = getApiData(country);
                        if(data != null){
                            Intent intent = new Intent(getApplicationContext(), CasesActivity.class);
                            intent.putExtra("country", country);
                            intent.putExtra("data", data);
                            startActivityForResult(intent,0);
                        }
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
                break;
            }
        }
        if(!found){
            Toast.makeText(this,"Country not found!",Toast.LENGTH_LONG).show();
        }
    }
}
