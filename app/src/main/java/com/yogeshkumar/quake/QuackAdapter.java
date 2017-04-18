package com.yogeshkumar.quake;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.DateFormat;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Yogesh Kumar on 4/16/2017.
 */

public class QuackAdapter extends ArrayAdapter<Quackdata> {

    public QuackAdapter(@NonNull Context context, @NonNull List<Quackdata> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

    Quackdata currentQuackdata=getItem(position);

        View listitemview=convertView;
        if(listitemview==null){

            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        TextView mag=(TextView)listitemview.findViewById(R.id.mag);
     //   mag.setText(currentQuackdata.getMagnitude());



        String locations=currentQuackdata.getPlace();
        String[]loc=locations.split(",");
        String loc1;
        loc1 = loc[0];
        String loc2=loc[1];

        TextView place=(TextView)listitemview.findViewById(R.id.place);
        TextView place2=(TextView)listitemview.findViewById(R.id.place2);
        place2.setText(loc2);
        place.setText(loc1);

        TextView time=(TextView)listitemview.findViewById(R.id.time);
        TextView date=(TextView)listitemview.findViewById(R.id.date);

        long timeinmillis=currentQuackdata.getTime();
        Date dateObject=new Date(timeinmillis);

        time.setText(formattime(dateObject));
        date.setText(formatdate(dateObject));

        GradientDrawable magnitudecircle=(GradientDrawable)mag.getBackground();
        int  magcolor=getMagnitudeColor(currentQuackdata.getMagnitude());
        magnitudecircle.setColor(magcolor);

        Double magnitude=currentQuackdata.getMagnitude();
        DecimalFormat decimalFormat=new DecimalFormat("0.0");

        String magnitudetodisplay=decimalFormat.format(magnitude);
        mag.setText(magnitudetodisplay);
       return listitemview;
    }

     String formatdate(Date dateObject){
       //  DateFormat.getDateInstance(,Locale.ENGLISH).format(dateObject);
         SimpleDateFormat dateFormat=new SimpleDateFormat("MMM DD ,yyyy");
         String datetodisplay=dateFormat.format(dateObject);
         return datetodisplay;
     }
     String formattime(Date dateObject){
         SimpleDateFormat timeformat=new SimpleDateFormat("h:mm a");
         String timetodisplay=timeformat.format(dateObject);
         return timetodisplay;
     }
public int  getMagnitudeColor(Double magnitude){

    int magnitudeColorResourceId;
    int magnitudeFloor = (int) Math.floor(magnitude);
    switch (magnitudeFloor) {
        case 0:
        case 1:
            magnitudeColorResourceId = R.color.magnitude1;
            break;
        case 2:
            magnitudeColorResourceId = R.color.magnitude2;
            break;
        case 3:
            magnitudeColorResourceId = R.color.magnitude3;
            break;
        case 4:
            magnitudeColorResourceId = R.color.magnitude4;
            break;
        case 5:
            magnitudeColorResourceId = R.color.magnitude5;
            break;
        case 6:
            magnitudeColorResourceId = R.color.magnitude6;
            break;
        case 7:
            magnitudeColorResourceId = R.color.magnitude7;
            break;
        case 8:
            magnitudeColorResourceId = R.color.magnitude8;
            break;
        case 9:
            magnitudeColorResourceId = R.color.magnitude9;
            break;
        default:
            magnitudeColorResourceId = R.color.magnitude10plus;
            break;
    }
    return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
}

}
