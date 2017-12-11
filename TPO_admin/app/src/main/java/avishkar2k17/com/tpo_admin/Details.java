package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Details extends AppCompatActivity {
    String comname;
    EditText search_reg;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle b=getIntent().getExtras();
        comname=b.getString("branch");
        lv=(ListView)findViewById(R.id.reg_list);
        search_reg=(EditText)findViewById(R.id.search);
        new Task().execute(comname);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String reg= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(Details.this,StudentDetail.class);
                in.putExtra("reg",reg);
                startActivity(in);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url = getApplicationContext().getString(R.string.httpUrl);
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(Details.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            url += "/reglist.php";
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            pd.dismiss();
            arrayAdapter = new ArrayAdapter<String>(Details.this, R.layout.layout_list_view, R.id.reg_number, result);
            for (int i = 0; i < result.size(); i++)
                Log.d("Satyam Kumar", String.valueOf(result.get(i)));
            lv.setAdapter(arrayAdapter);
            search_reg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Details.this.arrayAdapter.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            String comname = strings[0];
            String data = null;
            try {
                data = URLEncoder.encode("comname", "UTF-8") + "=" + URLEncoder.encode(comname, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            ArrayList<String> response = Make_connection.getStreamConnection(url, data);
            return response;
        }
    }
}
