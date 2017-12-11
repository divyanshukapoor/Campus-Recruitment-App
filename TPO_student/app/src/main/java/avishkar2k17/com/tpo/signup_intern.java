package avishkar2k17.com.tpo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class signup_intern extends AppCompatActivity {

    Button b,b1;
    Uri fileUri;
    Boolean statusOfImage=false;
    Spinner spinner;
    String  s7;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Bitmap bitmap;
    String encoded_string="not",path;
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    public static final int val=1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_intern);


        spinner = (Spinner) findViewById(R.id.branch);

        b1=(Button)findViewById(R.id.upload);
        b=(Button)findViewById(R.id.ButtonForSubmit);
        e1=(EditText) findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.password);
        e3=(EditText)findViewById(R.id.confirm);
        e6=(EditText)findViewById(R.id.contact);
        e4=(EditText)findViewById(R.id.email);
        e5=(EditText)findViewById(R.id.reg_no);

        e8=(EditText)findViewById(R.id.cpi1);
        tv=(TextView)findViewById(R.id.filePathv);

        List<String> categories = new ArrayList<String>();
        categories.add("Information Technology");
        categories.add("Computer Science Engineering");
        categories.add("Mechanical Engineering");
        categories.add("Electrical Engineering");
        categories.add("Bio Technology");
        categories.add("Civil Engineering");
        categories.add("Electronics & Communication Engineering");
        categories.add("Production And Industrial Engineering");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                s7=item;
                // Showing selected spinner item
               // Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusOfImage=true;
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,val);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getApplicationContext())){
                    String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                String s4 = e4.getText().toString();
                String s5 = e5.getText().toString();
                String s6 = e6.getText().toString();
                String s8 = e8.getText().toString();
                if (!s2.equals(s3)) {
                    Toast.makeText(signup_intern.this, "passwords don't match", Toast.LENGTH_LONG).show();
                } else if (s6.length() != 10) {
                    Toast.makeText(signup_intern.this, "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                } else if (!s4.matches(emailPattern)) {
                    Toast.makeText(signup_intern.this, "Please enter valid emailId", Toast.LENGTH_LONG).show();
                } else {
                    if (s1.equals("") || s2.equals("") || s3.equals("") || s4.equals("") || s5.equals("") || s6.equals("") || s7.equals("")) {
                        Toast.makeText(signup_intern.this, "Please fill complete details", Toast.LENGTH_LONG).show();
                    } else {
                        String es="";
                        try {
                             es=Encryption.encrypt(s2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String t = "register";
                        // Toast.makeText(signup_intern.this,encoded_string, Toast.LENGTH_LONG).show();
                        BackgroundTask backgroundTask = new BackgroundTask(signup_intern.this);
                        backgroundTask.execute(t, s1, es, s4, s5, s6, encoded_string, s7, s8);
                        Intent l = new Intent(signup_intern.this, MainActivity.class);
                        l.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        l.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(l);
                    }
                }
            }
                else
                    Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();

            }
        });

    }
    private String getRealSizeFromUri(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Audio.Media.SIZE };
            int column_index=0;
            cursor = context.getContentResolver().query(uri, proj, null, null, null);
            try {
                column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
            catch (OutOfMemoryError e)
            {
                Toast.makeText(signup_intern.this,"Select image of size less than 200KB",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==val && resultCode==RESULT_OK && data !=null) {
            fileUri = data.getData();
            String str = getRealSizeFromUri(getApplicationContext(), fileUri);
            int size = Integer.parseInt(str);
            if (size > 200000) {
                Toast.makeText(getApplicationContext(), "Upload image size less than 200Kb", Toast.LENGTH_SHORT).show();
            } else {
                path = getRealPathFromURI(fileUri);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                new upload(bitmap).execute();
                tv.setText(path);
            }
        }
    }
    private void BitConvert() throws FileNotFoundException {
        Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),
                R.drawable.defaultimage);
        new upload(bitmap).execute();
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    public class upload extends AsyncTask<Void,Void,Void>
    {
        Bitmap image;
        public upload(Bitmap image){
            this.image=image;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream stream=new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,100,stream);

            byte[] array=stream.toByteArray();
            encoded_string= Base64.encodeToString(array,Base64.DEFAULT);
            return null;
        }
    }
}
