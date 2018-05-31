package com.somia.fyp.tensorflowv1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.somia.fyp.R;

public class CameraActivity extends Activity {

    private String TAG = "CameraActivity";
    private Bitmap resizeAbleBitmap;
    private ImageClassifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        //Camera Activity
        if (null == savedInstanceState) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }
    }
}

//TODO:Experiment on Single Image
//        try {
//            classifier = new ImageClassifier(this);
//            resizeAbleBitmap=getResizedBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ger), ImageClassifier.DIM_IMG_SIZE_X, ImageClassifier.DIM_IMG_SIZE_Y);
//            String lable = classifier.classifyFrame(resizeAbleBitmap);
//            Log.d(TAG,"table="+lable);
//
//        } catch (IOException e) {
//            Log.e(TAG, "Failed to initialize an image classifier.");
//        }