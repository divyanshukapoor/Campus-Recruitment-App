package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Close_reg extends AppCompatActivity {

    EditText comname;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_reg);
        comname=(EditText)findViewById(R.id.comname);
        close=(Button) findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=comname.getText().toString();
                new Task().execute(name);
            }
        });
    }
    public class Task extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String rurl = getApplicationContext().getString(R.string.httpUrl);

        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(Close_reg.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            rurl += "/closecompany.php";
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            Toast.makeText(Close_reg.this, s, Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {
            String a = params[0];
            String data = null;
            try {
                data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ArrayList<String > res=Make_connection.getStreamConnection(rurl,data);
            Log.d("abcd",res.get(0));
            return res.get(0);
        }
    }
}

