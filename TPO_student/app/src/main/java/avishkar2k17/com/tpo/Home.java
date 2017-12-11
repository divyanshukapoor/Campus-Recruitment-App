package avishkar2k17.com.tpo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView iv;
    String name;
    public static HttpURLConnection connection;
    TextView tv1,tv2,tv5,tv4;
    String temp="";
    String UserName="";
    Bitmap bmp=null;
    String passwd,var;
    TextView ctv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv1=(TextView)findViewById(R.id.name_logged);
        tv2=(TextView)findViewById(R.id.reg_logged);
        tv4=(TextView)findViewById(R.id.tpo);
        tv5=(TextView)findViewById(R.id.cpi2);
        ctv= (TextView) findViewById(R.id.profile_verified);
        iv=(ImageView)findViewById(R.id.profile_logged);
        variable v=variable.getInstance();
        temp=v.reg;
        passwd=v.psd;
        SharedPreferences sharedPrefs = getSharedPreferences("ADefault", MODE_PRIVATE);
        SharedPreferences.Editor ed=sharedPrefs.edit();
        ed.remove("registration");
        ed.putString("registration",temp);
        ed.remove("password");
        ed.putString("password",passwd);
        Log.d("hyeare",passwd);
        ed.commit();
        saveImage();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void saveImage() {

         var=getApplicationContext().getString(R.string.httpUrl)+"/image/"+temp+".JPG";
       // bmp=getBitmapFromURL(var);
        if(variable.getInternetState(getApplicationContext())) {
            bcgTask obj = new bcgTask();
            obj.execute(var);
        }
        else
            Toast.makeText(Home.this,"check your internet connectivity",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences sharedPrefs = getSharedPreferences("ADefault", MODE_PRIVATE);
                        SharedPreferences.Editor ed=sharedPrefs.edit();
                        ed.remove("registration");
                        ed.remove("password");
                        ed.commit();
                        Home.this.finish();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            Intent i=new Intent(Home.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            SharedPreferences sharedPrefs = getSharedPreferences("ADefault", MODE_PRIVATE);
            SharedPreferences.Editor ed=sharedPrefs.edit();
            ed.remove("registration");
            ed.remove("password");
            ed.commit();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager =getFragmentManager();
        if (id == R.id.nav_home) {
            // Handle the camera action
            //fragmentManager.beginTransaction().replace(R.id.content_frame,new Home_fragment()).commit();
        } else if (id == R.id.nav_profile) {
            Intent in=new Intent(Home.this,Edit_Profile.class);
            in.putExtra("regno",temp);
            in.putExtra("pass",passwd);
            startActivity(in);
            //fragmentManager.beginTransaction().replace(R.id.content_frame,new Profile_fragment()).commit();
        } else if (id == R.id.nav_password) {
            Intent in=new Intent(Home.this,Password_fragment.class);
            in.putExtra("regno",temp);
            in.putExtra("pass",passwd);
            startActivity(in);
           // fragmentManager.beginTransaction().replace(R.id.content_frame,new Password_fragment()).commit();
        } else if (id == R.id.nav_interview) {
            Intent in=new Intent(Home.this,CurrentCompany.class);
            in.putExtra("regno",temp);
            startActivity(in);
        } else if (id == R.id.nav_ivexperience) {
            Intent in=new Intent(Home.this,Add_Interview_Experience.class);
            in.putExtra("regno",temp);
            startActivity(in);

        } else if (id == R.id.nav_viewexperience) {
            Intent in=new Intent(Home.this,View_Experience.class);
            startActivity(in);
        }else if (id == R.id.nav_graph) {
            Intent in=new Intent(Home.this,BarGraph.class);
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class bcgTask extends AsyncTask<String,Void,Void>
    {
        String src;

        @Override
        protected Void doInBackground(String... params) {
            src=params[0];
            try {
                URL url = new URL(src);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    input.close();
                connection.disconnect();
                connection=null;
                    bmp= myBitmap;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void bitmap) {
            if (bmp != null) {
                Bitmap resized = Bitmap.createScaledBitmap(bmp, 368, 420, true);
                iv.setImageBitmap(resized);
                new backgroundTask_name().execute();
                tv1.setText(UserName);
                tv2.setText(temp);
            } else {
                var = getApplicationContext().getString(R.string.httpUrl) + "/image/defaultimage" + ".JPG";
                if(variable.getInternetState(getApplicationContext())) {
                    bcgTaskT obj1 = new bcgTaskT();
                    obj1.execute(var);
                }
                else
                    Toast.makeText(Home.this, "check your internet connectivity", Toast.LENGTH_SHORT).show();
                }

            super.onPostExecute(bitmap);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
    public class bcgTaskT extends AsyncTask<String,Void,Void>
    {
        String src;

        @Override
        protected Void doInBackground(String... params) {
            src=params[0];
            try {
                URL url = new URL(src);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                input.close();
                bmp= myBitmap;
                connection.disconnect();
                connection=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void bitmap) {
            if(bmp!=null) {
                Bitmap resized = Bitmap.createScaledBitmap(bmp, 368, 420, true);
                iv.setImageBitmap(resized);
            }
            else{
                iv.setImageResource(R.drawable.defaultimage);
                new backgroundTask_name().execute();
                tv1.setText(UserName);
                tv2.setText(temp);
            }
            super.onPostExecute(bitmap);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
    public class backgroundTask_name extends AsyncTask<Void,Void,ArrayList<String> >
    {
        String json_url;
        @Override
        protected void onPreExecute() {
            json_url=getApplicationContext().getString(R.string.httpUrl)+"/name.php";
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {

            tv1.setText(result.get(0));
            if(result.get(2).equals("1"))
                ctv.setText("True");
            else
            ctv.setText("False");

             tv2.setText(result.get(1));
             tv4.setText(result.get(3));
            tv5.setText(result.get(4));

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> arrayList=new ArrayList<>();
            try {
                String data = URLEncoder.encode("reg_no", "UTF-8") + "=" + URLEncoder.encode(temp, "UTF-8");
                arrayList=Helper.getStreamConnection(json_url,data,"Failed");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
         return arrayList;
        }
    }

    @Override
    protected void onPause() {
      if(connection!=null)
      {
          connection.disconnect();
          connection=null;
      }
        super.onPause();
    }
    private void saveImage1() {

        var = getApplicationContext().getString(R.string.httpUrl) + "/image/" + temp + ".JPG";
        if (variable.getInternetState(getApplicationContext())) {
            bcgTaskT obj = new bcgTaskT();
            obj.execute(var);
        } else
            Toast.makeText(Home.this, "check your internet connectivity", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        saveImage1();
        super.onResume();
    }

}
