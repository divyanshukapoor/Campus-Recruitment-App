package avishkar2k17.com.tpo_admin;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Make_connection  {
    public static ArrayList<String> getStreamConnection(String rurl,String data)
    {
        ArrayList<String> arr=new ArrayList<>();
        try {
            URL url1 = new URL(rurl);
            HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
            httpURLConnection1.setRequestMethod("POST");
            httpURLConnection1.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //httpURLConnection1.setRequestProperty("Host", "192.168.0.101");
            httpURLConnection1.setDoOutput(true);
            httpURLConnection1.setDoInput(true);
            httpURLConnection1.connect();
            OutputStream OS1 = httpURLConnection1.getOutputStream();
            BufferedWriter bufferedWriter1 = new BufferedWriter(new OutputStreamWriter(OS1, "UTF-8"));
            bufferedWriter1.write(data);
            bufferedWriter1.flush();
            bufferedWriter1.close();
            OS1.close();
            InputStream IS1 = httpURLConnection1.getInputStream();
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(IS1, "iso-8859-1"));

            String response1 = "";
            String line1 = "";
            while ((line1 = bufferedReader1.readLine()) != null) {
                response1 += line1;
                arr.add(line1);
            }
            Log.d("dkkk",response1);
            bufferedReader1.close();
            IS1.close();
            httpURLConnection1.disconnect();
            return arr;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arr.add("Connectin Error!!");
        return arr;
    }
    public static String getOutputStreamConnection(String rurl,String data)
    {
        try {
            URL url = new URL(rurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.connect();
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            OS.close();
            httpURLConnection.disconnect();
            return "Login Success...";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Login Failes";
    }
    public static ArrayList<String> getInputStreamConnection(String rurl)
    {
        ArrayList<String> arr=new ArrayList<>();
        try {
            URL url = new URL(rurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                arr.add(line);
            }
            bufferedReader.close();
            IS.close();
            httpURLConnection.disconnect();
            return arr;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
