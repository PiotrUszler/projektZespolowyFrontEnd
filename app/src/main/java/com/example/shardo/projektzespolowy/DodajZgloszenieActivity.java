package com.example.shardo.projektzespolowy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shardo.projektzespolowy.ENUMS.EJednostkaCzasu;
import com.example.shardo.projektzespolowy.ENUMS.EStatusZgloszenia;
import com.example.shardo.projektzespolowy.ENUMS.ETypPriorytetu;
import com.example.shardo.projektzespolowy.ENUMS.EZgloszenieWewZew;
import com.fasterxml.jackson.annotation.JsonCreator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DodajZgloszenieActivity extends AppCompatActivity implements Button.OnClickListener{

    private Projekt projekt;
    private User user;
    private ListView priorytetLW;
    private Priorytet priorytet;
    private Button btn;
    private Zgloszenie zgloszenie;
    private int priorytetId;
    private int typId;
    private RadioGroup priorytetRadioGr;
    private RadioGroup typRadioGr;
    private String opisZgloszenia;
    private EditText opisET;
    private AlertDialog.Builder dlgAlert;
    private AlertDialog.Builder opisAlert;
    private AlertDialog.Builder OKAlert;
    private boolean OK_TYP = true;
    private boolean OK_PRIO = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_zgloszenie);

        projekt = (Projekt) getIntent().getSerializableExtra("projekt");
        user = (User) getIntent().getSerializableExtra("user");
        btn = (Button) findViewById(R.id.DodajZglBtn);
        btn.setOnClickListener(this);
        priorytetRadioGr = (RadioGroup) findViewById(R.id.priorytet_radioGr);
        typRadioGr = (RadioGroup) findViewById(R.id.typ_radioGr);
        opisET = (EditText) findViewById(R.id.editText);

        dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Proszę zanaczyć wymagane pola.");
        dlgAlert.setTitle("Brak wybrania priorytetu i/lub typu");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);

        opisAlert = new AlertDialog.Builder(this);
        opisAlert.setMessage("Proszę wprowadzić opis problemu.");
        opisAlert.setTitle("Brak opisu problemu");
        opisAlert.setPositiveButton("OK", null);

        OKAlert = new AlertDialog.Builder(this);
        OKAlert.setMessage("Zgłoszenie wysłano pomyślnie");
        OKAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        OKAlert.setCancelable(true);
    }

    @Override
    public void onClick(View v) {

        final String url = "http://10.0.2.2:8080/projektzespolowy/zgloszenia/saveZgloszenie";
        JSONObject json = new JSONObject();
        JSONObject jUser = new JSONObject();
        JSONObject jPriorytet = new JSONObject();
        JSONObject jProjekt = new JSONObject();
        JSONObject jZgloszenie = new JSONObject();
        HttpResponse response;
        HttpClient client = new DefaultHttpClient();

        priorytetId = priorytetRadioGr.getCheckedRadioButtonId();
        View radioButton = priorytetRadioGr.findViewById(priorytetId);
        int priorytetIdx = priorytetRadioGr.indexOfChild(radioButton);

        typId = typRadioGr.getCheckedRadioButtonId();
        View typRadioButton = typRadioGr.findViewById(typId);
        int typIdx = typRadioGr.indexOfChild(typRadioButton);

        if(priorytetIdx == -1){
            OK_PRIO = false;
        }else{
            OK_PRIO = true;
        }

        String typStr = "";
        switch(typIdx){
            case 0:
                typStr = "BLAD";
                OK_TYP = true;
                break;
            case 1:
                typStr = "SUGESTIA";
                OK_TYP = true;
                break;
            case 2:
                typStr = "INNE";
                OK_TYP = true;
                break;
            default:
                OK_TYP = false;
                break;
        }

        opisZgloszenia = opisET.getText().toString();

        if(OK_TYP == true && OK_PRIO == true && opisZgloszenia.length() > 0) {
            try {
                HttpPost post = new HttpPost(url);
                jPriorytet.put("id", priorytetIdx + 1);
                json.put("priorytet", jPriorytet);
                jProjekt.put("id", projekt.getId());
                json.put("projekt", jProjekt);
                jUser.put("id", user.getId());
                json.put("user", jUser);
                jZgloszenie.put("opis", opisZgloszenia);
                jZgloszenie.put("id", 0);
                jZgloszenie.put("status", "OCZEKUJE");
                jZgloszenie.put("typZgloszenia", typStr);
                jZgloszenie.put("zgloszenieWewZew", "ZEWNETRZNE");
                json.put("zgloszenie", jZgloszenie);
                StringEntity se = new StringEntity(json.toString());
                se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                post.setEntity(se);
                response = client.execute(post);
                if (response != null) {
                    InputStream in = response.getEntity().getContent(); //Get the data in the entity
                    Log.println(1, "tag", in.toString());
                }
                OKAlert.create().show();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(OK_PRIO == false || OK_TYP == false){
            dlgAlert.create().show();
        }
        else if(opisZgloszenia.length() == 0){
            opisAlert.create().show();
        }

    }
}
