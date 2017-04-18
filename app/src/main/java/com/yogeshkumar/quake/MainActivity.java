package com.yogeshkumar.quake;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    ListView listView;
    private static final String QUAKEURL="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2017-04-15&endtime=2017-04-16&minmagnitude=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ArrayList<Quackdata> list=new ArrayList<>();
//        list.add(new Quackdata(3.4,"1km SE of Norsup, Vanuatu",1461893618940L));
//        list.add(new Quackdata(7.8,"zila ghaziabad,bhutan",1460851117280L));
//        list.add(new Quackdata(1.2,"1km from chhapra,bangladesh",1460738756630L));
//        list.add(new Quackdata(9.1,"2.5km from ferozepur,china",1461872004420L));
        listView=(ListView)findViewById(R.id.listview);
//        QuackAdapter arrayAdapter=new QuackAdapter(this,list);
//        listView.setAdapter(arrayAdapter);
            new QuakeAsync().execute(QUAKEURL);

    }

    private void updateUI(List<Quackdata> quackdata){
        QuackAdapter adapter=new QuackAdapter(this,quackdata);
        listView.setAdapter(adapter);

    }
    private class QuakeAsync extends AsyncTask<String,Void,List<Quackdata>>{
        @Override
        protected List<Quackdata> doInBackground(String... urls) {

            if(urls.length<1||urls[0]==null){
                return null;
            }
            ArrayList<Quackdata> quackdatas;
           quackdatas= Utils.fetchEarthquakeData(urls[0]);

           return quackdatas;
        }

        @Override
        protected void onPostExecute(List<Quackdata> quackdata) {
           if(quackdata==null) return;
            updateUI(quackdata);
        }
    }
}
