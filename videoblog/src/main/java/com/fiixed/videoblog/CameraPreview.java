package com.fiixed.videoblog;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by abell on 12/17/13.
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "CameraPreview";

    private SurfaceHolder mHolder;
    private Camera mCamera;
    private List<Camera.Size> sizes;
    private Context mContext;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mContext = context;
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);

    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //Tell the camera to use this surface as its preview area
        try{
            if(mCamera !=null) {
                mCamera.setPreviewDisplay(holder);
            }
        }catch (IOException e) {
            Log.e(TAG, "Error setting up preview display", e);
        }
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mCamera == null) {
            return;
        }
        //the surface has changed size, update the camera preview size
        Camera.Parameters parameters = mCamera.getParameters();
        Camera.Size s = getBestSupportedSize(parameters.getSupportedPictureSizes(), width, height);
        parameters.setPreviewSize(s.width, s.height);
        mCamera.setParameters(parameters);
//                mCamera.setDisplayOrientation(90);
        setCameraDisplayOrientation(1, mCamera);
        try{
            mCamera.startPreview();
        } catch (Exception e) {
            Log.e(TAG, "Could not start preview", e);
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //We can no longer display on this surface, so stop the preview
        if(mCamera != null) {
            mCamera.stopPreview();
        }
    }
    //sets the correct camera orientation while also fixing the front camera mirroring problem
    public void setCameraDisplayOrientation(int cameraId, android.hardware.Camera camera) {

        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();

        android.hardware.Camera.getCameraInfo(cameraId, info);



//        int rotation = mContext.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 90;

//        switch (rotation) {
//            case Surface.ROTATION_0: degrees = 0; break;
//            case Surface.ROTATION_90: degrees = 90; break;
//            case Surface.ROTATION_180: degrees = 180; break;
//            case Surface.ROTATION_270: degrees = 270; break;
//        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    private Camera.Size getBestSupportedSize(List<Camera.Size> sizes, int width, int height) {
        Camera.Size bestSize = sizes.get(0);
        int largestArea = bestSize.width * bestSize.height;
        for(Camera.Size s: sizes) {
            int area = s.width * s.height;
            if(area > largestArea) {
                bestSize = s;
                largestArea = area;
            }
        }
        return bestSize;
    }
}
