package avishkar2k17.com.tpo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


public class Notify extends AppCompatActivity {

    AlertDialog LDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String str;
        Bundle bundle=getIntent().getExtras();
        str=bundle.getString("val");

        LDialog = new AlertDialog.Builder(this)
                .setTitle("OPEN Companies")
                .setMessage(str)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
        LDialog.show();
    }
    @Override
    protected void onDestroy() {
        LDialog.dismiss();
        super.onDestroy();
    }
}
