package com.example.shardo.projektzespolowy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class ProjektActivity extends AppCompatActivity implements Button.OnClickListener{

    TextView tw;
    Projekt projekt;
    Button zgloszenia;
    Button dodajZgloszenie;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projekt);
        tw = (TextView) findViewById(R.id.WybranyProjektTw);
        projekt = (Projekt) getIntent().getSerializableExtra("projekt");
        user = (User) getIntent().getSerializableExtra("user");
        tw.setText(projekt.getNazwa());

        zgloszenia = (Button) findViewById(R.id.ZgloszeniaBtn);
        dodajZgloszenie = (Button) findViewById(R.id.DodajZgloszenieBtn);
        zgloszenia.setOnClickListener(this);
        dodajZgloszenie.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.ZgloszeniaBtn:
                Intent i = new Intent(getApplicationContext(),ZgloszeniaActivity.class);
                Bundle b = new Bundle();
                i.putExtra("projekt", (Serializable) projekt);
                startActivity(i);
                break;
            case R.id.DodajZgloszenieBtn:
                Intent i2 = new Intent(getApplicationContext(), DodajZgloszenieActivity.class);
                Bundle b2 = new Bundle();
                i2.putExtra("projekt", (Serializable) projekt);
                i2.putExtra("user", (Serializable) user);
                startActivity(i2);
                break;
            default:
                break;
        }

    }
}
