package com.fiixed.videoblog;

import android.content.Context;
import android.hardware.Camera;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by abell on 12/15/13.
 */
 public class Storage {

    private ArrayList<VideoData> mVideos;


    public ArrayList<VideoData> getListOfVideos() {
        return null;
    }

    public void addVideoToArrayList(VideoData videoData) {
        //add another video to the ArrayList
    }

    //
    // Singleton pattern here:
    //
    private static Storage storageRef;
    private Storage(){
        //ToDo here

    }
    public static Storage getInstance()
    {
        if (storageRef == null){
            storageRef = new Storage();
        }
        return storageRef;
    }

    //
    // Serialization here
    //

    static String DATA_FILE = "data_file_single";
    static String DATA_FILE_ARRAY = "data_file_single";

    //Save a single piece of a custom class

    public static boolean saveVideoData(Context context, VideoData videoData) {
        try {
            FileOutputStream fos = context.openFileOutput(DATA_FILE, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(videoData);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static VideoData getVideoData(Context context) {
        try {
            FileInputStream fis = context.openFileInput(DATA_FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = is.readObject();
            is.close();

            if(readObject != null && readObject instanceof VideoData) {
                return (VideoData) readObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static boolean saveVideoDataArray(Context context, VideoData[] videoDatas) {
        try {
            FileOutputStream fos = context.openFileOutput(DATA_FILE_ARRAY, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(videoDatas);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

//    public static ArrayList<VideoData> getVideoDataArray(Context context) {
//        try {
//            FileInputStream fis = context.openFileInput(DATA_FILE_ARRAY);
//            ObjectInputStream is = new ObjectInputStream(fis);
//            Object readObject = is.readObject();
//            is.close();
//
//            if(readObject != null && readObject instanceof VideoData) {
//                return (ArrayList<VideoData>) readObject;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
//