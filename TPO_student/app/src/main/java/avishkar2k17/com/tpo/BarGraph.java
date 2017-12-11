package avishkar2k17.com.tpo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class BarGraph extends AppCompatActivity {

    Vector<Data> vector =new Vector<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_graph);
        int i;
        if(variable.getInternetState(getApplicationContext()))
        new Task().execute();
        else
        {
           // Toast.makeText(getApplicationContext(),"check your internet connectivity",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public class Task extends AsyncTask<Void,Void,ArrayList<String> >
    {
        String json_url;

        @Override
        protected void onPreExecute() {
            json_url=getApplicationContext().getString(R.string.httpUrl)+"/statistics.php";
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {

            for(int i=0;i<result.size();){

                Data obj=new Data();
                obj.branch=result.get(i);
                i++;
                obj.total=Integer.parseInt(result.get(i));
                i++;
                obj.placed=Integer.parseInt(result.get(i));
                i++;
                vector.add(obj);
            }
            HorizontalBarChart barChart = (HorizontalBarChart) findViewById(R.id.barchart);
            ArrayList<BarEntry> barEntries=new ArrayList<>();
            int i;
            for(i=0;i<vector.size();i++)
            {
                barEntries.add(new BarEntry(vector.get(i).placed,i));
                Log.d("Aman shroff",vector.get(i).branch);

            }
            BarDataSet barDataSet=new BarDataSet(barEntries,"Placements stats");

            ArrayList<String> arrayList=new ArrayList<>();
            for(i=0;i<vector.size();i++)
            {
                arrayList.add(vector.get(i).branch);
            }
            BarData barData=new BarData(arrayList,barDataSet);
            barChart.setData(barData);
            barChart.setTouchEnabled(true);
            barChart.setScaleEnabled(true);
            barChart.setDragEnabled(true);
            barChart.animateY(3000, Easing.EasingOption.EaseInOutBounce);
            XAxis xAxis=barChart.getXAxis();
            xAxis.setLabelsToSkip(0);
            xAxis.setTextSize(8f);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            barChart.getAxisRight().setEnabled(false);
            barChart.setHorizontalScrollBarEnabled(true);

        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
           ArrayList<String> arrayList;
            arrayList=Helper.getInputStreamConnection(json_url,"Failed");
            return arrayList;
        }
    }
}
