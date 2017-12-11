package avishkar2k17.com.tpo;

import android.app.Fragment;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Password_fragment extends AppCompatActivity implements View.OnClickListener {
    EditText cp, np, cnp;
    Button submit;
    String regno, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password);
        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Bundle b = getIntent().getExtras();
        regno = b.getString("regno");
        passwd = b.getString("pass");
        Log.d("xpassword",regno);
        Log.d("xpassword",passwd);

        cp = (EditText) findViewById(R.id.passwd_cn);
        np = (EditText) findViewById(R.id.passwd_nw);
        cnp = (EditText) findViewById(R.id.passwd_nwc);
        submit = (Button) findViewById(R.id.button3);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (variable.getInternetState(getApplicationContext())) {
            String currpass, newpass, rnewpass;
            currpass = cp.getText().toString();
            newpass = np.getText().toString();
            rnewpass = cnp.getText().toString();
            if (!newpass.equals(rnewpass)) {
                Toast.makeText(this, "Password doesn't match", Toast.LENGTH_SHORT).show();
            } else if (!currpass.equals(passwd)) {
                Toast.makeText(this, "Current password doesn't match", Toast.LENGTH_SHORT).show();
            } else {
                String t = "password";
                BackgroundTask backgroundTask = new BackgroundTask(Password_fragment.this);
                backgroundTask.execute(t, regno, newpass);
            }
        }
        else
            Toast.makeText(Password_fragment.this,"check your internet connectivity",Toast.LENGTH_SHORT).show();

    }
}
