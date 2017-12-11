package avishkar2k17.com.tpo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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


public class Mail extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    String pass;
    String email,reg;
    private EditText editTextEmail;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private EditText Reg_num;
    Boolean b=false;
    //Send button
    private Button buttonSend;
    ProgressDialog prog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        editTextEmail = (EditText) findViewById(R.id.email_id);
        Reg_num = (EditText) findViewById(R.id.Reg_no);

        buttonSend = (Button) findViewById(R.id.Submit);
        buttonSend.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        email = editTextEmail.getText().toString().trim();
        reg = Reg_num.getText().toString().trim();
        if (email.matches(emailPattern)) {
            if (variable.getInternetState(getApplicationContext()))
                new Task().execute();
            else {
                Toast.makeText(getApplicationContext(), "check your internet connectivity", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(Mail.this,"Enter valid email id",Toast.LENGTH_SHORT).show();
        }
    }


    public class Task extends AsyncTask<Void,Void,String  > {
        String json_url;


        @Override
        protected void onPreExecute() {
            json_url = getApplicationContext().getString(R.string.httpUrl) + "/match.php";

        }

        @Override
        protected void onPostExecute(String aBoolean) {

            if (!aBoolean.equals("") && !aBoolean.equals("Login Failed"))
                b = true;

            if (b) {
                String subject = "TPO Password Recovery:";
                String Password = aBoolean;
                try {
                    Password=Encryption.decrypt(Password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String message = "Your Password is:" + Password;

                SendMail sm = new SendMail(Mail.this, email, subject, message);
                sm.execute();
                editTextEmail.setText("");
                Reg_num.setText("");
            } else {
                Toast.makeText(Mail.this, "Credential don't match!", Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected String doInBackground(Void... params) {
            ArrayList<String> arrayList = new ArrayList<>();
            try {
                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(reg, "UTF-8");
                arrayList = Helper.getStreamConnection(json_url, data, "Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return arrayList.get(0);
        }
    }
}