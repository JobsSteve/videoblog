package com.fiixed.videoblog;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by abell on 12/15/13.
 */
public class VideoDetailFragment extends Fragment  {
    private static final String TAG = "VideoDetailFragment";

    public static final String EXTRA_VIDEO_ID = "com.fiixed.videodiary.video_id";
    public static final String UUID = "uuid";
    private VideoData mVideo;
    private UUID uuid;
    private EditText mTags;
    private TextView mDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        ActionBar actionBar = getActivity().getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle args = getArguments();
        if(args !=null) {
            //Set the article to be based on what the article object says
            uuid = java.util.UUID.fromString(args.getString(UUID));
        }
        Log.i(TAG, uuid.toString());

    }

    @Override
    public void onStart() {
        super.onStart();


    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.detail_fragment_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.ic_action_share:
                Toast.makeText(getActivity(), "Tapped share", Toast.LENGTH_SHORT).show();
//                openSearch();
                return true;
            case R.id.ic_action_discard:
                Toast.makeText(getActivity(), "Tapped discard", Toast.LENGTH_SHORT).show();
                Storage.getInstance().remove(getActivity(), mVideo.getId());
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, new VideoListFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

        mVideo = Storage.getInstance().getVideoData(getActivity(),uuid);

        mTags = (EditText) v.findViewById(R.id.detailTagEditText);
        mTags.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTags.setText(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //and this one too
            }
        });

        mDate = (TextView) v.findViewById(R.id.detailDateTimeTextView);
        mDate.setText(mVideo.getDate().toString());




        return v;
    }

}
