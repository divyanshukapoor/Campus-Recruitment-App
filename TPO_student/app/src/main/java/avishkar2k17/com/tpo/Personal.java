package avishkar2k17.com.tpo;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class Personal extends Fragment {
    EditText e1,e2,e3,e4,e5,e6;
    Button b;
    String s1,s2,s3,s4,s5,s6;
    String reg;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.personal, container, false);
        Toast.makeText(getContext(),"Upload Photo and Resume",Toast.LENGTH_LONG).show();
        if(variable.getInternetState(getContext()))
        new TaskF().execute("check");
        else{
            //Toast.makeText(getActivity(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        e1=(EditText)view.findViewById(R.id.name_per);
        e2=(EditText)view.findViewById(R.id.father_per);
        e3=(EditText)view.findViewById(R.id.mother_per);
        e4=(EditText)view.findViewById(R.id.email_per);
        e5=(EditText)view.findViewById(R.id.phone_per);
        e6=(EditText)view.findViewById(R.id.address_per);
        b=(Button)view.findViewById(R.id.submit_per);
        Bundle bundle=getActivity().getIntent().getExtras();
        reg=bundle.getString("regno");

     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (variable.getInternetState(getContext())) {
                  s1 = e1.getText().toString();
                  s2 = e2.getText().toString();
                  s3 = e3.getText().toString();
                  s4 = e4.getText().toString();
                  s5 = e5.getText().toString();
                  s6 = e6.getText().toString();
                 if(s1.equals("") ||s2.equals("") ||s3.equals("") ||s4.equals("") ||s5.equals("")){
                     Toast.makeText(getContext(),"Fill all the details",Toast.LENGTH_SHORT).show();
                 }
                 else if(!s4.matches(emailPattern)){
                     Toast.makeText(getContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                 }
                 else if(e5.length()>10 || e5.length()<6){
                     Toast.makeText(getContext(),"Invalid Phone number",Toast.LENGTH_SHORT).show();
                 }
                 else{
                     AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                     builder.setMessage("Press yes to submit your Personal Details")
                             .setCancelable(false)
                             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {
                                     setEnabled();
                                     String t = "edit_personal";
                                     BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                                     backgroundTask.execute(t, s1, s2, s3, s4, s5, s6);
                                 }
                             })
                             .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                 public void onClick(DialogInterface dialog, int id) {
                                     dialog.cancel();
                                 }
                             });
                     AlertDialog alert = builder.create();
                     alert.show();
                 }
             }
             else
             {
                 Toast.makeText(getActivity(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
             }
         }
     });
        return view;
    }

    public void setEnabled(){
        e1.setEnabled(false);
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);
        b.setEnabled(false);
    }
    public class TaskF extends AsyncTask<String,Void,ArrayList<String> > {
        String json_url2;

        @Override
        protected void onPreExecute() {
            variable var = variable.getInstance();
            reg = var.reg;
            json_url2 = getContext().getString(R.string.httpUrl) + "/helperForFindingTrue.php";
        }

        @Override
        protected void onPostExecute(ArrayList<String> aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean.get(0).equals("1")) {
                e1.setText(aBoolean.get(1));
                e2.setText(aBoolean.get(2));
                e3.setText(aBoolean.get(3));
                e4.setText(aBoolean.get(4));
                e5.setText(aBoolean.get(5));
                e6.setText(aBoolean.get(6));
                for (int i = 0; i <= 6; i++)
                    Log.d("aman", aBoolean.get(i));
                setEnabled();
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            if (params[0].equals("check")) {
                ArrayList<String> arrayList=new ArrayList<>();
                try {
                    String data = URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode("Personal", "UTF-8") + "&" +
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(reg, "UTF-8");
                    arrayList=Helper.getStreamConnection(json_url2,data,"Failed");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
             return arrayList;
            }
            return  null;
        }
    }

}
