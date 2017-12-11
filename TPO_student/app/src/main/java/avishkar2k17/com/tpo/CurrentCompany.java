package avishkar2k17.com.tpo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CurrentCompany extends AppCompatActivity {
    String regno;
    String t="register";
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        if (android.os.Build.VERSION.SDK_INT > 15) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        lv=(ListView)findViewById(R.id.listView);
        Bundle b = getIntent().getExtras();
        regno = b.getString("regno");
        if(variable.getInternetState(getApplicationContext())) {
            Task backgroundTask = new Task();
            backgroundTask.execute();
        }
        else{
           // Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             String com= (String) lv.getItemAtPosition(position);
                Intent in=new Intent(CurrentCompany.this,Company_detail.class);
                in.putExtra("com",com);
                startActivity(in);
            }
        });
    }

    public class Task extends AsyncTask<Void,Void,ArrayList<String> >
    {
        String json_url=getApplicationContext().getString(R.string.httpUrl)+"/interview.php";


        @Override
        protected void onPreExecute() {
                super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            //Toast.makeText(Home.this,result.get(1),Toast.LENGTH_LONG).show();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CurrentCompany.this,android.R.layout.simple_dropdown_item_1line,result);
            for(int i=0;i<result.size();i++)
            Log.d("Satyam Kumar", result.get(i));
            lv.setAdapter(arrayAdapter);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
          ArrayList<String> arrayList;
            arrayList=Helper.getInputStreamConnection(json_url,"Failed");
            return arrayList;
        }
    }
}
