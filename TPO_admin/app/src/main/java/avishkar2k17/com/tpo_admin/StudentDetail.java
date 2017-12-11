package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class StudentDetail extends AppCompatActivity {
    String reg;
    TextView name,regno,branch,per10,per12,cpi;
    Button v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        name=(TextView)findViewById(R.id.name);
        regno=(TextView)findViewById(R.id.registration);
        branch=(TextView)findViewById(R.id.branch);
        cpi=(TextView)findViewById(R.id.cpi);
        per10=(TextView)findViewById(R.id.per10);
        per12=(TextView)findViewById(R.id.per12);
        Bundle b=getIntent().getExtras();
        reg=b.getString("reg");
        Task task=new Task();
        task.execute(reg);
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url = getApplicationContext().getString(R.string.httpUrl);
        String token;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(StudentDetail.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            pd.dismiss();
            Log.d("dkkkk",result.get(0).toString());
            Log.d("dkkkk",result.get(1).toString());
            Log.d("dkkkk",result.get(2).toString());
            Log.d("dkkkk",result.get(3).toString());
            Log.d("dkkkk",result.get(4).toString());
            Log.d("dkkkk",result.get(5).toString());
            name.setText(result.get(0).toString());
            regno.setText(result.get(1).toString());
            cpi.setText(result.get(2).toString());
            branch.setText(result.get(3).toString());
            per10.setText(result.get(4).toString());
            per12.setText(result.get(5).toString());
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String data = null;
            token = strings[0];
            ArrayList<String> response = null;
            try {
                data = URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(reg, "UTF-8");
                url += "/userdetail.php";
                response = Make_connection.getStreamConnection(url,data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return response;
        }
    }
}
