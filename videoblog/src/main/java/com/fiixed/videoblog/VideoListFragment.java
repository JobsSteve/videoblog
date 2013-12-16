package com.fiixed.videoblog;

import android.app.Fragment;

/**
 * Created by abell on 12/15/13.
 */
public class VideoListFragment extends Fragment {

    OnVideoSelectedListener mCallback;

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnVideoSelectedListener {
        /**
         * Called by VideoListFragment when a list item is selected
         */
        public void onVideoSelected(String data);
    }


}
