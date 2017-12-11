package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Branch_select extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> arrayAdapter;
    EditText search_branch;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_select);
        lv=(ListView)findViewById(R.id.branch_list);
        search_branch=(EditText)findViewById(R.id.search2);
        new Task().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String com= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(Branch_select.this,Details.class);
                in.putExtra("branch",com);
                startActivity(in);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url=getApplicationContext().getString(R.string.httpUrl);
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {

            pd=new ProgressDialog(Branch_select.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            url+="/branch_select.php";
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            pd.dismiss();
            arrayAdapter = new ArrayAdapter<String>(Branch_select.this,R.layout.layout_list_view,R.id.reg_number,result);
            for(int i=0;i<result.size();i++)
                Log.d("Satyam Kumar", String.valueOf(result.get(i)));
            lv.setAdapter(arrayAdapter);
            search_branch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Branch_select.this.arrayAdapter.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> response=Make_connection.getInputStreamConnection(url);
            return response;
        }
    }
}
