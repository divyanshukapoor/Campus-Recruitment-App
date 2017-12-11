package avishkar2k17.com.tpo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class Resume extends Fragment {
    ImageView iv;
    Button b1, b2, b3, b4, b5;
    TextView tv1, tv2;
    int PICK_FILE_REQUEST1 = 1;
    int PICK_FILE_REQUEST2 = 2;
    Bitmap bmp;
    String selectedFilePath;
    String regN;
    String SERVER_URL, SERVER_URL1, SERVER_URL2;
    ProgressDialog dialog;
    String reg;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.resume, container, false);
        iv = (ImageView) view.findViewById(R.id.res_photo);
        regN = Edit_Profile.temp;

        Toast.makeText(getContext(),"Upload Photo and Resume",Toast.LENGTH_LONG).show();
        SERVER_URL2 = view.getContext().getString(R.string.httpUrl) + "/uploadPdf.php";
        SERVER_URL1 = view.getContext().getString(R.string.httpUrl) + "/uploadPhoto.php";
        b1 = (Button) view.findViewById(R.id.upload_photo);
        b2 = (Button) view.findViewById(R.id.upload_resume);
        b3 = (Button) view.findViewById(R.id.download_resume);
        b4 = (Button) view.findViewById(R.id.upload_resumeServer);
        b5 = (Button) view.findViewById(R.id.upload_photoServer);
        tv2 = (TextView) view.findViewById(R.id.res_text);
        tv1 = (TextView) view.findViewById(R.id.img_text);
        Bundle bundle=getActivity().getIntent().getExtras();
        reg=bundle.getString("regno");


        Toast.makeText(getActivity(), regN, Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser1();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser2();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getContext())) {
                    new backgroundTask_name().execute();
                } else
                    Toast.makeText(getActivity(), "check your internet connectivity", Toast.LENGTH_SHORT).show();

            }

        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getContext())) {
                    selectedFilePath = tv2.getText().toString();
                    if (selectedFilePath != null) {
                        dialog = ProgressDialog.show(getActivity(), "", "Uploading File...", true);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                uploadFile(selectedFilePath, "resume");
                            }
                        }).start();
                    } else {
                        Toast.makeText(getActivity(), "Please choose a File First", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(getActivity(), "check your internet connectivity", Toast.LENGTH_SHORT).show();

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (variable.getInternetState(getContext())) {
                    selectedFilePath = tv1.getText().toString();
                    if (selectedFilePath != null) {
                        dialog = ProgressDialog.show(getActivity(), "", "Uploading File...", true);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //creating new thread to handle Http Operations
                                uploadFile(selectedFilePath, "photo");
                            }
                        }).start();
                    } else {
                        Toast.makeText(getActivity(), "Please choose a File First", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getActivity(), "check your internet connectivity", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    private void proceedAfterPermission() {
        //We've got the permission, now we can proceed further
        Toast.makeText(getContext(), "We got the Storage Permission", Toast.LENGTH_LONG).show();
    }
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Context applicationContext = getContext();

        Cursor cursor = applicationContext.getContentResolver().query(contentURI, null, null, null, null);
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
    private void showFileChooser1() {
        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,PICK_FILE_REQUEST1);

    }

    private void showFileChooser2() {
        if (Build.VERSION.SDK_INT >= 19) {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, PICK_FILE_REQUEST2);
        }
        else {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, PICK_FILE_REQUEST2);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST1) {
                if (data == null) {
                    return;
                }
                Uri fileUri=data.getData();
                String str=getRealSizeFromUri(getContext(),fileUri);
                int size=Integer.parseInt(str);
                if(size>200000){
                    Toast.makeText(getContext(),"Upload image size less than 200Kb",Toast.LENGTH_SHORT).show();
                }
                else {
                    selectedFilePath = getRealPathFromURI(fileUri);
                    try {
                        tv1.setText(selectedFilePath);
                        bmp = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), fileUri);
                        iv.setImageBitmap(bmp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            if (requestCode == PICK_FILE_REQUEST2) {
                if (data == null) {
                    return;
                }
                Uri selectedFileUri = data.getData();

                        selectedFilePath = selectedFileUri.getPath().trim();
                        if (selectedFilePath != null && !selectedFilePath.equals("")) {
                            tv2.setText(selectedFilePath);
                        } else {
                            Toast.makeText(getActivity(), "Cannot upload file", Toast.LENGTH_SHORT).show();
                        }
            }
        }
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
                Toast.makeText(getContext(),"Select image of size less than 200KB And Resume of size less than 2 MB",Toast.LENGTH_SHORT).show();
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

    //android upload file to server
    public int uploadFile(final String selectedFilePath, final String type) {

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);
        if (type.equals("photo"))
            SERVER_URL = SERVER_URL1;
        else
            SERVER_URL = SERVER_URL2;


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        /*if (!selectedFile.isFile()) {
            dialog.dismiss();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(type.equals("photo"))
                    tv1.setText("Source File Doesn't Exist");
                    else
                        tv2.setText("Source File Doesn't Exists");
                }
            });

            return 0;
        } else {*/
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                if(type.equals("photo")) {
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                            + reg + ".jpg\"" + lineEnd);
                }
                else{
                    dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                            + reg + ".pdf\"" + lineEnd);
                }
                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                 Log.i("askaban", "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(type.equals("photo"))
                            tv1.setText("File Upload completed.\n");
                            else
                                tv2.setText("File Upload completed.\n");
                        }
                    });

                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Url error", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Can not read/write file", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            dialog.dismiss();
            return serverResponseCode;
        }



    public class backgroundTask_name extends AsyncTask<Void,Void,Void >
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Uri uriUrl = Uri.parse(getContext().getString(R.string.httpUrl) + "/resume/"+reg+".pdf");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
            return null;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (!(grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(getActivity(), "Permission denied to access your location.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
