package avishkar2k17.com.tpo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

public class Company_detail extends AppCompatActivity {
    TextView stipend,cpi,branch,designation,location,time,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_details);
        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        name=(TextView)findViewById(R.id.com_name);
        stipend=(TextView)findViewById(R.id.stipend);
        cpi=(TextView)findViewById(R.id.cpi);
        branch=(TextView)findViewById(R.id.branch);
        designation=(TextView)findViewById(R.id.designation);
        location=(TextView)findViewById(R.id.location);
        time=(TextView)findViewById(R.id.time);
        Bundle b = getIntent().getExtras();
        String com = b.getString("com");
        name.setText(com);
        if(variable.getInternetState(getApplicationContext())) {
            Bgtask task = new Bgtask();
            task.execute(com);
        }
        else
        {
            //Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public class Bgtask extends AsyncTask<String, String, ArrayList<String>> {
        String uurl=getApplicationContext().getString(R.string.httpUrl)+"/company.php";

        @Override
        protected void onPostExecute(ArrayList<String> arr) {
            int i=0;
            String item=arr.get(i);
            i++;
            branch.setText(item);
            item=arr.get(i);
            i++;
            stipend.setText(item);
            item=arr.get(i);
            i++;
            cpi.setText(item);
            item=arr.get(i);
            i++;
            designation.setText(item);
            item=arr.get(i);
            i++;
            location.setText(item);
            item=arr.get(i);
            i++;
            time.setText(item);
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            String name=params[0];
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("com", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                arrayList=Helper.getStreamConnection(uurl,data,"Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return  arrayList;
        }

    }
}
