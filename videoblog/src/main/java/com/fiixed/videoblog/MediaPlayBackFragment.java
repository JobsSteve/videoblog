package com.fiixed.videoblog;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by abell on 12/20/13.
 */
public class MediaPlayBackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        
        VideoView videoView = (VideoView) v.findViewById(R.id.videoView);
        //Use a media controller so that you can scroll the video contents
//and also to pause, start the video.
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(mVideo.getUri());
        videoView.start();
        return v;
    }


}
