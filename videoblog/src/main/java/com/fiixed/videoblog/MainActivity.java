package com.fiixed.videoblog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.UUID;

public class MainActivity extends Activity implements VideoListFragment.OnVideoSelectedListener, VideoListFragment.OnCameraSelectedListener,
        CameraFragment.OnVideoRecordedListener {

    public final String TAG = "com.fiixed.videoblog.MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            VideoListFragment videoListFragment = new VideoListFragment();

            videoListFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .replace(R.id.container, videoListFragment, "VideoListFragment")
                    .commit();


        }
        //
        //  Example of how to store one custom object
        //
//        VideoData sample = new VideoData("Mal");

        //save
//        Storage.getInstance().saveVideoData(this, sample);
        //retrieve
//        VideoData newVideoData = Storage.getInstance().getVideoData(this);
        //display (log
//        Log.e("DATA", newVideoData.firstName + " " + newVideoData.lastName + " " + newVideoData.howAwesome + " ");


        //
        // Example of how to store an array of custom objects
        //

//        VideoData[] sampleDataArray = {new VideoData("Zoe"),
//                new VideoData("Hoban"),
//                new VideoData("Inara"),
//                new VideoData("Kaylee")
//        };


        //save
//        Storage.getInstance().saveVideoDataArray(this, sampleDataArray);
        //retrieve
//        VideoData[] newDataArray = Storage.getInstance().getVideoDataArray(this);
        //display (log)
//        for(int i = 0; i < newDataArray.length;i++){
//            Log.e("DATAARRAY",newDataArray[i].firstName + " "
//                    + newDataArray[i].lastName + " "
//                    + newDataArray[i].howAwesome + " ");
//        }
    }

//    onVideoTaken() {
//
//    }


    @Override
    public void displayVideoData(UUID uuid) {
        VideoDetailFragment videoDetailFragment = new VideoDetailFragment();
        Bundle args = new Bundle();
        args.putString(VideoDetailFragment.UUID, String.valueOf(uuid));
        videoDetailFragment.setArguments(args);
        getFragmentManager().beginTransaction()
                .replace(R.id.container, videoDetailFragment, "VideoDetailFragment")
                .addToBackStack(null)
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        FragmentManager fm= getFragmentManager();
//        if(fm.getBackStackEntryCount()>0){
//            fm.popBackStack();
//        }
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraSelected() {
//        CameraFragment cameraFragment = new CameraFragment();
//        getFragmentManager().beginTransaction()
//                .replace(R.id.container, cameraFragment)
//                .commit();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new CameraFragment(), "CameraFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {

        int depth = getFragmentManager().getBackStackEntryCount();
        Log.e(TAG, String.valueOf(depth));
        if (getFragmentManager().findFragmentByTag("VideoListFragment").isVisible()) {
            // exit app
            finish();
        } else {
            if (getFragmentManager().findFragmentByTag("VideoDetailFragment").isVisible()) {
                // I'm viewing VideoDetailFragment
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new VideoListFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                super.onBackPressed();
            }
        }
    }


}
