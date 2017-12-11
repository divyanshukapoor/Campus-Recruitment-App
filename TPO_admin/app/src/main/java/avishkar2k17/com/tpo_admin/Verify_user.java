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

import java.util.ArrayList;

public class Verify_user extends AppCompatActivity {
    ListView lv;
    EditText search_reg;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        lv=(ListView)findViewById(R.id.reg_list);
        search_reg=(EditText)findViewById(R.id.search);
        new Task().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String com= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(Verify_user.this,Userdetail.class);
                in.putExtra("reg",com);
                startActivity(in);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url=getApplicationContext().getString(R.string.httpUrl);
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {

            pd=new ProgressDialog(Verify_user.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();url+="/verify.php";
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            pd.dismiss();
            arrayAdapter = new ArrayAdapter<String>(Verify_user.this,android.R.layout.simple_list_item_1,result);
            for(int i=0;i<result.size();i++)
                Log.d("Satyam Kumar", String.valueOf(result.get(i)));
            lv.setAdapter(arrayAdapter);
            search_reg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Verify_user.this.arrayAdapter.getFilter().filter(charSequence);
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
