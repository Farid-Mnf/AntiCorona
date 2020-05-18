package com.example.android.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.NumberFormat;

public class CasesActivity extends AppCompatActivity {
    private String country, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        country = intent.getStringExtra("country");
        country.toUpperCase();
        data = intent.getStringExtra("data");
        setCountryData();
    }

    public void setCountryData() {
        TextView countryName = findViewById(R.id.countryName);
        TextView cases = findViewById(R.id.cases);
        TextView recovered = findViewById(R.id.recovered);
        TextView deaths = findViewById(R.id.deaths);

        countryName.setText(country.toUpperCase());
        String[] result = parseJsonData(data);
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);

        if(!result[0].equals("null")){
            result[0] = myFormat.format(Integer.parseInt(result[0]));
        }else{
            result[0] = "N/A";
        }
        if(!result[1].equals("null")){
            result[1] = myFormat.format(Integer.parseInt(result[1]));
        }else{
            result[1] = "N/A";
        }
        if(!result[2].equals("null")){
            result[2] = myFormat.format(Integer.parseInt(result[2]));
        }else{
            result[2] = "N/A";
        }
        cases.setText(cases.getText() + result[0]);
        recovered.setText(recovered.getText() + result[2]);
        deaths.setText(deaths.getText() + result[1]);
    }

    public String[] parseJsonData(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject report = jsonObject.getJSONObject("report");
            String cases = report.getString("cases");
            String deaths = report.getString("deaths");
            String recovered = report.getString("recovered");
            return new String[]{cases, deaths, recovered};
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}