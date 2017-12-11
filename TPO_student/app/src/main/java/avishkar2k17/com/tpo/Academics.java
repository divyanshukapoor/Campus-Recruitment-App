package avishkar2k17.com.tpo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class Academics extends Fragment {
    EditText e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17;
    Button b;
    View view;
    String s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.academics, container, false);
        if(variable.getInternetState(getContext()))
        new TaskF().execute("check");
        else{
            //Toast.makeText(getActivity(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

        initialize();
        Bundle bundle=getActivity().getIntent().getExtras();
        s1=bundle.getString("regno");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getContext())) {
                     s2 = e2.getText().toString();
                     s3 = e3.getText().toString();
                     s4 = e4.getText().toString();
                     s5 = e5.getText().toString();
                     s6 = e6.getText().toString();
                     s7 = e7.getText().toString();
                     s8 = e8.getText().toString();
                     s9 = e9.getText().toString();
                     s10 = e10.getText().toString();
                     s11 = e11.getText().toString();
                     s12 = e12.getText().toString();
                     s13 = e13.getText().toString();
                     s14 = e14.getText().toString();
                     s15 = e15.getText().toString();
                     s16 = e16.getText().toString();
                     s17 = e17.getText().toString();
                    if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("") || s7.equals("") || s8.equals("") || s9.equals("")) {
                        Toast.makeText(getActivity(), "fill in completely", Toast.LENGTH_LONG).show();
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage("Press yes to submit your Academic Details")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        setEnabled();
                                        String t = "edit_profile";
                                        BackgroundTask backgroundTask = new BackgroundTask(getActivity());
                                        backgroundTask.execute(t, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17);
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
                } else
                {
                    Toast.makeText(getActivity(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
                }
            }

        });
        return view;
    }

    public void setEnabled(){
        e2.setEnabled(false);
        e3.setEnabled(false);
        e4.setEnabled(false);
        e5.setEnabled(false);
        e6.setEnabled(false);
        e7.setEnabled(false);
        e8.setEnabled(false);
        e9.setEnabled(false);
        e10.setEnabled(false);
        e11.setEnabled(false);
        e12.setEnabled(false);
        e13.setEnabled(false);
        e14.setEnabled(false);
        e15.setEnabled(false);
        e16.setEnabled(false);
        e17.setEnabled(false);
        b.setEnabled(false);
    }
    public void initialize()
    {
        e2=(EditText)view.findViewById(R.id.boards_10);
        e3=(EditText)view.findViewById(R.id.school_10);
        e4=(EditText)view.findViewById(R.id.year_10);
        e5=(EditText)view.findViewById(R.id.percet_10);
        e6=(EditText)view.findViewById(R.id.boards_12);
        e7=(EditText)view.findViewById(R.id.school_12);
        e8=(EditText)view.findViewById(R.id.year_12);
        e9=(EditText)view.findViewById(R.id.percent_12);
        e10=(EditText)view.findViewById(R.id.sem1);
        e11=(EditText)view.findViewById(R.id.sem2);
        e12=(EditText)view.findViewById(R.id.sem3);
        e13=(EditText)view.findViewById(R.id.sem4);
        e14=(EditText)view.findViewById(R.id.sem5);
        e15=(EditText)view.findViewById(R.id.sem6);
        e16=(EditText)view.findViewById(R.id.sem7);
        e17=(EditText)view.findViewById(R.id.sem8);
        b=(Button)view.findViewById(R.id.submit_acde);
    }

    public class TaskF extends AsyncTask<String,Void,ArrayList<String> >
    {
        String json_url2;
        @Override
        protected void onPreExecute() {
            variable var=variable.getInstance();
            s1=var.reg;
            json_url2=getContext().getString(R.string.httpUrl)+"/helperForFindingTrue.php";
        }

        @Override
        protected void onPostExecute(ArrayList<String> aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.get(0).equals("1")){
                e2.setText(aBoolean.get(1));
                e3.setText(aBoolean.get(2));
                e4.setText(aBoolean.get(3));
                e5.setText(aBoolean.get(4));
                e6.setText(aBoolean.get(5));
                e7.setText(aBoolean.get(6));
                e8.setText(aBoolean.get(7));
                e9.setText(aBoolean.get(8));
                e10.setText(aBoolean.get(9));
                e11.setText(aBoolean.get(10));
                e12.setText(aBoolean.get(11));
                e13.setText(aBoolean.get(12));
                e14.setText(aBoolean.get(13));
                e15.setText(aBoolean.get(14));
                e16.setText(aBoolean.get(15));
                e17.setText(aBoolean.get(16));
                for(int i=0;i<=16;i++)
                    Log.d("aman",aBoolean.get(i));
               setEnabled();
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {
            ArrayList<String > arrayList;
            if(params[0].equals("check"))
            {
                try {
                    String data =URLEncoder.encode("value", "UTF-8") + "=" + URLEncoder.encode("Academic", "UTF-8") + "&" +
                            URLEncoder.encode("address", "UTF-8") + "=" + URLEncoder.encode(s1, "UTF-8");
                    arrayList=Helper.getStreamConnection(json_url2,data,"failed");
                    return arrayList;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
