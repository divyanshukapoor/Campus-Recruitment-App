package avishkar2k17.com.tpo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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

public class View_Detail extends AppCompatActivity {

   // ListView lv;
    TextView tv1;
    String comname;
    String reg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail);
        tv1=(TextView)findViewById(R.id.tvid);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // lv = (ListView) findViewById(R.id.datalist);
        Bundle b=getIntent().getExtras();
        comname=b.getString("com");
        reg=b.getString("reg");
        TextView tv=(TextView)findViewById(R.id.companyname);
        tv.setText(comname);
        if(variable.getInternetState(getApplicationContext())) {
            Task backgroundTask = new Task();
            backgroundTask.execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }
    }



    public class Task extends AsyncTask<Void,Void,ArrayList<String> > {
        String json_url = getApplicationContext().getString(R.string.httpUrl) + "/viewExperience.php";
        String comName;

        @Override
        protected void onPreExecute() {
            comName = comname;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            String ans = "";
            for (int i = 0; i < result.size(); i++) {
                String str = result.get(i);
                if (str.charAt(str.length() - 1) == '#') {
                    for (int j = 0; j < str.length() - 1; j++)
                        ans += str.charAt(j);
                    ans += "\n";
                } else
                    ans += result.get(i);
            }
            tv1.setText(ans);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(reg, "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(comName, "UTF-8");
               arrayList=Helper.getStreamConnection(json_url,data,"Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
    }
}
