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

//    @Override
//    public VideoData getItem(int position) {
//        return super.getItem(position);
//    }
//
//    public int getCount() {
//        return mData.length;
//    }

    public View getView(int position, View convertView, ViewGroup parent) {
        VideoHolder holder;

        View row = convertView;
        //inflate the layout for a single row
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (row == null) {//if the row has not been created before
            row = layoutInflater.inflate(mLayoutResourceId, parent, false);


            holder = new VideoHolder();

            //get a reference to all the different view elements we wish to update
            holder.dateTextView = (TextView) row.findViewById(R.id.listViewDateTimeTextView);
            holder.tagsTextView = (TextView) row.findViewById(R.id.listViewTagTextView);
            holder.vidImageView = (ImageView)row.findViewById(R.id.vidImageView);



            row.setTag(holder);

        } else {
            holder = (VideoHolder) row.getTag();
        }

        //get the data from the data array
        VideoData videoData = mData[position];


        //setting the view to the data we need to display
        holder.dateTextView.setText(videoData.getDate().toString());
        holder.tagsTextView.setText(videoData.getTags());



//        int resId = mContext.getResources().getIdentifier(weatherData.getEmoji(weatherData.getIcon()), "drawable", mContext.getPackageName());
//        holder.imageView.setImageResource(resId);



        //returning the row view(because this is called getView after all)
        return row;
    }

//    public void setData(VideoData[] replacementArray) {
//        mData = replacementArray;
//        this.notifyDataSetChanged();
//
//    }

    private class VideoHolder {
        public TextView dateTextView;
        public TextView tagsTextView;
        public ImageView vidImageView;
    }
}


