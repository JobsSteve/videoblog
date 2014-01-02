package com.fiixed.videoblog;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.UUID;

public class MainActivity extends Activity implements VideoListFragment.OnVideoSelectedListener, VideoListFragment.OnCameraSelectedListener,
        CameraFragment.OnVideoRecordedListener {



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
                    .add(R.id.container, videoListFragment)
                    //add this transaction to the back stack
//                    .addToBackStack(null)
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
                .addToBackStack(null)
                .replace(R.id.container, videoDetailFragment, "VideoDetailFragment")
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        FragmentManager fm= getFragmentManager();
        if(fm.getBackStackEntryCount()>0){
            fm.popBackStack();
        }
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCameraSelected() {
//        CameraFragment cameraFragment = new CameraFragment();
//        getFragmentManager().beginTransaction()
//                .addToBackStack(null)
//                .replace(R.id.container,cameraFragment)
//                .commit();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new CameraFragment(), "CameraFragment")
                .addToBackStack("A_B_TAG")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().findFragmentByTag("VideoDetailFragment") != null) {
            // I'm viewing VideoDetailFragment
            getFragmentManager().popBackStack("A_B_TAG",
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            super.onBackPressed();
        }
    }








}
