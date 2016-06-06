package com.example.shardo.projektzespolowy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class WybierzProjekt extends AppCompatActivity implements Button.OnClickListener{

    private User user;
    private Button projektbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz_projekt);

        user = (User) getIntent().getSerializableExtra("user");
        projektbtn = (Button) findViewById(R.id.wybierzProjektBtn);
        projektbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getApplicationContext(),ProjektListActivity.class);
        Bundle b = new Bundle();
        i.putExtra("user", (Serializable) user);
        startActivity(i);
    }


}
