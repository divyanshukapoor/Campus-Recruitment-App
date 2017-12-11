package avishkar2k17.com.tpo;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Add_Interview_Experience extends AppCompatActivity{
    String regno,data,name;
    EditText comname,et;
    Button add;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addinterview_experience);
        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Bundle b=getIntent().getExtras();
        regno= (String) b.get("regno");
        comname=(EditText)findViewById(R.id.name);
        et=(EditText)findViewById(R.id.ivexperience);
        add=(Button)findViewById(R.id.addButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getApplicationContext())) {
                    if (comname.getText().toString().equals(""))
                        Toast.makeText(Add_Interview_Experience.this, "please fill company name!", Toast.LENGTH_LONG).show();
                    else if (et.getText().toString().equals(""))
                        Toast.makeText(Add_Interview_Experience.this, "please add experience!", Toast.LENGTH_LONG).show();
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(Add_Interview_Experience.this);
                        builder.setMessage("Press yes to submit your experience")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        new Task().execute();
                                        finish();
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                else
                    Toast.makeText(Add_Interview_Experience.this,"check your internet connectivity",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public class Task extends AsyncTask<String,String,String>
    {
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url=getApplicationContext().getString(R.string.httpUrl)+"/addexperience.php";
            data=et.getText().toString();
            String nStr="";
            for(int i=0;i<data.length();i++)
            {
                if(data.charAt(i) == '\n')
                {
                   nStr+='#';
                }
                nStr+=data.charAt(i);
            }
            data=nStr;
            Log.d("database",data);
            name=comname.getText().toString();
        }

        @Override
        protected String doInBackground(String... params) {
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data1 = URLEncoder.encode("d", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8") + "&" +
                        URLEncoder.encode("r", "UTF-8") + "=" + URLEncoder.encode(regno, "UTF-8") + "&" +
                        URLEncoder.encode("n", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");

                arrayList=Helper.getStreamConnection(json_url,data1,"ADD UNSUCCESSFUL");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return arrayList.get(0);
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Add_Interview_Experience.this,s,Toast.LENGTH_LONG).show();
        }
    }
}
