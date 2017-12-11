package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Add_company extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7;
    String name,stipend,cpi,branch,designation,location,deadline;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        e1=(EditText)findViewById(R.id.company_name);
        e2=(EditText)findViewById(R.id.stipend);
        e3=(EditText)findViewById(R.id.cpi);
        e4=(EditText)findViewById(R.id.branch);
        e5=(EditText)findViewById(R.id.designation);
        e6=(EditText)findViewById(R.id.location);
        e7=(EditText)findViewById(R.id.time);
        b=(Button)findViewById(R.id.regButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();
                stipend=e2.getText().toString();
                cpi=e3.getText().toString();
                branch=e4.getText().toString();
                designation=e5.getText().toString();
                location=e6.getText().toString();
                deadline=e7.getText().toString();
                Task task=new Task();
                task.execute(name,stipend,cpi,branch,designation,location,deadline);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url = getApplicationContext().getString(R.string.httpUrl);
        String token;
        ProgressDialog pd;
        @Override
        protected ArrayList doInBackground(String... strings) {
            String name=strings[0];
            String data;
            String stipend=strings[1];
            String cpi=strings[2];
            String branch=strings[3];
            String designation=strings[4];
            String location=strings[5];
            String deadline=strings[6];
            ArrayList<String> response=new ArrayList<>();
            try {
                data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")+ "&" +
                        URLEncoder.encode("stipend", "UTF-8") + "=" + URLEncoder.encode(stipend, "UTF-8")+ "&" +
                        URLEncoder.encode("cpi", "UTF-8") + "=" + URLEncoder.encode(cpi, "UTF-8")+ "&" +
                        URLEncoder.encode("branch", "UTF-8") + "=" + URLEncoder.encode(branch, "UTF-8")+ "&" +
                        URLEncoder.encode("designation", "UTF-8") + "=" + URLEncoder.encode(designation, "UTF-8")+ "&" +
                        URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8")+ "&" +
                        URLEncoder.encode("deadline", "UTF-8") + "=" + URLEncoder.encode(deadline, "UTF-8");
                response = Make_connection.getStreamConnection(url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPreExecute()
        {
            pd=new ProgressDialog(Add_company.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            url+="/addcompany.php";
        }

        @Override
        protected void onPostExecute(ArrayList arrayList) {
            pd.dismiss();
            Toast.makeText(Add_company.this,arrayList.get(0).toString(),Toast.LENGTH_LONG).show();
        }
    }
}
