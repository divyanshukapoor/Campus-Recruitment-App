package avishkar2k17.com.tpo;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class variable {
    private variable(){

    }
    public String name;
    public String reg;
    public String cpi;
    public String branch;
    public String psd;
    public static variable obj;
    public static variable getInstance() {

        if (obj == null) {
            obj = new variable();
        }
        return obj;
    }
    public static Boolean getInternetState(Context ctx){

        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
           return true;
        }
        else
           return false;
    }
}
