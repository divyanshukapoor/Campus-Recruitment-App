package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Update_Intern extends AppCompatActivity {
     EditText regno,comname;
    String regisno,companyname;
    TextView tv,tv2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduce_credits);
        regno=(EditText)findViewById(R.id.regno);
        comname=(EditText)findViewById(R.id.cred);
        tv=(TextView)findViewById(R.id.tv1);
        tv2=(TextView)findViewById(R.id.tv);
        tv.setText("Enter Company Name:-");
        tv2.setText("Update Intern");
        comname.setInputType(InputType.TYPE_CLASS_TEXT);
        b=(Button)findViewById(R.id.submit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regisno= String.valueOf(regno.getText());
                companyname= String.valueOf(comname.getText());
                Task bgtask = new Task();
                bgtask.execute(regisno,companyname);
            }
        });
    }
    public class Task extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String rurl = getApplicationContext().getString(R.string.httpUrl);

        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(Update_Intern.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            rurl += "/update_Intern.php";
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            Toast.makeText(Update_Intern.this, s, Toast.LENGTH_LONG).show();
            if(s.equals("Successfully submitted..."))
            {
                new Statistics_Updater(Update_Intern.this).execute(regisno);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String a = params[0];
            String b = params[1];
            String data = null;
            try {
                data = URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("com", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ArrayList<String > res=Make_connection.getStreamConnection(rurl,data);
            Log.d("abcd",res.get(0));
            return res.get(0);
        }
    }
}
