package avishkar2k17.com.tpo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity implements View.OnClickListener {

    TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("2017-18");
        spinnerArray.add("2018-19");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner1);
        sItems.setAdapter(adapter);
        signup=(TextView)findViewById(R.id.request);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent sign=new Intent(login.this,signup.class);
        startActivity(sign);
    }
}
