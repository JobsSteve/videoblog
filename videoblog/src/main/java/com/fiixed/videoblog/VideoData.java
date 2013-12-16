package com.fiixed.videoblog;

import java.util.Date;
import java.util.UUID;

/**
 * Created by abell on 12/15/13.
 */
public class VideoData {

    private UUID mId;
    private String mtags;
    private Date mDate;

    public VideoData() {
        //generate unique identifier
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTags() {
        return mtags;
    }

    public void setTags(String tags) {
        this.mtags = tags;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }
}
