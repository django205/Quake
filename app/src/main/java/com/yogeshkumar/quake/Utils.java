package com.yogeshkumar.quake;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Yogesh Kumar on 4/18/2017.
 */

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();

    public static ArrayList<Quackdata> fetchEarthquakeData(String stringUrl) {

        URL url=createUrl(stringUrl);
        if(url==null) return null;

        String jsonResponse = null;
        jsonResponse = makeHttpRequest(url);

        ArrayList<Quackdata> earthquake=extractdatafromjson(jsonResponse);
        return earthquake;
    }

    private static ArrayList<Quackdata> extractdatafromjson(String jsonResponse) {

        if(TextUtils.isEmpty(jsonResponse)){
            return null;
        }
        ArrayList<Quackdata> earthquake=new ArrayList<>();
        try {
            JSONObject rootobject=new JSONObject(jsonResponse);
            JSONArray featurearray=rootobject.getJSONArray("features");
            for(int i=0;i<featurearray.length();i++){
                JSONObject features=featurearray.getJSONObject(i);
                JSONObject properties=features.getJSONObject("properties");
                Double magnitude=properties.getDouble("mag");
                String place=properties.getString("place");
                Long time=properties.getLong("time");
                earthquake.add(new Quackdata(magnitude,place,time));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return earthquake;
    }

    private static URL createUrl(String stringurl){
        URL url=null;
        try {
            url=new URL(stringurl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static String makeHttpRequest(URL url){
        String jsonResponse="";
        HttpURLConnection urlconnection=null;
        InputStream inputStream=null;

        if(url==null) return jsonResponse;
        try {
            urlconnection=(HttpURLConnection)url.openConnection();
            urlconnection.setRequestMethod("GET");
            urlconnection.setReadTimeout(10000);
            urlconnection.setConnectTimeout(15000);
            urlconnection.connect();
            inputStream=urlconnection.getInputStream();
            jsonResponse=readfromStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(urlconnection!=null) urlconnection.disconnect();

            if(inputStream!=null) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



return jsonResponse;
    }

    private static String readfromStream(InputStream inputstream) throws IOException {
        StringBuilder output=new StringBuilder();
        if (inputstream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputstream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();

    }

}
