package com.fiixed.videoblog;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends Activity implements VideoListFragment.OnVideoSelectedListener {

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
                    .commit();


        }
    }

//    onVideoTaken() {
//
//    }


    @Override
    public void onVideoSelected(int position) {
        VideoDetailFragment onePaneFragment = new VideoDetailFragment();

        Bundle args = new Bundle();

        args.putInt(VideoDetailFragment.POSITION, position);

        onePaneFragment.setArguments(args);

        getFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, onePaneFragment, "DETAIL_FRAGMENT")
                .commit();


        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }


}
