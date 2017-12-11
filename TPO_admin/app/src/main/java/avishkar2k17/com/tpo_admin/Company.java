package avishkar2k17.com.tpo_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Company extends AppCompatActivity {
    ImageButton addcom,comreg,updateIntern,fetchdatail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        addcom=(ImageButton)findViewById(R.id.add_company);
        comreg=(ImageButton)findViewById(R.id.close_reg);
        updateIntern=(ImageButton)findViewById(R.id.update_intern);
        fetchdatail=(ImageButton)findViewById(R.id.compwise_intern);
        addcom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Company.this,Add_company.class);
                startActivity(in);
            }
        });
        comreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Company.this,Close_reg.class);
                startActivity(in);
            }
        });
        updateIntern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Company.this,Update_Intern.class);
                startActivity(in);
            }
        });
        fetchdatail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Company.this,Comp_Intern.class);
                startActivity(in);
            }
        });
    }
}
