package avishkar2k17.com.tpo_admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ImageButton verify,credits,company,details;
            verify=(ImageButton)findViewById(R.id.verify);
            credits=(ImageButton)findViewById(R.id.credits);
            company=(ImageButton)findViewById(R.id.company);
            details=(ImageButton)findViewById(R.id.details);
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(Select.this,Verify_user.class);
                    startActivity(in);
                }
            });
            credits.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(Select.this,ReduceCredits.class);
                    startActivity(in);
                }
            });
            company.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(Select.this,Company.class);
                    startActivity(in);
                }
            });
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in=new Intent(Select.this,Branch_select.class);
                    startActivity(in);
                }
            });
        }
    }

