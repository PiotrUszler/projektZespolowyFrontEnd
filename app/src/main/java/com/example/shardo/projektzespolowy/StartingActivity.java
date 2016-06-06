package com.example.shardo.projektzespolowy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

public class StartingActivity extends AppCompatActivity implements Button.OnClickListener {

    private Button btnLogin;
    private EditText email;
    private EditText password;
    public AlertDialog.Builder dlgAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        btnLogin = (Button) findViewById(R.id.logInBtn);
        btnLogin.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        email = (EditText) findViewById(R.id.emailText);
        password = (EditText) findViewById(R.id.passText);
        String emailTxt = email.getText().toString();
        String passTxt = password.getText().toString();
        String[] params = {emailTxt,passTxt};
        dlgAlert = new AlertDialog.Builder(this);
        dlgAlert.setMessage("Błędny login lub hasło.");
        dlgAlert.setTitle("Błąd logowania");
        dlgAlert.setPositiveButton("OK", null);
        dlgAlert.setCancelable(true);
        new HttpRequestTask().execute(params);
    }


    private class HttpRequestTask extends AsyncTask<String, Void, User>{
        @Override
        protected User doInBackground(String... params) {
            try {
                final String url = "http://10.0.2.2:8080/projektzespolowy/users/login/"+params[0]+","+params[1];
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                User user = restTemplate.getForObject(url, User.class);
                return user;
            } catch (Exception e) {
                Log.e("StartingActivity", e.getMessage(), e);
                dlgAlert.create().show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            if(!user.equals(null)){
                Intent i = new Intent(getApplicationContext(),WybierzProjekt.class);
                Bundle b = new Bundle();
                i.putExtra("user", (Serializable) user);
                startActivity(i);
            }
            else{
                dlgAlert.create().show();
            }
        }


    }
}
