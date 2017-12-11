package avishkar2k17.com.tpo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class login_intern extends AppCompatActivity implements View.OnClickListener {
   TextView signup;
    Button Login;
    EditText e1,e2;
    String a,b;
    TextView tv;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_intern);
        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SharedPreferences sharedPrefs = getSharedPreferences("ADefault", MODE_PRIVATE);
        SharedPreferences.Editor ed=sharedPrefs.edit();
        if(sharedPrefs.contains("registration") && sharedPrefs.contains("password")) {
            String t = "login";
            String a=sharedPrefs.getString("registration",null);
            String b=sharedPrefs.getString("password",null);

            if(b==null)
                Log.d("iNin","dfsd");
            //new TaskNotify().execute();
            BackgroundTask backgroundTask = new BackgroundTask(login_intern.this);
            backgroundTask.execute(t,a, b);
        }

        e1=(EditText)findViewById(R.id.LoginReg);
        e2=(EditText)findViewById(R.id.LoginPass);
        signup=(TextView)findViewById(R.id.request);
        signup.setOnClickListener(this);
        Login=(Button)findViewById(R.id.login);
        Login.setOnClickListener(this);
        tv=(TextView)findViewById(R.id.forget_passwd);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==signup) {
            Intent sign = new Intent(login_intern.this, signup_intern.class);
            startActivity(sign);
        }
        else if(view==Login)
        {

            if(variable.getInternetState(getApplicationContext())) {
                a = e1.getText().toString();
                b = e2.getText().toString();
                e1.setText("");
                e2.setText("");
                variable obj = variable.getInstance();
                obj.reg = a;
                obj.psd=b;
                String t = "login";
                //new TaskNotify().execute();
                BackgroundTask backgroundTask = new BackgroundTask(login_intern.this);
                backgroundTask.execute(t, a, b);
            }
            else
            {
                Toast.makeText(login_intern.this,"Check your internet connectivity",Toast.LENGTH_SHORT).show();
            }
        }
        else if(view==tv){
            startActivity(new Intent(login_intern.this,Mail.class));
        }
    }

}
