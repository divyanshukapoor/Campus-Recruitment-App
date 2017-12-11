package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ReduceCredits extends AppCompatActivity {

    EditText regno,credits;
    String regisno,cre;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduce_credits);
        regno=(EditText)findViewById(R.id.regno);
        credits=(EditText)findViewById(R.id.cred);
        submit=(Button)findViewById(R.id.submit);
        credits.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regisno= String.valueOf(regno.getText());
                cre= String.valueOf(credits.getText());
                Task task=new Task();
                task.execute(regisno,cre);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url = getApplicationContext().getString(R.string.httpUrl);
        String token;
        ProgressDialog pd;
        @Override
        protected ArrayList doInBackground(String... strings) {
            String regno=strings[0];
            String data;
            String cre=strings[1];
            ArrayList<String> response=new ArrayList<>();
            try {
                data = URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(regno, "UTF-8")+ "&" +
                        URLEncoder.encode("cre", "UTF-8") + "=" + URLEncoder.encode(cre, "UTF-8");
                response = Make_connection.getStreamConnection(url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute()
        {
            pd=new ProgressDialog(ReduceCredits.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            url+="/reducecredits.php";
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            pd.dismiss();
            Toast.makeText(ReduceCredits.this,arrayList.get(0).toString(),Toast.LENGTH_LONG).show();
        }
    }
}
