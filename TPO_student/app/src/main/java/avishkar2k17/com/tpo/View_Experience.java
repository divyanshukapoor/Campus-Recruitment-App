package avishkar2k17.com.tpo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class View_Experience extends AppCompatActivity {
    private String regno;
    private ListView lv;
    private ArrayAdapter<String> arrayAdapter;
    private EditText search_company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_experience);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        lv=(ListView)findViewById(R.id.comlist);
        search_company=(EditText)findViewById(R.id.search_comp);
        if(variable.getInternetState(getApplicationContext())) {
            Task backgroundTask = new Task();
            backgroundTask.execute();
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String com= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(View_Experience.this,ViewExperienceByReg.class);
                in.putExtra("com",com);
                startActivity(in);
            }
        });
    }

    public class Task extends AsyncTask<Void,Void,ArrayList<String> >
    {
        String json_url=getApplicationContext().getString(R.string.httpUrl)+"/view.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {

           arrayAdapter = new ArrayAdapter<String>(View_Experience.this,android.R.layout.simple_dropdown_item_1line,result);
            for(int i=0;i<result.size();i++)
                Log.d("Satyam Kumar", result.get(i));
            lv.setAdapter(arrayAdapter);
            search_company.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("xview",s.toString());
                View_Experience.this.arrayAdapter.getFilter().filter(s);
                    arrayAdapter.notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arrayList;
            arrayList=Helper.getInputStreamConnection(json_url,"Failed");
            return arrayList;
        }
    }
}
