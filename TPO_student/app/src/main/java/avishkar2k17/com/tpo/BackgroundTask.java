package avishkar2k17.com.tpo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.LocaleDisplayNames;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public  class BackgroundTask extends AsyncTask<String,String,String>{

    Context ctx;
String d,e,g;
    String decryptString="",encryptString="";
    String a,b,c,f,con;

    String i,t;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
    }
    String  bh;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bh=ctx.getString(R.string.httpUrl);

    }

    @Override
    protected String doInBackground(String... params) {

        String reg_url = bh+"/signup.php";
        String login_url = bh+"/login.php";
        String pass_url=bh+"/forgot.php";
        String edit_profile_url=bh+"/edit_profile.php";
        String edit_personal_url=bh+"/edit_personal.php";
        con = params[0];

        if (con.equals("register")) {

            a = params[1];
            b = params[2];
            c = params[3];
            d = params[4];
            e = params[5];
            f = params[6];
            g = params[7];
            t = params[8];
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8") + "&" +
                        URLEncoder.encode("reg_no", "UTF-8") + "=" + URLEncoder.encode(d, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(e, "UTF-8") + "&" +
                        URLEncoder.encode("branch", "UTF-8") + "=" + URLEncoder.encode(g, "UTF-8") + "&" +
                        URLEncoder.encode("cpi", "UTF-8") + "=" + URLEncoder.encode(t, "UTF-8")+ "&" +
                        URLEncoder.encode("encoded_image", "UTF-8") + "=" + URLEncoder.encode(f, "UTF-8");

                arrayList=Helper.getStreamConnection(reg_url,data,"Registration Unsuccessfull");

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
         return arrayList.get(0);
        } else if (con.equals("login")) {
            a = params[1];
            encryptString = params[2];
           if(encryptString==null)
               Log.d("isNull","df");
           ArrayList<String> arrayList=new ArrayList<>();

            try {

                String data = URLEncoder.encode("reg_no", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8");

                arrayList=Helper.getStreamConnection(login_url,data,"Login Unsuccessfull");
            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            try {
                decryptString=Encryption.decrypt(arrayList.get(1));
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Log.d("encyption1",decryptString);
            return arrayList.get(0);
        }else if (con.equals("edit_profile")) {
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("reg_no", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("b10", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("s10", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("y10", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                        URLEncoder.encode("p10", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                        URLEncoder.encode("b12", "UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8") + "&" +
                        URLEncoder.encode("s12", "UTF-8") + "=" + URLEncoder.encode(params[7], "UTF-8") + "&" +
                        URLEncoder.encode("y12", "UTF-8") + "=" + URLEncoder.encode(params[8], "UTF-8") + "&" +
                        URLEncoder.encode("p12", "UTF-8") + "=" + URLEncoder.encode(params[9], "UTF-8") + "&" +
                        URLEncoder.encode("s1", "UTF-8") + "=" + URLEncoder.encode(params[10], "UTF-8") + "&" +
                        URLEncoder.encode("s2", "UTF-8") + "=" + URLEncoder.encode(params[11], "UTF-8") + "&" +
                        URLEncoder.encode("s3", "UTF-8") + "=" + URLEncoder.encode(params[12], "UTF-8") + "&" +
                        URLEncoder.encode("s4", "UTF-8") + "=" + URLEncoder.encode(params[13], "UTF-8") + "&" +
                        URLEncoder.encode("s5", "UTF-8") + "=" + URLEncoder.encode(params[14], "UTF-8") + "&" +
                        URLEncoder.encode("s6", "UTF-8") + "=" + URLEncoder.encode(params[15], "UTF-8") + "&" +
                        URLEncoder.encode("s7", "UTF-8") + "=" + URLEncoder.encode(params[16], "UTF-8") + "&" +
                        URLEncoder.encode("s8", "UTF-8") + "=" + URLEncoder.encode(params[17], "UTF-8");
                arrayList=Helper.getStreamConnection(edit_profile_url,data,"Unsuccessful");

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            return arrayList.get(0);
        }else if (con.equals("edit_personal")) {
           ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                        URLEncoder.encode("father", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                        URLEncoder.encode("mother", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                        URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8");
                arrayList=Helper.getStreamConnection(edit_personal_url,data,"Unsuccessful");

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            return arrayList.get(0);
        }
        else
        {
            a = params[1];
            b = params[2];
            try {
                b=Encryption.encrypt(b);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data1 = URLEncoder.encode("reg_number", "UTF-8") + "=" + URLEncoder.encode(a, "UTF-8") + "&" +
                        URLEncoder.encode("npassword", "UTF-8") + "=" + URLEncoder.encode(b, "UTF-8");
                arrayList=Helper.getStreamConnection(pass_url,data1,"Unsuccessful");

            } catch (UnsupportedEncodingException e1) {
                e1.printStackTrace();
            }
            return arrayList.get(0);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
           if (s.equals("Registration Success..."))
               Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
           else if (s.equals("Login Success...")) {
               Log.d("how!",s);
               Log.d("how!",encryptString);

               if (encryptString.equals(decryptString)) {
                   Intent log = new Intent(ctx, Home.class);
                   log.putExtra("reg", a);
                   log.putExtra("pass", encryptString);
                   variable var = variable.getInstance();
                   var.psd = encryptString;
                   var.reg = a;
                   Log.d("checkitout",var.psd);
                   Log.d("checkitout",var.reg);
                   log.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                   log.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   new TaskNotify().execute();
                   ctx.startActivity(log);
               }
               else
                   Toast.makeText(ctx,"Password Don't match",Toast.LENGTH_SHORT).show();
           }
            else
           {
               Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
           }
    }

    public class TaskNotify extends AsyncTask<Void,Void,ArrayList<String>>
    {
        String json_url=ctx.getString(R.string.httpUrl)+"/notify.php";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            String str="";
            int k=1;
            for(int i=0;i<result.size();i+=2)
            {
                String a=String.valueOf(k);
                k++;
                str+=("("+a+"). "+result.get(i)+"\n"+"       DeadLine-"+result.get(i+1)+" "+"\n");
            }
            Intent myIntent = new Intent(ctx, Notify.class);
            myIntent.putExtra("val",str);
            PendingIntent pendingIntent = PendingIntent.getActivity(
                    ctx,
                    0,
                    myIntent,
                    Intent.FLAG_ACTIVITY_NEW_TASK);

            Notification myNotification = new NotificationCompat.Builder(ctx)
                    .setContentTitle("Currently OPEN Companies!")
                    .setContentText("CLICK TO SHOW DETAILS")
                    .setTicker("Notification!")
                    .setWhen(System.currentTimeMillis())
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.abc)
                    .build();
            myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
            NotificationManager notificationManager =
                    (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, myNotification);

            super.onPostExecute(result);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arrayList;
                arrayList=Helper.getInputStreamConnection(json_url,"Failed");
            return arrayList;
        }
    }
    public class BckTask extends AsyncTask<String, String, String > {
        String uurl=ctx.getString(R.string.httpUrl)+"/DecryptPass.php";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String  doInBackground(String... params) {
            String name=params[0];
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("com", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                arrayList=Helper.getStreamConnection(uurl,data,"Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return  null;
        }

    }
}

