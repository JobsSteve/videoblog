package com.fiixed.videoblog;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by abell on 12/15/13.
 */
public class VideoDetailFragment extends Fragment {

    public static final String EXTRA_VIDEO_ID = "com.fiixed.videodiary.video_id";
    private VideoData mVideo;
    private EditText mTags;
    private TextView mDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_video, container, false);

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
