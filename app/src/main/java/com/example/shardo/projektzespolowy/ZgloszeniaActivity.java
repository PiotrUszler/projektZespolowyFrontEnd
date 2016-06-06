package com.example.shardo.projektzespolowy;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;
import java.util.ArrayList;

public class ZgloszeniaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView lw;
    private Zgloszenie[] zgloszenia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgloszenia);
        lw = (ListView) findViewById(R.id.zgloszeniaLW);
        Projekt projekt = (Projekt) getIntent().getSerializableExtra("projekt");

        new HttpRequestTask().execute(projekt);
        lw.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(),ZgloszenieActivity.class);
        Bundle b = new Bundle();
        i.putExtra("zgloszenie", (Serializable) zgloszenia[position]);
        startActivity(i);
    }

    private class HttpRequestTask extends AsyncTask<Projekt, Void, Zgloszenie[]> {
        @Override
        protected Zgloszenie[] doInBackground(Projekt... params) {
            try {
                final String url = "http://10.0.2.2:8080/projektzespolowy/zgloszenia/getByProjektId/"+params[0].getId();//zminic
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                zgloszenia = restTemplate.getForObject(url, Zgloszenie[].class);
                return zgloszenia;
            } catch (Exception e) {
                Log.e("Zgloszenie Activity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Zgloszenie[] zgloszenia) {
            ArrayAdapter<String> adapter ;

            ArrayList<String> zgloszeniaNazwy = new ArrayList<>();
            for (int i = 0; i < zgloszenia.length; i++){
                zgloszeniaNazwy.add(zgloszenia[i].getOpis());
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row,zgloszeniaNazwy);
            lw.setAdapter(adapter);
        }
}}
