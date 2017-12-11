package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by HP on 10/12/2017.
 */

public class Comp_Intern extends AppCompatActivity {

    Button close;
    TextView tv;
    EditText search_reg;
    ListView lv;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        tv=(TextView)findViewById(R.id.tv);
        tv.setText("Select Company");
        lv=(ListView)findViewById(R.id.reg_list);
        search_reg=(EditText)findViewById(R.id.search);
        search_reg.setInputType(InputType.TYPE_CLASS_TEXT);
        new Task().execute();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String comname= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(Comp_Intern.this,Student_List.class);
                in.putExtra("com",comname);
                startActivity(in);
            }
        });
    }
    class Task extends AsyncTask<String, String, ArrayList> {
        String url = getApplicationContext().getString(R.string.httpUrl);
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            pd = new ProgressDialog(Comp_Intern.this);
            pd.setMessage("Please Wait");
            pd.setCancelable(false);
            pd.show();
            url += "/fetchComp.php";
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            pd.dismiss();
            arrayAdapter = new ArrayAdapter<String>(Comp_Intern.this, R.layout.layout_list_view, R.id.reg_number, result);
            for (int i = 0; i < result.size(); i++)
                Log.d("Satyam Kumar", String.valueOf(result.get(i)));
            lv.setAdapter(arrayAdapter);
            search_reg.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Comp_Intern.this.arrayAdapter.getFilter().filter(charSequence);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            ArrayList<String> response = Make_connection.getInputStreamConnection(url);
            return response;
        }
    }
}