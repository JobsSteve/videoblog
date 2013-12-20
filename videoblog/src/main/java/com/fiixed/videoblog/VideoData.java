package com.fiixed.videoblog;

import android.net.Uri;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by abell on 12/15/13.
 */
public class VideoData implements Serializable {

    private UUID mId;
    private String mtags;
    private Date mDate;



    private Uri mUri;

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

    public Uri getUri() {
        return mUri;
    }

    public void setUri(Uri mUri) {
        this.mUri = mUri;
    }

    /**
     * Always treat de-serialization as a full-blown constructor, by validating
     * the final state of the de-serialized object.
     */
    private void readObject(ObjectInputStream aInputStream)
            throws ClassNotFoundException, IOException {
        // always perform the default de-serialization first
        aInputStream.defaultReadObject();
    }

    /**
     * This is the default implementation of writeObject. Customise if
     * necessary.
     */
    private void writeObject(ObjectOutputStream aOutputStream)
            throws IOException {
        // perform the default serialization for all non-transient, non-static
        // fields
        aOutputStream.defaultWriteObject();
    }
}
