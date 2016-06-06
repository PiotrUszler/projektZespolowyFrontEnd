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

public class ProjektListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lw;
    private Projekt[] projekty;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projekt_list);
        lw = (ListView) findViewById(R.id.listView);
        user = (User) getIntent().getSerializableExtra("user");

        new HttpRequestTask().execute(user);

        lw.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(getApplicationContext(),ProjektActivity.class);
        Bundle b = new Bundle();
        i.putExtra("projekt", (Serializable) projekty[position]);
        i.putExtra("user", (Serializable) user);
        startActivity(i);
    }

    private class HttpRequestTask extends AsyncTask<User, Void, Projekt[]> {
        @Override
        protected Projekt[] doInBackground(User... params) {
            try {
                final String url = "http://10.0.2.2:8080/projektzespolowy/projekty/getByUserId/"+params[0].getId();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                projekty = restTemplate.getForObject(url, Projekt[].class);
                return projekty;
            } catch (Exception e) {
                Log.e("Projekt List", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Projekt[] projekty) {
            ArrayAdapter<String> adapter ;

            ArrayList<String> projektyNazwy = new ArrayList<>();
            for (int i = 0; i < projekty.length; i++){
                projektyNazwy.add(projekty[i].getNazwa());
            }
            adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row,projektyNazwy);
            lw.setAdapter(adapter);
        }
}
}
