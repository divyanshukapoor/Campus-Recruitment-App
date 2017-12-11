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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class ViewExperienceByReg extends AppCompatActivity {
  private  String regno;
    private ListView lv;
    private String comname;
    private EditText search_by_reg;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_experience_by_reg);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Bundle b=getIntent().getExtras();
        comname=b.getString("com");
        lv=(ListView)findViewById(R.id.comlist1);
        search_by_reg=(EditText)findViewById(R.id.search);
        if(variable.getInternetState(getApplicationContext())) {
            Task backgroundTask = new Task();
            backgroundTask.execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String com= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(ViewExperienceByReg.this,View_Detail.class);
                in.putExtra("com",comname);
                in.putExtra("reg",com);
                startActivity(in);
            }
        });
    }

    public class Task extends AsyncTask<Void,Void,ArrayList<String> > {
        String json_url = getApplicationContext().getString(R.string.httpUrl) + "/viewReg.php";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            arrayAdapter = new ArrayAdapter<String>(ViewExperienceByReg.this, android.R.layout.simple_dropdown_item_1line, result);
            for (int i = 0; i < result.size(); i++)
                Log.d("Satyam Kumar", result.get(i));
            lv.setAdapter(arrayAdapter);

        search_by_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ViewExperienceByReg.this.arrayAdapter.getFilter().filter((s));
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("company", "UTF-8") + "=" + URLEncoder.encode(comname, "UTF-8");
            arrayList=Helper.getStreamConnection(json_url,data,"Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
