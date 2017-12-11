package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class Login extends AppCompatActivity {
    EditText adid, passwd;
    String id, password;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        adid = (EditText) findViewById(R.id.Loginid);
        passwd = (EditText) findViewById(R.id.LoginPass);
        b = (Button) findViewById(R.id.login);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = adid.getText().toString();
                password =passwd.getText().toString();
                Task bgtask = new Task();
                bgtask.execute(id, password);
            }
        });
    }
    public class Task extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String rurl = getApplicationContext().getString(R.string.httpUrl);

        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(Login.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            rurl += "/login.php";
            Log.d("abcd", id);
            Log.d("abcd", password);
        }

        @Override
        protected void onPostExecute(String s) {
            pd.dismiss();
            Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
            if (s.equals("Login Success...")) {
                Intent in = new Intent(Login.this, Select.class);
                startActivity(in);
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String a = params[0];
            String b = params[1];
            String data = null;
            try {
                data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("adpassword", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ArrayList<String > res=new ArrayList<>();
            res=Make_connection.getStreamConnection(rurl,data);
            Log.d("abcd",res.get(0));
            return res.get(0);
        }
    }
}
