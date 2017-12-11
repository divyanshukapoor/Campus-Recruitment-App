package avishkar2k17.com.tpo_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by HP on 10/12/2017.
 */

public class Statistics_Updater extends AsyncTask<String, String, String> {
    ProgressDialog pd;
    Context ctx;
    Statistics_Updater(Context ctx)
    {
        this.ctx=ctx;
    }
    String rurl = ctx.getString(R.string.httpUrl);

    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(ctx);
        pd.setMessage("Please Wait");
        pd.setCancelable(false);
        pd.show();
        rurl += "/statistics_updater.php";
    }

    @Override
    protected void onPostExecute(String s) {
        pd.dismiss();
        Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... params) {
        String a = params[0];
        String data = null;
        try {
            data = URLEncoder.encode("reg", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ArrayList<String > res=Make_connection.getStreamConnection(rurl,data);
        Log.d("abcd",res.get(0));
        return res.get(0);
    }


}
