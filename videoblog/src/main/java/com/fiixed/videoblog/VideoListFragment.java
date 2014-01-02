package com.fiixed.videoblog;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by abell on 12/15/13.
 */
public class VideoListFragment extends Fragment {

    public static final String TAG = "VideoListFragment";
    HashMap<UUID, VideoData> videoDataHashMap;
    ArrayList<VideoData> videoDataArrayList = new ArrayList<VideoData>();
    OnVideoSelectedListener mCallback;
    OnCameraSelectedListener cameraCallback;
    private ListView listView;
    VideoAdapter videoAdapter;


    public interface OnCameraSelectedListener {
        public void onCameraSelected();
    }

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnVideoSelectedListener {
        /**
         * Called by VideoListFragment when a list item is selected
         */
        public void displayVideoData(UUID uuid);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


        videoDataHashMap = Storage.getInstance().getMyData(getActivity());

        if (videoDataHashMap != null) {
            for (VideoData d : videoDataHashMap.values()) {
                videoDataArrayList.add(d);
            }
        } else {
            VideoData test = new VideoData();
            videoDataArrayList.add(0, test);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.list_fragment_actions, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(getActivity(), "Tapped search", Toast.LENGTH_SHORT).show();
//                openSearch();
                return true;
            case R.id.action_video:
                Toast.makeText(getActivity(), "Tapped camera", Toast.LENGTH_SHORT).show();
                cameraCallback.onCameraSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //make sure the activity we're attached to actually has implemented the right callback method
        if (activity instanceof OnVideoSelectedListener && activity instanceof OnCameraSelectedListener) {
            mCallback = (OnVideoSelectedListener) activity;
            cameraCallback = (OnCameraSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString() + "must implement onVideoSelectedListener!!");
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_listview, container, false);
//        ActionBar actionBar = getActivity().getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        videoAdapter = new VideoAdapter(getActivity(), R.layout.row, videoDataArrayList);
        listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(videoAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                VideoData d = videoDataArrayList.get(position);
                mCallback.displayVideoData(d.getId());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ((VideoAdapter)listView.getAdapter()).notifyDataSetChanged();
    }
}
