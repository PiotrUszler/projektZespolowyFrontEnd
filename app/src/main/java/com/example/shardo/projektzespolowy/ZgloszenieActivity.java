package com.example.shardo.projektzespolowy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ZgloszenieActivity extends AppCompatActivity {

    private TextView opis;
    private TextView priorytet;
    private TextView status;
    private TextView typ;
    private Zgloszenie zgloszenie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zgloszenie);

        zgloszenie = (Zgloszenie) getIntent().getSerializableExtra("zgloszenie");
        opis = (TextView) findViewById(R.id.opisTW);
        priorytet = (TextView) findViewById(R.id.priorytetTW);
        status = (TextView) findViewById(R.id.statusTW);
        typ = (TextView) findViewById(R.id.typZgloszeniaTW);

        opis.setText(zgloszenie.getOpis());
        priorytet.setText(zgloszenie.getPriorytet().getTyp().name());
        status.setText(zgloszenie.getStatus().name());
        typ.setText(zgloszenie.getTypZgloszenia().name());
    }
}
