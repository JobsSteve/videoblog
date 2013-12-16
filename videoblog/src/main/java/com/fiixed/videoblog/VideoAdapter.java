package com.fiixed.videoblog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by abell on 12/15/13.
 */
public class VideoAdapter extends ArrayAdapter<VideoData> {


    Context mContext;
    int mLayoutResourceId;
    VideoData[] mData;

    public VideoAdapter(Context context, int layoutResourceId, VideoData[] data) {
        super(context, layoutResourceId, data);
        this.mContext = context;
        this.mLayoutResourceId = layoutResourceId;
        this.mData = data;
    }

    @Override
    public VideoData getItem(int position) {
        return super.getItem(position);
    }

    public int getCount() {
        return mData.length;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder holder;

        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (row == null) {//if the row has not been created before
            row = layoutInflater.inflate(mLayoutResourceId, parent, false);


            holder = new VideoHolder();

            //get a reference to all the different view elements we wish to update
            holder.day = (TextView) row.findViewById(R.id.dayTextView);
            holder.date = (TextView) row.findViewById(R.id.dateTextView);
            holder.tempMin = (TextView) row.findViewById(R.id.tempMinTextView);
            holder.tempMinF = (TextView) row.findViewById(R.id.tempMinFTextView);
            holder.tempMax = (TextView) row.findViewById(R.id.tempMaxTextView);
            holder.tempMaxF = (TextView) row.findViewById(R.id.tempMaxFTextView);
            holder.imageView = (ImageView) row.findViewById(R.id.imageView);
            holder.imageView2 = (ImageView) row.findViewById(R.id.imageView2);
            row.setTag(holder);

        } else {
            holder = (VideoHolder) row.getTag();
        }

        //get the data from the data array
        VideoData videoData = mData[position];


        //setting the view to the data we need to display
        holder.day.setText(weatherData.simpleDay());
        holder.date.setText(weatherData.simpleDate());
        holder.tempMin.setText(String.valueOf(weatherData.fahrenheitToCelsius(weatherData.getmTempMin())) + "°C");
        holder.tempMinF.setText(String.valueOf(weatherData.getmTempMin()) + "°F");
        holder.tempMax.setText(String.valueOf(weatherData.fahrenheitToCelsius(weatherData.getmTempMax())) + "°C");
        holder.tempMaxF.setText(String.valueOf(weatherData.getmTempMax()) + "°F");

        int resId = mContext.getResources().getIdentifier(weatherData.getEmoji(weatherData.getIcon()), "drawable", mContext.getPackageName());
        holder.imageView.setImageResource(resId);

        int resId2 = mContext.getResources().getIdentifier(weatherData.getEmoji(weatherData.getIcon()), "drawable", mContext.getPackageName());
        holder.imageView2.setImageResource(resId2);

        //returning the row view(because this is called getView after all)
        return row;
    }

    public void setData(VideoData[] replacementArray) {
        mData = replacementArray;
        this.notifyDataSetChanged();

    }

    private class VideoHolder {
        public TextView day;
        public TextView date;
        public TextView tempMin;
        public TextView tempMinF;
        public TextView tempMax;
        public TextView tempMaxF;
        public ImageView imageView;
        public ImageView imageView2;
    }
}


