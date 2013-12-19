package com.fiixed.videoblog;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Display;
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
    private Activity mActivity;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera = camera;
        mActivity = (Activity)context;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // make any resize, rotate or reformatting changes here
        Camera.Parameters params = mCamera.getParameters();
        sizes = params.getSupportedPreviewSizes();
        Log.d("PREVIEW SIZES", String.valueOf(getMaxSupportedVideoSize().width)+":"+String.valueOf(getMaxSupportedVideoSize().height));
        params.setPreviewSize(getMaxSupportedVideoSize().width, getMaxSupportedVideoSize().height);
        mCamera.setParameters(params);

        setCameraDisplayOrientation(mActivity, 1, mCamera);


        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e) {
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public Camera.Size getMaxSupportedVideoSize() {
        int maximum = sizes.get(0).width;
        int position = 0;
        for (int i = 0; i < sizes.size() - 1; i++) {
            if (sizes.get(i).width > maximum) {
                maximum = sizes.get(i).width; // new maximum
                position = i - 1;
            }
        }
        if (position == 0) {
            int secondMax = sizes.get(1).width;
            position = 1;
            for (int j = 1; j < sizes.size() - 1; j++) {
                if (sizes.get(j).width > secondMax) {
                    secondMax = sizes.get(j).width; // new maximum
                    position = j;
                }

            }
        }

        return sizes.get(position);
        // end method max

    }

    public static void setCameraDisplayOrientation(Activity activity,
                                                   int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = activity.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }

        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }
}



