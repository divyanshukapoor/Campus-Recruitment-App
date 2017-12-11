package avishkar2k17.com.tpo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1,b2;
    boolean connected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.spp_button);
        b2=(Button)findViewById(R.id.sip_button);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);



    }
    @Override
    public void onClick(View view) {
        if(view ==b1)
        {
            //see variable class
            connected=variable.getInternetState(getApplicationContext());
            if(!connected)
                Toast.makeText(MainActivity.this,"Please Make sure you are connected to internet...",Toast.LENGTH_LONG).show();
            else {
                Intent spp = new Intent(MainActivity.this, login.class);
                startActivity(spp);
            }
        }
        else if(view ==b2)
        {
            //See variable class
            connected=variable.getInternetState(getApplicationContext());
            if(!connected)
                Toast.makeText(MainActivity.this,"Please Make sure you are connected to internet...",Toast.LENGTH_LONG).show();
            else
            {
            Intent sip = new Intent(MainActivity.this, login_intern.class);
            startActivity(sip);
            }
        }
    }
    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return "";
    }

}
